package utilities;

import org.junit.Assert;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class actionHelper {
    public actionHelper() {

    }

    public static String talkScreenshot(WebDriver driver) {
        try {
            String fileSeparator = System.getProperty("file.separator");
            String extentReportsPath = System.getProperty("user.dir") + fileSeparator + "src"+ fileSeparator + "test" + fileSeparator + "java" + fileSeparator + "reporting";
            String screenshotPath = extentReportsPath + fileSeparator +"screenshot";

            File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotName = "screenshot" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy_HH.mm.ss")) + ".png";
            String screenshot = screenshotPath + fileSeparator + screenshotName;

            FileUtils.copyFile(file, new File(screenshot));
            return "." + fileSeparator + "screenshot" + fileSeparator + screenshotName;
        } catch (Exception ex) {
            Assert.fail();
            throw new RuntimeException(ex);
        }
    }
}
