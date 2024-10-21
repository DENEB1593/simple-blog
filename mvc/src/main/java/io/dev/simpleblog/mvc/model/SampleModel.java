package io.dev.simpleblog.mvc.model;

import io.dev.simpleblog.mvc.client.response.SampleResponse;
import lombok.Builder;

@Builder
public record SampleModel(String name, String email) {

  public static SampleModel toModel(SampleResponse response) {
    return SampleModel.builder()
      .name(response.name())
      .email(response.email())
      .build();
  }

}
