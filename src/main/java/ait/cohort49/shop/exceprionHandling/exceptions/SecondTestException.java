package ait.cohort49.shop.exceprionHandling.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Sergey Bugaenko
 * {@code @date} 20.12.2024
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "This is SecondTestException")
public class SecondTestException extends RuntimeException {
    public SecondTestException(String message) {
        super(message);
    }
}
