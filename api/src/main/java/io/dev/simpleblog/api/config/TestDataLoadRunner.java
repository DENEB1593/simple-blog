package io.dev.simpleblog.api.config;

import io.dev.simpleblog.api.controller.request.SampleCreate;
import io.dev.simpleblog.core.domain.sample.service.SampleService;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TestDataLoadRunner implements CommandLineRunner {

  private final SampleService sampleService;

  @Override
  public void run(String... args) {
    Stream.of(
      new SampleCreate("John", "john@email.com"),
      new SampleCreate("Jackson", "jackson@email.com"),
      new SampleCreate("Alexander", "alexander@email.com")
    )
      .map(SampleCreate::toSample)
      .forEach(sampleService::save);
  }
}
