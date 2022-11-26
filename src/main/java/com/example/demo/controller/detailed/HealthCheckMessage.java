package com.example.demo.controller.detailed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author kunal
 *
 */
public class HealthCheckMessage {

  private String text;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate date;

  @JsonFormat(pattern = "hh:mm:ss a")
  private LocalTime time;

  @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss a")
  private LocalDateTime timestamp;

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public LocalTime getTime() {
    return time;
  }

  public void setTime(LocalTime time) {
    this.time = time;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }
}
