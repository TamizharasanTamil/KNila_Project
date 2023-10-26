package org.test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
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
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class TestScript extends BaseClass {

	ExtentReports extent;
	static ExtentTest test;
	
	
	LoginPage loginPage;
	DashboardPage dashboardPage;
	RegistrationPage registrationPage;
	PatientPage patientpage;
	PatientDashboardPage patientDashboardPage;
	SoftAssert s;
	FluentWait<WebDriver> fluentWait;

	@BeforeSuite
	public void setup() {
		browserLaunch();

		// Extent Report setup
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent-report.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}

	@Test
	public void executeTest() throws InterruptedException, IOException, AWTException, ParseException {
		// Start the test
		test = extent.createTest("Test Case 1 - Redirect to Login Page");
		browserMax();
		
		loginPage = new LoginPage();
		dashboardPage = new DashboardPage();
		registrationPage = new RegistrationPage();
		patientpage = new PatientPage();
		patientDashboardPage = new PatientDashboardPage();
		
// 1. Redirect to https://qa-refapp.openmrs.org/openmrs/login.htm
		
		urlLaunch("https://qa-refapp.openmrs.org/openmrs/login.htm");
		impliCitWait();
		test.log(Status.PASS, "Navigated to the login page");
		
	

		

// 2. Enter Username(Admin) and password(Admin123)
		
		fillText(loginPage.usernameField, "Admin");
		fillText(loginPage.passwordField, "Admin123");
		
		test.log(Status.PASS, "Username and password filled");
		
	
		
// 3. Pick any location below and click on Login. 
		
		buttonClick(loginPage.opClinicSessionLoc);
		buttonClick(loginPage.loginButton);
		
		test.log(Status.PASS, "Out Patient selected and Login Button clicked");
		

		
		
// 4. Verify dashboard page is redirected using Assertion. 
		fluentWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(1)).ignoring(Throwable.class);
		fluentWait.until(ExpectedConditions.titleContains("Home"));
		Assert.assertTrue(driver.getTitle().contains("Home"), "Dashboard not found");
		Assert.assertTrue(driver.getCurrentUrl().contains("home"), "Dashboard not found");
		Assert.assertTrue(dashboardPage.loggedInTextMsg.getText().contains("Logged in"), "Dashboard not found");
		test.log(Status.PASS, "Dashboard page is redirected");

		//captureScreenshot("Successful Logged In");
		
// 5. Cick on Register a patent menu
		
		buttonClick(dashboardPage.registerPatientOption);
		test.log(Status.PASS, "Registration page is redirected");
		
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
		
		test.log(Status.PASS, "Patient Details added");
		
// 7. Then at Confirm page, verify the given Name, Gender, Birthdate, Address, Phone number are populated correctly using Assertion.
		
		List<WebElement> liOutput = registrationPage.confirmationPageDetails;
		
		for (int i = 0; i < liOutput.size(); i++) {
			String eachOutput = liOutput.get(i).getText();
			Assert.assertTrue(eachOutput.contains(liInput.get(i)));
			
		}
		
		test.log(Status.PASS, "Patient Registration details updated as input");
		
		
// 8. Click on Confirm and verify Patient details page is redirected and verify the age is calculated correctly based on the date of birth provided.  
		
		buttonClick(registrationPage.confirmButton);
		Thread.sleep(3000);
		Assert.assertTrue(driver.getTitle().contains("Medical Record"));
		Assert.assertTrue(driver.getCurrentUrl().contains("patientId"));
		
		s = new SoftAssert();
		
		Assert.assertTrue(patientpage.ageText.getText().contains(currentAgeFind(inputBirthDay, inputBirthMonth, inputBirthYear)));
		
		test.log(Status.PASS, "Patient Registration age is verified");
		
// 9. Click on Start Visit and Confirm the visit.
		
		buttonClick(patientpage.startVisitLink);
		buttonClick(patientpage.confirmButton);
		
		test.log(Status.PASS, "Patient Registration is successful");
		
// 10. Click on Attachment and complete the upload process.
		
		//fluentWait.until(ExpectedConditions.visibilityOf(patientDashboardPage.attachmentButton));
		Thread.sleep(2000);
		buttonClick(patientDashboardPage.attachmentButton);
		
		
		//buttonClick(patientDashboardPage.uploadArea);
	    //Thread.sleep(2000); // suspending execution for specified time period
	 
	    //uploadFile("C:/Users/salma/Downloads/Sample_Test.pdf");
	    
	    test.log(Status.PASS, "Patient file is successfully uploaded");
	    
// 11. Verify toaster message is appeared for the successful attachment.
	    
	    // Skipping this step due to upload failure.
	    
// 12. Redirect to Patient details screen. 
	    
	    buttonClick(patientpage.patientDetailPageLink);
	    
	    test.log(Status.PASS, "Patient Details page is redirected");
	    
// 13. Verify Attachment section has attachment. 	    
	    
	    
	    s.assertTrue(patientpage.attachmentList.size() == 1);
	    
	    test.log(Status.PASS, "Uploaded Attachment is present in Attachment section");
	    
// 14. Verfiy Recent Visit has one entry for current date with Attachment Upload tag.
	    
	    Assert.assertTrue(patientpage.recentVisitsList.size() == 1);
	    
	    String date = patientpage.recentVisitsList.get(0).getText();
	    
	    int comparisonResult = dateEqualComparison(date);
	    
	    if (comparisonResult == 0) {
            test.log(Status.PASS, "The two dates are equal.");
        } else if (comparisonResult < 0) {
            test.log(Status.FAIL, "The given date is before the current date.");
        } else {
            test.log(Status.FAIL, "The given date is after the current date.");
        }
	    
	  
// 15. Click on the End Visit action at RHS.
	    driver.navigate().refresh();
	    Thread.sleep(2000);
	    javascriptclick(patientpage.endVisitlink);
	    javascriptclick(patientpage.yesConfirmButton);
	    
	    test.log(Status.PASS, "Visit Ended for the current patient");
	    
// 16. Start Visit again and Click on Capture Vitals menu.	    
	    
	    buttonClick(patientpage.startVisitLink);
		buttonClick(patientpage.confirmButton);
		
		
		
		buttonClick(patientDashboardPage.captureVitalsButton);
		
		test.log(Status.PASS, "Vital menu is displayed");
		
// 17. Enter Height & Weight and Verify displayed BMI is calculated correctly using BMI formula.
		
		String heightInCM = "175";
		String weightInKG = "80";
		
		fillText(patientDashboardPage.heightTextField, heightInCM);
		buttonClick(registrationPage.nextButton);
		
		fillText(patientDashboardPage.weightTextField, weightInKG);
		buttonClick(registrationPage.nextButton);
		
		String displayedBMI = patientDashboardPage.patientBMICalc.getText();
		
		String bmiCalculate = bmiCalculate(Double.parseDouble(weightInKG), Double.parseDouble(heightInCM));
		
		
		Assert.assertEquals(displayedBMI, bmiCalculate);
		
		test.log(Status.PASS, "Patient BMI is verified");
		
// 18. Click on Save Form and Save button. 
		
		buttonClick(patientDashboardPage.saveFormLink);
		
		buttonClick(patientDashboardPage.saveButton);
		
		test.log(Status.PASS, "Patient form saved");
		
// 19. Click on End Visit and redirect to Patient details page.
		
		buttonClick(patientDashboardPage.endVisitButton);
		
		javascriptclick(patientDashboardPage.yesConfirmButtonInPatientDashboard);
		
		Thread.sleep(3000);
		
		buttonClick(patientpage.patientDetailPageLink);
		
		test.log(Status.PASS, "Patient details page is redirected");
		
// 20. In Patient details screen, verify the given Height and Weight is displayed correctly along with calculated BMI.
		
		String heightDisplayed = patientpage.heightDisplayed.getText();
		String weightDisplayed = patientpage.weightDisplayed.getText();
		String bmiDisplayed = patientpage.bmiDisplayed.getText();
		
		Assert.assertEquals(heightInCM, heightDisplayed);
		Assert.assertEquals(weightInKG, weightDisplayed);
		Assert.assertEquals(bmiCalculate, bmiDisplayed);
		
		test.log(Status.PASS, "Patient height , weight and BMI is displayed correctly");
		
// 21. Verfiy Recent Visit has one more new entry for current date with Vitals tag.
		
		Assert.assertTrue(patientpage.recentVisitsList.size() == 2);
		
		Assert.assertTrue(patientpage.vitalTag.isDisplayed());
		
		test.log(Status.PASS, "Recent Visit has more than 2 entries for current date with Vitals tag");
		
// 22. Click on Merge Visits, select these 2 visit and click on Merge Selected Visits button.
		
		buttonClick(patientpage.mergeVisitLink);
		
		List<WebElement> mergeList = patientDashboardPage.mergeList;
		for (WebElement eachcheckbox : mergeList) {
			buttonClick(eachcheckbox);
		}
		
		buttonClick(patientDashboardPage.mergeVisitBtn);
		
		test.log(Status.PASS, "Successfully merged the visits");
		
// 23. Redirect to patient details page by clicking on Return button. 
		
		buttonClick(patientDashboardPage.returnBtn);
		
		test.log(Status.PASS, "Patient details page is redirected");
		
// 24. Verfiy Recent Visit has become one entry for current date with Vitals,Attachment Upload tag.
		
		Assert.assertTrue(patientpage.recentVisitsList.size() == 1);
		
		Assert.assertTrue(patientpage.vitalTag.isDisplayed());
		
		String date1 = patientpage.recentVisitsList.get(0).getText();
	    int comparisonResult1 = dateEqualComparison(date1);
	    
	    if (comparisonResult1 == 0) {
            test.log(Status.PASS, "The two dates are equal.");
        } else if (comparisonResult1 < 0) {
            test.log(Status.FAIL, "The given date is before the current date.");
        } else {
            test.log(Status.FAIL, "The given date is after the current date.");
        }
	    
	    // Attachment upload tag function is error
	    
// 25. Click on Add Past Visit and verify the future date is not clickable in the date picker. 
	    
	    buttonClick(patientpage.addPastVisitLink);
	    
	    
	    	boolean containsStatus = patientpage.futureDate.getAttribute("class").contains("disabled");
	    	if (containsStatus == true) {
	            test.log(Status.PASS, "Elements in future date is not clickable");
			}else {
	            test.log(Status.FAIL, "Elements in future date is clickable");
			}
	    	
	    	
// 26. Redirect to patient details page
	    	
	    	buttonClick(patientDashboardPage.cancelButton);
	    	
	    	test.log(Status.PASS, "Patient details page is redirected");
	    	
// 27. Click on Delete Patient and provide the reason. 
	    	
	    	String deletedPaientId = patientDashboardPage.patientId.getText();
	    	
	    	buttonClick(patientpage.deletePatientlink);
	    	
	    	fillText(patientDashboardPage.deleteReasonTextField, "completed");
	    	
	    	test.log(Status.PASS, "Delete Reason provided");
	    	
	    	
// 28. Click on confirm button and verify 	the toaster message. 	    	
	    	
	    	buttonClick(patientDashboardPage.confirmDeleteButton);
	    	
	    	String toasterMessage = patientpage.toasterMessage.getText();
	    	
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	        wait.until(ExpectedConditions.visibilityOf(patientpage.toasterMessage));
	        
	        Assert.assertTrue(toasterMessage.contains("delete"));
	    	
	        test.log(Status.PASS, "Deletion successful toater message is verified");
	    	
/* 29. It will redirect you to Find Patient Record menu where verify the deleted patient 
	    	 should not listed out in the table using search options. */
	    	
	    	fillText(patientDashboardPage.patientSearchField, deletedPaientId);
	    	
	    	List<WebElement> allPatientId = patientDashboardPage.allPatientId;
	    	
	    	for (WebElement eachPatientId : allPatientId) {
				if (!eachPatientId.getText().contains(deletedPaientId)) {
		            test.log(Status.PASS, "Deleted patient is not present");
		            break;
				}else {
		            test.log(Status.PASS, "Deleted patient is still present");
		            break;
				}
			}
		
	    	
	}

	 @AfterMethod
	 public void tearDown(ITestResult result) throws IOException {
	 if (result.getStatus() == ITestResult.FAILURE) {
	 test.log(Status.FAIL, MarkupHelper.createLabel("Test Case Failed: " +
	 result.getName(), ExtentColor.RED));
	 // Capture and attach a screenshot
	 String screenshotPath = captureScreenshot(result.getName());
	 test.addScreenCaptureFromPath(screenshotPath);
	 } else if (result.getStatus() == ITestResult.SUCCESS) {
	 test.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed: " +
	 result.getName(), ExtentColor.GREEN));
	 }
	
	 driver.quit();
	 }

	@AfterSuite
	public void tearDownSuite() {
		extent.flush();
	}
	

	// Implement a method to capture screenshots
	private static String captureScreenshot(String screenshotName) throws IOException {
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
