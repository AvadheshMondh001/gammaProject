package framework;

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

import io.github.bonigarcia.wdm.WebDriverManager;

public class LinearFrameWork {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ExtentSparkReporter esr = new ExtentSparkReporter("report//o.html");
		ExtentReports er = new ExtentReports();
		er.attachReporter(esr);
		ExtentTest test = er.createTest("TC001");
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		test.log(Status.INFO, "browser launching is successful");
		driver.get("http://localhost:8888/");
		test.log(Status.INFO, "url is hited successfully");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		test.log(Status.INFO, "username is entered successfully");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		test.log(Status.INFO, "password is entered successfully");
		driver.findElement(By.name("Login")).click();
		test.log(Status.INFO, "click is done successfully");
		TakesScreenshot tss = (TakesScreenshot) driver;
		File from = tss.getScreenshotAs(OutputType.FILE);
		File to = new File("report//Screenshot2.png");
		Files.copy(from, to);

		// driver.findElement(By.name("Login")).click();
		er.flush();
	}

}
