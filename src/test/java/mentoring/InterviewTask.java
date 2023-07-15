package mentoring;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utils.API_calls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static utils.API_calls.GET;

public class InterviewTask {

    private String expectedCapital = "Jerusalem";
    private String actualCapital = "";
    @Test
    public void validateCapital(){
        Response response = RestAssured.given().accept("application/json")
                .when().get("https://restcountries.com/v3.1/all")
                .then().statusCode(200).extract().response();//alt+enter to get response

        List<Map<String, Object>> parsed = response.as(new TypeRef<List<Map<String, Object>>>() {});

        List<String> capital = new ArrayList<>();

        //getting to outer map of all facts of country
        for (Map<String, Object> outerMap : parsed) {
            Map<String, Object> innerMap = (Map<String, Object>) outerMap.get("name");//all facts of NAME section

            //return value-- country
            if (innerMap.get("common").equals("Israel")) {
                System.out.println(outerMap.get("capital"));//name and capitals  [Jerusalem]

                capital = (List<String>)outerMap.get("capital");
                actualCapital = capital.get(0);
                break;
            }
        }
        System.out.println(actualCapital +" and " + expectedCapital);
        Assert.assertEquals(expectedCapital, actualCapital);
    }

    @Test

    public void validateBreakingBadQuotes(){
        RestAssured.baseURI = "https://api.breakingbadquotes.xyz";
        Response re = GET("v1/quotes/10");

        List<Map<String, Object>> parsed = re.as(new TypeRef<List<Map<String, Object>>>() {});

        for (int i=0; i<parsed.size(); i++){
            Map<String, Object> map = parsed.get(i);

            if(map.get("author").equals("Jesse Pinkman")){
                System.out.println(map.get("quote"));
            }
        }
    }
}
