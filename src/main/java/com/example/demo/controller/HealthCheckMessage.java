package com.example.demo.controller;

import java.time.LocalDateTime;

/**
 * @author kunal
 *
 */

public class HealthCheckMessage {

	private String status;

	private LocalDateTime currentTime;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(LocalDateTime currentTime) {
		this.currentTime = currentTime;
	}

}
