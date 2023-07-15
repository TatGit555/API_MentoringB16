package get;

import io.restassured.RestAssured;
import org.junit.Test;

public class Currency {
    /*
    1. Defined URL / endpoint (manually):
    https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/usd/eur.json
    2. Add query string params if needed
    3. Add headers if needed
    4. Define http method (GET)
    5. SEND request
     */

    @Test
    public void usdCurrencyTest(){

        //1 define url
        RestAssured.baseURI = "https://fakestoreapi.com/products/1";
        //2 skip
        RestAssured.given().header("Accept","application/json")//3 GIVEN as in cucumber
                .when().get()               //4 WHEN action get
                .then().statusCode(200)  //5 response 200 green, also put 201 for negative test. StatusCode has assertion inside
                .log().body();              //printout




    }
}
