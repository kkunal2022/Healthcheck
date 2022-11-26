package com.example.demo.controller;

import java.time.format.DateTimeFormatter;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

/**
 * @author kunal
 *
 */

@Configuration
@AutoConfigureBefore({ JacksonAutoConfiguration.class })
public class HealthCheckDateTime {

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperBuilderCustomizer() {
		return jacksonObjectMapperBuilder -> {
			final String dateTimeFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'";
			jacksonObjectMapperBuilder
					.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)))
					.deserializers(new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
		};
	}

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperBuilderGlobalCustomizer() {
		return jacksonObjectMapperBuilder -> {
			final String dateFormat = "dd/MM/yyyy";
			final String timeFormat = "hh:mm:ss a";
			final String dateTimeFormat = "dd/MM/yyyy hh:mm:ss a";
			jacksonObjectMapperBuilder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)))
					.deserializers(new LocalDateDeserializer(DateTimeFormatter.ofPattern(dateFormat)))
					.serializers(new LocalTimeSerializer(DateTimeFormatter.ofPattern(timeFormat)))
					.deserializers(new LocalTimeDeserializer(DateTimeFormatter.ofPattern(timeFormat)))
					.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)))
					.deserializers(new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
		};
	}

}
