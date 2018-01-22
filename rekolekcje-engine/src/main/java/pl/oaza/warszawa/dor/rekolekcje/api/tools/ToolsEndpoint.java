package pl.oaza.warszawa.dor.rekolekcje.api.tools;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ToolsEndpoint {

  @GetMapping("/api/status")
  public String getStatus() {
    return "Connection successful";
  }
}
