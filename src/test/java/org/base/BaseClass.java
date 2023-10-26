package org.base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public static WebDriver driver;
	public static Actions a;
	
	public static void browserLaunch() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--disable-notifications");
		
		// Set preferences to disable notification permissions
		chromeOptions.setExperimentalOption("prefs", 
            new java.util.HashMap<String, Object>() {{
                put("profile.default_content_setting_values.notifications", 2);
            }}
        );
		driver = new ChromeDriver(chromeOptions);

	}

	public static void urlLaunch(String url) {
		driver.get(url);
	}
	
	public static void impliCitWait() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}
	
	public static void browserMax() {
		driver.manage().window().maximize();

	}
	
	public static void fillText(WebElement ele, String text) {
		ele.sendKeys(text);
	}
	
	public static void buttonClick(WebElement ele) {
		ele.click();
	}
	
	public static void actionsclassclick(WebElement target) {
		a = new Actions(driver);
		a.moveToElement(target).click(target).perform();

	}
	
	public static void javascriptclick(WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", ele);

	}
	
	public static void selectByText(WebElement ele, String text) {
		Select s  = new Select(ele);
		s.selectByVisibleText(text);
		

	}
	
	public static String selectBValue(WebElement ele, String text) {
		Select s  = new Select(ele);
		s.selectByValue(text);
		return text;

	}
	
	public static String currentAgeFind(String inputBirthDay, String inputBirthMonth, String inputBirthYear) {
		

		        // Parse the birthdate values
		        int day = Integer.parseInt(inputBirthDay);
		        Month month = Month.valueOf(inputBirthMonth.toUpperCase()); // Convert input month to an enum
		        int year = Integer.parseInt(inputBirthYear);

		        // Create the birthdate
		        LocalDate birthdate = LocalDate.of(year, month, day);

		        // Get the current date
		        LocalDate currentDate = LocalDate.now();

		        // Calculate the age
		        Period age = Period.between(birthdate, currentDate);

		        int years = age.getYears();
//		        int months = age.getMonths();
//		        int days = age.getDays();

		        return years+ " year";
		    }
	
	
	
	public static void uploadFile(String fileLocation) throws AWTException {
		// creating object of Robot class
	    Robot rb = new Robot();
	 
	    // copying File path to Clipboard
	    StringSelection str = new StringSelection(fileLocation);
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
	 
	     // press Contol+V for pasting
	     rb.keyPress(KeyEvent.VK_CONTROL);
	     rb.keyPress(KeyEvent.VK_V);
	 
	    // release Contol+V for pasting
	    rb.keyRelease(KeyEvent.VK_CONTROL);
	    rb.keyRelease(KeyEvent.VK_V);
	 
	    // for pressing and releasing Enter
	    rb.keyPress(KeyEvent.VK_ENTER);
	    rb.keyRelease(KeyEvent.VK_ENTER);
    }
		
	
	public static int dateEqualComparison(String date) throws ParseException {
        // Create a SimpleDateFormat object with the correct pattern for "26.Oct.2023"
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MMM.yyyy", new java.util.Locale("en"));

        // Convert the given date to a Date object
        Date givenDate = dateFormat.parse(date);

        // Get the current date as a Date object
        Date currentDate = new Date();

        // Compare the two dates based on day, month, and year
        int comparisonResult = compareDatesIgnoringTime(givenDate, currentDate);

        return comparisonResult;
    }

    public static int compareDatesIgnoringTime(Date date1, Date date2) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String formattedDate1 = dateFormat.format(date1);
        String formattedDate2 = dateFormat.format(date2);

        return formattedDate1.compareTo(formattedDate2);
    }
	
	public static String bmiCalculate(double weightInKG, double heightInCM) {
		double heightInMT = heightInCM/100;
		double bmi = weightInKG / (heightInMT * heightInMT);
		
		// Create a DecimalFormat object with one decimal place
        DecimalFormat df = new DecimalFormat("#.#");

        // Format the original value to one decimal place
        String roundedValue = df.format(bmi);
        
		return roundedValue;

	}
	
	
}
