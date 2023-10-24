package org.pojo;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PatientPage extends BaseClass{

	public PatientPage() {
		PageFactory.initElements(driver	, this);
	}
	
	@FindBy(xpath = "//div[contains(@class,'gender-age')]//span[2]")
	public WebElement ageText;
	
	@FindBy(xpath = "//div[contains(text(),'Start Visit')]")
	public WebElement startVisitLink;
	
	@FindBy(xpath = "//button[contains(@id,'-confirm')]")
	public WebElement confirmButton;
	
}
