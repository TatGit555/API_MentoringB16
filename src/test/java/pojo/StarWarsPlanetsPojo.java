package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)  //can remove not needed parameters

public class StarWarsPlanetsPojo {
    private String name;
//    private String diameter;
//    private String climate;
//    private String gravity;
//    private String surface_water;
    private String terrain;
    private String population;
//    private List<String> films;
//    private List<String> residents;
//    private String created;
//    private String edited;
//    private String url;
}

