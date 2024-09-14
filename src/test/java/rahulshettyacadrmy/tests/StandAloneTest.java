package rahulshettyacadrmy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacadrmy.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		// Nhừng dòng trên cũng có nhiệm vụ là điều khiển chromebrowser giống với dòng
		// bên dưới nhưng dòng bên trên là tự động còn dòng bên dưới là thủ công.
		// System.setProperty("webdriver.chrome.driver","D:/FPT/automation_test/chromedriver-win64/chromedriver-win64/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		
		LandingPage l = new LandingPage(driver);
		
		driver.get("https://rahulshettyacademy.com/client/");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("thanhtu1052@gmail.com");
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys("Thanhtu1052");
		driver.findElement(By.cssSelector("	input[class*='login-btn']")).click();

		List<WebElement> productNames = driver.findElements(By.xpath("//div/h5/b"));
		List<WebElement> addToCartButtons = driver.findElements(By.cssSelector("button[class*='w-10']"));
		for (int i = 0; i < productNames.size(); i++) {
			String specificName = productNames.get(i).getText();
			if (specificName.contains("ZARA COAT 3")) {
				addToCartButtons.get(i).click();
			}

		}
		
		WebDriverWait wait = new WebDriverWait (driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		Thread.sleep(3000);
		
		// Cách 2
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream()
				.filter(p -> p.findElement(By.cssSelector("b")).getText().equals("ADIDAS ORIGINAL")).findFirst()
				.orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		Thread.sleep(1000);
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		//có thể find Element mà không cần tag name nếu với attribute và value của nó là unique.
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		for(int i=0; i<20; i++) {
			js.executeScript("window.scrollBy(0,100)");
			Thread.sleep(100);
		}

		
		List<WebElement> cartProducts =driver.findElements(By.xpath("//*[@class='cartSection']/h3"));
		Boolean match = cartProducts.stream().anyMatch(p->p.getText().equalsIgnoreCase("ADIDAS ORIGINAL"));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		System.out.println("check");
		driver.findElement(By.cssSelector(".form-group input[class*='text-validated']")).sendKeys("ind");
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));
		
		List<WebElement> suggestiveResults = driver.findElements(By.cssSelector("button[class*='ng-star-inserted']"));
		for(int i=0; i<suggestiveResults.size(); i++)
		{
		System.out.println(suggestiveResults.get(i).getText());
		String nameCountry = suggestiveResults.get(i).getText();
		/*if (nameCountry.contains("India"))
		{
			suggestiveResults.get(i).click();
		}*/
		
		}
		Boolean match2 = suggestiveResults.stream().anyMatch(p->p.getText().equalsIgnoreCase("india"));
		Assert.assertTrue(match2);
		suggestiveResults.stream().filter(p->p.getText().equalsIgnoreCase("India")).findFirst().ifPresent(WebElement::click);
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confirmMess = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMess.equalsIgnoreCase("Thankyou for the order."));
		driver.close();
		
		
		
		
		
		
		

	}

}
