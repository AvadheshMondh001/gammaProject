package framework;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.checkerframework.checker.calledmethods.qual.EnsuresCalledMethodsIf.List;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.ScreenshotException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;

import gamaProject.ExtentReport;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.OperaDriverManager;

public class Framework2 {
	private static final long time = 0;
//	private static final int elementName = 0;
//	private static final WebElement we = null;
	static ExtentReports ex;

	WebDriver driver;
	static ExtentTest test;

	public static void main(String[] args) throws IOException {

		Framework2 launch = new Framework2();

		launch.launchBrowser("chrome");
		launch.openURL("http://localhost:8888/");
		launch.enterTextValue("name", "user_name", "username", "admin");
		launch.enterTextValue("name", "user_password", "password", "admin");
		launch.click("Login", "name", "Login");
		launch.getInnerText("Marketing", "linkText", "Marketing");
	}

	public WebDriver launchBrowser(String Browser) throws IOException {
		try {
			if (Browser.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				test.log(Status.PASS, "browser is  launched successfully ");

			} else if (Browser.equalsIgnoreCase("fireFox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				test.log(Status.PASS, "browser is  launched successfully ");

			} else if (Browser.equalsIgnoreCase("EdgeDriver")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new EdgeDriver();
				test.log(Status.PASS, "browser is  launched successfully ");
			} else {
				test.log(Status.FAIL, "browser is  not launched ");
			}
		} catch (Exception e) {
			getScreenShot();
		}
		return driver;
	}

	public void close(String browserPage) throws IOException {
		try {
			driver.close();
			test.log(Status.PASS, "browser is  not closed successfully");
		} catch (Exception e) {
			getScreenShot();
		}
	}

	public void Maximize() throws IOException {
		try {
			driver.manage().window().maximize();
			test.log(Status.PASS, "browser is maximized successfully ");
		} catch (Exception e) {
			getScreenShot();
		}
	}

	public void minimize() throws IOException {
		try {
			driver.manage().window().minimize();
			test.log(Status.PASS, "browser is  not minimized successfully");
		} catch (Exception e) {
			getScreenShot();
		}
	}

	public void quit(String browserpage) throws IOException {
		try {
			driver.quit();
			test.log(Status.PASS, "browser is quited successfully ");
		} catch (Exception e) {
			getScreenShot();
		}
	}

	public void navigatrURL(String url) {
		driver.get(url);
		test.log(Status.PASS, url + "browser is launched successfully");
	}

	public void openURL(String url) throws IOException {
		try {
			driver.get(url);
			test.log(Status.PASS, url + "is navigated successfully");
		} catch (Exception e) {
			getScreenShot();
		}
	}

	public WebElement enterTextValue(String locatorName, String locatorvalue, String elementName, String dataValue)
			throws IOException {
		try {
			WebElement we = getWebElement(locatorvalue, locatorName, elementName);
			if (we.isDisplayed() == true) {
				test.log(Status.PASS, "element is displayed");
				if (we.isEnabled() == true) {
					test.log(Status.PASS, "element is enabled");
					we.clear();
					we.sendKeys(dataValue);
				} else {
					test.log(Status.FAIL, "element is not enabled");
				}
			} else {
				test.log(Status.FAIL, "element is not enabled");
			}

		} catch (Exception e) {
			getScreenShot();
		}
		return null;
	}

	public void click(String locatorvalue, String locatorType, String elementName) throws IOException {

		try {
			WebElement we = getWebElement(locatorvalue, locatorType, elementName);
			boolean status = checkElement(locatorvalue, locatorType, elementName);
			if (status == true) {
				we.click();
				test.log(Status.PASS, elementName + "click is done");
			} else {
				test.log(Status.FAIL, elementName + "click is not done");
			}
		} catch (Exception e) {
			getScreenShot();
		}

	}

	public boolean checkElement(String locatorvalue, String locatorType, String locatorName) {
		boolean status = false;
		WebElement we = getWebElement(locatorvalue, locatorType, locatorName);
		if (we.isDisplayed()) {
			test.log(Status.PASS, "element is displayed");
			if (we.isEnabled()) {
				test.log(Status.PASS, "element is enabled");
				status = true;
			} else {
				test.log(Status.FAIL, "element is  not visible");
			}
		} else {
			test.log(Status.FAIL, "element is  not enabled");
		}
		return status;

	}

//			private boolean checkElement(String locatorvalue, String locatorType, String locatorName) {
//		// TODO Auto-generated method stub
//		return false;
//	
//
//	(Exception e) {
//	      getScreenShot();
//		
//		}
	public WebElement getWebElement(String locatorValue, String locatorType, String elementName) {
		WebElement we = null;
		if (locatorType.equalsIgnoreCase("xpath")) {
			we = driver.findElement(By.xpath(locatorValue));
		} else if (locatorType.equalsIgnoreCase("name")) {
			we = driver.findElement(By.name(locatorValue));
		} else if (locatorType.equalsIgnoreCase("className")) {
			we = driver.findElement(By.className(locatorValue));
		} else if (locatorType.equalsIgnoreCase("id")) {
			we = driver.findElement(By.id(locatorValue));
		} else if (locatorType.equalsIgnoreCase("cssSelector")) {
			we = driver.findElement(By.cssSelector(locatorValue));
		} else if (locatorType.equalsIgnoreCase("partialLink")) {
			we = driver.findElement(By.partialLinkText(locatorValue));
		} else if (locatorType.equalsIgnoreCase("linkText")) {
			we = driver.findElement(By.linkText(locatorValue));
		} else if (locatorType.equalsIgnoreCase("tagName")) {
			we = driver.findElement(By.tagName(locatorValue));
			test.log(Status.PASS, "locator found the exact element");
		} else {
			test.log(Status.FAIL, "locator not found the exact element");

		}
		return we;
	}

//	public void inputTextValue(String value, String locatorValue, String locatorType, String elementName)
//			throws IOException {
//		try {
//			WebElement we = getWebElement(locatorValue, locatorType, elementName);
//			boolean status = checkElement(locatorValue, locatorType, elementName);
//			if (status == true) {
//				we.sendKeys(value);
//				test.log(Status.PASS, "entered value " + elementName + "is successfully");
//			} else {
//				test.log(Status.FAIL, "entered value " + elementName + "is successfully");
//			}
//		} catch (Exception e) {
//			getScreenShot();
//		}
//	}

	public String getInnerText(String locatorValue, String locatorType, String elementName) throws IOException {

		String innerText = "";
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				innerText = we.getText();
				test.log(Status.PASS, "element name - " + elementName + "is  got successfully");
			} else {
				test.log(Status.FAIL, "element name- " + elementName + "is  failed");
			}
		} catch (Exception e) {
			getScreenShot();

		}

		return innerText;
	}

	public void selectByVisibleText(String locatorValue, String locatorType, String elementName,
			String dropDownAttributeValue) throws IOException {
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				Select select = new Select(we);
				select.selectByVisibleText(dropDownAttributeValue);
				test.log(Status.PASS, elementName + "SelectValue is successful");
			} else {
				test.log(Status.FAIL, elementName + "is  failed");
			}
		} catch (Exception e) {
			getScreenShot();
		}
	}

	public void selectByValue(String locatorValue, String locatorType, String elementName, String dropDownValue)
			throws IOException {
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				Select select = new Select(we);
				select.selectByVisibleText(dropDownValue);
				test.log(Status.PASS, elementName + "SelectValue is successful");
			} else {
				test.log(Status.FAIL, elementName + "is  failed");
			}
		} catch (Exception e) {
			getScreenShot();
		}
	}

	public void selectByIndex(String locatorValue, String locatorType, String elementName, int dropDownValue)
			throws IOException {
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				Select select = new Select(we);
				select.selectByIndex(dropDownValue);
				test.log(Status.PASS, elementName + "SelectValue is successful");
			} else {
				test.log(Status.FAIL, elementName + "is  failed");
			}
		} catch (Exception e) {
			getScreenShot();
		}
	}

	public void deselectByValue(String locatorValue, String locatorType, String elementName, String dropDownValue)
			throws IOException {
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				Select select = new Select(we);
				select.deselectByValue(dropDownValue);
				test.log(Status.PASS, elementName + "SelectValue is successful");
			} else {
				test.log(Status.FAIL, elementName + "is  failed");
			}
		} catch (Exception e) {
			getScreenShot();
		}
	}

	public void deselectByIndex(String locatorValue, String locatorType, String elementName, int dropDownValue)
			throws IOException {
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				Select select = new Select(we);
				select.deselectByIndex(dropDownValue);
				test.log(Status.PASS, elementName + "SelectValue is successful");
			} else {
				test.log(Status.FAIL, elementName + "is  failed");
			}
		} catch (Exception e) {
			getScreenShot();
		}
	}

	public void selectDeselectAll(String locatorValue, String locatorType, String elementName) throws IOException {

		WebElement we = getWebElement(locatorValue, locatorType, elementName);
		try {
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				Select sl = new Select(we);
				sl.deselectAll();
				test.log(Status.INFO, elementName + "deselect all value in multiple dropdown successfully");
			} else {
				test.log(Status.FAIL, elementName + "deselect all value in multiple dropdown successfully");
			}
		} catch (Exception e) {
			getScreenShot();
		}
	}

	public String selectGetFirstSlectedOptions(String locatorValue, String locatorType, String elementName)
			throws IOException {
		String text = "";
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				Select select = new Select(we);
				text = select.getFirstSelectedOption().getText();
				test.log(Status.PASS, elementName + "SelectValue is successful");
			} else {
				test.log(Status.FAIL, elementName + "is  failed");
			}
		} catch (Exception e) {
			getScreenShot();
		}
		return text;
	}

	public String getAllSelectedOptions(String locatorValue, String locatorType, String elementName)
			throws IOException {
		String getOptions = "";
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				Select sel = new Select(we);
				java.util.List<WebElement> lst = sel.getAllSelectedOptions();

				for (int i = 0; i < lst.size(); i++) {
					getOptions = lst.get(i).getText();
					i++;
					test.log(Status.PASS, "get allSelectedOptions one by one is passed");
				}
			} else {
				test.log(Status.FAIL, "get allSelectedOptions one by one is  failed");
			}
		} catch (Exception e) {
			getScreenShot();
		}
		return getOptions;
	}

	public String selectgetOptions(String locatorValue, String locatorType, String elementName) throws IOException {
		String getOptions = "";
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				Select sel = new Select(we);
				java.util.List<WebElement> lst = sel.getAllSelectedOptions();

				for (int i = 0; i < lst.size(); i++) {
					getOptions = lst.get(i).getText();
					i++;
					test.log(Status.PASS, "get allSelectedOptions one by one is passed" + i + getOptions);
				}
			} else {

				test.log(Status.FAIL, "get allSelectedOptions one by one is  failed" + elementName + locatorValue);
			}
		} catch (Exception e) {
			getScreenShot();
		}
		return getOptions;
	}

	public void getScreenShot() throws IOException {
		TakesScreenshot tss = (TakesScreenshot) driver;
		File from = tss.getScreenshotAs(OutputType.FILE);
		File to = new File("report//screenshot.png");
		Files.copy(from, to);
		test.addScreenCaptureFromPath(to.getAbsolutePath());
	}

	public String getcurrentDate(String pattern, int chooseDate) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, chooseDate);
		Date d = c.getTime();
		DateFormat df = new SimpleDateFormat(pattern);
		String stamp = df.format(d);

		return stamp;

	}
//////-------------------ACTIONS_________CLASS---------------------////////

//------@MouseOver......................
//-----@Description it is used to  MouseOver on  dropDown........
///------@param LocatorValue.........
///-------@param locatorType............
///------@param  locatorName...........
///------@param  return  Object of actions class..........
///-------@throws IOException.........\

	public Actions actioncontextClick(String locatorValue, String locatorType, String elementName) throws IOException {
		Actions ac = null;
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				ac = new Actions(driver);
				ac.contextClick(we).perform();
				test.log(Status.PASS, elementName + "rightClick is sucessful");
			} else {
				test.log(Status.FAIL, "rightClick is not successsful");
			}
		} catch (Exception e) {
			getScreenShot();
		}
		return ac;
	}

	public void switchToWindowByTitle(String expectedTitle) {
		Set<String> set = driver.getWindowHandles();
		for (String handles : set) {
			driver.switchTo().window(handles);
			String actualTitle = driver.getTitle();
			String expTitle = expectedTitle;
			if (actualTitle.equalsIgnoreCase(expTitle))
				;
			break;

		}
	}

	public void switchToWindowByURL(String expectedUrl) {
		Set<String> set = driver.getWindowHandles();
		for (String handles : set) {
			driver.switchTo().window(handles);
			String actualUrl = driver.getCurrentUrl();
			String expUrl = expectedUrl;
			if (actualUrl.equalsIgnoreCase(expUrl)) {
				break;
			} else {
				test.log(Status.FAIL, "window is not switched");
			}
		}
	}

	public String getTitle() {
		String title = driver.getTitle();
		test.log(Status.PASS, title + "is traced successfully");
		return title;

	}

	public String getCurrentUrl() {
		String url = driver.getCurrentUrl();
		test.log(Status.INFO, url + "is traced successfully");
		return url;
	}
	// ----------------------upload file-------------------------

	public void uploadFile(String locatorValue, String locatorType, String elementName, String filePath) {
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				we.sendKeys(filePath);
				test.log(Status.PASS, elementName + "is file upload is done");
			} else {
				test.log(Status.FAIL, elementName + "file upload is not done");
			}
		} catch (Exception e) {
			try {
				getScreenShot();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			test.log(Status.FAIL, e);
		}
	}

//// i Frame---------------//
	public void switchToFrameByIndex(int indexValue, String elementName) {
		try {
			driver.switchTo().frame(indexValue);
			test.log(Status.PASS, elementName + "switch to frame successfully");
		} catch (Exception e) {
			test.log(Status.PASS, e);
		}
	}

	public void switchToFrameByString(String nameOrid, String elementName) throws IOException {
		try {
			driver.switchTo().frame(nameOrid);
			test.log(Status.PASS, elementName + "switch to frame successfully");
		} catch (Exception e) {
			getScreenShot();
			test.log(Status.FAIL, e);
		}
	}

	public void switchToFrameByWebElement(String we, String elementName) throws IOException {
		try {
			driver.switchTo().frame(we);
			test.log(Status.PASS, elementName + "switch to frame successfully");
		} catch (Exception e) {
			getScreenShot();
			test.log(Status.FAIL, e);
		}
	}

	public void switchToParentFrame(String elementName) throws IOException {
		try {
			driver.switchTo().parentFrame();
			test.log(Status.PASS, elementName + "switch to frame successfully");
		} catch (Exception e) {
			getScreenShot();
		}

	}

	public String getAlertText(String elementName) {
		String popUpValue = "";
		popUpValue = "";
		try {
			popUpValue = driver.switchTo().alert().getText();
			test.log(Status.PASS, elementName + "getText of PopUp is traced");
		} catch (Exception e) {
			test.log(Status.FAIL, elementName + "gettext of popUp is not traced");
		}
		return popUpValue;
	}

	public void acceptAlert(String elementName) {
		try {
			driver.switchTo().alert().accept();
			test.log(Status.PASS, elementName + "click on ok button is successfully");

		} catch (Exception e) {
			test.log(Status.FAIL, elementName + "click on ok button is not successfully");
		}
	}

	public void dismisAlert(String elementName) {
		try {
			driver.switchTo().alert().dismiss();
			test.log(Status.PASS, elementName + "click on cancel button is successfully");
		} catch (Exception e) {
			test.log(Status.FAIL, elementName + "click on cancel button is not successfully");
		}
	}

	public void implicitlyWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
	}

	public void explicitlyWaitVisibility(int time, WebDriver we) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOf((WebElement) we));
	}

	public void explicitlyWaitInvisibility(int time, WebElement we) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.invisibilityOf(we));
	}

	public void explicitlyWaitEementToBeClickable(long time, WebElement we) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.elementToBeClickable(we));
	}

	public void extentReport() {
		DateFormat date = new SimpleDateFormat("dd_MMM_hh--MM--ss");
		String timeStamp = date.format(new Date());

		ExtentSparkReporter esr = new ExtentSparkReporter("report//o" + timeStamp + ".html");
		ex = new ExtentReports();
		ex.attachReporter(esr);
		test = ex.createTest("TC001");
		test.log(Status.INFO, "browser is launched successfully");

	}

	public void flush() {
		ex.flush();
	}

	public String getAttributeValue(String locatorValue, String locatorType, String elementName, String attributeName)
			throws IOException {
		String attributeValue = "";
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				attributeValue = we.getAttribute(attributeValue);
				test.log(Status.INFO, elementName + "attributeValue is recieved" + "-" + attributeValue);
			} else {
				test.log(Status.FAIL, "attributeValue is recieved" + "-" + attributeValue);
			}
		} catch (Exception e) {
			getScreenShot();
		}
		return attributeValue;

	}

	public org.openqa.selenium.Dimension getSize(String locatorValue, String locatorType, String elementName)
			throws IOException {

		org.openqa.selenium.Dimension dime = null;
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				dime = we.getSize();
				int height = dime.getHeight();
				int width = dime.getWidth();
				test.log(Status.INFO, "actualHeight is traced");
			}
			test.log(Status.FAIL, "actualWidth is traced");
		} catch (Exception e) {
			getScreenShot();
		}
		return dime;

	}

	public Point getLocation(String locatorValue, String locatorType, String elementName) throws IOException {
		org.openqa.selenium.Point pnt = null;
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);

			if (st == true) {
				pnt = we.getLocation();
				int x = pnt.getX();
				int y = pnt.getY();
				test.log(Status.INFO, "x and y point is traced well");
			} else {
				test.log(Status.FAIL, "x and y point is not traced successfully");
			}

		} catch (Exception e) {
			getScreenShot();
		}
		return null;
	}

	/// -------------ACTIONS CLASS--------------------//
	public Actions actionMouseOver(String locatorValue, String locatorType, String elementName) throws IOException {
		Actions ac = null;
		try {

			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, locatorType);
			if (st == true) {
				ac = new Actions(driver);
				ac.moveToElement(we).perform();
				test.log(Status.INFO, elementName + "mouseOver is successfully");
			} else {
				test.log(Status.FAIL, elementName + "mouseOver is not successfully");
			}
		} catch (Exception e) {
			getScreenShot();
		}
		return ac;
	}

	public Actions actionDragAndDrop(WebElement drag, WebElement drop, String elementName) throws IOException {
		Actions ac = null;
		try {
			ac = new Actions(driver);
			ac.dragAndDrop(drag, drop).perform();
			test.log(Status.INFO, elementName + "element is draged successfully");
		} catch (Exception e) {
			getScreenShot();
		}
		return ac;
	}

	//////////**********this is a action without webelement************///
	
	
	public Actions actionClick(String locatorValue,String locatorType,String elementName) throws IOException {
			
	    	Actions ac=null;
	    	try {
	    	     WebElement we= getWebElement(locatorValue, locatorType, elementName);
	    	     boolean st= checkElement(locatorValue, locatorType,elementName);
	    	   if (st==true) {
	    	   ac=new Actions(driver);
	    	   ac.click(we).perform();
	    	   test.log(Status.INFO,elementName+ "click is done successfully");
	    	    }else {
               test.log(Status.INFO,elementName+ "click is not  successfully");
	    	    }
	    	   }catch(Exception e) {
	    	    	getScreenShot();
	    	    }
			return ac;
	
//	    	return ac;
	}
          
	
	///*****this is a action with webelement*************//
	
	
	public Actions actionSendKeys(String locatorValue,String locatorType,String elementName,String keysValue) throws IOException {
	      
	    	 Actions ac =null;
	    	try {
	    	WebElement we=	getWebElement(locatorValue, locatorType, elementName);
	    	boolean st=	checkElement(locatorValue, locatorType, elementName);
	    	 if(st==true) {
	    		 ac=new Actions(driver);
	    		 ac.sendKeys(we,keysValue).build().perform();
	    		 test.log(Status.INFO,elementName+ "keysvalue  is sent successfully"); 
	    	 }else {
	    		 test.log(Status.FAIL,elementName+ "keysValue is not sent successfully");
	    	 } }catch(Exception e) {
	    		 getScreenShot();
	    	 }
			return ac;
	} 
	
	   
	
	
	
	
	
} 















	
	    	 
