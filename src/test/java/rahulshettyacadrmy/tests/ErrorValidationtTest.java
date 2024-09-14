package rahulshettyacadrmy.tests;

import java.io.IOException;
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
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacadrmy.TestComponents.BaseTest;
import rahulshettyacadrmy.pageobjects.CartPage;
import rahulshettyacadrmy.pageobjects.LandingPage;
import rahulshettyacadrmy.pageobjects.PaymentPage;
import rahulshettyacadrmy.pageobjects.productCatalogue;

public class ErrorValidationtTest extends BaseTest {

	@Test(groups = { "ErrorHandling" })
	// Chuyển sang test annotation vì có thể bỏ qua public static void main. Nếu
	// dùng static thì không gọi được method launchApplication() trực tiếp mà phải
	// thông qua object.
	public void LoginErrorValidation() throws IOException {
		// LandingPage landingPage = launchApplication();
		// có thể lược bỏ dòng trên bằng cách thêm @BeforeMethod vào method
		// launchApplication của BaseTest class và như vậy thì trước khi thực hiện test
		// này thì method đó đã được thực hiện.
		// Khi đó, phải tạo object landingPage public tại BaseTest class thay vì chị gọi
		// nó trong method launchApplication.

		landingPage.loginApplication("thanhtu10522@gmail.com", "Thanhtu1052");
		Assert.assertEquals("Incorrect email or password", landingPage.getErrorMess());

	}
//
//	@Test
//	public void ProductErrorValidation() {
////(...)
//	}
}
