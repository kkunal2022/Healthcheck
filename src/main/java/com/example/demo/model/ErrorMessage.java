package com.example.demo.model;

import com.example.demo.logging.Loggable;

/**
 * @author kunal
 *
 */
@Loggable
public class ErrorMessage {

    private Object data;

    public ErrorMessage(int code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public ErrorMessage(int code, String msg, Object object) {
        this.code = code;
        this.message = msg;
        this.data = object;
    }

    public ErrorMessage() {
    }

    public static ErrorMessage success() {
        return new ErrorMessage(0, "SUCCESS");
    }

    public static ErrorMessage success(String msg) {
        return new ErrorMessage(0, msg);
    }

    public static ErrorMessage success(String msg, Object data) {
        return new ErrorMessage(0, msg, data);
    }

    public static ErrorMessage error(int code, String msg) {
        return new ErrorMessage(code, msg);
    }

    public static ErrorMessage error(String msg) {
        return new ErrorMessage(405, msg);
    }

    private int code;
    private String message;
    private Object Data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }

    public static ErrorMessage getSuccessInstance(String message, Object data) {
        ErrorMessage result = success(message);
        result.setData(data);
        return result;
    }
}
