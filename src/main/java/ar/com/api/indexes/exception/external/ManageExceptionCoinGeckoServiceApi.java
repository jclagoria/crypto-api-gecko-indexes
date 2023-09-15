package ar.com.api.indexes.exception.external;

import ar.com.api.indexes.exception.ServiceException;

public class ManageExceptionCoinGeckoServiceApi {

    public static void throwServiceException(Throwable throwable) {
        throw new
                ServiceException(
                    throwable.getMessage(),
                    throwable.fillInStackTrace()
        );
    }



}
