package com.java4s.test.pages;

import com.java4s.test.config.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class RegisterPage {

    private EventFiringWebDriver driver;
    public RegisterPage(EventFiringWebDriver driver) {
        this.driver = driver;
    }

    private By name = By.cssSelector("#Name");
    private By id = By.cssSelector("#id");
    private By contactNumber = By.cssSelector("#contactNumber");
    private By registerButton = By.cssSelector("#employee > button");
    private By title = By.cssSelector("h2[class='card-title']");


    public void openHomePage(){
        driver.get(Properties.getBaseUrl());
    }

    public void findNameTextField(String userName){
        driver.findElement(name).sendKeys(userName);
    }

    public void findIdTextField(String userId){
        driver.findElement(id).sendKeys(userId);
    }

    public void findContactNumberTextField(String userContactNumber){
        driver.findElement(contactNumber).sendKeys(userContactNumber);
    }

    public void findRegisterButton(){
        driver.findElement(registerButton).click();
    }

    public boolean findConfirmMessage(){
        ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");

        String confirmMSG = driver.findElement(title).getText();

        if (confirmMSG.equals("User successfully registered")){
            return true;
        }
        return false;
    }


}
