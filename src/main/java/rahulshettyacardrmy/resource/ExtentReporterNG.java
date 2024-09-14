package rahulshettyacardrmy.resource;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getReportObject() //vì là static nên không cần creat object để gọi method này mà dùng trưucj tiếp class ExtentReporterNG để gọi.
	{
		//Need to import 2 kind of classes: ExtentSparkReporter, ExtentReports.
		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Result of Test Case 1");
		reporter.config().setDocumentTitle("TEST DOCUMENT OF THANH TÚ");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter); //kết quả test sẽ được send vào reporter theo path
		extent.setSystemInfo("Tester", "Thanh Tú");
		extent.createTest(path);	
		return extent;
		
		
	}
	
	
}
