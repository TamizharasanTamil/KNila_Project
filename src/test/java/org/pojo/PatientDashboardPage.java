package org.pojo;

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
	
}
