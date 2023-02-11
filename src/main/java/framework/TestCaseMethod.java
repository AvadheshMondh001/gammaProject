package framework;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCaseMethod {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

	
	DateFormat df = new SimpleDateFormat("dd-mm-yyyy-hh-mm-ss");
	String timeStamp = df.format(new java.util.Date());

	ExtentSparkReporter esr = new ExtentSparkReporter("report//o " + timeStamp + ".html");
	ExtentReports er = new ExtentReports();
	er.attachReporter(esr);
	ExtentTest test = er.createTest("Tc001");
	WebDriverManager.chromedriver().setup();
	ChromeDriver driver = new ChromeDriver();	
	test.log(Status.INFO, "Browser is launched successfully");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20000));
	driver.get("http://localhost:8888/");
	test.log(Status.INFO, "application is started succesfully");
	
	verifyCreateLead(driver, test, "//input[@name='user_name']", "user_name", "admin");
	verifyCreateLead(driver, test, "//input[@name='user_password']", "user_password", "admin");
	verifyCreateClick(driver, "//input[@name='Login']", test);

	er.flush();
} 
	 public static WebElement getWebElement( WebDriver driver,ExtentTest test, String locatorType, String locatorType2) {
	    WebElement we=null;
	    if (locatorType.equalsIgnoreCase("xpath")) {
	    we=driver.findElement(By.xpath(locatorType));
	    }
	    else if(locatorType.equalsIgnoreCase("name")) {
	    	we=driver.findElement(By.name(locatorType));
	    }
	    	
	    	else if(locatorType.equalsIgnoreCase("id")) {
	    		we=driver.findElement(By.id(locatorType));
	    }
	    	else if(locatorType.equalsIgnoreCase("cssSelector")) {
	    		we=driver.findElement(By.cssSelector(locatorType));
	    	}
	    	else if(locatorType.equalsIgnoreCase("partialLinkText")) {
	    		we=driver.findElement(By.partialLinkText(locatorType));
	    	}
	    	else if(locatorType.equalsIgnoreCase("linkText")) {
	    		we=driver.findElement(By.linkText(locatorType));
	    	}
	    	else if(locatorType.equalsIgnoreCase("className")) {
	    		we=driver.findElement(By.className(locatorType));
	    	}
	    	else if(locatorType.equalsIgnoreCase("tagName")) {
	    		we=driver.findElement(By.tagName(locatorType));
	    	}
	    
		 return we;
	 }
	 public static void enterTextValue(WebDriver driver,ExtentTest test,String locatorValue,String locatorType,String elementName,String dataValue) {
     try {
    	 WebElement we=getWebElement(driver, test, locatorValue, locatorType);
     
	boolean st= TestCaseMethod(driver, test ,elementName);
	  if(st==true) {
		  we.sendKeys();
	  }}
	  catch(Exception e) {
     takesScreenShot();
	
	
	  
     }
	
	
	 }
	
	
	

private static boolean TestCaseMethod(WebDriver driver, ExtentTest test, String elementName) {
		// TODO Auto-generated method stub
		return false;
	}
public static void verifyCreateLead(WebDriver driver, ExtentTest test, String locator, String elementName,
		String dataValue) throws IOException {

	try {
		WebElement we = driver.findElement(By.xpath(locator));
		if (we.isDisplayed() == true) {
			test.log(Status.INFO, elementName + "webElement is visible");

			if (we.isEnabled() == true) {
				test.log(Status.INFO, elementName + "webElement is enabled" + elementName);
				we.sendKeys(dataValue);
			} else {
				test.log(Status.INFO, elementName + "webElement is not enabled" + dataValue);
			}
		} else {
			test.log(Status.INFO, elementName + "webElement is not not visible");
		}
	} catch (Exception e) {
		TakesScreenshot tss = (TakesScreenshot) driver;
		File from = tss.getScreenshotAs(OutputType.FILE);
		File to = new File("report//screenshot.png");
		Files.copy(from, to);
		test.addScreenCaptureFromPath(to.getAbsolutePath());
	}

}

public static void verifyCreateClick(WebDriver driver, String locatorValue, ExtentTest test) {

	WebElement click = driver.findElement(By.xpath(locatorValue));
	if (click.isDisplayed() == true) {
		test.log(Status.INFO, "click is performed successfully");

		if (click.isEnabled() == true) {
			test.log(Status.INFO, "click is performed successfully");
			click.click();
		} else {

			test.log(Status.INFO, "click is performed successfully");

		}
		test.log(Status.INFO, "click is performed successfully");

	}
}
}


