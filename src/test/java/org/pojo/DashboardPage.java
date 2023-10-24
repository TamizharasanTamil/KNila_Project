package org.pojo;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage extends BaseClass{
	
	public DashboardPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@id='content']//h4")
	public WebElement loggedInTextMsg;
	
	@FindBy(xpath = "//a[contains(@id,'registerPatient')]")
	public WebElement registerPatientOption;
	

}
