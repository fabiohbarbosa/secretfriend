package br.com.fabiohgbarbosa.secretfriend.exception;

import org.springframework.http.HttpStatus;

/**
 * App superclass exception
 * Created by fabio on 08/09/15.
 */
public abstract class AmigoSecretoException extends RuntimeException {

    private HttpStatus httpStatus;

    public AmigoSecretoException(final String message, final HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
