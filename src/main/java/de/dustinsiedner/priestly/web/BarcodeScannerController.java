package de.dustinsiedner.priestly.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BarcodeScannerController {

  @GetMapping("/barcode")
  public String scanner() {
      return "scanner";
  }

  @GetMapping("/")
  public String index() {
      return "index";
  }
}
