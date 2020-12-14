package com.pragmatic.selenium.hrm;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestUserLogin {
    private WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    private WebDriver beforeMethod() {
        driver = new ChromeDriver();

        driver.get("https://hrm.pragmatictestlabs.com");
        return driver;
    }

    @AfterMethod
    private void afterMethod(){
        driver.close();
    }

    @Test
    public void testValidUserLogin(){

        //type the username
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");

        //type the password
        driver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321");

        //click the login button
        driver.findElement(By.id("btnLogin")).click();

        //Get the Welcome message
        String msgWelcome = driver.findElement(By.id("welcome")).getText();

        Assert.assertEquals(msgWelcome,"Welcome Admin");

    }

    @Test
    public void testValidUserLoginWithFormSubmit(){

        //type the username
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");

        //type the password
        driver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321" + Keys.ENTER);

        //click the login button
        driver.findElement(By.id("btnLogin")).click();

        //Get the Welcome message
        String msgWelcome = driver.findElement(By.id("welcome")).getText();

        Assert.assertEquals(msgWelcome,"Welcome Admin");

    }

    @Test
    public void testUserLoginWithBlankUsernameAndPassword(){

        //type the username
        driver.findElement(By.id("txtUsername")).sendKeys("");

        //type the password
        driver.findElement(By.id("txtPassword")).sendKeys("");
        driver.findElement(By.id("txtPassword")).submit();

        //verify the error message
        Assert.assertEquals(driver.findElement(By.id("spanMessage")).getText(),"Username cannot be empty");
    }

    @Test
    public void testUserLoginWithBlankUsername(){

        //type the username
        driver.findElement(By.id("txtUsername")).sendKeys("");

        //type the password
        driver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321");
        driver.findElement(By.id("txtPassword")).submit();

        //verify the error message
        Assert.assertEquals(driver.findElement(By.id("spanMessage")).getText(),"Username cannot be empty");
    }

    @Test
    public void testUserLoginWithBlankPassword(){

        //type the username
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtUsername")).submit();

        //type the password
        driver.findElement(By.id("txtPassword")).sendKeys("");

        //verify the error message
        Assert.assertEquals(driver.findElement(By.id("spanMessage")).getText(),"Password cannot be empty");
    }

}
