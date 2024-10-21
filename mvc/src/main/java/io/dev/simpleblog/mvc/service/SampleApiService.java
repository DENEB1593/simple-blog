package io.dev.simpleblog.mvc.service;

import io.dev.simpleblog.mvc.client.SampleClient;
import io.dev.simpleblog.mvc.model.SampleModel;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SampleApiService {

  private final SampleClient sampleClient;

  public List<SampleModel> getSamples() {
    return sampleClient.getSample().stream()
      .map(SampleModel::toModel)
      .toList();
  }

}
