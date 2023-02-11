package gamaProject;

import java.io.File;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtentReport {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       //  File fl =new File();
         ExtentSparkReporter ex= new ExtentSparkReporter("report\\o.html");
         ExtentReports exr = new ExtentReports();
         exr.attachReporter(ex);
       ExtentTest   print =  exr.createTest("TC001");
      WebDriverManager.chromedriver().setup();
       ChromeDriver driver = new ChromeDriver();
       print.log(Status.INFO, " browser launched is succeessful");
       driver.get("http://localhost:8888/");
       print.log(Status.INFO, "navigate url is success");
       driver.findElement(By.name("user_name")).sendKeys("admin");
       print.log(Status.INFO, "username is entered successfully");
       driver.findElement(By.name("user_password")).sendKeys("admin");
       print.log(Status.INFO,"userpassword is entered successfully");
       driver.findElement(By.name("Login")).click();
       print.log(Status.INFO,"user can log in successfully");
       
       driver.findElement(By.linkText("Help")).click();
       print.log(Status.INFO,"marketing window is opened");
       driver.findElement(By.linkText("About Us")).click();
       print.log(Status.INFO,"about us window is opened");
       driver.findElement(By.linkText("Feedback")).click();
       print.log(Status.INFO,"feedback window is opened");
       exr.flush();
       
      Set<String> handlevalue= driver.getWindowHandles();
      System.out.println(handlevalue);
      
      print.log(Status.INFO, " value is attached successfully");
     for (String string : handlevalue) {
		System.out.println(string);
		driver.switchTo().window(string);
		String titles=driver.getTitle();
		System.out.println(titles);
	if(titles.equalsIgnoreCase("")) {
      break;		
	
	}
	print.log(Status.INFO,"value is traced succeessfully");

	}
     driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys("it's a web based application");
      WebElement script = driver.findElement(By.xpath("//td[@class='dvtCellInfo']//input[@class='small']"));
       script.clear();
       script.sendKeys("avadhesh3549@gmail.com");
       print.log(Status.INFO,"sendkeys are assigned successfully");
      
	}
	
}


