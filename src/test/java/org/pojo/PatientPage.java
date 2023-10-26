package org.pojo;

import java.util.List;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PatientPage extends BaseClass{

	public PatientPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[contains(@class,'gender-age')]//span[2]")
	public WebElement ageText;
	
	@FindBy(xpath = "//div[contains(text(),'Start Visit')]")
	public WebElement startVisitLink;
	
	@FindBy(xpath = "//button[contains(@id,'-confirm')]")
	public WebElement confirmButton;
	
	@FindBy(xpath = "//ul[@id='breadcrumbs']//a[contains(@href,'patient')]")
	public WebElement patientDetailPageLink;
	
	@FindBy(xpath = "//h3[text()='Recent Visits']/following::div[1]//li//a")
	public List<WebElement> recentVisitsList;
	
	@FindBy(xpath = "//h3[text()='ATTACHMENTS']/following::div[1]//li//a")
	public List<WebElement> attachmentList;
	
	@FindBy(xpath = "(//div[contains(text(),'End Visit')])[1]")
	public WebElement endVisitlink;
	
	@FindBy(xpath = "//button[text()='Yes' and (not (@type))]")
	public WebElement yesConfirmButton;
	
	@FindBy(xpath = "//span[@id='weight']//span[1]")
	public WebElement weightDisplayed;
	
	@FindBy(xpath = "//span[@id='height']//span[1]")
	public WebElement heightDisplayed;
	
	@FindBy(id = "calculated-bmi")
	public WebElement bmiDisplayed;
	
	@FindBy(xpath = "//div[text()='Vitals']")
	public WebElement vitalTag;
	
	@FindBy(xpath = "//div[contains(text(),'Merge Visits')]")
	public WebElement mergeVisitLink;
	
	@FindBy(xpath = "//div[contains(text(),'Add Past Visit')]")
	public WebElement addPastVisitLink;
	
	@FindBy(xpath = "(//td[@class='day disabled'])[last()]")
	public WebElement futureDate;
	
	@FindBy(xpath = "//div[contains(text(),'Delete Patient')]")
	public WebElement deletePatientlink;
	
	@FindBy(xpath = "//*[contains(text(),'deleted successfully')]")
	public WebElement toasterMessage;
	
	
	
}
