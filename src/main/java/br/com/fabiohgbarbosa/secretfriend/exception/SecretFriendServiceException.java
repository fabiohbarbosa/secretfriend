package br.com.fabiohgbarbosa.secretfriend.exception;

import org.springframework.http.HttpStatus;

/**
 * Service Layer App Exception
 * Created by fabio on 08/09/15.
 */
public class SecretFriendServiceException extends SecretFriendException {

    public SecretFriendServiceException(final String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}