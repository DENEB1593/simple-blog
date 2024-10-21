package io.dev.simpleblog.mvc.client;

import io.dev.simpleblog.mvc.client.response.SampleResponse;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Slf4j
@Component
public class SampleClient {

  private final RestClient restClient;

  public SampleClient() {
    this.restClient = RestClient.builder()
      .baseUrl("http://localhost:8080")
      .build();
  }

  public List<SampleResponse> getSample() {
    return restClient
      .get()
      .uri("/api/v1/sample")
      .accept(MediaType.APPLICATION_JSON)
      .retrieve()
      .onStatus(HttpStatusCode::is5xxServerError, ((request, response) -> {
        LOG.error("sample api request error - message: {}", response.getStatusCode());
      }))
      .body(new ParameterizedTypeReference<>() {
      });
  }
}
