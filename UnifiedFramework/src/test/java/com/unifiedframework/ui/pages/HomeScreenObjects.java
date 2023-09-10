package com.unifiedframework.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.unifiedframework.libraries.BaseClass;

public class HomeScreenObjects extends BaseClass {

	private WebDriver driver;

	@FindBy(className = "ico-account")
	private WebElement myAccount;

	public HomeScreenObjects(WebDriver driver) {
		try {
			this.driver = driver;
			PageFactory.initElements(this.driver, this);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}

	public WebElement myAccount() {
		return myAccount;
	}
}
