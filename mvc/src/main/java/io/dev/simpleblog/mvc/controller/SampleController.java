package io.dev.simpleblog.mvc.controller;

import io.dev.simpleblog.mvc.service.SampleApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SampleController {

  private final SampleApiService sampleApiService;

  @GetMapping("/")
  public String view(Model model) {
    model.addAttribute("samples", sampleApiService.getSamples());
    return "sample";
  }
}
