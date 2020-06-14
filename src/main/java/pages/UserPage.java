package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import wdMethods.ProjectMethods;

public class UserPage extends ProjectMethods {

    @FindBy(xpath = "//*[text()='New User']")
    private WebElement newUser;

    @FindBy(xpath = "//input[@id='user_username']")
    private WebElement user_username;

    @FindBy(xpath = "//input[@id='user_password']")
    private WebElement user_password;

    @FindBy(xpath = "//input[@id='user_email']")
    private WebElement user_email;

    @FindBy(xpath = "//input[@value='Create User']")
    private WebElement create_User;

    @FindBy(xpath = "//a[text()='Cancel']")
    private WebElement cancel;

    @FindBy(xpath = "//*[@class='flash flash_notice']")
    private WebElement flash_notice;

    @FindBy(xpath = "(//*[text()='Username']//following::td)[1]")
    private WebElement createdUserName;

    @FindBy(xpath = "(//*[text()='Email']//following::td)[1]")
    private WebElement createdEmailId;

    @FindBy(xpath = "(//label[text()='Username']//following::select)[1]")
    private WebElement filterUser;

    @FindBy(xpath = "//label[text()='Email']//following::select")
    private WebElement filterEmail;

    @FindBy(xpath = "//*[@type='submit']")
    private WebElement filterSubmit;

    @FindBy(xpath = "(//td[@class='col col-username'])[1]")
    private WebElement first_col_username;

    @FindBy(xpath = "(//td[@class='col col-email'])[1]")
    private WebElement first_col_email;

    @FindBy(xpath = "//input[@id='q_username']")
    private WebElement first_enterUserName;

    @FindBy(xpath = "//input[@id='q_email']")
    private WebElement first_enterEmail;

    public UserPage() {
        PageFactory.initElements(driver, this);
    }

    public UserPage clickOnNewUser() {
        click(newUser);
        return this;
    }

    public UserPage enterUserName(String userName) {
        type(user_username, userName);
        return this;
    }

    public UserPage enterPassword() {
        type(user_password, "password123");
        return this;
    }

    public UserPage enterEmailId(String emaiId) {
        type(user_email, emaiId);
        return this;
    }

    public UserPage clickOnCreateUser() {
        click(create_User);
        return this;
    }

    public UserPage clickOnCancel() {
        click(cancel);
        return this;
    }

    public UserPage verifySuccessMessage() {
        verifyExactText(flash_notice, "User was successfully created.");
        return this;
    }

    public UserPage verifyEmailId(String email) {
        verifyExactText(createdEmailId, email);
        return this;
    }

    public UserPage verifyUserName(String userName) {
        verifyExactText(createdUserName, userName);
        return this;
    }

    public UserPage selectUserNameDropDrown(String userName) {
        selectDropDownUsingText(filterUser, userName);
        return this;
    }

    public UserPage selectEmailDropDrown(String emailId) {
        selectDropDownUsingText(filterEmail, emailId);
        return this;
    }

    public UserPage clickOnFilterButton() {
        click(filterSubmit);
        return this;
    }

    public UserPage verifyFirstcolumnEmail(String email) {
        verifyExactText(first_col_email, email);
        return this;
    }

    public UserPage verifyFirstcolumnUserName(String userName) {
        verifyExactText(first_col_username, userName);
        return this;
    }

    public UserPage enterUserName_filter(String userName) {
        type(first_enterUserName, userName);
        return this;
    }

    public UserPage enterEmailId_filter(String emailId) {
        type(first_enterEmail, emailId);
        return this;
    }

}
