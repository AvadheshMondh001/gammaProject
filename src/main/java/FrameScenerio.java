import java.io.IOException;

import framework.Framework2;

public class FrameScenerio {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Framework2 testCase = new Framework2();
		testCase.extentReport();

		testCase.launchBrowser("chrome");

		testCase.navigatrURL("http://localhost:8888/");
		testCase.enterTextValue("xpath", "//input[@name='user_name']", "name", "admin");
		testCase.enterTextValue("xpath", "//input[@name='user_password']", "password", "admin");
		testCase.click("Login","name","login");
		testCase.getInnerText("Marketing", "linkText", "marketing");
		testCase.click("Marketing","linkText","marketing");
		testCase.click("Leads", "linkText", "Leads");
		testCase.flush();

	}

}
