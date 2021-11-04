package picsartAssignment;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

import javax.lang.model.SourceVersion;

import org.everit.json.schema.Schema;
import org.everit.json.schema.*;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.*;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.ValidationMessage;

import Utility.RetryAnalyzer;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestAssuredTest {
	private static final Logger LOGGER = Logger.getLogger("RestAssuredTest.class");
	
	@Test(retryAnalyzer = Utility.RetryAnalyzer.class)
 public void verifyRestAssuredMethod() throws ParseException, Exception, Exception {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://api.picsart.com";
		Response response=RestAssured.given().header("Content-Type","application/json")
				  .when().get("/localizations/en/messages?project=reusable_components,photo_editor")
				  .then().contentType(ContentType.JSON).extract().response();
		LOGGER.info("\n***Status Code***: " + response.getStatusCode());
		assertTrue(response.getStatusCode()==200,"Response should be successful");
		LOGGER.info("\n***Response body***: " + response.getBody().asString());
		  
		  JSONParser parser = new JSONParser(); 
		  JSONObject object = (JSONObject)  parser.parse(response.getBody().asString());
		 /* 
		  Set keys=object.keySet(); Iterator itr=keys.iterator();
		  
		  while(itr.hasNext()) {
		  System.out.println("KEYNAME  :"+itr.next()+"        VALUE :"+object.get(itr.
		  next())); }
		 */
		
		
		ObjectMapper objectMapper = new ObjectMapper(); 
		JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V201909);  
        String schemaStr = response.getBody().asString();
		 
		  JsonNode json = objectMapper.readTree(response.getBody().asString());  
		  JsonSchema schema = schemaFactory.getSchema(schemaStr);
		  Set<ValidationMessage> validationResult = schema.validate( json );  
		  
		  assertTrue(validationResult.isEmpty(),"There is no Validation Error");
			}

}
