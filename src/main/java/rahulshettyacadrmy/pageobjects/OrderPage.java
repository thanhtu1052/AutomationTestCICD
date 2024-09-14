package rahulshettyacadrmy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacadrmy.AbstractComponent.abstractComponent;

public class OrderPage extends abstractComponent {

	WebDriver driver;
	public OrderPage (WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//tbody/tr/td [2]")
	List<WebElement> orderProductName;
	
	
	public Boolean VerifyOrderProductDisplay(String productName)
	{
		Boolean match = orderProductName.stream().anyMatch(p->p.getText().equalsIgnoreCase(productName));
		return match;
	}
	

	
	
	
}
