package com.example.demo.logging;

import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author kunal
 * @project Healthcheck
 */
@Component
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Loggable
{
    LogLevel value() default LogLevel.DEBUG;

    boolean params() default true;

    boolean result() default true;
}
