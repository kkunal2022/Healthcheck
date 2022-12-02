package com.example.demo.exception;

import com.example.demo.logging.Loggable;
import com.example.demo.model.ErrorDetail;
import com.example.demo.model.ErrorMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;

/**
 * @author kunal
 *
 */
@Loggable
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ControllerExceptionHandler {
    private static final Logger logger = LogManager.getLogger(ControllerExceptionHandler.class);

    public ControllerExceptionHandler()
    {
        super();
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException,
                                                             HttpHeaders httpHeaders, HttpStatus httpStatus, HttpServletRequest httpServletRequest)
    {
        ErrorDetail errorDetailMessage = new ErrorDetail();
        errorDetailMessage.setTimestamp(String.valueOf(new Date().getTime()));
        errorDetailMessage.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetailMessage.setTitle("Resource Not Found");
        errorDetailMessage.setDetail(resourceNotFoundException.getMessage());
        errorDetailMessage.setErrorMessage(resourceNotFoundException.getClass().getName());

        return new ResponseEntity<>(errorDetailMessage, httpHeaders, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({TypeMismatchException.class})
    public ResponseEntity<Object> handleTypeMismatch(TypeMismatchException typeMismatchException, HttpHeaders httpHeaders,
                                                        HttpStatus httpStatus, WebRequest request)
    {
        ErrorDetail errorDetailMessage = new ErrorDetail();
        errorDetailMessage.setTimestamp(String.valueOf(new Date().getTime()));
        errorDetailMessage.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDetailMessage.setTitle(typeMismatchException.getPropertyName() + " Parameter Type Mismatch");
        errorDetailMessage.setDetail(typeMismatchException.getMessage());
        errorDetailMessage.setErrorMessage(request.getDescription(true));

        return new ResponseEntity<>(errorDetailMessage, httpHeaders, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({NoHandlerFoundException.class})
    public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException noHandlerFoundException, HttpHeaders httpHeaders,
                                                                   HttpStatus httpStatus, WebRequest request)
    {
        ErrorDetail errorDetailMessage = new ErrorDetail();
        errorDetailMessage.setTimestamp(String.valueOf(new Date().getTime()));
        errorDetailMessage.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetailMessage.setTitle(noHandlerFoundException.getRequestURL());
        errorDetailMessage.setDetail(request.getDescription(true));
        errorDetailMessage.setErrorMessage("Rest Handler Not Found (check for valid URI)");

        return new ResponseEntity<>(errorDetailMessage, httpHeaders, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException,
                                                                         HttpHeaders headers, HttpStatus httpStatus, WebRequest request)
    {
        ErrorDetail errorDetailMessage = new ErrorDetail();
        errorDetailMessage.setTimestamp(String.valueOf(new Date().getTime()));
        errorDetailMessage.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetailMessage.setTitle(httpRequestMethodNotSupportedException.getMethod());
        errorDetailMessage.setDetail(request.getDescription(true));
        errorDetailMessage.setErrorMessage("HTTP Method Not Valid for Endpoint (check for valid URI and proper HTTP Method)");

        return new ResponseEntity<>(errorDetailMessage, headers, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException httpMessageNotReadableException,
                                                                  HttpHeaders httpHeaders, HttpStatus httpStatus, WebRequest request)
    {
        ErrorDetail errorDetailMessage = new ErrorDetail();
        errorDetailMessage.setTimestamp(String.valueOf(new Date().getTime()));
        errorDetailMessage.setStatus(httpStatus.value());
        errorDetailMessage.setTitle("Message Not Readable");
        errorDetailMessage.setDetail(httpMessageNotReadableException.getMessage());
        errorDetailMessage.setErrorMessage(httpMessageNotReadableException.getClass().getName());

        return new ResponseEntity<>(errorDetailMessage, httpHeaders, httpStatus);
    }

    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException httpMediaTypeNotSupportedException,
                                                                     HttpHeaders httpHeaders, HttpStatus httpStatus, WebRequest request)
    {
        ErrorDetail errorDetailMessage = new ErrorDetail();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(httpMediaTypeNotSupportedException.getContentType());
        stringBuilder.append(" Media type is not supported. Supported media types are ");

        httpMediaTypeNotSupportedException.getSupportedMediaTypes().forEach(k -> stringBuilder.append(k).append(" "));

        errorDetailMessage.setTimestamp(String.valueOf(new Date().getTime()));
        errorDetailMessage.setStatus(httpStatus.value());
        errorDetailMessage.setTitle("Method not supported");
        errorDetailMessage.setDetail(stringBuilder.toString());
        errorDetailMessage.setErrorMessage(httpMediaTypeNotSupportedException.getClass().getName());

        return new ResponseEntity<>(errorDetailMessage, httpHeaders, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MissingPathVariableException.class})
    public ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException missingPathVariableException, HttpHeaders httpHeaders,
                                                               HttpStatus httpStatus, WebRequest request)
    {
        ErrorDetail errorDetailMessage = new ErrorDetail();
        errorDetailMessage.setTimestamp(String.valueOf(new Date().getTime()));
        errorDetailMessage.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDetailMessage.setTitle(missingPathVariableException.getVariableName() + " Missing Path Variable");
        errorDetailMessage.setDetail(missingPathVariableException.getMessage());
        errorDetailMessage.setErrorMessage(missingPathVariableException.getClass().getName());

        return new ResponseEntity<>(errorDetailMessage, httpHeaders, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleExceptionInternal(Exception exception, Object body, HttpHeaders httpHeaders,
                                                          HttpStatus httpStatus, WebRequest request)
    {
        ErrorDetail errorDetailMessage = new ErrorDetail();
        errorDetailMessage.setTimestamp(String.valueOf(new Date().getTime()));
        errorDetailMessage.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDetailMessage.setTitle("Internal Server Error");
        errorDetailMessage.setDetail(exception.getMessage());
        errorDetailMessage.setErrorMessage(exception.getClass().getName());

        return new ResponseEntity<>(errorDetailMessage, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*
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
    */
}
