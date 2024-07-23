package de.dustinsiedner.priestly.data;

public record ProduktReport(int ProduktId, String Name, String Hersteller, int sollAnzahl, int istAnzahl, String differenz) {

        public ProduktReport {
            if (ProduktId < 0) {
                throw new IllegalArgumentException("ProduktId darf nicht negativ sein");
            }
            if (Name == null || Name.isBlank()) {
                throw new IllegalArgumentException("Name darf nicht null oder leer sein");
            }
            if (Hersteller == null || Hersteller.isBlank()) {
                throw new IllegalArgumentException("Hersteller darf nicht null oder leer sein");
            }
            if (sollAnzahl < 0) {
                throw new IllegalArgumentException("SollAnzahl darf nicht negativ sein");
            }
            if (istAnzahl < 0) {
                throw new IllegalArgumentException("IstAnzahl darf nicht negativ sein");
            }
        }
}
