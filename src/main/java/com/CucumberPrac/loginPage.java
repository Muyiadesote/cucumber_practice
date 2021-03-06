package com.CucumberPrac;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


/**
 * Created by musti on 22/06/2017.
 */
public class loginPage {
    WebDriver driver;
    public loginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    // Web Elements for Login Test + Assert loginPage
    @FindBy(id = "identifierId")
    private WebElement Email;

    @FindBy(xpath = ".//*[@id='identifierNext']/content/span")
    private WebElement SubmitEmail;

    @FindBy(xpath = ".//*[@id='password']/div[1]/div/div[1]/input")
    private WebElement EnterPWord;

    @FindBy(xpath = ".//*[@id='passwordNext']/content/span")
    private WebElement submitPWord;

    @FindBy(xpath = ".//*[@id='gb']/div[2]/div[1]")
    private WebElement LoginVerify;

    // Web elements for LogoutTest + Assert Log Out Test

    @FindBy(xpath = ".//*[@id='gb']/div[2]/div[3]/div/div[3]/div/a/span")
    private WebElement LogOutBtn;

    @FindBy(xpath = ".//*[@id='gb_71']")
    private WebElement SignOutBtn;

    @FindBy(xpath = ".//*[@id='view_container']/form/div[2]/div/div/div/ul[1]/li[1]/div/div[2]/p[3]")
    private WebElement VerifySignOut;

    @FindBy(xpath = "//div[contains(@class='LXRPh')]")
    private WebElement errorMessage;

    @FindBy(xpath = "//*[@id='headingText']/content")
    private WebElement copyText;


    public void doLogin(String myEmail, String myPassword) throws InterruptedException {
        Email.sendKeys(myEmail);
        SubmitEmail.click();
        Thread.sleep(2000);
        EnterPWord.sendKeys(myPassword);
        submitPWord.click();
    }

    public void doLoginDate(){

        Email.sendKeys(CurrentDate.getCurentDate());
    }

    public void saveTextToFile(){

        // This code will call the FileWriter class and save the retrieved text from 'copyText.getText' method & save the text to file specified.
        String textToSave =  copyText.getText();
        FileWriter myFileWriter = new FileWriter("CaseWord");
        myFileWriter.writeToFile(textToSave);
        myFileWriter.endFile();

        // This code will retrieve text from the file and pass into the email text field on the web app.

        Path path = Paths.get("src/test/resources/CaseWord");
        Scanner scanner;
        try {
            scanner = new Scanner(path);
        } catch (IOException e) {
            throw new RuntimeException("Could not find text file");
        }
        String retrieveText = null;
        int iterationCounter = 0;
        while (scanner.hasNext()) {
            iterationCounter++;
            retrieveText = scanner.next();
        }
        scanner.close();
        if (iterationCounter > 1) {
            throw new RuntimeException("More than one line was found in CaseWord file");
        } else if (retrieveText == null) {
            throw new RuntimeException("Could not retrieve a Text");
            //Email.sendKeys(retrieveText);
        }
    }

    public boolean verifyLoginPage(){
        return LoginVerify.isDisplayed();

    }

    public void doLogOut(){

        LogOutBtn.click();
        SignOutBtn.click();
    }

    public boolean VerifyLogOut(){

        return VerifySignOut.isDisplayed();
    }

    public boolean VerifyerrorMessage(String text){
        return errorMessage.isDisplayed();
    }






}
