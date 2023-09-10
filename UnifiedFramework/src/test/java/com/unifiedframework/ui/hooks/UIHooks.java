package com.unifiedframework.ui.hooks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.unifiedframework.libraries.BaseClass;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

public class UIHooks extends BaseClass {
	
	@BeforeAll
	public static void before_or_after_all() {
		try {
			readPorpertyFile();
			initializeLog("UI");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Before
	public void setup() {
		try {
			openChrome(baseURL());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}

	@After
	public void takeScreenshot(Scenario scenario) {
		try {
			if(scenario.isFailed()) {
				final byte[] screenshot = ((TakesScreenshot) driver()).getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", "Screenshot");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		closeBrowser();
	}
	
}
