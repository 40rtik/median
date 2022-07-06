package de.kremer.median.exception.message;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * Controller error message.
 *
 * @author kremer
 */
@AllArgsConstructor
@Data
public class ErrorMessage {

    /*
    Created at
     */
    private final Date date;

    /*
    Error message
     */
    private final String message;

}
