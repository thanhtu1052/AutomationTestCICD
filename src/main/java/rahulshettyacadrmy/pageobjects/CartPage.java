package rahulshettyacadrmy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacadrmy.AbstractComponent.abstractComponent;

public class CartPage extends abstractComponent {

	WebDriver driver;
	public CartPage (WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@class='cartSection']/h")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkButton;
	
	public Boolean VerifyProductDisplay(String productName)
	{
		Boolean match = cartProducts.stream().anyMatch(p->p.getText().equalsIgnoreCase(productName));
		return match;   
	}
	
	public void goToPaymentPage ()
	{
		checkButton.click();
	}
	

	
	
	
}
