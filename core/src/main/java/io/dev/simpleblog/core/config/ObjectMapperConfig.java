package io.dev.simpleblog.core.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.ZoneId;
import java.util.TimeZone;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class ObjectMapperConfig {

  @Bean
  public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
    return builder
      .featuresToDisable(
        MapperFeature.ALLOW_FINAL_FIELDS_AS_MUTATORS,
        DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
      )
      .serializationInclusion(JsonInclude.Include.NON_NULL)
      .modules(
        new JavaTimeModule()
      )
      .build()
      .setTimeZone(TimeZone.getTimeZone(ZoneId.of("Asia/Seoul")));
  }

}
