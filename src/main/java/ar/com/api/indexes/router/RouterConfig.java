package ar.com.api.indexes.router;

import ar.com.api.indexes.exception.CoinGeckoDataNotFoudException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.http.MediaType;

import ar.com.api.indexes.handler.IndexesApiHandler;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

@Configuration
public class RouterConfig {
 
 @Value("${coins.baseURL}")
 private String URL_SERVICE_API;

 @Value("${coins.healthAPI}") 
 private String URL_HEALTH_GECKO_API;

 @Value("${coins.indexesApi}") 
 private String URL_INDEXES_GECKO_API;

 @Value("${coins.indexesListApi}") 
 private String URL_INDEXES_LIST_GECKO_API;
 
 @Bean
 public RouterFunction<ServerResponse> route(IndexesApiHandler handler) {

  return RouterFunctions
            .route()
            .GET(URL_SERVICE_API + URL_INDEXES_GECKO_API, 
                    RequestPredicates.accept(MediaType.APPLICATION_JSON),
                    handler::getListIndexesMarketParameters
                    )
             .GET(URL_SERVICE_API + URL_INDEXES_LIST_GECKO_API,
                        handler::getListMarketIndexesOnlyMarketIdAndIndexId)
            .build();

 }

 @Bean
 public WebExceptionHandler exceptionHandler() {
  return (ServerWebExchange exchange, Throwable ex) -> {
   if (ex instanceof CoinGeckoDataNotFoudException) {
    exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
    return exchange.getResponse().setComplete();
   }
   return Mono.error(ex);
  };
 }

}
