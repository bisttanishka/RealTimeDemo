package realTimeApi.realTimeApi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APIProjectExample {
	@Test
	public void getRequest() {

		RestAssured.baseURI = "https://reqres.in";
		long startTime = System.currentTimeMillis();

		// Define the duration (1 minute = 60 seconds)
		long durationInMillis = 60 * 1000;

		// Continue hitting the API for 1 minute
		while (System.currentTimeMillis() - startTime < durationInMillis) {
			Response response = given().pathParam("mypath", "resource").when().get("api/{mypath}").then()
					.statusCode(200).body("data[1].name", equalTo("fuchsia rose")).extract().response();

			String responseBody = response.asString();
			System.out.println("Response Body: " + responseBody);

			String id = response.jsonPath().get("data[1].color").toString();
			System.out.println(id);
			System.out.println("ExtraLineAddedHere");
		}

	}
}
