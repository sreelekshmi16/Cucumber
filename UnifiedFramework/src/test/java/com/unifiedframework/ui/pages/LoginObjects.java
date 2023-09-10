package com.unifiedframework.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.unifiedframework.libraries.BaseClass;

public class LoginObjects extends BaseClass {

private WebDriver driver;
	
	@FindBy(id = "Email")
	private WebElement email;
	@FindBy(id = "Password")
	private WebElement password;
	@FindBy(xpath = "//button[contains(.,'Log in')]")
	private WebElement login;
	
	public LoginObjects(WebDriver driver) {
		try {
			this.driver = driver;
			PageFactory.initElements(this.driver, this);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}
	
	public WebElement email() {
		return email;
	}
	
	public void login() {
		try {
			waitForElementToLoad(email, 30);
			sendKeyElements(email,"demoqa2@yopmail.com");
			sendKeyElements(password,"demoqa");
			login.click();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}
}
