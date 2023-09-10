package com.unifiedframework.api.stepdefinitions;

import java.io.File;

import org.testng.Assert;

import com.unifiedframework.libraries.BaseClass;
import com.unifiedframework.libraries.FakerData;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.MatcherAssert.assertThat;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

public class APISteps extends BaseClass {
	private Response expectedResponse, actualResponse, deleteResponse;
	private Response tokenResponse = null;
	private int actualResponseCode = 0, expectedResponseCode = 0;
	private long actualResponseTime = 0, expectedResponseTime = 0, actualRepsonseSize = 0, expectedReposneSize = 0;
	private static String token="";
	private int id;
	private File postUsersSchema = new File("Schema/postUsersSchema.json");
	private File getUsersSchema = new File("Schema/getUsersSchema.json");
	private FakerData fakerObj;
	
	//http://restapi.adequateshop.com/swagger/ui/index
	
	public APISteps() {
		fakerObj = new FakerData();
	}
	
	public void initializeToken() {
		try {
			log.info("Entered before test api");
			
			tokenResponse = RestAssured.given().contentType(ContentType.JSON)
					//.header("Authorization", "Bearer "+token)
					.body("{"+ "\"email\":\"experion@gmail.com\","+ "\"password\":123456"+ "}")
					.when().post("http://restapi.adequateshop.com/api/authaccount/login");

			System.out.println(tokenResponse.getBody().asString());

			token = tokenResponse.path("data.Token");
			System.out.println(token);
		}catch(Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}
	
	@Given("a user submits valid get users request")
	public void a_user_submits_valid_get_users_request() {
		try {
			expectedResponseCode = 200;
			expectedReposneSize = 3000;
			expectedResponseTime = 1000;
			
			expectedResponse = RestAssured.given()
					.header("Authorization", "Bearer "+token)
					.get("http://restapi.adequateshop.com/api/users/");

			actualResponseCode = expectedResponse.getStatusCode();
			System.out.println("Response Code: " + actualResponseCode);

			actualResponseTime = expectedResponse.getTime();
			System.out.println("Time: " + actualResponseTime);

			actualRepsonseSize = expectedResponse.getBody().asByteArray().length;
			System.out.println("Size: " + actualRepsonseSize);

			System.out.println("Get Response body: " + expectedResponse.asString());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}
	
	@Given("a user submits valid create users request")
	public void a_user_submits_valid_create_users_request() {
		try {
			String[] testdata = fakerObj.getUser();
			expectedResponseCode = 201;
			expectedReposneSize = 3000;
			expectedResponseTime = 1000;
			expectedResponse = RestAssured.given()
					.contentType(ContentType.JSON)
					.body("{"+ "\""+"name\":"+"\""+ testdata[0] +"\","+"\""+"email\":"+"\""+testdata[1] +"\","+ "\""+"location\":\"USA\"}")
					.header("Authorization", "Bearer "+token)
					.post("http://restapi.adequateshop.com/api/users");

			actualResponseCode = expectedResponse.getStatusCode();
			System.out.println("Response Code: " + actualResponseCode);

			actualResponseTime = expectedResponse.getTime();
			System.out.println("Time: " + actualResponseTime);

			actualRepsonseSize = expectedResponse.getBody().asByteArray().length;
			System.out.println("Size: " + actualRepsonseSize);

			System.out.println("Post Response body: " + expectedResponse.asString());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}
	
	@Given("a user submits valid update users request")
	public void a_user_submits_valid_update_users_request() {
		try {
			String[] testdata = fakerObj.getUser();
			expectedResponseCode = 200;
			expectedReposneSize = 3000;
			expectedResponseTime = 1000;
			
			deleteResponse = RestAssured.given().contentType(ContentType.JSON)
					.header("Authorization", "Bearer "+token)
					.body("{"+ "\""+"name\":"+"\""+testdata[0] +"\","+"\""+"email\":"+"\""+testdata[1] +"\","+ "\""+"location\":\"USA\"}")
					.when().post("http://restapi.adequateshop.com/api/users");

			System.out.println("Post Response body: " + deleteResponse.asString());
			
			id = deleteResponse.path("id");
			
			String[] testdata1 = fakerObj.getUser();
			expectedResponse = RestAssured.given().contentType(ContentType.JSON)
					.header("Authorization", "Bearer "+token)
					.body("{"+ "\""+"id\":"+"\""+id+"\","+ "\""+"name\":"+"\""+testdata1[0] +"\","+"\""+"email\":"+"\""+"drsandeshrai@gmail.com\","+ "\""+"location\":\"USA\"}")
					.when().put("http://restapi.adequateshop.com/api/users/"+id);

			actualResponseCode = expectedResponse.getStatusCode();
			System.out.println("Response Code: " + actualResponseCode);

			actualResponseTime = expectedResponse.getTime();
			System.out.println("Time: " + actualResponseTime);

			actualRepsonseSize = expectedResponse.getBody().asByteArray().length;
			System.out.println("Size: " + actualRepsonseSize);

			System.out.println("Put Response body: " + expectedResponse.asString());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}
	
	@Given("a user submits valid delete users request")
	public void a_user_submits_valid_delete_users_request() {
		try {
			String[] testdata = fakerObj.getUser();
			expectedResponseCode = 200;
			expectedReposneSize = 3000;
			expectedResponseTime = 1000;
			
			deleteResponse = RestAssured.given().contentType(ContentType.JSON)
					.header("Authorization", "Bearer "+token)
					.body("{"+ "\""+"name\":"+"\""+testdata[0] +"\","+"\""+"email\":"+"\""+testdata[1] +"\","+ "\""+"location\":\"USA\"}")
					.when().post("http://restapi.adequateshop.com/api/users");

			System.out.println("Post Response body: " + deleteResponse.asString());
			
			id = deleteResponse.path("id");
			System.out.println("ID: " + id);
			
			expectedResponse = RestAssured.given()
					.header("Authorization", "Bearer "+token)
					.when().delete("http://restapi.adequateshop.com/api/Users/"+id);

			actualResponseCode = expectedResponse.getStatusCode();
			System.out.println("Response Code: " + actualResponseCode);

			actualResponseTime = expectedResponse.getTime();
			System.out.println("Time: " + actualResponseTime);

			actualRepsonseSize = expectedResponse.getBody().asByteArray().length;
			System.out.println("Size: " + actualRepsonseSize);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}
	
	@When("a success response is received")
	public void a_success_response_is_received() {
		try {
			Assert.assertEquals(actualResponseCode, expectedResponseCode);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}
	
	@Then("verify response time, size")
	public void verify_response_size_time() {
		try {
			System.out.println("Actual Time: "+ actualResponseTime + " Expected Time: "+ expectedResponseTime);
			System.out.println("Actual Size: "+ actualRepsonseSize + " Expected Size: "+ expectedReposneSize);
			Assert.assertTrue(actualResponseTime <= expectedResponseTime);
			Assert.assertTrue(actualRepsonseSize <= expectedReposneSize);			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}

	@And("^verify (.*) response schema$")
	public void verify_response_schema(String schema) {
		try {
			if(schema.equals("getUsers")) {
				assertThat(expectedResponse.getBody().asString(), matchesJsonSchema(getUsersSchema));
			}else if(schema.equals("postUsers") || schema.equals("putUsers")) {
				assertThat(expectedResponse.getBody().asString(), matchesJsonSchema(postUsersSchema));
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}
	
	@And("verify the result using get api")
	public void verify_the_result_using_get_api() {
		try {
			actualResponse = RestAssured.given()
					.header("Authorization", "Bearer "+token)
					.get("http://restapi.adequateshop.com/api/users/"+id);

			String expectedJSON = expectedResponse.asString();
			String actualJSON = actualResponse.asString();

			System.out.println("Expected Response: "+expectedResponse.asString());
			System.out.println("Actual Response: "+actualResponse.asString());

			assertThatJson(expectedJSON).whenIgnoringPaths("createdat").whenIgnoringPaths("profilepicture").isEqualTo(actualJSON);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}
}
