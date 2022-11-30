package com.example.demo.exception;

import com.example.demo.model.ErrorMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

/**
 * @author kunal
 *
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger logger = LogManager.getLogger(ControllerExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ErrorMessage httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException) {
        httpRequestMethodNotSupportedException.printStackTrace();
        logger.info("httpRequestMethodNotSupportedException - ", httpRequestMethodNotSupportedException);
        StringBuilder errorMessageMethods = new StringBuilder();
        String[] httpSupportedMethods = httpRequestMethodNotSupportedException.getSupportedMethods();
        for (String method : Objects.requireNonNull(httpSupportedMethods)) {
            errorMessageMethods.append(method);
        }
        ErrorMessage errorMessage = ErrorMessage.error("Request method " + httpRequestMethodNotSupportedException.getMethod() +
                "  not supported !" + " GET Mapping URL Wrong -  : " + errorMessageMethods + "!");

        return errorMessage;
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ErrorMessage myErrorHandler(Exception exception) {
        exception.printStackTrace();
        logger.info("myErrorHandler - ", exception);
        ErrorMessage errorMessage = ErrorMessage.error("Application Error");
        return errorMessage;
    }

    @ResponseBody
    @ExceptionHandler(value = ServletRequestBindingException.class)
    public ErrorMessage myErrorHandler(ServletRequestBindingException e) {
        e.printStackTrace();
        logger.info("Intercept binding parameter exception - ", e);
        ErrorMessage errorMessage = ErrorMessage.error(3000, "Required Parameters Are Not Passed");
        return errorMessage;
    }
}
