package ar.com.api.indexes.services;

import ar.com.api.indexes.exception.external.CoinGeckoBadRequestException;
import ar.com.api.indexes.exception.external.CoinGeckoServerException;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class CoinGeckoServiceApi {

    @NotNull
    static Function<ClientResponse, Mono<? extends Throwable>> getClientResponseMonoDataException() {
        return clientResponse -> {
          throw new CoinGeckoBadRequestException(
                  clientResponse.statusCode().toString()
          );
        };
    }

    @NotNull
    static Function<ClientResponse, Mono<? extends Throwable>> getClientResponseMonoServerException() {
        return clientResponse ->
        {
            throw new CoinGeckoServerException
                    (
                            clientResponse.statusCode().toString()
                    );
        };
    }


}
