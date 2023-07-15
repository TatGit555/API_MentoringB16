package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class API_calls {

    public static Response GET(String endPoint){
        return RestAssured.given().accept(ContentType.JSON)
                .when().get(endPoint).then().statusCode(200).log().body().extract().response();
    }
}
