package rahulshettyacadrmy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacadrmy.AbstractComponent.abstractComponent;

public class LandingPage extends abstractComponent {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	// Cách 1
	// WebElement emailAddress =
	// driver.findElement(By.cssSelector("input[type='email']"));
	// driver.findElement(By.id("userEmail']")); //Nếu tìm bằng id thì có cách 2

	// Cách 2
	// Page Factory
	@FindBy(id = "userEmail")
	WebElement emailAddress;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement loginButton;

	@FindBy(css = "[class*='flyInOut']")
	WebElement ErrorMessage;

	public productCatalogue loginApplication(String email, String password) {
		emailAddress.sendKeys(email);
		userPassword.sendKeys(password);
		loginButton.click();
		productCatalogue productCatalogue = new productCatalogue(driver);
		return productCatalogue;
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}

	public String getErrorMess() {
		waitForWebElementToAppear(ErrorMessage);
		return ErrorMessage.getText();
	}
}
