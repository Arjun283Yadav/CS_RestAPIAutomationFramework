package api.endpoints;

import api.paylode.UserPaylode;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {

	public static Response createUser(UserPaylode paylode) {
		Response res = RestAssured.
		given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.body(paylode)
		.when()
		.post(Routes.post_url);
		
		return res;
	}
	
	public static Response getUser(String userName) {
		Response res = RestAssured.
		given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.pathParam("username", userName)
		.when()
		.get(Routes.get_url);
		
		return res;
	}
	
	public static Response updateUser(UserPaylode paylode, String userName) {
		Response res = RestAssured.
		given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.pathParam("username", userName)
		.body(paylode)
		.when()
		.put(Routes.put_url);
		
		return res;
	}
	
	public static Response deleteUser(String userName) {
		Response res = RestAssured.
		given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.pathParam("username", userName)
		.when()
		.delete(Routes.delete_url);
		
		return res;
	}
}
