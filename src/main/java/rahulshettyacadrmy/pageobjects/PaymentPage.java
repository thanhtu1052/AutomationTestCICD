package rahulshettyacadrmy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacadrmy.AbstractComponent.abstractComponent;

public class PaymentPage extends abstractComponent{

	WebDriver driver;
	public PaymentPage (WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".form-group input[class*='text-validated']")
	WebElement selectCountryField;
	
	@FindBy (css="button[class*='ng-star-inserted']")
	List<WebElement> suggestiveResults;
	
	@FindBy (css=".action__submit")
	WebElement submitButton;
	
	public void typeCountry(String keywords)
	{
		selectCountryField.sendKeys(keywords);
	}
	
	public boolean checkThePresentOfCountry(String country)
	{
		Boolean match = suggestiveResults.stream().anyMatch(p->p.getText().equalsIgnoreCase(country));
		return match;
	}
	
	public void selectCountry(String country)
	{
		suggestiveResults.stream().filter(p->p.getText().equalsIgnoreCase(country)).findFirst().ifPresent(WebElement::click);
	}
	
	public void goToThankyouPage()
	{
		submitButton.click();
	}
	
	
	

	
	
}
