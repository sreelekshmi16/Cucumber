package com.unifiedframework.api.hooks;

import com.unifiedframework.api.stepdefinitions.APISteps;
import com.unifiedframework.libraries.BaseClass;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

public class APIHooks extends BaseClass {

private APISteps apiSteps;
	
	public APIHooks() {
		apiSteps = new APISteps();
	}
	
	@BeforeAll
	public static void before_or_after_all() {
		readPorpertyFile();
		initializeLog("API");
	}	

	@Before
	public void setup() {
		apiSteps.initializeToken();
	}
}
