import java.util.ArrayList;

public class Beheerder {
    private String naam;
    private ArrayList<Gemeente> gemeentes;

    public Beheerder(String naam) {
        this.naam = naam;
        this.gemeentes = (ArrayList<Gemeente>) DataSeeder.gemeentes;
    }

    public void rapporteerGemeentesEnAZCs() {
        for (Gemeente gemeente : gemeentes) {
            rapporteerGemeente(gemeente);
        }
    }

    private void rapporteerGemeente(Gemeente gemeente) {
        System.out.println("Gemeente: " + gemeente.getNaam());
        System.out.println("Aantal inwoners: " + gemeente.getAantalInwoners());
        System.out.println("Beschikbare plaatsen: " + gemeente.beschikbarePlaatsen());
        System.out.println("Geplaatste asielzoekers: " + gemeente.getDaadwerkelijkePlaatsing());

        double percentage = (gemeente.getDaadwerkelijkePlaatsing() / (double) gemeente.getAantalInwoners()) * 100;
        System.out.printf("Plaatsingspercentage: %.2f%%\n", percentage);

        System.out.println("AZC's:");
        for (AZC azc : gemeente.getAZCs()) {
            System.out.println(" - " + azc.getNaam() + ", Adres " + azc.getAdres());
        }
    }

    public void rapporteerUitkeringen() {
        for (Gemeente gemeente : gemeentes) {
            rapporteerUitkeringVoorGemeente(gemeente);
        }
    }

    private void rapporteerUitkeringVoorGemeente(Gemeente gemeente) {
        int verplichtAantal = (int) (0.005 * gemeente.getAantalInwoners()); // 0,5% van het aantal inwoners
        int totaalGeplaatst = gemeente.getDaadwerkelijkePlaatsing();

        int extraPlaatsen = Math.max(totaalGeplaatst - verplichtAantal, 0);
        int uitkering = (extraPlaatsen > 100) ? 2000 * extraPlaatsen : 1000 * extraPlaatsen;

        System.out.println("Gemeente: " + gemeente.getNaam() + ", Uitkering: €" + uitkering);
    }
}
