package org.base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public static WebDriver driver;
	
	public static void browserLaunch() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--disable-notifications");
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
		
	
	
}
