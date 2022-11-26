package com.example.demo.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kunal
 *
 */

@RestController
public class HealthCheckController {
	
	@GetMapping(value = "/healthcheck")
	public HealthCheckMessage healthcheck() {
		HealthCheckMessage healthCheckMessage = new HealthCheckMessage();
		healthCheckMessage.setStatus("OK");
		healthCheckMessage.setCurrentTime(LocalDateTime.parse("2018-06-06T21:59:36"));
		return healthCheckMessage;
	}

	@GetMapping(value = "/healthcheck?format=short" )
	public ResponseEntity<?> healthchecks() {
		HealthMessageStatus healthCheckMessageStatus = new HealthMessageStatus();
		healthCheckMessageStatus.setStatus("OK");
		return new ResponseEntity<>(healthCheckMessageStatus, HttpStatus.OK);
	}

	@PutMapping(value = "/healthcheck")
	public void healthcheckPut() {
		return;
	}

	@PostMapping(value = "/healthcheck")
	public void healthcheckPost() {
		return;
	}

	@DeleteMapping(value = "/healthcheck")
	public void healthcheckDelete() {
		return;
	}

}