package gamaProject;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OnceMore {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
     ExtentSparkReporter exs = new  ExtentSparkReporter("report//o.html");
	 ExtentReports    exr = new ExtentReports();
	         exr.attachReporter(exs);
	         ExtentTest  test=   exr.createTest("TC001")   ;      
	WebDriverManager.chromedriver().setup();
	ChromeDriver driver =new ChromeDriver(); 
	test.log(Status.INFO, "browser is launched");
	  driver.get("http://localhost:8888/");
	
	driver.findElement(By.name("user_name")).sendKeys("admin");

	
		System.out.println("exception is handled");
	
	test.log(Status.INFO, "user name is passed");
	driver.findElement(By.name("user_password")).sendKeys("admin");
	test.log(Status.INFO,"user password is success");
	driver.findElement(By.name("Login")).click();
	test.log(Status.INFO,"login is success");
	exr.flush();
	
	}

}
