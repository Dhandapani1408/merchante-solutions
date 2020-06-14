package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import wdMethods.ProjectMethods;

public class LandingPage extends ProjectMethods{
    @FindBy(xpath = "//*[text()='Users']")
    private WebElement users;
    
    @FindBy(xpath = "//*[text()='Products']")
    private WebElement products;
    
    @FindBy(xpath = "//*[text()='Orders']")
    private WebElement orders;
    
    @FindBy(xpath = "//a[text()='Dashboard']")
    private WebElement dashboard;
    public LandingPage() {
        PageFactory.initElements(driver, this);
    }
    
    public UserPage clickOnUser() {
       click(users);
       return new UserPage();

    }

}
