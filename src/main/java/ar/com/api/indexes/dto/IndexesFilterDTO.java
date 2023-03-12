package ar.com.api.indexes.dto;

import java.util.Optional;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class IndexesFilterDTO implements IFilterDTO{

 private Optional<Integer> perPage;
 private Optional<Integer> page;

 @Override
 public String getUrlFilterString() {
  
  StringBuilder urlBuilder = new StringBuilder();

  if(perPage.isPresent())
   urlBuilder.append("?per_page=").append(perPage.get());

  String delimiterUrlStr = urlBuilder.isEmpty() ? "?" : "&";

  if(page.isPresent()) 
   urlBuilder.append(delimiterUrlStr).append("page=").append(page.get());  

  return urlBuilder.toString();
 }
 
}
