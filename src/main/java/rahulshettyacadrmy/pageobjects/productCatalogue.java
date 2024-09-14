package rahulshettyacadrmy.pageobjects;

import java.time.Duration;
import java.util.Collection;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacadrmy.AbstractComponent.abstractComponent;

public class productCatalogue extends abstractComponent {

	WebDriver driver;

	public productCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div/h5/b")
	List<WebElement> productNames;

	@FindBy(css = "button[class*='w-10']")
	List<WebElement> addToCartButtons;

	@FindBy(css = "#toast-container")
	WebElement toast;
	By toastBy = By.cssSelector("#toast-container");

	@FindBy(css = ".ng-animating")
	WebElement loadingIcon;
	By loadingIconBy = By.cssSelector(".ng-animating");

	@FindBy(css = ".mb-3")
	List<WebElement> products;
	By productsBy = By.cssSelector(".mb-3");

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartButton;
	
	
	 	
	

	//Cách 1: tốn effort vì phải lặp đi lặp lại
	public void waiting() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(toast));
		wait.until(ExpectedConditions.invisibilityOfElementLocated((By) loadingIcon));
		
	//Cách 2: tạo method tại abstractComponent

	}

	public void addProduct1(String productName1) {
		waitForElementToAppear(productsBy);
		for (int i = 0; i < productNames.size(); i++) {
			String specificName = productNames.get(i).getText();
			if (specificName.contains(productName1)) {
				addToCartButtons.get(i).click();
			}
		}
		waitForElementToAppear(toastBy);
		waitForElementToDisappear(loadingIconBy);
	}

	public void addProduct2(String productName2) {
		waitForElementToAppear(productsBy);
		WebElement prod = products.stream()
				.filter(p -> p.findElement(By.cssSelector("b")).getText().equals(productName2)).findFirst()
				.orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		waitForElementToAppear(toastBy); //wait để confirm là đã add thành công vào cart. Tuy nhiên nếu việc loading mất thời gian quá thì có thể cân nhắc chuyển sang Thread.sleep(1000);
		waitForElementToDisappear(loadingIconBy);
	}
	
	public WebElement getCartButton()
	{
		waitForElementToAppear(productsBy);
		return cartButton;
	}
	
	


}
