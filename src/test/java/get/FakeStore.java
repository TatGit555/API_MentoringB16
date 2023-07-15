package get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class FakeStore {

    @Test
    public void priceVal(){
        RestAssured.baseURI = "https://fakestoreapi.com/products/1";
        Response response = RestAssured.given().header("Accept", "application/json")
                .when().get()
                .then().statusCode(200)
                .log().body()
                .extract().response();

        Map<String, Object> deserial = response.as(new TypeRef<Map<String, Object>>() {
        });
        System.out.println(deserial.get("price"));//

        double price = (double) deserial.get("price");
        Assert.assertEquals( 109.95, price, 0);

        Map <String, Object> ratingMap = (Map<String, Object>) deserial.get("rating");

        double rate = (double) ratingMap.get("rate");
        int count = (int) ratingMap.get("count");

        Assert.assertTrue(rate == 3.9);
        Assert.assertTrue(count == 120);
    }

    @Test
    public void sumOfPrices(){
        RestAssured.baseURI = "https://fakestoreapi.com/";
        RestAssured.basePath = "products/";
        Response response = RestAssured.given().header("Accept", "application/json")
                .when().get()
                .then().statusCode(200)
                .extract().response();

        List<Map<String, Object>> deserial = response.as(new TypeRef<List<Map<String, Object>>>() {
        });

        double total = 0.00;
        for( Map<String, Object> product : deserial){
            //double            //cast          //Object
            System.out.println(product.get("price"));
            double price = Double.parseDouble(product.get("price").toString());
            total += price;
        }
        System.out.println(total + "  is total price");//3240.9199999999987  is total price
        Assert.assertTrue(total>2000);
    }
}
