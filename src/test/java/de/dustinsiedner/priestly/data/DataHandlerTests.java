package de.dustinsiedner.priestly.data;

import de.unknownreality.dataframe.DataFrame;
import de.unknownreality.dataframe.DataRow;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


public class DataHandlerTests {

    static DataHandler dataHandler;
    @Test
    @DisplayName("Test 0: DataHandler kann instanziiert werden")
    public void test0(){
        DataHandler dataHandler = new DataHandler();
        assertNotNull(dataHandler.getData());
    }

    @BeforeEach
    public void setUp(){
        dataHandler = new DataHandler();
    }

    @Test
    @DisplayName("Test 1: Teste ob 'data' korrekt erzeugt wird")
    public void test1(){
        DataFrame data = dataHandler.getData();
        assertNotNull(data);
    }

    @Test
    @DisplayName("Test 2: Teste ob 'getRowById' korrekt funktioniert")
    public void test2(){
        DataFrame row = dataHandler.getRowById("1");
        assertNotNull(row);
    }

    @Test
    @DisplayName("Test 3: Teste ob bei 'getRecentScans()' eine leere Liste zurueckgegeben wird")
    public void test3(){
        assertTrue(dataHandler.getRecentScans().isEmpty());
    }

    @Test
    @DisplayName("Test 4: Teste ob bei 'getInventoryReport()' eine leere Liste zurueckgegeben wird")
    public void test4(){
        ProduktReport p1 = new ProduktReport(100330, "Aschekapsel (Polystyrol)", "Urnen-Gesch\u00e4ft", 7, 0, "-7", true, false);
        ProduktReport p2 = new ProduktReport(100784, "Kupferurne dunkel mit hellen Ornamenten", "Urnen-Gesch\u00e4ft", 10, 0, "-10", true, false);
        ProduktReport p3 = new ProduktReport(100785, "Kupferurne schwarz mit goldenen Blattelementen", "Urnen-Gesch\u00e4ft", 3, 0, "-3", true, false);
        List<ProduktReport> expected = List.of(p1, p2, p3);
        assertThat(dataHandler.getInventoryReport()).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test 5: Teste ob 'getDataAsObjects()' korrekt funktioniert")
    public void test5(){
        List<Produkt> list = dataHandler.getDataAsObjects();
        assertNotNull(list);
    }

}
