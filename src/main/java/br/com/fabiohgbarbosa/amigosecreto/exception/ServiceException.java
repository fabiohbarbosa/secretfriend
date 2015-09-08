package br.com.fabiohgbarbosa.amigosecreto.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by fabio on 08/09/15.
 */
public class ServiceException extends AmigoSecretoException {

    public ServiceException(final String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}