package testCases.ui;

import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LandingPage;
import utils.ExcelDataProvider;
import wdMethods.ProjectMethods;

public class CreateAndFilterTest extends ProjectMethods {
    static String emaiId = null;
    static String username = null;

    @BeforeMethod
    public void beforeMethod() {

    }

    @AfterMethod
    public void afterMethod() {
        endTestcase();
        closeAllBrowsers();
    }

    @Test(dataProvider = "fetchData")
    public void merchante(String testCaseName) throws InterruptedException {
        test = startTestCase(testCaseName, testCaseName);

        startApp(browserName, "http://qainterview.merchante-solutions.com/admin");

        if (testCaseName.equalsIgnoreCase("Create New User")) {
            new LandingPage().clickOnUser().clickOnNewUser().enterUserName(username).enterPassword()
                    .enterEmailId(emaiId).clickOnCreateUser().verifySuccessMessage().verifyEmailId(emaiId)
                    .verifyUserName(username);
        } else {
            new LandingPage().clickOnUser().selectUserNameDropDrown("Equals").enterUserName_filter(username)
                    .selectEmailDropDrown("Equals").enterEmailId_filter(emaiId).clickOnFilterButton()
                    .verifyFirstcolumnEmail(emaiId).verifyFirstcolumnUserName(username);
        }

    }

    @DataProvider(name = "fetchData")
    public Object[][] getData() {
        RandomStringUtils random = new RandomStringUtils();
        Date date = new Date();
        emaiId = date.getTime() + "test@test.com";
        username = random.random(7, true, false);
        ExcelDataProvider ex = new ExcelDataProvider();
        // String[][] data = ex.getExcelData("Test1");

        Object[][] data = new Object[2][1];
        data[0][0] = "Create New User";
        data[1][0] = "Filter Options";
        return data;
    }
}
