import java.util.ArrayList;

public class COAMedewerkerTest {
    public static void main(String[] args) {
        // Creëer een lijst van gemeentes en AZC's
        Gemeente gemeente = new Gemeente("Gemeente 1", 1000);
        AZC azc1 = new AZC("AZC 1", "adres1", gemeente, 100);
        gemeente.addAZC(azc1);
        ArrayList<Gemeente> gemeentes = new ArrayList<>();
        gemeentes.add(gemeente);

        // Creëer een COAMedewerker
        COAMedewerker coaMedewerker = new COAMedewerker("Medewerker 1", gemeentes);

        // Registreer en plaats een vluchteling
        coaMedewerker.plaatsOfVerhuisVluchteling();

        // Update het dossier van de vluchteling
        coaMedewerker.updateDossier();
    }
}
