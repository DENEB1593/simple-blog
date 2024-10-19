package io.dev.simpleblog.mvc.controller;

import io.dev.simpleblog.mvc.client.SampleClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SampleController {

  private final SampleClient sampleClient;

  @GetMapping("/")
  public String view(Model model) {
    model.addAttribute("sample", sampleClient.getSample());
    return "sample";
  }
}
