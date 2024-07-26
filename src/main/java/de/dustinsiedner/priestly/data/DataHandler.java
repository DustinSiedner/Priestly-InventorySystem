package de.dustinsiedner.priestly.data;

import de.unknownreality.dataframe.DataFrame;
import de.unknownreality.dataframe.DataRow;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DataHandler {

  private DataFrame data;

  private DataFrame currentInventory;

  private List<Produkt> recentScans = new ArrayList<>();

  public DataHandler(){
    File file = new File("src/main/resources/BeispielDaten-Priestly.csv");
    data = DataFrame.fromCSV(file, ';', true);

    currentInventory = DataFrame.fromCSV(file, ';', true);
    flushCounts(currentInventory);
    currentInventory.print();
  }

  // Handler Methoden
  public DataFrame getData(){
      return data;
  }

  public DataFrame getRowById(String id){
    DataFrame row = data.select("ProduktId", Integer.parseInt(id));
    row.print();
    return row;
  }

  public void increaseCount(String id, int value){
    DataRow row = currentInventory.select("ProduktId", Integer.parseInt(id)).getRow(0);
    System.out.println("Row: " + row.toString());
    Comparable count = row.get("Anzahl");
    row.set("Anzahl", Integer.parseInt(count.toString()) + value);
    Produkt produkt = new Produkt(
        (Integer) row.get("ProduktId"),
        row.getString("Name"),
        row.getString("Hersteller"),
        row.getDouble("Preis"),
        (Integer) row.get("Anzahl")
    );
    recentScans.add(produkt);
    currentInventory.update(row);
    printCurrentInventory();
  }

  public void increaseCount(String id){
    increaseCount(id, 1);
  }

  public List<Produkt> getDataAsObjects() {
    List<Produkt> list = new ArrayList<>();
    for (DataRow row : data) {
      list.add(new Produkt(
          (Integer) row.get("ProduktId"),
          row.getString("Name"),
          row.getString("Hersteller"),
          row.getDouble("Preis"),
          (Integer) row.get("Anzahl")
      ));
    }
    return list;
  }

  private void flushCounts(DataFrame df){
    for(int i = 0; i < data.size(); i++){
      DataRow currentRow = currentInventory.getRow(i);
      currentRow.set("Anzahl", 0);
      currentInventory.update(currentRow);
    }
  }

  public void printData(){
    data.print();
  }

  public void printCurrentInventory(){
    currentInventory.print();
  }

  public List<Produkt> getRecentScans(){
    return recentScans;
  }

  public List<ProduktReport> getInventoryReport(){
    List<ProduktReport> report = new ArrayList<>();
    List<Produkt> dataList = getDataAsObjects();
    System.out.println("DataList: " + dataList.toString());
    for(int i = 0; i < data.size(); i++){
      int sollAnzahl = dataList.get(i).Anzahl();
      int istAnzahl = (Integer) currentInventory.getRow(i).get("Anzahl");
      String differenz = String.valueOf(istAnzahl - sollAnzahl);

      boolean isNegative = differenz.startsWith("-");
      boolean isPositive = false;

      if(istAnzahl > sollAnzahl){
        differenz = "+" + differenz;
        isPositive = true;
      }
      System.out.println("Soll: " + sollAnzahl + " Ist: " + istAnzahl + " Differenz: " + differenz);
      report.add(new ProduktReport(
          (Integer) data.getRow(i).get("ProduktId"),
          data.getRow(i).get("Name").toString(),
          data.getRow(i).get("Hersteller").toString(),
          sollAnzahl,
          istAnzahl,
          differenz,
          isNegative,
          isPositive
      ));
    }
    return report;
  }
}
