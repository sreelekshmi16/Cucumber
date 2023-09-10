package com.unifiedframework.ui.stepdefinitions;
import org.testng.Assert;

import com.unifiedframework.libraries.BaseClass;
import com.unifiedframework.ui.pages.CustomerInfoObjects;
import com.unifiedframework.ui.pages.HomeScreenObjects;
import com.unifiedframework.ui.pages.LandingScreenObjects;
import com.unifiedframework.ui.pages.LoginObjects;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps extends BaseClass {
	
	private LandingScreenObjects landingObj;
	private LoginObjects loginObj;
	private HomeScreenObjects homeObj;
	private CustomerInfoObjects customerObj;
	
	public LoginSteps() {
		try {
			log.info("Loginsteps constructor");
			landingObj = new LandingScreenObjects(driver());
			loginObj= new LoginObjects(driver());
			homeObj = new HomeScreenObjects(driver());
			customerObj = new CustomerInfoObjects(driver());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}
	
	@Given("user navigates to login screen")
	public void user_navigates_to_login_screen() {
		try {		    
			waitForElementToLoad(landingObj.login(), 30);
			landingObj.login().click();
		} catch (Exception e) {
			System.out.print("Exception occurred is: "+ e.toString());
			e.printStackTrace();
			log.error(e);
		}
	}

	@When("user tries to login with valid credentials")
	public void user_tries_to_login_with_valid_credentials() {
		try {
			loginObj.login();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}

	@Then("verify text from homescreen")
	public void verify_text_from_homescreen() throws Exception {
		String actual = "Test";
		try {
			waitForElementToLoad(homeObj.myAccount(), 30);
			homeObj.myAccount().click();
			
			waitForElementToLoad(customerObj.email(), 30);
			actual = customerObj.email().getAttribute("value");
			
			Assert.assertEquals(actual, "demoqa2@yopmail.com");
		} catch (Exception e) {
			//log.info(e.toString());
			e.printStackTrace();
			throw e;
			//log.error(e);
		}
	}
}
