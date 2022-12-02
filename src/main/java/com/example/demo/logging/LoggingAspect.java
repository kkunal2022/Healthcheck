package com.example.demo.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.stream.IntStream;

/**
 * @author kunal
 * @project Healthcheck
 */
@Component
@Aspect
public class LoggingAspect {

    @Around(value = "@within(com.example.demo.logging.Loggable) || @annotation(com.example.demo.logging.Loggable)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Loggable loggableMethod = method.getAnnotation(Loggable.class);

        Loggable loggableClass = proceedingJoinPoint.getTarget().getClass().getAnnotation(Loggable.class);

        LogLevel logLevel = loggableMethod != null ? loggableMethod.value() : loggableClass.value();

        String starInspectionLog = "**********";
        LogWriter.write(proceedingJoinPoint.getTarget().getClass(), logLevel, starInspectionLog + proceedingJoinPoint.getTarget()
                        .getClass() + "." + method.getName() + "() Before Advice Point Start Execution" + starInspectionLog);

        boolean showParams = loggableMethod != null ? loggableMethod.params() : loggableClass.params();
        if (showParams) {
            if (proceedingJoinPoint.getArgs() != null && proceedingJoinPoint.getArgs().length > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                IntStream.range(0, proceedingJoinPoint.getArgs().length).forEachOrdered(i -> {
                    stringBuilder.append(method.getParameterTypes()[i].getName()).append(":").append(proceedingJoinPoint.getArgs()[i]);
                    if (i < proceedingJoinPoint.getArgs().length - 1) {
                        stringBuilder.append(", ");
                    }
                });

                LogWriter.write(proceedingJoinPoint.getTarget().getClass(), logLevel, proceedingJoinPoint.getTarget()
                                .getClass() + "." + method.getName() + "() Show Params Args " + stringBuilder);
            }
        }

        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();

        long endTime = System.currentTimeMillis();

        if (result != null) {
            boolean showResults = loggableMethod != null ? loggableMethod.result() : loggableClass.result();
            if (showResults) {
                LogWriter.write(proceedingJoinPoint.getTarget().getClass(), logLevel, proceedingJoinPoint.getTarget()
                                .getClass() + "." + method.getName() + "() Show Results : " + result);
            }
        }

        LogWriter.write(proceedingJoinPoint.getTarget()
                        .getClass(), logLevel,
                starInspectionLog + proceedingJoinPoint.getTarget()
                        .getClass() + "." + method.getName() + "() Show After Advice Point Finished Execution And Takes " + (endTime - startTime) + " millis time to execute " + starInspectionLog);

        return result;
    }
}