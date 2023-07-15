package get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojo.ContinentPojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;

public class GameOfThrones {

    /*
    1. Defined URL / endpoint (manually):
    https://thronesapi.com/api/v2/Characters
    2. Add query string params if needed
    3. Add headers if needed
    4. Define http method (GET)
    5. SEND request
     */
    @Test
    public void getCharactersTest(){
        RestAssured.baseURI = "https://thronesapi.com/api/v2/Characters";
        given().header("Accept", "application/json")
                .when().get()
                .then().statusCode(200)
                .log().body();
    }


    @Test
    public void specificCharacterTest(){
        RestAssured.baseURI = "https://thronesapi.com/api/v2/Characters/10";
        Response response = RestAssured.given().header("Accept","application/json")
                .when().get()
                .then().statusCode(200)
                .log().body()
                .extract().response();

        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {
        }) ; //API is done here Then starts JAVA
        System.out.println(deserializedResponse);
        //{id=10, firstName=Cateyln, lastName=Stark, fullName=Catelyn Stark, title=Lady of Winterfell, family=House Stark, image=catelyn-stark.jpg, imageUrl=https://thronesapi.com/assets/images/catelyn-stark.jpg}
        //flattening means converting Json O to Map

        String firstName = (String) deserializedResponse.get("firstName");
        String lastName = String.valueOf(deserializedResponse.get("lastName"));
        Assert.assertEquals("Cateyln","Cateyln");
        Assert.assertEquals("Stark","Stark");
    }


    @Test
    public void findContinent(){
        RestAssured.baseURI = "https://thronesapi.com";
        RestAssured.basePath ="api/v2/continents";

        Response response = RestAssured.given().accept("application/json")
                .when().get()
                .then().statusCode(200).log().body().extract().response();

        List<Map<String, Object>> parsedResponse = response.as(new TypeRef<List<Map<String, Object>>>() {
        }) ;

        int actualCount = parsedResponse.size();
        int expectedCount = 4;

        Assert.assertEquals(expectedCount,actualCount);

        System.out.println("====================================");

        List <String> continentList = new ArrayList<>();
        for (int i=0; i<parsedResponse.size(); i++){
            Map<String, Object> tempMap = parsedResponse.get(i);

            System.out.println(tempMap.get("name")); //Westeros/Essos/Sothoryos/Ulthos

            continentList.add((String) tempMap.get("name"));
        }
        System.out.println(continentList);//[Westeros, Essos, Sothoryos, Ulthos]
    }

    @Test
    public void continentTest(){
        RestAssured.baseURI = "https://thronesapi.com";
        RestAssured.basePath = "api/v2/continents/0";

        Response response = RestAssured.given().accept("application/json")
                .when().get()
                .then().statusCode(200).log().body().extract().response();

        ContinentPojo parsedResponse = response.as(ContinentPojo.class);
        int id = parsedResponse.getId();
        String name = parsedResponse.getName();
        Assert.assertEquals(0, id);
        Assert.assertEquals("Westeros", name);

    }

    @Test

    public void continentsTest2(){
        RestAssured.baseURI = "https://thronesapi.com";
        RestAssured.basePath = "api/v2/continents/";

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get()
                .then().statusCode(200).log().body().extract().response();

        //POJO deserialization

        ContinentPojo[] parsedResp = response.as(ContinentPojo[].class);
        Map<String, Integer> idMap = new HashMap<>();

        for (int i=0; i< parsedResp.length; i++){

            ContinentPojo continentPojo = parsedResp[i]; //return keys
            String name = continentPojo.getName();
            int id = continentPojo.getId();
            idMap.put(name,id);

            System.out.println(idMap);
        }
    }
}
