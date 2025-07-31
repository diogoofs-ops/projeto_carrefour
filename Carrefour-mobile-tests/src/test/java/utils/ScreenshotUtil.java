package utils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    private static boolean testFailed;

    public static void captureScreenshot(WebDriver driver, String scenarioName) throws IOException {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotName = "screenshots/" + scenarioName + "_" + timestamp + ".png";
        if (testFailed) {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("target/screenshots/test-failure.png"));
        }
        try {
            Files.createDirectories(Paths.get("screenshots"));
            Files.copy(srcFile.toPath(), Paths.get(screenshotName));
            System.out.println("📸 Screenshot salvo em: " + screenshotName);
        } catch (IOException e) {
            System.err.println("❌ Falha ao salvar screenshot: " + e.getMessage());
        }
    }
}
