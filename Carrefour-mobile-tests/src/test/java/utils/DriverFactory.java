package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverFactory {
    public static AppiumDriver driver;

    public static AppiumDriver setUp() throws MalformedURLException {
        UiAutomator2Options caps = new UiAutomator2Options();
        caps.setPlatformName("Android");
        caps.setDeviceName("emulator-5554");
        caps.setApp("C:\\Projetos\\Carrefour-mobile-tests\\android.wdio.native.app.v1.0.8.apk");
        caps.setAutomationName("UiAutomator2");
        caps.setUdid("emulator-5554");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }
}
