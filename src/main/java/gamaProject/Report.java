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
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;

public class Report {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		File fl = new File("Vtiger.html");
		ExtentSparkReporter ex = new ExtentSparkReporter(fl);
		ExtentReports er = new ExtentReports();
		er.attachReporter(ex);
		ExtentTest extTest = er.createTest("TC001");
		ChromeDriver driver = new ChromeDriver();
		extTest.log(Status.INFO, "browser launching is success");

		driver.get("http://localhost:8888/");
		extTest.log(Status.INFO, "username is success");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		extTest.log(Status.INFO, "user password is successful");
		extTest.log(Status.INFO, "user name is executed succeeded.");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.name("Login")).click();
		extTest.log(Status.INFO,"login is suuceed");
		
		TakesScreenshot screen = (TakesScreenshot)driver;
		  File from = screen.getScreenshotAs(OutputType.FILE);
		  File to= new File("takes//screenshot.png");
		  Files.copy(from, to);
		  extTest.addScreenCaptureFromPath(to.getAbsolutePath());
		 
		  er.flush();
	}

}
