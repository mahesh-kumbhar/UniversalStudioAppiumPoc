package userActions;

import com.aventstack.extentreports.ExtentTest;

public class ReportResources
{ // This Class is created to access ExtentReport from test package
    static ExtentTest extentTest;

    public ReportResources(ExtentTest extentTest) {
        this.extentTest = extentTest;
    }
}
