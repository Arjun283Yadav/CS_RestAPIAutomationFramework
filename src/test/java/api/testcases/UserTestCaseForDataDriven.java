package api.testcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.paylode.UserPaylode;
import io.restassured.response.Response;

public class UserTestCaseForDataDriven {

	UserPaylode userpaylode;
	
	@Test(priority = 1, dataProvider = "TestCaseData", dataProviderClass = DataProvider.class)
	public void testCreateUser(String userID, String userName, String firstName, String lastName, String email, String password, String phone) {
		
		userpaylode = new UserPaylode();
		
		userpaylode.setId(Integer.parseInt(userID));
		userpaylode.setUsername(userName);
		userpaylode.setFirstname(firstName);
		userpaylode.setLastname(lastName);
		userpaylode.setEmail(email);
		userpaylode.setPassword(password);
		userpaylode.setPhone(phone);
	
		
		
		Response response = UserEndPoints.createUser(userpaylode);
		
		System.out.println(" Creating a user Data  ............");
		
		//// log response
		
		response.then().log().all();
		System.out.println(response.getBody().asPrettyString());
		
		//System.out.println(userpaylode.getUsername());
		
		/// validation
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 2 , dataProvider = "userNameData" , dataProviderClass = DataProvider.class)
	public void testGetUser(String userName) {
		
		Response response = UserEndPoints.getUser(userName);
		
		System.out.println(" Featching a user Data  ............");
		
		//// log response
		
		response.then().log().all();
		
		/// validation
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
//	@Test(priority = 3)
//	public void testUpdateUser() {
//		
//		userpaylode.setFirstname(faker.name().firstName());
//		Response response = UserEndPoints.updateUser(userpaylode , this.userpaylode.getUsername());
//		
//		//// log response
//		
//		response.then().log().all();
//		
//		/// validation
//		
//		Assert.assertEquals(response.getStatusCode(), 200);
//		
//		// Read User Data to check that FirstName is update or not
//		
//		System.out.println(" After Updating a user Data  ..........");
//		
//		Response responseUpdateUser = UserEndPoints.getUser(this.userpaylode.getUsername());
//		responseUpdateUser.then().log().all();
//	}

	
	@Test(priority = 4, dataProvider = "userNameData", dataProviderClass = DataProvider.class)
	public void testdeleteUser(String userName) {
	
	Response response = UserEndPoints.deleteUser(userName);
	
	System.out.println("  Deleteing a user Data  ..................");
	
	//// log response
	
	response.then().log().all();
	
	/// validation
	
	Assert.assertEquals(response.getStatusCode(), 200);
}
	
}
