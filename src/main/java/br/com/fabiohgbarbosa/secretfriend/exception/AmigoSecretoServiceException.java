package br.com.fabiohgbarbosa.secretfriend.exception;

import org.springframework.http.HttpStatus;

/**
 * Service layer App Exception
 * Created by fabio on 08/09/15.
 */
public class AmigoSecretoServiceException extends AmigoSecretoException {

    public AmigoSecretoServiceException(final String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}