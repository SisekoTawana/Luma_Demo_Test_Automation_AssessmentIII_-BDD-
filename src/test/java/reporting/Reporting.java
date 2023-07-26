package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reporting {
    public static ExtentReports extent;
    static String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance("src/test/java/reporting/extentReport_" + timestamp +".html");

        }
        return extent;
    }

    public static ExtentReports createInstance(String fileName) {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);
        htmlReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        return extent;
    }

    public static void FlushReport() throws IOException {
        extent.flush();
//        Desktop.getDesktop().browse(new File("src/test/java/reporting/extentReport_\" + timestamp +\".html").toURI());
    }
}
