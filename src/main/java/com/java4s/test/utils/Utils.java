package com.java4s.test.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Utils {

    private EventFiringWebDriver driver;

    public Utils (EventFiringWebDriver driver) {
        this.driver = driver;
    }


    public String dateWithoutChars(){
        Date date = new Date();
        return date.toString().replaceAll("\\W", "");
    }

    public void takeScreenshot() throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("./src/test/testArtifacts/" +
                dateWithoutChars() + ".png"));
    }

    public void showConsoleLog(){
        System.out.println("BROWSER CONSOLE LOG AFTER BUG: ");
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : logEntries) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
        }

    }



}
