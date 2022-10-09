package br.com.fiap.abctechapi.handler;

import br.com.fiap.abctechapi.handler.exception.MaxAssistsException;
import br.com.fiap.abctechapi.handler.exception.MinimunAssistsRequiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(MinimunAssistsRequiredException.class)
    public ResponseEntity<ErrorMessageResponse> errorMinAssistsRequired(MinimunAssistsRequiredException exception) {
        return getErrorMessageResponseResponseEntity(HttpStatus.BAD_REQUEST, exception.getMessage(), exception.getDescription());
    }

    @ExceptionHandler(MaxAssistsException.class)
    public ResponseEntity<ErrorMessageResponse> errorMaxAssistsException(MaxAssistsException exception) {
        return getErrorMessageResponseResponseEntity(HttpStatus.BAD_REQUEST, exception.getMessage(), exception.getDescription());
    }

    private ResponseEntity<ErrorMessageResponse> getErrorMessageResponseResponseEntity(HttpStatus statusCode, String message, String description) {
        ErrorMessageResponse error = new ErrorMessageResponse();
        error.setStatusCode(statusCode.value());
        error.setTimestamp(new Date());
        error.setMessage(message);
        error.setDescription(description);

        return new ResponseEntity<ErrorMessageResponse>(error, HttpStatus.BAD_REQUEST);
    }
}
