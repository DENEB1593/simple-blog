package io.dev.simpleblog.core.domain.sample.service;

import io.dev.simpleblog.core.domain.sample.Sample;
import io.dev.simpleblog.core.domain.sample.repo.SampleRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SampleService {

  private final SampleRepository sampleRepository;

  @Transactional
  public Sample save(Sample sample) {
    return sampleRepository.save(sample);
  }

  @Transactional(readOnly = true)
  public List<Sample> finaAll() {
    return sampleRepository.findAll();
  }

}
