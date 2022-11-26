package com.example.demo.controller;

import java.time.format.DateTimeFormatter;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;


/**
 * @author kunal
 *
 */

@Configuration
@AutoConfigureBefore({JacksonAutoConfiguration.class})
public class HealthCheckDateTime {

  @Bean
  public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperBuilderCustomizer() {
    return jacksonObjectMapperBuilder -> {
        final String dateTimeFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        jacksonObjectMapperBuilder
            .serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)))
            .deserializers(
                new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
      };
  }
}
