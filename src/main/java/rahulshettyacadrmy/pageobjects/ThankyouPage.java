package rahulshettyacadrmy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import rahulshettyacadrmy.AbstractComponent.abstractComponent;

public class ThankyouPage extends abstractComponent {

	WebDriver driver;
	
	public ThankyouPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (css=".hero-primary")
	WebElement ThankyouText;
	
	public String checkThankyouText()
	{
		String confirmMess = ThankyouText.getText();
		return confirmMess;
	}
	
	
	
	
	
	
}
