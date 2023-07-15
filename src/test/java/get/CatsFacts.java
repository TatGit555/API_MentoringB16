package get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class CatsFacts {

    @Test
    public void facts (){

        RestAssured.baseURI = "https://catfact.ninja";
        RestAssured.basePath = "facts?limit=100";

        Response response = RestAssured.given().accept("application/json")
                .queryParam("limit",100)
                .when().get()
                .then().statusCode(200).extract().response();

        Map<String, Object> parsedResp = response.as(new TypeRef<Map<String, Object>>() {
        });

        List<Map<String, Object>> data = (List<Map<String, Object>>) parsedResp.get("data");

        int expNumber = 100;
        int actNumber = data.size();

        Assert.assertEquals(expNumber,actNumber);

        //Homework

//        List<String> continentList = new ArrayList<>();
//        List<Integer> idList = new ArrayList<>();
//
//        for(int i = 0; i<parsedResp.size(); i++) {
//            String tempMap = parsedResp.get(i).toString();
////            System.out.println(tempMap.get("name"));
////            continentList.add((String) tempMap.get("name"));
//        }
//
//        for(int i = 0; i<parsedResp.size(); i++){
//            Map<String, Object> tempId = (Map<String, Object>) parsedResp.get(i);
//            idList.add((Integer) tempId.get("id"));
//            System.out.println(tempId.get("id"));
//        }
//        System.out.println(continentList);
//        System.out.println(idList);
//
//        Map<String, Integer> newMap = new HashMap<>();
//        for(int i = 0; i<parsedResp.size(); i++){
//            newMap.put(continentList.get(i),idList.get(i) );
//        }
//        System.out.println(newMap);

        //Task2

        List<String> lessThan50 = new ArrayList<>();
        List<String> moreThan200 = new ArrayList<>();
        List<String> nonCatFacts = new ArrayList<>();

        for(int i=0; i< data.size(); i++){
            String allDataFacts = data.get(i).get("fact").toString().toLowerCase();//just amount of symbols in string
            if(allDataFacts.length() < 50){
                lessThan50.add(allDataFacts);

            } else if (allDataFacts.length() >200) {
                moreThan200.add(allDataFacts);
            }

            else if (!allDataFacts.contains("cat")){
                nonCatFacts.add(allDataFacts);
            }
        }
        System.out.println(moreThan200 + " more than 200");
        System.out.println(lessThan50 + " less than 50");
        System.out.println(nonCatFacts+ " non cat facts");
    }
}


