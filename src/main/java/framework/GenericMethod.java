package framework;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GenericMethod {

	public static void main(String[] args) {

	}

	public WebDriver LaunchBrowser(String Browser, String Url) {
		WebDriverManager.chromedriver().setup();
		if (Browser.equalsIgnoreCase("chrome")) {
			ChromeDriver driver = new ChromeDriver();
			driver.get(Url);
		} else {
		}
		if (Browser.equalsIgnoreCase("Internet")) {
			InternetExplorerDriver driver = new InternetExplorerDriver();
			driver.get(Url);
		} else {
		}
		if (Browser.equalsIgnoreCase("FireFox")) {
			FirefoxDriver driver = new FirefoxDriver();
			driver.get(Url);

		} else {

		}

		if (Browser.equalsIgnoreCase("Safari")) {
			SafariDriver driver = new SafariDriver();
			driver.get(Url);
		} else {
			test.log(Status.INFO, "browser is not launched");
		}
		WebDriver driver;
		return driver;
	}

	public void getUrl(String url) {

	}

	public void enterTextBoxValue(WebDriver driver, String locatorType, String locatorValue, String elementName,
			String dataValue) {
		try {
			WebElement we = getWebElement(locatorType, locatorValue);
			boolean st = checkElement(elementName, locatorType, locatorValue);
			if (st == true) {
				we.sendKeys(dataValue);
			}
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("hi");
		}
	}
public void click(WebDriver driver,String locatorType,String  locatorValue,String ElementName) {
	try { WebElement  we= getWebElement(locatorType, locatorValue);
	boolean st= checkElement(ElementName, locatorType, locatorValue);
	if(st==true) {
		we.click();
	}
	}  catch (Exception e) {
	}
}
	
      public void selectByValue(String locatorType, String locatorValue, String elementName,
			String ValueAttributeSelect) {
		WebElement we = getWebElement(locatorType, locatorValue);
		boolean st = checkElement(elementName, locatorType, locatorValue);
		if (st == true) {
			Select selectobj = new Select(we);
			selectobj.selectByValue(ValueAttributeSelect);

		}
	}
	//////////////////////////////////////

	//public boolean checkElement(String elementName, String locatorType, String locatorValue) {
		// TODO Auto-generated method stub
//////////////	return false;
	

	public void selectByvisual(String locatorType, String locatorValue, String elementName,String visualText) {
		WebElement we = getWebElement(locatorType, locatorValue);
		boolean st = checkElement(elementName, locatorType, locatorValue);
		if (st == true) {
			Select selectobj = new Select(we);
			selectobj.selectByVisibleText(visualText);
		}
	}

	public void selectByIndex(String locatorType, String locatorValue, String elementName, int index) {
		WebElement we = getWebElement(locatorType, locatorValue);
		boolean st = checkElement(elementName, locatorType, locatorValue);
		if (st == true) {
			Select selectobj = new Select(we);
			selectobj.selectByIndex(index);
		}
	}

	public String getInnerText(WebDriver driver, String locatorType, String locatorValue, String elementName) {
		try {
			WebElement we = getWebElement(locatorType, locatorType);
			boolean st = checkElement(elementName, locatorType, locatorValue);
			if (st == true) {
				String innertext = we.getText();
				return innertext;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return "";
	}

	public WebElement getWebElement(String locatorType, String locatorValue) {
	 WebElement we=null;
	  if(locatorType.equalsIgnoreCase("xpath")) {
		  we=driver.findElement(By.xpath(locatorValue));
	  }
	  else if(locatorType.equalsIgnoreCase("name")) {
		  we.driver.findElement(By.name(locatorValue));
	  }else if(locatorType.equalsIgnoreCase("className")) {
		  we.driver.findElement(By.className(locatorValue));
	 else if(locatorType.equalsIgnoreCase("id")) {
		we.driver.findElement(By.id(locatorValue));
	else if(locatorType.equalsIgnoreCase("cssSelector")) {
		 we.driver.findElement(By.cssSelector(locatorValue)); 
   else if(locatorType.equalsIgnoreCase("partialLink")) {
		 we.driver.findElement(By.partialLinkText(locatorValue));
   else if(locatorType.equalsIgnoreCase("linkText")) {
		 we.driver.findElement(By.linkText(locatorValue));	  
   else if(locatorType.equalsIgnoreCase("tagName")) {
	     we.driver.findElement(By.tagName(locatorValue));	  
  else {
	  test.log(Status.INFO,"locator not found out");
  }
  return we;
   }
   public boolean checkElement(String elementname,String locatorType,String locatorValue) {
    boolean status=false;
   WebElement we = getWebElement(locatorType, locatorValue);
   if(we.isDisplayed()==true) {
	   test.log(Status.INFO,elementname+" is displayed");
   }if(we.isEnabled()==true) {
	   test.log(Status.INFO,elementname+"is enabled");
   }else {
	   test.log(Status.INFO,elementname+"is  not enabled");
   }else {
	   test.log(Status.INFO,elementname+"is not displayed");
	   TakesScreenshot("latest error");
	    }
   return status;
   }

public void TakesScreenshot(String FileName) {
	// TODO Auto-generated method stub
	TakesScreenshot take =(TakesScreenshot)driver;
	 File from = take.getScreenshotAs(OutputType.FILE);
	 File to =   new File(FileName + ".png");
	 Files.copy(from, to);
}
   public ExtentReports generateReports(String TestId, String fileName) {
	 DateFormat df =new SimpleDateFormat ("dd--mm--yyyy__hh--MM--ss");
	String dformet= df.format(new Date());
	ExtentSparkReporter exs = new ExtentSparkReporter("report//o."+dformet+".html");
	ExtentReports r =new ExtentReports();
	      r.attachReporter(exs);
	      ExtentTest test = r.createTest("tc001");
  
	   return r;
   }
   public void getSizeofElement(ExtentTest test,String locatorType, String locatorValue,String elementname) {
   try {
	   WebElement we = getWebElement(locatorType, locatorValue);
	   boolean st= checkElement(elementname,locatorType,locatorValue);
	   if(st==true) {
		   org.openqa.selenium.Dimension size = we.getSize();
	  double height = size.height;
	  double widgh = size.width;
	  if(height==expectedHeightvalue) {
		   test.log(Status.INFO,"height is as expected");
	  }else {
		  test.log(Status.FAIL, "height is unexpected");
	  }if(width==expectedWidthvalue) {
		  test.log(Status.INFO,"width is as expected");
	  }else {
		  test.log(Status.FAIL,"width is as expected");
	   } catch{
		   test.log(Status.FAIL,"e");
		   TakesScreenshot("getSize");
	   }
   
	   public void getLocationElement(ExtentTest test,String locatorType,String locatorValue,String elementname) {
	try {
		WebElement we = getWebElement( locatorType,locatorType);
	boolean st= checkElement(elementname, locatorType, locatorValue);
		if(st==true) {
			org.openqa.selenium.Point lo = we.getLocation();
			double X= lo.getX();
			double Y= lo.getY();
	if(X==ExpectedHeightValue)	{
		test.log(Status.PASS,"X element is as expected");
	}else {
		test.log(Status.FAIL,"X element is  not as expected");
	}if(Y==ExpectedWidthValue) {
		test.log(Status.PASS,"Y element is as expected");
	}else {
		test.log(Status.FAIL,"Y element is not as expected");
	}
		}}
	catch(Exception e) {
		test.log(Status.INFO, e);
		TakesScreenshot("getLocation");
	}
	}
		public void EA( ExtentTest test ,Object Ac1,Object Ex1,Object Ac2,Object Ex2) {
			if(Ac1==Ex1) {
				test.log(Status.PASS,"Height of Element is as expected");
			}else {
				test.log(Status.FAIL,"Height of Element is not as expected");
			}if(Ac2==Ex2) {
				test.log(Status.PASS,"width of element is as expected");
			}else {
				test.log(Status.FAIL,"width of element is not as expected");
			}
			}
	}
	   
			
		
		
		
		
		
		
		
		
		
		
		
		
	
			
		
		
	
	
	
	   
	   
	   
   
   
   
   
   
   
   
   
   
	   
   
		  
		  
   
		  
		  
		  
		  
		  
	  
	
