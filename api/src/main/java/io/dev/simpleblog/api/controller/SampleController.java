package io.dev.simpleblog.api.controller;


import io.dev.simpleblog.core.domain.sample.Sample;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sample")
public class SampleController {

  @GetMapping
  public Sample sample() {
    return Sample.builder()
        .id("sample-id")
        .name("sample-name")
        .build();
  }

}
