package api.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.paylode.UserPaylode;
import io.restassured.response.Response;

public class UserTestCase2 {
	
	Faker faker;
	
	UserPaylode userpaylode;
	
	public static Logger logger;
	
	@BeforeClass
	public void generateTestData() {
		/// we are creating fake random data for our test cases
		
		// creating object of faker 
		faker = new Faker();
		
		// creating object of userpaylode class to set fake random data
		// in our pojo class(userpaylode class)
		
		userpaylode = new UserPaylode();
		
		userpaylode.setId(faker.idNumber().hashCode());
		userpaylode.setUsername(faker.name().username());
		userpaylode.setFirstname(faker.name().firstName());
		userpaylode.setLastname(faker.name().lastName());
		userpaylode.setEmail(faker.internet().safeEmailAddress());
		userpaylode.setPassword(faker.internet().password(7, 10));
		userpaylode.setPhone(faker.phoneNumber().cellPhone());
		
		logger = LogManager.getLogger("RestAssuredAutomationFramework");
	}

	
	@Test(priority = 1)
	public void testCreateUser() {
		
		Response response = UserEndPoints2.createUser(userpaylode);
		
		System.out.println(" Creating a user Data  ............");
		
		//// log response
		
		response.then().log().all();
		System.out.println(response.getBody().asPrettyString());
		
		//System.out.println(userpaylode.getUsername());
		
		/// validation
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		/// log 
		
				logger.info(" Create User Executed .......");
	}
	
	@Test(priority = 2)
	public void testGetUser() {
		
		Response response = UserEndPoints2.getUser(this.userpaylode.getUsername());
		
		System.out.println(" Featching a user Data  ............");
		
		//// log response
		
		response.then().log().all();
		
		/// validation
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		/// log 
		
				logger.info(" Get User Executed ......");
	}
	
	@Test(priority = 3)
	public void testUpdateUser() {
		
		userpaylode.setFirstname(faker.name().firstName());
		Response response = UserEndPoints2.updateUser(userpaylode , this.userpaylode.getUsername());
		
		//// log response
		
		response.then().log().all();
		
		/// validation
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		// Read User Data to check that FirstName is update or not
		
		System.out.println(" After Updating a user Data  ..........");
		
		Response responseUpdateUser = UserEndPoints2.getUser(this.userpaylode.getUsername());
		responseUpdateUser.then().log().all();
		
		/// log 
		
				logger.info(" Update User Executed ........");
	}

	@Test(priority = 4)
	public void testdeleteUser() {
	
	Response response = UserEndPoints2.deleteUser(this.userpaylode.getUsername());
	
	System.out.println("  Deleteing a user Data  ..................");
	
	//// log response
	
	response.then().log().all();
	
	/// validation
	
	Assert.assertEquals(response.getStatusCode(), 200);
	
	/// log 
	
			logger.info(" Delete User Executed .......");
}
	
}
