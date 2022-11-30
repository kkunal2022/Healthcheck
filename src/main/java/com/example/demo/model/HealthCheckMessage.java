package com.example.demo.model;

import java.time.Instant;

/**
 * @author kunal
 *
 */

public class HealthCheckMessage {

	private String status;

	private Instant currentTime;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Instant getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Instant currentTime) {
		this.currentTime = currentTime;
	}

}
