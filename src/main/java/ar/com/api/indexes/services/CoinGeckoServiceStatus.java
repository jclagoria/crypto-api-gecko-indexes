package ar.com.api.indexes.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import ar.com.api.indexes.model.Ping;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CoinGeckoServiceStatus {
 
 @Value("${api.ping}")        
 private String URL_PING_SERVICE;

 private WebClient webClient;

 public CoinGeckoServiceStatus(WebClient webClient) {
  this.webClient = webClient;
 }

 public Mono<Ping> getStatusCoinGeckoService() {
  
  log.info("Calling method: ", URL_PING_SERVICE); 

  return webClient
         .get()
         .uri(URL_PING_SERVICE)
         .retrieve()
         .bodyToMono(Ping.class)
         .doOnError(throwable -> log.error("The service is unavailable!", throwable));
 }

}
