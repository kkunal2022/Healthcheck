/**
 * 
 */
package com.example.demo.model;

import com.example.demo.logging.Loggable;

/**
 * @author kunal
 *
 */
@Loggable
public class HealthMessageStatus {

	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
