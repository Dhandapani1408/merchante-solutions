package wdMethods;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import utils.ExcelDataProvider;

public class ProjectMethods extends SeMethods {

    public String browserName = "chrome";
    public String dataSheetName;
    public String AuthorName;
    public String TestCaseName;
    public String Description;
    public String Category;

    @BeforeSuite
    public void beforeSuite() {
        startResult();
    }

    @BeforeTest
    public void beforeTest() {
    }

    @AfterSuite
    public void afterSuite() {
        endResult();
    }

    @AfterTest
    public void afterTest() {
    }

    @AfterMethod
    public void afterMethod() {

    }

    @DataProvider(name = "fetchData")
    public Object[][] getData() {
        return new ExcelDataProvider().getExcelData(dataSheetName);
    }

}
