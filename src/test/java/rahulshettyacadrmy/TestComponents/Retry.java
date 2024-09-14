package rahulshettyacadrmy.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

//HANDLE FLAKY TEST IN OUR FRAMEWORK
public class Retry implements IRetryAnalyzer {
 int count = 0;
 int maxTry =2;
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(count<maxTry)
		{
			count++;
			return true; //để thực hiện vòng lặp count+1
		}
		return false;
	}

}

//Thêm vào test cần retry (không thể declare tại xml file như ITestListener): @Test(retryAnalyzer = Retry.class)
