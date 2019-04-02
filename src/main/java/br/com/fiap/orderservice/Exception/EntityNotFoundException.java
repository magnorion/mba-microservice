package br.com.fiap.orderservice.Exception;

import static br.com.fiap.orderservice.Exception.Exception.generateMessage;
import static br.com.fiap.orderservice.Exception.Exception.toMap;
import java.lang.Exception;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(Class clazz, String searchParamsMap) {
        super(generateMessage(clazz.getSimpleName(), toMap(String.class, String.class, searchParamsMap)));
    }
}
