package de.dustinsiedner.priestly.web;

import de.dustinsiedner.priestly.data.DataHandler;
import de.dustinsiedner.priestly.data.Produkt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BarcodeScannerController {

  DataHandler dataHandler = new DataHandler();

  @GetMapping("/barcode")
  public String scanner(Model model) {
    List<Produkt> scanList = dataHandler.getRecentScans();
    model.addAttribute("scans", scanList);
    return "scanner";
  }

  @PostMapping("/barcode")
  public String scannerPost(@RequestParam String id, @RequestParam String anzahl) {
    System.out.println("ID: " + id + " Anzahl: " + anzahl);
    dataHandler.getRowById(id);
    dataHandler.increaseCount(id, Integer.parseInt(anzahl));
    System.out.println("Anzahl erhöht");
    dataHandler.printCurrentInventory();
    return "scanner";
  }

  @GetMapping("/")
  public String index() {
      return "index";
  }

  @GetMapping("/stock-management")
  public String stockManagement(Model model) {
    List<Produkt> dataList = dataHandler.getDataAsObjects();
    model.addAttribute("stocks", dataList);
    System.out.println(dataList.toString());
    return "stock-management";
  }
}
