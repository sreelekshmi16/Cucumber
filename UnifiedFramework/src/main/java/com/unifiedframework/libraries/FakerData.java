package com.unifiedframework.libraries;

import java.util.Locale;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

public class FakerData {
	private Locale local = new Locale("en-IND");
	private Faker fake = new Faker(local);
	private FakeValuesService fakeService = new FakeValuesService(local, new RandomService());
	String[] testData;

	public String[] getUser() {
		try {
			testData = new String[2];
			testData[0] = fake.name().firstName();
			testData[1] = fakeService.bothify("?????####@mailinator.com");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return testData;
	}
}
