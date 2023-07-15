package pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class PetStorePojo {
    private int id;
    private String name;
    private List<String> photoUrls;
    private List<Object> tags;
    private String status;
    private PetStoreCategoryPojo category; //json object another pojo class
}
