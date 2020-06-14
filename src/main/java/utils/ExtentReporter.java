package utils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public interface ExtentReporter {

	public void reportStep(String desc, String status);

	public ExtentReports startResult();

	public ExtentTest startTestCase(String testCaseName, String testDescription);

	public void endResult();
	
	public void endTestcase();
}
