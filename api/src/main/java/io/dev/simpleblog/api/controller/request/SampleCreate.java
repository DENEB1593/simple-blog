package io.dev.simpleblog.api.controller.request;

import static org.springframework.util.Assert.state;

import io.dev.simpleblog.core.domain.sample.Sample;
import lombok.Builder;
import org.apache.commons.lang3.StringUtils;

@Builder
public record SampleCreate(String name, String email) {

  public Sample toSample() {
    state(StringUtils.isNotEmpty(name), "name cannot be empty");
    state(StringUtils.isNotEmpty(email), "email cannot be empty");

    return Sample.builder()
      .name(name)
      .email(email)
      .build();
  }

}
