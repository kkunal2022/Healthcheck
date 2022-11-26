package com.example.demo.controller.detailed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kunal
 *
 */

@RestController
@RequestMapping(path = "/field-format")
public class HealthCheckMessageRestController {

	@GetMapping(path = "/message")
	public HealthCheckMessage getMessage() {
		final HealthCheckMessage healthCheckMessage = new HealthCheckMessage();
		healthCheckMessage.setText("OK");
		healthCheckMessage.setDate(LocalDate.parse("2022-11-30"));
		healthCheckMessage.setTime(LocalTime.parse("23:59:59"));
		healthCheckMessage.setTimestamp(LocalDateTime.parse("2022-11-30T23:59:59"));
		return healthCheckMessage;
	}
}
