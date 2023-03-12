package ar.com.api.indexes.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import ar.com.api.indexes.dto.Ping;
import ar.com.api.indexes.services.CoinGeckoServiceStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
@Slf4j
public class IndexesApiHandler {
 
 private CoinGeckoServiceStatus serviceStatus;

 public Mono<ServerResponse> getStatusServiceCoinGecko(ServerRequest serverRequest) {

  log.info("In getStatusServiceCoinGecko");

  return ServerResponse
                .ok()
                .body(
                     serviceStatus.getStatusCoinGeckoService(), 
                     Ping.class);
 }

}
