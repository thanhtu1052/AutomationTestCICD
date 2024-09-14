package rahulshettyacadrmy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacadrmy.TestComponents.BaseTest;
import rahulshettyacadrmy.data.DataReader;
import rahulshettyacadrmy.pageobjects.CartPage;
import rahulshettyacadrmy.pageobjects.LandingPage;
import rahulshettyacadrmy.pageobjects.OrderPage;
import rahulshettyacadrmy.pageobjects.PaymentPage;
import rahulshettyacadrmy.pageobjects.ThankyouPage;
import rahulshettyacadrmy.pageobjects.productCatalogue;
import org.openqa.selenium.TakesScreenshot;

public class SubmitOrderTest extends BaseTest {

	@Test(dataProvider = "getData", groups = { "Purchase" }) // Chuyển sang test annotation vì có thể bỏ qua public
																// static void main. Nếu dùng static thì không gọi được
																// method launchApplication() trực tiếp mà phải thông
																// qua object.
	public void SubmitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		// LandingPage landingPage = launchApplication();
		// có thể lược bỏ dòng trên bằng cách thêm @BeforeMethod vào method
		// launchApplication của BaseTest class và như vậy thì trước khi thực hiện test
		// này thì method đó đã được thực hiện.
		// Khi đó, phải tạo object landingPage public tại BaseTest class thay vì chị gọi
		// nó trong method launchApplication.

		landingPage.loginApplication(input.get("email"), input.get("password"));
		// productCatalogue productCatalogue =
		// landingPage.loginApplication("thanhtu1052@gmail.com", "Thanhtu1052");
		productCatalogue productCatalogue = new productCatalogue(driver); // có thể code nhanh như sau: productCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		productCatalogue.addProduct1(input.get("productName1"));
		productCatalogue.addProduct2(input.get("productName2"));
		productCatalogue.getCartButton().click();

		Thread.sleep(1000);

		CartPage cartPage = new CartPage(driver);
		boolean match1 = cartPage.VerifyProductDisplay(input.get("productName1"));
		Assert.assertTrue(match1);
		boolean match2 = cartPage.VerifyProductDisplay(input.get("productName2"));
		Assert.assertTrue(match2);
		

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(1000);
		js.executeScript("window.scrollBy(0,500)");

		cartPage.goToPaymentPage();

		PaymentPage paymentPage = new PaymentPage(driver);
		paymentPage.typeCountry("ind");
		Boolean match3 = paymentPage.checkThePresentOfCountry("India");
		Assert.assertTrue(match2);
		paymentPage.selectCountry("India");
		paymentPage.goToThankyouPage();

		ThankyouPage thankYouPage = new ThankyouPage(driver);
		Assert.assertTrue(thankYouPage.checkThankyouText().equalsIgnoreCase("THANKYOU FOR THE ORDER"));
	}

	@Test(dataProvider = "getData", dependsOnMethods = { "SubmitOrder" })
	public void OrderHistoryTest(HashMap<String, String> input) {
		productCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		productCatalogue.goToMyOrderPage();
		OrderPage orderPage = new OrderPage(driver);
		boolean match1 = orderPage.VerifyOrderProductDisplay(input.get("productName1"));
		Assert.assertTrue(match1);
		boolean match2 = orderPage.VerifyOrderProductDisplay(input.get("productName2"));
		Assert.assertTrue(match2);
	}

// @DataProvider 
// public Object[][] getData() 
// {
	// CÁCH 1:
	// return new Object[][] {{"thanhtu1052@gmail.com","Thanhtu1052","ZARA COAT 3",
	// "ADIDAS ORIGINAL"},{"anshika@gmail.com","Iamking@000","ADIDAS
	// ORIGINAL","IPHONE 13 PRO"}};
	// Nêwu là như trên thì tại mỗi test phải define String email, String password,
	// String productName1, String productName 2. Vì việc này phải lặp đi lặp lại
	// nhiều lần đối với từng @Test một nên cần dùng HashMap để tiết kiệm thời gian
	// hơn.
	// Nếu là Object thì value có thể là String hoặc int,... đều được.

	// CÁCH 2:
//	 HashMap<String,String> map1 = new HashMap<String,String>(); //<String,String> is a pair of key-value
//	 map1.put("email", "thanhtu1052@gmail.com");
//	 map1.put("password", "Thanhtu1052");
//	 map1.put("productName1", "ZARA COAT 3");
//	 map1.put("productName2", "ADIDAS ORIGINAL");
//	 
//	 HashMap<String,String> map2 = new HashMap<String,String>();
//	 map2.put("email", "anshika@gmail.com");
//	 map2.put("password", "Iamking@000");
//	 map2.put("productName1", "ADIDAS ORIGINAL");
//	 map2.put("productName2", "IPHONE 13 PRO");
//	 
//	 return new Object[][] {{map1},{map2}};
//	 
	// }

	// CÁCH 3
//	@DataProvider
//	public List<HashMap<String, String>> getData() throws IOException {
//		List<HashMap<String, String>> data = getJsonDataToMap(
//				System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacadrmy\\data\\PurchaseOrder.json");
//		return data;
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacadrmy\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

}
