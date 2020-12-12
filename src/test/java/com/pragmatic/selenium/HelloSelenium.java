package com.pragmatic.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Set;

public class HelloSelenium {

    public static void main(String[] args) {

        //setup the browser driver
        WebDriverManager.chromedriver().setup();

        //Create an instance of the browser
        WebDriver driver = new ChromeDriver();

        //navigate to the login page
        driver.get("https://hrm.pragmatictestlabs.com/");

        //type the username

        //type the password

        //click the login button

        driver.close();
    }

}
