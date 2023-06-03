package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentManager
{
    private static ExtentReports extent;
    private ExtentManager(){}
    private static ExtentReports getInstance()
    {
        if(extent ==null)
            createInstance();
        return extent;

    }

    public static ExtentReports createInstance() {
        String fileName = "ExtentReport.html";//getReportFileLocation();
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(fileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(fileName);

//        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
//        htmlReporter.config().setChartVisibilityOnOpen(true);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        return extent;
    }
}
