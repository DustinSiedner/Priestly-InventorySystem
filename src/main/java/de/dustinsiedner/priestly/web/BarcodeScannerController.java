package de.dustinsiedner.priestly.web;

import de.dustinsiedner.priestly.data.DataHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BarcodeScannerController {

  DataHandler dataHandler = new DataHandler();

  @GetMapping("/barcode")
  public String scanner() {
      return "scanner";
  }

  @PostMapping("/barcode")
  public String scannerPost(@RequestParam String id, @RequestParam String anzahl) {
    System.out.println("ID: " + id + " Anzahl: " + anzahl);
    dataHandler.getRowById(id);
    return "scanner";
  }

  @GetMapping("/")
  public String index() {
      return "index";
  }
}
