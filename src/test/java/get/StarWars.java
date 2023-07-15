package get;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojo.StarWarsPlanetsPojo;
import pojo.StarWarsPojo;

import java.util.List;

public class StarWars {

    @Test
    public void getCharacterTest(){
        Response response = RestAssured.given().accept("application/json")
                .when().get("https://swapi.dev/api/people/1")
                .then().statusCode(200).log().body().extract().response();//alt+enter to get response

        StarWarsPojo parsedResponse = response.as(StarWarsPojo.class);
        String expectedName = "Luke Skywalker";
        String actualName = parsedResponse.getName();

        Assert.assertEquals("Failed to validate name", expectedName,actualName);
    }

    @Test
    public void getPlanets(){
        Response response = RestAssured.given().accept("application/json")
                .when().get("https://swapi.dev/api/planets/1")
                .then().statusCode(200).log().body().extract().response();//alt+enter to get response

        StarWarsPlanetsPojo parsedResponse = response.as(StarWarsPlanetsPojo.class);

        String name = parsedResponse.getName();
        String population = parsedResponse.getPopulation();
        String terrain = parsedResponse.getTerrain();

        Assert.assertEquals("Tatooine", name);
        Assert.assertEquals("200000", population);
        Assert.assertEquals("desert",terrain);
    }
}
