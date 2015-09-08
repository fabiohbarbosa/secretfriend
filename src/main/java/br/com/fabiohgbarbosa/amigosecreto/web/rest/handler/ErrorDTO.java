package br.com.fabiohgbarbosa.amigosecreto.web.rest.handler;

/**
 * Envelope Error
 *
 * Created by fabio on 08/09/15.
 */
public class ErrorDTO {
    private String message;

    public ErrorDTO() {
    }

    public ErrorDTO(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }


}