package com.unifiedframework.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.unifiedframework.libraries.BaseClass;

public class RegisterObjects extends BaseClass {

	private WebDriver driver;

	@FindBy(id = "gender-male")
	private WebElement Gender;
	@FindBy(id = "FirstName")
	private WebElement FirstName;
	@FindBy(id = "LastName")
	private WebElement LastName;
	@FindBy(name = "DateOfBirthDay")
	private WebElement DateOfBirthDay;
	@FindBy(name = "DateOfBirthMonth")
	private WebElement DateOfBirthMonth;
	@FindBy(name = "DateOfBirthYear")
	private WebElement DateOfBirthYear;
	@FindBy(xpath = "//button[contains(.,'Log in')]")
	private WebElement login;
	@FindBy(id = "Email")
	private WebElement email;
	@FindBy(id = "Password")
	private WebElement password;
	@FindBy(id = "ConfirmPassword")
	private WebElement confirmPassword;
	@FindBy(id = "register-button")
	private WebElement registerbutton;
	@FindBy(className = "ico-account")
	private WebElement myaccount;
	@FindBy(className = "result")
	private WebElement successMessage;


	public RegisterObjects(WebDriver driver) {
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
	
	public WebElement successMessage() {
		return successMessage;
	}

	public void register() {
		try {
			waitForElementToLoad(FirstName, 30);
			Gender.click();
			sendKeyElements(FirstName,"demo");
			sendKeyElements(LastName,"qa");
			sendKeyElements(email,"demoqa2@yopmail.com");
			sendKeyElements(password,"demoqa");
			sendKeyElements(confirmPassword,"demoqa");
			registerbutton.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
