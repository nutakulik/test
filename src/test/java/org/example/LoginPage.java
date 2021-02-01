package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class LoginPage {
    public WebDriver driver;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[contains(@id, 'passp-field-login')]")
    private WebElement inputLogin;

    public void setLoginInput(String login ){
//        WebDriverWait wait = new WebDriverWait(driver, 60);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id, 'passp-field-login')]")));
        inputLogin.clear();
        this.inputLogin.sendKeys(login);
    }
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div/div/div[2]/div[3]/div/div/div/div[1]/form/div[3]/button")
    private WebElement SignButtonLogin;

    public void clickSignButtonLogin(){
        SignButtonLogin.click();
    }

    @FindBy(xpath = "//*[@id=\"passp-field-passwd\"]")
    private WebElement inputPassword;

    public void setInputPasswordInput(String password ){
//        WebDriverWait wait = new WebDriverWait(driver, 60);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id, 'passp-field-login')]")));
        this.inputPassword.sendKeys(password);
    }

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div/div/div[2]/div[3]/div/div/div/form/div[3]/button")
    private WebElement SignButtonPassword;
    public void clickSignButtonPassw(){
        SignButtonPassword.click();
    }
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div/div/div[2]/div[3]/div/div/div/form/div[4]/button")
    private WebElement SignButtonPasswordAdd;


    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div/div/div[2]/div[3]/div/div/div/form/div[2]/div[2]")
    WebElement errorTextPassword;


    public void closeTab(){
        ArrayList<String> newTab= new ArrayList <String>(driver.getWindowHandles());
        driver.close();
        driver.switchTo().window(newTab.get(0));
    }

    public void checkAdditionalInput() {
        WebElement additionalInput = driver.findElement(By.xpath("//*[@id=\"passp-field-captcha_answer\"]"));
        if (additionalInput != null ){
            if(additionalInput.isDisplayed()) {
                SignButtonPasswordAdd.click();
                WebDriverWait wait = new WebDriverWait(driver, 60);
                wait.until(ExpectedConditions.textToBePresentInElement(errorTextPassword, "Неверный пароль"));
            }
        }
        else {
            SignButtonPassword.click();
        }
    }

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div/div/div[2]/div[3]/div/div/div/div[1]/form/div[1]/div[2]")
    WebElement errorTextLogin;

}
