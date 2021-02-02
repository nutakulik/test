package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.ArrayList;


import java.util.concurrent.TimeUnit;

public class Tests {

    public static WebDriver driver;
    public static StartPage startPage;
    public static LoginPage loginPage;
    public static ProfilePage profilePage;
    @BeforeMethod
    public static void setup(){
        if(driver==null) {
            System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            driver.get(ConfProperties.getProperty("page"));
        }
        startPage = new StartPage(driver);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
    }
    @AfterMethod
    public void end(){
        ArrayList<String> newTab= new ArrayList <String>(driver.getWindowHandles());
        driver.close();
        driver.switchTo().window(newTab.get(0));
    }

    @Test (priority=1)
    public  void logIn(){
        startPage.clickSignPost();
        loginPage.setLoginInput(ConfProperties.getProperty("validLogin"));
        loginPage.clickSignButtonLogin();
        loginPage.setInputPasswordInput(ConfProperties.getProperty("validPass"));
        loginPage.clickSignButtonPassw();
        profilePage.clickUser();
        String [] userName = profilePage.getUserName().split("@");
        Assert.assertEquals(userName[0], ConfProperties.getProperty("validLogin"));

        profilePage.clickUser();
        profilePage.clickLogOutBtn();
//        profilePage.closeTab();
    }
//
    @Test (priority=1)
    public  void logOut(){
        startPage.clickSignPost();
        loginPage.setLoginInput(ConfProperties.getProperty("validLogin"));
        loginPage.clickSignButtonLogin();
        loginPage.setInputPasswordInput(ConfProperties.getProperty("validPass"));
        loginPage.clickSignButtonPassw();
        profilePage.clickUser();
        String URLProfile = driver.getCurrentUrl();
        profilePage.clickLogOutBtn();
        String URLLogin = driver.getCurrentUrl();
        Assert.assertNotEquals(URLLogin,URLProfile);

        //loginPage.closeTab();
    }

    @Test (priority=1)
    public  void failPassword(){
        startPage.clickSignPost();
        loginPage.setLoginInput(ConfProperties.getProperty("validLogin"));
        loginPage.clickSignButtonLogin();
        loginPage.setInputPasswordInput(ConfProperties.getProperty("notvalidPass"));
        loginPage.clickSignButtonPassw();
        //loginPage.checkAdditionalInput();
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.textToBePresentInElement(loginPage.errorTextPassword, "Неверный пароль"));
        Assert.assertEquals(loginPage.errorTextPassword.getText(),"Неверный пароль");

        //loginPage.closeTab();
    }

    @Test (priority=1)
    public  void failLogin(){
        startPage.clickSignPost();
        loginPage.setLoginInput(ConfProperties.getProperty("notvalidLogin"));
        loginPage.clickSignButtonLogin();
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.textToBePresentInElement(loginPage.errorTextLogin, "Такого аккаунта нет"));
        Assert.assertEquals(loginPage.errorTextLogin.getText(),"Такого аккаунта нет");

        //loginPage.closeTab();
    }

    @Test (priority=1)
    public  void navigate(){
        startPage.clickVideo();
        String URL = driver.getCurrentUrl();
        Assert.assertTrue(URL.contains("video"));
        startPage.closeTab();

        startPage.clickImage();
        URL = driver.getCurrentUrl();
        Assert.assertTrue(URL.contains("image"));
        startPage.closeTab();

        startPage.clickMaps();
        URL = driver.getCurrentUrl();
        Assert.assertTrue(URL.contains("maps"));
        startPage.closeTab();

        startPage.clickNews();
        URL = driver.getCurrentUrl();
        Assert.assertTrue(URL.contains("news"));
        startPage.closeTab();

        startPage.clickTranslate();
        URL = driver.getCurrentUrl();
        Assert.assertTrue(URL.contains("translate"));
        startPage.closeTab();

        startPage.clickMusic();
        URL = driver.getCurrentUrl();
        Assert.assertTrue(URL.contains("music"));
        //startPage.closeTab();


    }

    @Test (priority=2)
    public void changeLanguage() throws InterruptedException {
        startPage.changeLanguage();
        startPage.pageChangeLang();
        Assert.assertEquals(startPage.languageOfPage(),"en");
    }
}
