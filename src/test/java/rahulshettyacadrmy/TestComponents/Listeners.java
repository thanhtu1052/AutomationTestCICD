/**
 * 
 */
package rahulshettyacadrmy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacardrmy.resource.ExtentReporterNG;

/**
 * 
 */
public class Listeners extends BaseTest implements ITestListener {

	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	//Each object creation have its own thread and its unique thread ID. If implement 2 test concurrently, 2 "test" object will be created and the test will confuse which "test" object is belong to itself.
	@Override
	public void onTestStart(ITestResult result) {
		// Được gọi khi một bài kiểm tra bắt đầu
		test = extent.createTest(result.getMethod().getMethodName()); // Tên của Test Case chính là tên method.
		//push/set this "test" object into ThreadLocal and ThreadLocal will assign unique thread id for "test" object. And from that system will know the "test" object is belong to SubmitOrderTest ở ErrorValidation.
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// Được gọi khi một bài kiểm tra thành công
		extentTest.get().log(Status.PASS, "Test Passed"); // OPTIONAL.
	}

	@Override
	public void onTestFailure(ITestResult result) {

		// Được gọi khi một bài kiểm tra thất bại
		// From result, you can hold test's information. Because driver of test has its
		// life, we can reuse driver of test for getScreenshot method.
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			// getTestClass(): get Test Class name (from xml file) where the method is
			// present and then from that name get real class from package.
			// field is part of class not method so that you cannot write like this:
			// getRealMethod().getField(driver);. getField mean getting any field which
			// having "driver"
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		extentTest.get().fail(result.getThrowable()); // Print error message in the report. //MANDATORY: this code line is very
											// important to know the reason why TC fail.
		//get() mean that which thread ID is asking for get information. the "test" object will be retrieved through get() method.
		// Screenshot,...
		String filepath = null;
		try {
			filepath = getScreenShot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); // ?
		}
		extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName()); // hiển thị tại report.

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// Được gọi khi một bài kiểm tra bị bỏ qua
		System.out.println("Test skipped: " + result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// Được gọi khi một bài kiểm tra thất bại nhưng nằm trong tỷ lệ thành công được
		// phép
		System.out.println("Test failed but within success percentage: " + result.getMethod().getMethodName());
	}

	@Override
	public void onStart(ITestContext context) {
		// Được gọi trước khi bắt đầu tất cả các bài kiểm tra trong một suite
		System.out.println("Test suite started: " + context.getSuite().getName());
	}

	@Override
	public void onFinish(ITestContext context) {
		// Được gọi sau khi tất cả các bài kiểm tra trong một suite đã hoàn tất
		extent.flush();
	}

}
