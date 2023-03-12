package ar.com.api.indexes.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import ar.com.api.indexes.dto.IndexesFilterDTO;
import ar.com.api.indexes.model.Indexes;
import ar.com.api.indexes.model.MarketBase;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Service
@Slf4j
public class IndexesCoinGeckoApiService {

 @Value("${api.indexesApi}")
 private String URL_API_INDEXES;

 @Value("${api.indexesListApi}")
 private String URL_API_INDEXES_LIST;

 private WebClient webClient;

 public IndexesCoinGeckoApiService(WebClient wClient) {
  this.webClient = wClient;
 }

 public Flux<Indexes> getListMarketIndexesParameters(IndexesFilterDTO filterDTO){

  log.info("In service getListMarketIndexesParameters " 
           + URL_API_INDEXES 
           + filterDTO.getUrlFilterString());

  return webClient
              .get()
              .uri(URL_API_INDEXES + filterDTO.getUrlFilterString())
              .retrieve()
              .bodyToFlux(Indexes.class)
              .doOnError(throwable -> log.error("The service is unavailable!", throwable))
              .onErrorComplete();
 }

 public Flux<MarketBase> getListMarketIndexOnlyMarketIdAndIndexId() {

  log.info("In service getListMarketIndexOnlyMarketIdAndIndexId " 
           + URL_API_INDEXES_LIST);
  
  return webClient
             .get()
             .uri(URL_API_INDEXES_LIST)
             .retrieve()
             .bodyToFlux(MarketBase.class)
             .doOnError(throwable -> log.error("The service is unavailable!", throwable))
             .onErrorComplete();
 }
 
}