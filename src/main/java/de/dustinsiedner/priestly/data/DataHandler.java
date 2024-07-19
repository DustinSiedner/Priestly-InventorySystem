package de.dustinsiedner.priestly.data;

import de.unknownreality.dataframe.DataFrame;
import de.unknownreality.dataframe.DataRow;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DataHandler {

  private DataFrame data;

  public DataHandler(){
    File file = new File("src/main/resources/BeispielDaten-Priestly.csv");
    data = DataFrame.fromCSV(file, ';', true);
    data.print();
  }

  // Handler Methoden
  public DataFrame getData(){
      return data;
  }

  public DataFrame getRowById(String id){
    DataFrame row = data.select("Produkt-Id", Integer.parseInt(id));
    row.print();
    return row;
  }

  public void increaseCount(String id, int value){
    DataRow row = data.select("Produkt-Id", Integer.parseInt(id)).getRow(0);
    Comparable count = row.get("Anzahl");
    row.set("Anzahl", Integer.parseInt(count.toString()) + value);
    data.update(row);
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
}
