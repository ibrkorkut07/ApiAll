package tests.withPojoClass.a_Matchers;

import baseUrl.TestBaseUrls;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get09 extends TestBaseUrls {
   /*
	 When I send GET Request to
	 	  http://dummy.restapiexample.com/api/v1/employees
	 Then
		 Status code is 200
		 1) Print all ids greater than 10 on the console
		    Assert that there are 14 ids greater than 10
		 2) Print all ages less than 30 on the console
		    Assert that maximum age is 23
		 3) Print all employee names whose salaries are greater than 350000
		    Assert that Charde Marshall is one of the employees whose salary is greater than 350,000
		 4) Print all employee salaries whose ids are less than 11
		    Assert that maximum salary is 433060
	*/

}