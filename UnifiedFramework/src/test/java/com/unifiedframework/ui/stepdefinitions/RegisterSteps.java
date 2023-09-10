package com.unifiedframework.ui.stepdefinitions;

import org.testng.Assert;

import com.unifiedframework.libraries.BaseClass;
import com.unifiedframework.ui.pages.LandingScreenObjects;
import com.unifiedframework.ui.pages.RegisterObjects;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegisterSteps extends BaseClass {

	private LandingScreenObjects landingObj;
	private RegisterObjects registerObj;

	public RegisterSteps() {
		try {
			landingObj = new LandingScreenObjects(driver());
			registerObj= new RegisterObjects(driver());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}

	@Given("user navigates to Register screen")
	public void user_navigates_to_register_screen() {
		try {			
			waitForElementToLoad(landingObj.register(), 30);
			landingObj.register().click();
		} catch (Exception e) {
			System.out.print("Exception occurred is: "+ e.toString());
			e.printStackTrace();
			log.error(e);
		}
	}

	@When("user tries to register with valid credentials")
	public void user_tries_to_register_with_valid_credentials() {
		try {
			registerObj.register();
		} catch (Exception e) {
			System.out.print("Exception occurred: "+ e.toString());
			e.printStackTrace();
			log.error(e);
		}
	}

	@Then("verify registration is successfull")
	public void verify_registration_is_successfull() {
		try {
			String expected = "Your registration completed";
			waitForElementToLoad(registerObj.successMessage(), 30);
			String actual = registerObj.successMessage().getText();
			System.out.println(actual);
			Assert.assertEquals(expected, actual);
		} catch (Exception e) {
			System.out.print("Exception occurred is: "+ e.toString());
			e.printStackTrace();
			log.error(e);
		}
	}
}
