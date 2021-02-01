package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class StartPage {
    public WebDriver driver;
    public LoginPage loginPage;
    public StartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy (xpath = "/html/body/div[1]/div[1]/div/div[1]/div/a[1]")
    private WebElement signPost;

    public void clickSignPost(){
        signPost.click();
        ArrayList<String> newTab= new ArrayList <String>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));

    }

    @FindBy (xpath = "/html/body/div[1]/div[2]/div[3]/div/div[2]/div/div/div[2]/a[1]")
    private WebElement videoButton;
    public void clickVideo(){
        videoButton.click();
        ArrayList<String> newTab= new ArrayList <String>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));

    }

    @FindBy (xpath = "/html/body/div[1]/div[2]/div[3]/div/div[2]/div/div/div[2]/a[2]")
    private WebElement imageButton;
    public void clickImage(){
        imageButton.click();
        ArrayList<String> newTab= new ArrayList <String>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));

    }


    public void closeTab(){
        ArrayList<String> newTab= new ArrayList <String>(driver.getWindowHandles());
        driver.close();
        driver.switchTo().window(newTab.get(0));
    }

    @FindBy (xpath = "/html/body/div[1]/div[2]/div[3]/div/div[2]/div/div/div[2]/a[3]")
    private WebElement newsButton;
    public void clickNews(){
        newsButton.click();
        ArrayList<String> newTab= new ArrayList <String>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));

    }



    @FindBy (xpath = "/html/body/div[1]/div[2]/div[3]/div/div[2]/div/div/div[2]/a[4]")
    private WebElement mapsButton;
    public void clickMaps(){
        mapsButton.click();
        ArrayList<String> newTab= new ArrayList <String>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));

    }

    @FindBy (xpath = "/html/body/div[1]/div[2]/div[3]/div/div[2]/div/div/div[2]/a[6]")
    private WebElement translateButton;
    public void clickTranslate(){
        translateButton.click();
        ArrayList<String> newTab= new ArrayList <String>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));

    }

    @FindBy (xpath = "/html/body/div[1]/div[2]/div[3]/div/div[2]/div/div/div[2]/a[8]")
    private WebElement musicButton;
    public void clickMusic(){
        musicButton.click();
        ArrayList<String> newTab= new ArrayList <String>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));

    }

    @FindBy (xpath = "/html/body/div[1]/div[2]/div[2]/div/div/div/div[3]/div/div/div[3]/div/a")
    private WebElement changeLanguageButton;

    @FindBy (xpath = "/html/body/div[5]/ul/div[2]/li/a[1]")
    private  WebElement moreLanguageButton;

    @FindBy (xpath = "//*[@id=\"form__a11y\"]/div[2]/div/div[1]/div[1]")
    private  WebElement chooseLanguageList;

    @FindBy (xpath = "/html/body/div[2]/form/div[2]/div/div[1]/div[1]/select/option[6]")
    private WebElement chooseEng;

    @FindBy (xpath = "//*[@id=\"form__a11y\"]/div[3]/div/button")
    private WebElement saveLanguage;

    @FindBy (xpath = "/html/body/div[5]")
    WebElement fgjn;

    public void pageChangeLang() {
        changeLanguageButton.click();
        moreLanguageButton.click();
    }
    public void changeLanguage() throws InterruptedException {
        pageChangeLang();
//        changeLanguageButton.click();
//        moreLanguageButton.click();

        driver.findElement(By.xpath("//*[@id=\"form__a11y\"]/div[2]/div/div[1]/div[1]")).click();

        new WebDriverWait(driver, 30).until(ExpectedConditions.attributeContains(By.xpath("//*[@id=\"form__a11y\"]/div[2]/div/div[1]/div[1]/button"),"aria-haspopup", "true"));
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[@class= \"popup popup_animate_no popup_direction_down popup_theme_ffffff popup_autoclosable_yes popup_adaptive_yes select__popup select__popup_size_m select__popup_theme_normal i-bem popup_js_inited popup_visibility_visible popup_to_bottom\"]")));
            driver.findElement(By.xpath("/html/body/div[@class= \"popup popup_animate_no popup_direction_down popup_theme_ffffff popup_autoclosable_yes popup_adaptive_yes select__popup select__popup_size_m select__popup_theme_normal i-bem popup_js_inited popup_visibility_visible popup_to_bottom\"]/div/div/div[6]")).click();

        saveLanguage.click();
    }

    public String languageOfPage(){
        return driver.findElement(By.xpath("/html")).getAttribute("lang");
    }
}
