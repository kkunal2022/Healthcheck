package com.example.demo.controller;

import java.time.Instant;
import com.example.demo.logging.Loggable;
import com.example.demo.model.HealthCheckMessage;
import com.example.demo.model.HealthMessageStatus;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

/**
 * @author kunal
 *
 */

@Loggable
@RestController
public class HealthCheckController {
	private static final Logger logger = LogManager.getLogger(HealthCheckController.class);

	/*
		The GetMapping URL - /healthcheck?format=short is not the correct way to define and is not correct as per the REST Standards.
		We should use @RequestParam annotation to handle query parameters in the request URL, for instance, /healthcheck/format
		with @GetMapping(value = "/healthcheck/format" , produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<HealthMessageStatus> healthQueryParam(@RequestParam(name="short") String short) { .... }
		For the @GetMapping(value = "/healthcheck") URL to work we should comment the @GetMapping(value = "/healthcheck?format=short") URL
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@GetMapping(value = "/healthcheck?format=short" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HealthMessageStatus> healthcheckGet() {
		logger.info("Inside /healthcheck?format=short GET Method ");
		HealthMessageStatus healthMessageStatus = new HealthMessageStatus();
		if (healthMessageStatus!=null) {
			healthMessageStatus.setStatus("OK");
			return new ResponseEntity<>(healthMessageStatus, HttpStatus.METHOD_NOT_ALLOWED);
		}
		//throw new HttpRequestMethodNotSupportedException(healthMessageStatus.getStatus());
		return new ResponseEntity<>(healthMessageStatus, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@GetMapping(value = "/healthcheck" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HealthCheckMessage> healthcheck() {
		logger.info("Inside /healthcheck GET Method ");
		HealthCheckMessage healthCheckMessage = new HealthCheckMessage();
		if (healthCheckMessage!=null) {
			healthCheckMessage.setStatus("OK");
			Instant healthCheckMessageInstantTime = Instant.parse("2018-06-06T21:59:36Z");
			healthCheckMessage.setCurrentTime(healthCheckMessageInstantTime);
			return new ResponseEntity<>(healthCheckMessage, HttpStatus.OK);
		}
		return new ResponseEntity<>(healthCheckMessage, HttpStatus.BAD_REQUEST);
	}

	@PutMapping(value = "/healthcheck" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> healthcheckPut() {
		logger.info("Inside /healthcheck PUT Method ");
		final HttpHeaders httpHeaders= new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<>(httpHeaders, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@PostMapping(value = "/healthcheck" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> healthcheckPost() {
		logger.info("Inside /healthcheck POST Method ");
		final HttpHeaders httpHeaders= new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<>(httpHeaders, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@DeleteMapping(value = "/healthcheck" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> healthcheckDelete() {
		logger.info("Inside /healthcheck DELETE Method ");
		final HttpHeaders httpHeaders= new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<>(httpHeaders, HttpStatus.METHOD_NOT_ALLOWED);
	}

}