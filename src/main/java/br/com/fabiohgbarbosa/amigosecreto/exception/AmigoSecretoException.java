package br.com.fabiohgbarbosa.amigosecreto.exception;

import org.springframework.http.HttpStatus;

/**
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
