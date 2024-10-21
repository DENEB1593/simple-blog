package io.dev.simpleblog.api.controller;

import io.dev.simpleblog.api.controller.response.SampleDto;
import io.dev.simpleblog.core.domain.sample.service.SampleService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sample")
@RequiredArgsConstructor
public class SampleApiController {

  private final SampleService sampleService;

  @GetMapping
  public List<SampleDto> samples() {
    return sampleService.finaAll()
      .stream()
      .map(SampleDto::toDto)
      .toList();
  }
}
