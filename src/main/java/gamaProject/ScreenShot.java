package gamaProject;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Report;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ScreenShot {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
        ExtentSparkReporter ext = new ExtentSparkReporter("report//o.html");
        ExtentReports exr = new ExtentReports();
                      exr.attachReporter(ext);
		        ExtentTest test =  exr.createTest("TC001");
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
	test.log(Status.INFO," browser is assigned");
		driver.get("http://localhost:8888/");
	test.log(Status.INFO,"user name is entered successful");
		driver.findElement(By.name("user_name")).sendKeys("admin");
	test.log(Status.INFO, "user password is entered successfully");	
		driver.findElement(By.name("user_password")).sendKeys("admin");
	test.log(Status.INFO, "browser is loged in successfully");
	
   TakesScreenshot  tk = (TakesScreenshot)driver;
  File to= tk .getScreenshotAs(OutputType.FILE);
   File from = new File("report//Screenshot.png");
   Files.copy(to, from);
	
   driver.findElement(By.name("Login")).click();
	exr.flush();
		
	

	}

}
