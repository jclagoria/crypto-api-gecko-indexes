package ar.com.api.indexes.handler;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import ar.com.api.indexes.dto.IndexesFilterDTO;
import ar.com.api.indexes.model.Indexes;
import ar.com.api.indexes.model.Ping;
import ar.com.api.indexes.services.CoinGeckoServiceStatus;
import ar.com.api.indexes.services.IndexesCoinGeckoApiService;
import ar.com.api.indexes.utils.StringToInteger;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
@Slf4j
public class IndexesApiHandler {
 
 private CoinGeckoServiceStatus serviceStatus;

 private IndexesCoinGeckoApiService serviceIndexes;

 public Mono<ServerResponse> getStatusServiceCoinGecko(ServerRequest serverRequest) {

  log.info("In getStatusServiceCoinGecko");

  return ServerResponse
                .ok()
                .body(
                     serviceStatus.getStatusCoinGeckoService(), 
                     Ping.class);
 }

 public Mono<ServerResponse> getListIndexesMarketParameters(ServerRequest sRequest){

     log.info("In getListIndexesMarket");

     Optional<Integer> perPageOpt = Optional.empty();
     Optional<Integer> pageOpt = Optional.empty();

     if(sRequest.queryParam("perPage").isPresent()) {
          perPageOpt = Optional
                         .of(
                              sRequest.queryParam("perPage")
                                   .get()
                                   .transform(StringToInteger.INSTANCE)
                                   );
     }

     if(sRequest.queryParam("page").isPresent()) {
          pageOpt = Optional
                         .of(
                              sRequest.queryParam("page")
                                   .get()
                                   .transform(StringToInteger.INSTANCE)
                                   );
     }



     IndexesFilterDTO filterDTO = IndexesFilterDTO
                                        .builder()
                                        .perPage(perPageOpt)
                                        .page(pageOpt)
                                        .build();

     return ServerResponse
                    .ok()
                    .body(
                         serviceIndexes.getListMarketIndexesParameters(filterDTO),
                          Indexes.class
                          );
 }

}
