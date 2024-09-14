package rahulshettyacadrmy.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacadrmy.TestComponents.BaseTest;
import rahulshettyacadrmy.pageobjects.CartPage;
import rahulshettyacadrmy.pageobjects.LandingPage;
import rahulshettyacadrmy.pageobjects.PaymentPage;
import rahulshettyacadrmy.pageobjects.ThankyouPage;
import rahulshettyacadrmy.pageobjects.productCatalogue;

public class StepDefinitionImpl extends BaseTest {

	public LandingPage landingPage;
	public productCatalogue productCatalogue; // define public ở đây để mỗi object của method chỉ cần gọi 1 lần tại 1
												// Given method nào đó, và ở các Given method tiếp theo thì có thể reuse
												// object đó.
	public PaymentPage paymentPage;

	@Given("I landed on Ecommerce Page") // static
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();

	}

	@Given("^Logged with username (.+) and password (.+)$") // regular expresssion: pram không được define ở đây
	public void logged_with_username_and_password(String email, String password) // regular expresssion: pram không được
																					// define ở đây
	{
		productCatalogue productCatalogue = landingPage.loginApplication(email, password);

	}

	@When("^I add product (.+), (.+) to Cart$")
	public void i_add_product_to_Cart(String productName1, String productName2) {
		productCatalogue.addProduct1(productName1);
		productCatalogue.addProduct2(productName2);
	}

	@When("^Check out (.+), (.+) in Cart and submit order$")
	public void check_out_in_Cart(String productName1, String productName2) throws InterruptedException {
		productCatalogue.getCartButton().click();
		Thread.sleep(1000);
		CartPage cartPage = new CartPage(driver);
		boolean match1 = cartPage.VerifyProductDisplay(productName1);
		Assert.assertTrue(match1);
		boolean match2 = cartPage.VerifyProductDisplay(productName2);
		Assert.assertTrue(match2);

		PaymentPage paymentPage = new PaymentPage(driver);
		paymentPage.typeCountry("ind");
		Boolean match3 = paymentPage.checkThePresentOfCountry("India");
		Assert.assertTrue(match3);
		paymentPage.selectCountry("India");
		paymentPage.goToThankyouPage();
	}

	@Then("{String} message is displayed on Cofirmation Page")
	public void message_is_displayed_on_Cofirmation_Page(String string) {
		paymentPage.goToThankyouPage();
		ThankyouPage thankYouPage = new ThankyouPage(driver);
		Assert.assertTrue(thankYouPage.checkThankyouText().equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then ("{String} validation message is displayed")
	public void validation_message_is_displayed(String string)
	{
		Assert.assertEquals("Incorrect email or password", landingPage.getErrorMess());
		driver.close();
	}
	

}
