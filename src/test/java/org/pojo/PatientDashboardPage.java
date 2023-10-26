package org.pojo;

import java.util.List;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PatientDashboardPage extends BaseClass{

	public PatientDashboardPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[contains(@id,'attachments')]")
	public WebElement attachmentButton;
	
	@FindBy(id = "visit-documents-dropzone")
	public WebElement uploadArea;
	
	@FindBy(xpath = "//a[contains(@id,'vitals')]")
	public WebElement captureVitalsButton;
	
	@FindBy(xpath = "//span[@id='height']//input")
	public WebElement heightTextField;
	
	@FindBy(xpath = "//span[@id='weight']//input")
	public WebElement weightTextField;
	
	@FindBy(id = "calculated-bmi")
	public WebElement patientBMICalc;
	
	@FindBy(id = "save-form")
	public WebElement saveFormLink;
	
	@FindBy(xpath = "//button[@type='submit']")
	public WebElement saveButton;
	
	@FindBy(xpath = "//a[contains(@href,'EndVisit')]")
	public WebElement endVisitButton;
	
	@FindBy(xpath = "//div[@id='end-visit-dialog']//button[text()='Yes' and (not (@type))]")
	public WebElement yesConfirmButtonInPatientDashboard;
	
	@FindBy(name = "mergeVisits")
	public List<WebElement> mergeList;
	
	@FindBy(id = "mergeVisitsBtn")
	public WebElement mergeVisitBtn;
	
	@FindBy(xpath = "//input[@id='mergeVisitsBtn']//preceding-sibling::input")
	public WebElement returnBtn;
	
	@FindBy(xpath  = "//div[contains(@id,'retrospective-visit-creation-dialog')]//button[@class='cancel']")
	public WebElement cancelButton;
	
	@FindBy(id = "delete-reason")
	public WebElement deleteReasonTextField;
	
	@FindBy(xpath  = "//div[contains(@id,'delete')]//button[text()='Confirm']")
	public WebElement confirmDeleteButton;
	
	@FindBy(xpath = "//em[text()='Patient ID']//..//span")
	public WebElement patientId;
	
	@FindBy(xpath = "//table[@id='patient-search-results-table']//tr//td[1]")
	public List<WebElement> allPatientId;
	
	@FindBy(id = "patient-search")
	public WebElement patientSearchField;
	
}
