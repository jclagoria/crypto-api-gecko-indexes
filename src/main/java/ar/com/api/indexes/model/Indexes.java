package ar.com.api.indexes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper=false)
public class Indexes extends MarketBase{
 
 @JsonProperty("market")
 private String market;

 @JsonProperty("last")
 private double last;

 @JsonProperty("is_multi_asset_composite")
 private boolean isMultiAssetComposite;

}