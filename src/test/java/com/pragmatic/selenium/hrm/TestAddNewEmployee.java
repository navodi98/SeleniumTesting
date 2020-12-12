package com.pragmatic.selenium.hrm;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestAddNewEmployee {
    public static final By TXT_USERNAME = By.id("txtUsername");
    public static final By TXT_PASSWORD = By.id("txtPassword");
    public static final By BTN_LOGIN = By.id("btnLogin");
    public static final By MNU_PIM = By.id("menu_pim_viewPimModule");
    public static final By MNU_ADD_EMPLOYEE = By.id("menu_pim_addEmployee");
    public static final By TXT_FIRSTNAME = By.id("firstName");
    public static final By TXT_LASTNAME = By.id("lastName");
    public static final By TXT_EMP_ID = By.id("employeeId");
    public static final By PROFILE = By.id("photofile");
    public static final By BTN_SAVE = By.id("btnSave");
    public static final By CHK_LOGIN = By.id("chkLogin");
    public static final By TXT_LOGIN_USERNAME = By.id("user_name");
    public static final By TXT_LOGIN_PASSWORD = By.id("user_password");
    public static final By TXT_LOGIN_PASSWORD_CONFIRM = By.id("re_password");
    private WebDriver driver;
    private Faker faker;

    @BeforeClass
    public void beforeClass(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    private WebDriver beforeMethod() {
        faker = new Faker();
        driver = new ChromeDriver();

        driver.get("https://hrm.pragmatictestlabs.com");
        login();
        navigateToAddEmployeePage();
        return driver;
    }

    @AfterMethod
    private void afterMethod(){
        driver.close();
    }

    @Test
    public void testAddNewEmployeeWithMandatoryInfo(){
        driver.findElement(TXT_FIRSTNAME).sendKeys(faker.name().firstName());
        driver.findElement(TXT_LASTNAME).sendKeys(faker.name().lastName());
        driver.findElement(BTN_SAVE).click();
    }

    @Test
    public void testAddNewEmployeeWithLoginDetails(){
        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();
        String username = String.format("%s.%s", firstname, lastname);

        driver.findElement(TXT_FIRSTNAME).sendKeys(firstname);
        driver.findElement(TXT_LASTNAME).sendKeys(lastname);
        driver.findElement(BTN_LOGIN).click();
        driver.findElement(TXT_LOGIN_USERNAME).sendKeys(username);
        driver.findElement(TXT_LOGIN_PASSWORD).sendKeys("Ptl@#321A");
        driver.findElement(TXT_LOGIN_PASSWORD_CONFIRM).sendKeys("Ptl@#321A");

        driver.findElement(BTN_SAVE).click();
    }

    @Test
    public void testAddNewEmployeeWithProfileImage(){
        driver.findElement(TXT_FIRSTNAME).sendKeys("Sanduni");
        driver.findElement(TXT_LASTNAME).sendKeys("Navodi");
        driver.findElement(PROFILE).sendKeys("E:\\selenium\\test_data\\tester.png");
        driver.findElement(BTN_SAVE).click();
    }

    private void login() {
        driver.findElement(TXT_USERNAME).sendKeys("Admin");
        driver.findElement(TXT_PASSWORD).sendKeys("Ptl@#321");
        driver.findElement(BTN_LOGIN).click();
    }

    private void navigateToAddEmployeePage() {
        //Click the PIM menu
        driver.findElement(MNU_PIM).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        //Click the add employee menu
        wait.until(ExpectedConditions.elementToBeClickable(MNU_ADD_EMPLOYEE)).click();

    }
}
