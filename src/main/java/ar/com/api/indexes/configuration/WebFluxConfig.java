package ar.com.api.indexes.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebFlux
@Slf4j
public class WebFluxConfig implements WebFluxConfigurer {
 
 @Value("${api.urlCoinGecko}")
 private String URL_GECKO_SERVICE;

 @Bean
 public WebClient getWebClient() {

  log.info("Create and return WebClient -> " + URL_GECKO_SERVICE);

  return WebClient
            .builder()
            .baseUrl(URL_GECKO_SERVICE)
            .build();
 }

}
