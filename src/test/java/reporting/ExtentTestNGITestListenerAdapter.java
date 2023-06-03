package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.screenrecording.CanRecordScreen;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import userActions.ReportResources;
import utilitys.TimeUtility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import static tests.BaseSettings.driver;


public class ExtentTestNGITestListenerAdapter extends TestListenerAdapter
{
    private static ExtentReports extent = ExtentManager.createInstance();
    TimeUtility utility = new TimeUtility();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private ExtentTest extentTest;

    @Override
    public void onStart(ITestContext context) {
        extent.setSystemInfo("os.name", System.getProperty("os.name"));
        extent.setSystemInfo("os.arch", System.getProperty("os.arch"));
        extent.setSystemInfo("os.version", System.getProperty("os.version"));
        extent.setSystemInfo("java.version", System.getProperty("java.version"));
        extent.setSystemInfo("project", context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        extentTest = extent.createTest(result.getTestClass().getName() + "::" + result.getMethod().getMethodName());
        test.set(extentTest);

        // Share extentTest report object to src.main.test package for Test Step logging purpose
        ReportResources reportResources = new ReportResources(extentTest);

        // Start video recording
     //   ((CanRecordScreen) driver).startRecordingScreen();



    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result)
    {

        // Take Screenshot -  /

        String ssFileName = "Screenshots"+File.separator+"Screenshot_"+utility.getDateTime()+".png";

        takeScreenshot(ssFileName);
        // Add captured SS to report
        File screenshot = driver.getScreenshotAs(OutputType.FILE);
        try {
            extentTest.addScreenCaptureFromPath(ssFileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // ************** Record Screen Video  ****************** //
        /*    try {
                saveVideoRecording(result);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
         */

        //**************** Close Report ith Failed State
        test.get().fail(result.getThrowable());
    }
    public void saveVideoRecording (ITestResult result) throws IOException {
        String RecordingFilePath = System.getProperty("user.dir")+File.separator +"VideoRecording";
                File videoDir = new File(RecordingFilePath);
        if(!videoDir.exists())
        {
            videoDir.mkdirs();
        }

        String videoFile = ((CanRecordScreen) driver).stopRecordingScreen();
        String videoFileName = RecordingFilePath+File.separator+result.getName()+"_Record_"+utility.getDateTime()+".mp4";
        FileOutputStream stream = null;
        stream = new FileOutputStream(videoFileName);

        byte[] screenRecordingBytes = Base64.getDecoder().decode(videoFile);
        stream.write(screenRecordingBytes);
        stream.close();

        //Attach to Report
        extentTest.addScreenCaptureFromPath(videoFileName);
    }
    public void takeScreenshot(String fileName) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result)
    {
        test.get().skip(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context)
    {
        extent.flush();
    }
}
