import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AZCMedewerkerTest {
    private AZCMedewerker azcMedewerker;
    private AZC azc;

    @Before
    public void setUp() {
        azc = new AZC("AZC Amsterdam 1", "Straat 1", DataSeeder.gemeentes.get(1),  100);
        azcMedewerker = new AZCMedewerker("Test Medewerker", azc);

        // Voeg kamers toe aan het AZC
        Kamer kamer1 = new Kamer(101, 1, KamerType.EENPERSOONSKAMER, "Man", false);  // Een kamer met 1 plaats
        azc.getKamerManager().voegKamerToe(kamer1);

        Kamer kamer2 = new Kamer(102, 4, KamerType.GEZINSKAMER, "gemengd", false);  // Een gezinskamer met 4 plaatsen
        azc.getKamerManager().voegKamerToe(kamer2);
    }

    @Test
    public void testPlaatsenVluchteling() {
        Vluchteling vluchteling = new Vluchteling("Jane Doe", 25, "female", false, null);
        Bericht bericht = new Bericht("plaatsing", vluchteling, null, "Nieuwe asielzoeker geplaatst in AZC", azc.getNaam(), false);

        azcMedewerker.plaatsenVluchteling(vluchteling, null, bericht);

        // Controleer of de vluchteling aan een kamer is toegevoegd
        Kamer toegewezenKamer = azc.getKamerManager().vindKamerVanBewoner(vluchteling);
        assertNotNull(toegewezenKamer);
        assertTrue(toegewezenKamer.getBewoners().contains(vluchteling));
    }

    @Test
    public void testPlaatsenVluchtelingGeenGeschikteKamer() {
        // Maak een vluchteling die een kamer nodig heeft
        Vluchteling vluchteling = new Vluchteling("John Smith", 40, "male", false, null);

        // Maak een bericht voor de plaatsing van de vluchteling
        Bericht bericht = new Bericht("plaatsing", vluchteling, null, "Nieuwe asielzoeker geplaatst in AZC", azc.getNaam(), false);

        // Verwijder alle kamers om te simuleren dat er geen beschikbare kamers zijn
        azc.getKamerManager().getKamers().clear();

        azcMedewerker.plaatsenVluchteling(vluchteling, null, bericht);

        // Controleer dat de vluchteling niet is geplaatst omdat er geen geschikte kamers zijn
        Kamer toegewezenKamer = azc.getKamerManager().vindKamerVanBewoner(vluchteling);
        assertNull(toegewezenKamer);
        assertFalse(bericht.isProcessed());
    }
}
