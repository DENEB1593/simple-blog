package io.dev.simpleblog.api.controller.response;

import io.dev.simpleblog.core.domain.sample.Sample;
import lombok.Builder;

@Builder
public record SampleDto(
  String name,
  String email
) {

  public static SampleDto toDto(Sample sample) {
    return SampleDto.builder()
      .name(sample.getName())
      .email(sample.getEmail())
      .build();
  }

}
