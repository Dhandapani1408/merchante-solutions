package utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public abstract class Reporter implements ExtentReporter {
    public static ExtentTest test;
    public static ExtentReports extent;
    public String testCaseName, testDescription, category, authors;

    public void reportStep(String desc, String status) {

        long snapNumber = 100000l;

        try {
            if (!desc.contains("-api")) {
                snapNumber = takeSnap();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!desc.contains("-api")) {
            // Write if it is successful or failure or information
            if (status.toUpperCase().equals("PASS")) {
                test.log(LogStatus.PASS, desc);
            } else if (status.toUpperCase().equals("FAIL")) {
                test.log(LogStatus.FAIL, desc + test.addScreenCapture("./../reports/images/" + snapNumber + ".jpg"));
                throw new RuntimeException("FAILED");
            } else if (status.toUpperCase().equals("INFO")) {
                test.log(LogStatus.INFO, desc);
            } else if (status.toUpperCase().equals("WARN")) {
                test.log(LogStatus.WARNING, desc + test.addScreenCapture("./../reports/images/" + snapNumber + ".jpg"));
            }
        } else {
            if (status.toUpperCase().equals("PASS")) {
                test.log(LogStatus.PASS, desc);
            } else if (status.toUpperCase().equals("FAIL")) {
                test.log(LogStatus.FAIL, desc);
                throw new RuntimeException("FAILED");
            } else if (status.toUpperCase().equals("INFO")) {
                test.log(LogStatus.INFO, desc);
            } else if (status.toUpperCase().equals("WARN")) {
                test.log(LogStatus.WARNING, desc);
            }
        }
    }

    public abstract long takeSnap();

    public ExtentReports startResult() {
        try {
            FileUtils.deleteDirectory(new File("./reports"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Report Deleted");
        extent = new ExtentReports("./reports/result.html", false);
        extent.loadConfig(new File("./extent.xml"));
        System.out.println("started results");
        return extent;
    }

    public ExtentTest startTestCase(String testCaseName, String testDescription) {
        test = extent.startTest(testCaseName, testDescription);
        return test;
    }

    public void endResult() {
        extent.flush();
    }

    public void endTestcase() {
        extent.endTest(test);
    }

}