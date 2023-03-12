package ar.com.api.indexes.utils;

import java.util.function.Function;

public enum StringToInteger implements Function<String, Integer>{
 
 INSTANCE;

 @Override
 public Integer apply(String input) {
  return Integer.valueOf(input);
 }
 
}
