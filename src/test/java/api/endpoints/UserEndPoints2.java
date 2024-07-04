package api.endpoints;

import java.util.ResourceBundle;

import api.paylode.UserPaylode;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints2 {
	
	// this method is used for lode the properties file
	// to read the file we call getUrl method
	public static ResourceBundle getUrl() {
		
		ResourceBundle routes =	ResourceBundle.getBundle("config"); // lode config.properties
		return routes;
	}

	
	public static Response createUser(UserPaylode paylode) {
		String post_url = getUrl().getString("post_url");
		Response res = RestAssured.
		given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.body(paylode)
		.when()
		.post(post_url);
		
		return res;
	}
	
	public static Response getUser(String userName) {
		String get_url = getUrl().getString("get_url");
		Response res = RestAssured.
		given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.pathParam("username", userName)
		.when()
		.get(get_url);
		
		return res;
	}
	
	public static Response updateUser(UserPaylode paylode, String userName) {
		
		String put_url = getUrl().getString("put_url");
		
		Response res = RestAssured.
		given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.pathParam("username", userName)
		.body(paylode)
		.when()
		.put(put_url);
		
		return res;
	}
	
	public static Response deleteUser(String userName) {
		
		String delete_url = getUrl().getString("delete_url");
		
		Response res = RestAssured.
		given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.pathParam("username", userName)
		.when()
		.delete(delete_url);
		
		return res;
	}
}
