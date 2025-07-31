package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    private static boolean testFailed = false;

    public static void setTestFailed(boolean failed) {
        testFailed = failed;
    }

    public static void captureScreenshot(WebDriver driver, String scenarioName) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

            Path screenshotsDir = Paths.get("screenshots");
            Files.createDirectories(screenshotsDir);

            Path screenshotPath = screenshotsDir.resolve(scenarioName + "_" + timestamp + ".png");
            FileUtils.copyFile(screenshot, screenshotPath.toFile());

            System.out.println("📸 Screenshot salvo em: " + screenshotPath);

            if (testFailed) {
                Path failureDir = Paths.get("target", "screenshots");
                Files.createDirectories(failureDir);

                Path failurePath = failureDir.resolve("test-failure_" + timestamp + ".png");
                FileUtils.copyFile(screenshot, failurePath.toFile());

                System.out.println("⚠️ Screenshot de falha salvo em: " + failurePath);
            }

        } catch (IOException e) {
            System.err.println("❌ Falha ao salvar screenshot: " + e.getMessage());
        }
    }
}
