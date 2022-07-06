package de.kremer.median.exception.handler;

import de.kremer.median.exception.MedianException;
import de.kremer.median.exception.message.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

/**
 * Hook for all controllers, to replace internal Exceptions with an error message for the client.
 *
 * @author kremer
 */
@RestControllerAdvice
public class MedianExceptionHandler {

    @ExceptionHandler(MedianException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handleMedianException(MedianException ex) {
        return new ErrorMessage(new Date(), ex.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleRuntimeException(IllegalStateException ex) {
        return new ErrorMessage(new Date(), ex.getMessage());
    }
}
