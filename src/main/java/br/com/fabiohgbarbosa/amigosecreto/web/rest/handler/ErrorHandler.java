package br.com.fabiohgbarbosa.amigosecreto.web.rest.handler;

import br.com.fabiohgbarbosa.amigosecreto.exception.AmigoSecretoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fabio on 08/09/15.
 */
@ControllerAdvice
public class ErrorHandler {

    /**
     * Handle exception in Service Layer
     * @param e Exception
     * @return Custom JSON Envelope Error
     */
    @ExceptionHandler(AmigoSecretoException.class)
    @ResponseBody
    public ResponseEntity<ErrorDTO> handle(AmigoSecretoException e) {
        ErrorDTO errorDTO = new ErrorDTO(e.getMessage());
        return new ResponseEntity<ErrorDTO>(errorDTO, e.getHttpStatus());
    }


    /**
     * Handle exception of Hibernate Validator in RestControllers
     * @param e Exception
     * @return Custom JSON Envelope Error
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ErrorDTO> handle(MethodArgumentNotValidException e) {

        BindingResult result = e.getBindingResult();
        StringBuilder buffer = new StringBuilder();

        // get field errors
        for (FieldError fieldError : result.getFieldErrors()) {
            buffer.append(fieldError.getDefaultMessage());
            buffer.append(", ");
        }
        buffer.deleteCharAt(buffer.length()-2); //remove last comma

        ErrorDTO errorDTO = new ErrorDTO(buffer.toString());
        return new ResponseEntity<ErrorDTO>(errorDTO, HttpStatus.BAD_REQUEST);
    }

}