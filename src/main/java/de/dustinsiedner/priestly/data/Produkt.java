package de.dustinsiedner.priestly.data;

public record Produkt(int ProduktId, String Name, String Hersteller, double Preis, int Anzahl) {

    public Produkt {
        if (ProduktId < 0) {
            throw new IllegalArgumentException("ProduktId darf nicht negativ sein");
        }
        if (Name == null || Name.isBlank()) {
            throw new IllegalArgumentException("Name darf nicht null oder leer sein");
        }
        if (Hersteller == null || Hersteller.isBlank()) {
            throw new IllegalArgumentException("Hersteller darf nicht null oder leer sein");
        }
        if (Preis < 0) {
            throw new IllegalArgumentException("Preis darf nicht negativ sein");
        }
        if (Anzahl < 0) {
            throw new IllegalArgumentException("Anzahl darf nicht negativ sein");
        }
    }
}
