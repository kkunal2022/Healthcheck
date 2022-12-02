package com.example.demo.model;

import com.example.demo.logging.Loggable;

/**
 * @author kunal
 * @project Healthcheck
 */
@Loggable
public class ErrorDetail {
    private String title;
    private int status;
    private String detail;
    private String timestamp;
    private String errorMessage;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getErrorMessage(String name) {
        return errorMessage;
    }

    public String setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return errorMessage;
    }

    @Override
    public String toString() {
        return "ErrorDetail{" +
                "title='" + title + '\'' +
                ", status=" + status +
                ", detail='" + detail + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
