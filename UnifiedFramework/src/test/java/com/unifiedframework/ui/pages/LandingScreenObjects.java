package com.unifiedframework.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.unifiedframework.libraries.BaseClass;

public class LandingScreenObjects extends BaseClass {

	private WebDriver driver;

	@FindBy(css = ".btn.frontend-button")
	private WebElement frontend;
	@FindBy(className = "ico-login")
	private WebElement login;
	@FindBy(className = "ico-register")
	private WebElement register;


	public LandingScreenObjects(WebDriver driver) {
		try {
			this.driver = driver;
			PageFactory.initElements(this.driver, this);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}

	public WebElement frontend() {
		return frontend;
	}
	
	public WebElement login() {
		return login;
	}
	
	public WebElement register() {
		return register;
	}
}
