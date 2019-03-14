package com.java4s.test.config;

import com.java4s.test.listener.EventHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class BrowserConfig {

    private static WebDriver getDriver() {

        String browser = Properties.getBrowser();

        switch (browser) {
           /* case "firefox":
              case "ie":*/


            default:
                System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
                //System.setProperty(
                  //      "webdriver.chrome.driver",
                    //    new File(BrowserConfig.class.getResource("/chromedriver.exe").getFile()).getPath());


                return new ChromeDriver();
        }
    }

    public static void quitDriver(WebDriver driver){

        driver.quit();
    }

    public static EventFiringWebDriver getConfiguredDriver() {
        WebDriver driver = getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
        EventFiringWebDriver wrappedDriver = new EventFiringWebDriver(driver);
        wrappedDriver.register(new EventHandler());

        return wrappedDriver;

    }

}
