package rahulshettyacadrmy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacadrmy.pageobjects.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage; // Lúc này landingPage là null. Nhưng nếu thực hiện launchApplication method thì
									// object này sẽ có real life.

	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\rahulshettyacardrmy\\resource\\GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser") !=null?  System.getProperty("browser"):prop.getProperty("browser");
		//ternary operator: Nếu (System.getProperty("browser") !=null?) true thực hiện  (System.getProperty("browser")) và nếu false thực hiện prop.getProperty("browser");
		//!= có nghĩa là không phải null
		//String browserName = prop.getProperty("browser");
		if (browserName.contains("chrome")) { //dùng contains vì nếu dùng equalsIgnoreCase thì sẽ không touch được đến chromeheadless
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless")){
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension (1440,900)); //full screen (optional vif có khả năng test sẽ failure nếu element bị hiden)
			//lệnh full screen này có thể chạy ở cả headed mode và headless mode
		} else if (browserName.equalsIgnoreCase("firefox")) {
			// FireFox
		}

		else if (browserName.equalsIgnoreCase("edge")) {
			// gecko
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();//chỉ có thể chạy ở headed mode
		return driver;

	}

	@BeforeMethod (alwaysRun =true) //luôn run đối với mọi test dù được đặt trong group nào. Nếu không có alwaysRun thì before Method và after Mthod sẽ không được thực hiện và @test thuộc group sẽ fairlure.
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage; // return landingPage để khi gọi method launchApplication thì có thể vừa thực
							// hiện tạo object driver, vừa gọi class LandingPage luôn (thay thế cho new
							// LandingPage).
	}
	@AfterMethod (alwaysRun =true) 
	public void close (WebDriver driver) throws IOException
	{
		//driver = initializeDriver(); //không cần gọi lại driver vì driver đã có reallife sau khi thực hiện launchApplication method. Và driver đưuocj sử dụng ở đây là driver public đã được gọi ở đầu.
		driver.quit();
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read Json to String
		File jsonFile = new File(filePath);
		String jsonContent = FileUtils.readFileToString(jsonFile, StandardCharsets.UTF_8);
	    //Convert String to HashMap (Jackson Databind Deoendency) (conver String sang Java object)
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {});
		//lúc này data là một List gồm map1 và map2 được define tại json file
		return data;		
		
	}
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file =new File ("D:\\screenshot" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return "D:\\screenshot" + testCaseName + ".png";
	}
	

}
