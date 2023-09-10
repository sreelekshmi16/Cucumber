package com.unifiedframework.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.unifiedframework.libraries.BaseClass;

public class CustomerInfoObjects extends BaseClass {

	private WebDriver driver;

	@FindBy(xpath = "//input[@id='FirstName']")
	private WebElement firstName;
	@FindBy(id = "Email")
	private WebElement email;

	public CustomerInfoObjects(WebDriver driver) {
		try {
			this.driver = driver;
			PageFactory.initElements(this.driver, this);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}

	public WebElement firstName() {
		return firstName;
	}
	
	public WebElement email() {
		return email;
	}
}
