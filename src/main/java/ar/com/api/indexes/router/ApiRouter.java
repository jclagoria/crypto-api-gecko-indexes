package ar.com.api.indexes.router;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.http.MediaType;

import ar.com.api.indexes.handler.IndexesApiHandler;
@Configuration
public class ApiRouter {
 
 @Value("${coins.baseURL}")
 private String URL_SERVICE_API;

 @Value("${coins.healthAPI}") 
 private String URL_HEALTH_GECKO_API;

 @Value("${coins.indexesApi}") 
 private String URL_INDEXES_GECKO_API;
 
 @Bean
 public RouterFunction<ServerResponse> route(IndexesApiHandler handler) {

  return RouterFunctions
            .route()
            .GET(URL_SERVICE_API + URL_HEALTH_GECKO_API, 
                        handler::getStatusServiceCoinGecko)
            .GET(URL_SERVICE_API + URL_INDEXES_GECKO_API, 
                    RequestPredicates.accept(MediaType.APPLICATION_JSON),
                    handler::getListIndexesMarketParameters
                    )                        
            .build();

 }

}
