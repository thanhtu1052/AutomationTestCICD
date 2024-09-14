package rahulshettyacadrmy.AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacadrmy.pageobjects.OrderPage;

public class abstractComponent {
	
	WebDriver driver;

	public abstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement cartButton;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;
	
	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait (driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitForElementToDisappear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait (driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement findBy)
	{
		WebDriverWait wait = new WebDriverWait (driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void clickCartButton()
	{
		cartButton.click();
	}
	
    public OrderPage goToMyOrderPage()
    {
    	orderHeader.click();
    	OrderPage orderPage = new OrderPage(driver);
    	return orderPage;
    }
}
