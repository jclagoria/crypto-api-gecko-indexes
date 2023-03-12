package ar.com.api.indexes.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarketBase implements Serializable {
 
 @JsonProperty("name")
 private String name;

 @JsonProperty("id")
 private String id;

}
