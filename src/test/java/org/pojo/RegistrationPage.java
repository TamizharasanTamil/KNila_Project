package org.pojo;

import java.util.List;

import org.base.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage extends BaseClass{
	
	public RegistrationPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "givenName")
	public WebElement givenNameField;
	
	@FindBy(name = "familyName")
	public WebElement familyNameField;
	
	@FindBy(id = "next-button")
	public WebElement nextButton;
	
	@FindBy(id = "gender-field")
	public WebElement genderSelection;
	
	@FindBy(id = "birthdateDay-field")
	public WebElement birthDayField;
	
	@FindBy(id = "birthdateMonth-field")
	public WebElement birthMonthField;
	
	@FindBy(id = "birthdateYear-field")
	public WebElement birthYearField;
	
	@FindBy(id = "address1")
	public WebElement address1Field;
	
	@FindBy(id = "cityVillage")
	public WebElement cityField;

	@FindBy(id = "stateProvince")
	public WebElement stateField;
	
	@FindBy(id = "country")
	public WebElement countryField;
	
	@FindBy(id = "postalCode")
	public WebElement postalCodeField;
	
	@FindBy(name = "phoneNumber")
	public WebElement phoneNumberField;
	
	@FindBy(xpath = "//span[@class='title']//parent::p")
	public List<WebElement> confirmationPageDetails;
	
	@FindBy(id = "submit")
	public WebElement confirmButton;

}
