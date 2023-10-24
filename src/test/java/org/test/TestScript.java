package org.test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pojo.DashboardPage;
import org.pojo.LoginPage;
import org.pojo.PatientDashboardPage;
import org.pojo.PatientPage;
import org.pojo.RegistrationPage;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class TestScript extends BaseClass {

	ExtentReports extent;
	ExtentTest test;
	
	
	LoginPage loginPage;
	DashboardPage dashboardPage;
	RegistrationPage registrationPage;
	PatientPage patientpage;
	PatientDashboardPage patientDashboardPage;

	@BeforeSuite
	public void setup() {
		browserLaunch();

		// Extent Report setup
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent-report.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}

	@Test
	public void executeTest() throws InterruptedException, IOException, AWTException {
		// Start the test
		test = extent.createTest("Test Case 1 - Redirect to Login Page");
		browserMax();
		
// 1. Redirect to https://qa-refapp.openmrs.org/openmrs/login.htm
		
		urlLaunch("https://qa-refapp.openmrs.org/openmrs/login.htm");
		impliCitWait();
		test.log(Status.PASS, "Navigated to the login page");

		loginPage = new LoginPage();
		dashboardPage = new DashboardPage();
		registrationPage = new RegistrationPage();
		patientpage = new PatientPage();
		patientDashboardPage = new PatientDashboardPage();

// 2. Enter Username(Admin) and password(Admin123)
		
		fillText(loginPage.usernameField, "Admin");
		fillText(loginPage.passwordField, "Admin123");
		
// 3. Pick any location below and click on Login. 
		
		buttonClick(loginPage.opClinicSessionLoc);
		buttonClick(loginPage.loginButton);
		// FluentWait<WebDriver> w = new
		// FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(1)).ignoring(Throwable.class);
		// w.until(ExpectedConditions.titleContains("Home"));
		Thread.sleep(3000);
		
// 4. Verify dashboard page is redirected using Assertion. 
		
		Assert.assertTrue(driver.getTitle().contains("Home"), "Dashboard not found");
		Assert.assertTrue(driver.getCurrentUrl().contains("home"), "Dashboard not found");
		Assert.assertTrue(dashboardPage.loggedInTextMsg.getText().contains("Logged in"), "Dashboard not found");
		test.log(Status.PASS, "Dashboard page is redirected");

		//captureScreenshot("Successful Logged In");
		
// 5. Cick on Register a patent menu
		
		buttonClick(dashboardPage.registerPatientOption);
		
// 6. Enter the detail of Demographics(Name, Gender, Birthdate) and Contact Info(Address, Phone number)
		
		// Input Values
		
		String givenName = "Tamil";
		String familyName = "Arasan";
		String inputName = givenName+", "+familyName; // The expected name format
		String inputGender = "Male";
		String inputBirthDay = "15";
		String inputBirthMonth = "November";
		String inputBirthYear = "1994";
		String inputBirthday = inputBirthDay+", "+inputBirthMonth+", "+inputBirthYear;
		String address1 = "10";
		String city = "Chennai";
		String state = "TN";
		String country = "INDIA";
		String pincode = "624001";
		String inputAddress = address1+", "+city+", "+state+", "+country+", "+pincode;
		String inputPhoneNumber = "9788406460";
		String inputRelatives = "---";
		
		List<String> liInput = new ArrayList<String>();
		liInput.add(inputName);
		liInput.add(inputGender);
		liInput.add(inputBirthday);
		liInput.add(inputAddress);
		liInput.add(inputPhoneNumber);
		liInput.add(inputRelatives);
		
		
		fillText(registrationPage.givenNameField, givenName);
		fillText(registrationPage.familyNameField, familyName);
		
		buttonClick(registrationPage.nextButton);
		
		selectByText(registrationPage.genderSelection, inputGender);
		
		buttonClick(registrationPage.nextButton);
		
		fillText(registrationPage.birthDayField, inputBirthDay);
		selectByText(registrationPage.birthMonthField, inputBirthMonth);
		fillText(registrationPage.birthYearField, inputBirthYear);
		
		buttonClick(registrationPage.nextButton);
		
		fillText(registrationPage.address1Field, address1);
		
		fillText(registrationPage.cityField, city);
		fillText(registrationPage.stateField, state);
		fillText(registrationPage.countryField, country);
		fillText(registrationPage.postalCodeField, pincode);
		
		buttonClick(registrationPage.nextButton);
		
		fillText(registrationPage.phoneNumberField, inputPhoneNumber);
		
		buttonClick(registrationPage.nextButton);
		
		buttonClick(registrationPage.nextButton);
		
// 7. Then at Confirm page, verify the given Name, Gender, Birthdate, Address, Phone number are populated correctly using Assertion.
		
		List<WebElement> liOutput = registrationPage.confirmationPageDetails;
		
		for (int i = 0; i < liOutput.size(); i++) {
			String eachOutput = liOutput.get(i).getText();
			Assert.assertTrue(eachOutput.contains(liInput.get(i)));
			
		}
		
		test.log(Status.PASS, "Patient Registration details updated");
		
		
// 8. Click on Confirm and verify Patient details page is redirected and verify the age is calculated correctly based on the date of birth provided.  
		
		buttonClick(registrationPage.confirmButton);
		Thread.sleep(3000);
		Assert.assertTrue(driver.getTitle().contains("Medical Record"));
		Assert.assertTrue(driver.getCurrentUrl().contains("patientId"));
		
		Assert.assertTrue(patientpage.ageText.getText().contains(currentAgeFind(inputBirthDay, inputBirthMonth, inputBirthYear)));
		
		test.log(Status.PASS, "Patient Registration age is verified");
		
// 9. Click on Start Visit and Confirm the visit.
		
		buttonClick(patientpage.startVisitLink);
		buttonClick(patientpage.confirmButton);
		
		test.log(Status.PASS, "Patient Registration is successful");
		
// 10. Click on Attachment and complete the upload process.
		
		Thread.sleep(2000);
		buttonClick(patientDashboardPage.attachmentButton);
		Thread.sleep(2000);
		
		buttonClick(patientDashboardPage.uploadArea);
	    Thread.sleep(2000); // suspending execution for specified time period
	 
	    uploadFile("C:\\Users\\salma\\Downloads\\Sample_Test.pdf");
	    
	    test.log(Status.PASS, "Patient file is successfully uploaded");

	}

	 @AfterMethod
	 public void tearDown(ITestResult result) throws IOException {
	 if (result.getStatus() == ITestResult.FAILURE) {
	 test.log(Status.FAIL, MarkupHelper.createLabel("Test Case Failed: " +
	 result.getName(), ExtentColor.RED));
	 // Capture and attach a screenshot
	 String screenshotPath = captureScreenshot(result.getName());
	 //System.out.println(screenshotPath);
	 test.addScreenCaptureFromPath(screenshotPath);
	 } else if (result.getStatus() == ITestResult.SUCCESS) {
	 test.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed: " +
	 result.getName(), ExtentColor.GREEN));
	 }
	
	 //driver.quit();
	 }

	@AfterSuite
	public void tearDownSuite() {
		extent.flush();
	}

	// Implement a method to capture screenshots
	private String captureScreenshot(String screenshotName) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);

		
		String path = System.getProperty("user.dir");
		String screenshotPath = path + "\\Screenshots\\" + screenshotName + ".png";
		System.out.println(screenshotPath);

		try {
			FileUtils.copyFile(srcFile, new File(screenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}

		test.addScreenCaptureFromPath(screenshotPath);
		return screenshotPath;
	}
}
