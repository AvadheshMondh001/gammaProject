package gamaProject;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class WindoHandle {
	public static void main(String[] args) {
		ExtentSparkReporter reporter = new ExtentSparkReporter("avadheshsir.html");
		
		ExtentReports report = new ExtentReports();
		
		report.attachReporter(reporter);
		
//		report.setSystemInfo("Tester", "AvdheshSir");
//		report.setSystemInfo("OS", "windows10");
//		report.setSystemInfo("TestingTypes", "RegressionTesting");
		
		ExtentTest logger = report.createTest("TC001 Handling Windows");
		
	
      System.setProperty("webdriver.chrome.driver", "C:\\Users\\pc\\Desktop\\eclipse\\mywork\\mywork\\gamaProject\\chromedriver.exe");
	  ChromeDriver driver = new ChromeDriver();
	  logger.log(Status.INFO, "chrome driver has been launched");
	  
	  driver.get("http://localhost:8888/");
	  logger.log(Status.INFO, "url has been hitted");
	  String title=  driver.getTitle();
	  System.out.println(title);
	  report.flush();

	  driver.findElement(By.name("user_name")).sendKeys("admin");
	  driver.findElement(By.name("user_password")).sendKeys("admin");
	  driver.findElement(By.name("Login")).click();
	 // driver.manage().window().maximize();
	 String title1= driver.getTitle();
	 System.out.println(title1);
	// driver.manage().window().minimize();
	 driver.findElement(By.linkText("Help")).click();
	 driver.findElement(By.linkText("About Us")).click();
	 
	 driver.findElement(By.linkText("Feedback")).click();

	Set<String> handleValues= driver.getWindowHandles();
	 System.out.println(handleValues);
	 
	 for (String handleValue : handleValues) {
		 driver.switchTo().window(handleValue);
		String getTitle= driver.getTitle();
		 System.out.println(handleValue);
		 System.out.println(getTitle);
		
		 if(getTitle.equalsIgnoreCase("")) {
			 break;
		 }
		 
	}
	 
	 WebElement send= driver.findElement(By.xpath("//td[@class='dvtCellInfo']//input[@class='small']"));
	 send.clear();
	 send.sendKeys("avadhesh3549@gmail.com");
	 
	 driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys("this application only run through internet");
	
	 driver.manage().window().maximize();
    driver.findElement(By.xpath("//input[@value='Send']")).click();
	 
	report.flush();
	}
	
	

}
