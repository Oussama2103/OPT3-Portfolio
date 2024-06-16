import java.util.ArrayList;
import java.util.Scanner;

public class VluchtelingActies {
    private Scanner scanner;
    private ArrayList<Gemeente> gemeentes;
    private COAMedewerker coaMedewerker;

    public VluchtelingActies(Scanner scanner, ArrayList<Gemeente> gemeentes, COAMedewerker coaMedewerker) {
        this.scanner = scanner;
        this.gemeentes = (ArrayList<Gemeente>) DataSeeder.gemeentes;
        this.coaMedewerker = coaMedewerker;
    }

    public void registreer() {
        System.out.println("Vluchteling registreren:");
        System.out.print("Voer naam in: ");
        String naam = scanner.nextLine();
        System.out.print("Voer leeftijd in: ");
        int leeftijd = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Voer gender in: ");
        String gender = scanner.nextLine();
        System.out.print("Is het land waar de asielzoeker vandaan komt veilig?: ja/nee ");
        String landVanHerkomst1 = scanner.nextLine();
        boolean landVanHerkomst = landVanHerkomst1.equalsIgnoreCase("ja");
        System.out.print("Heeft de asielzoeker een paspoort?: ");
        String paspoortInvoer = scanner.nextLine();
        boolean heeftPaspoort = paspoortInvoer.equalsIgnoreCase("ja");
        Vluchteling vluchteling = new Vluchteling(naam, leeftijd, gender, landVanHerkomst, null);
        coaMedewerker.registreerVluchteling(vluchteling, heeftPaspoort);
    }

    public void plaatsenOfVerhuizen() {
        System.out.print("Voer naam van de asielzoeker in: ");
        String asielzoekerNaam = scanner.nextLine();
        Vluchteling vluchteling = vindVluchtelingOpNaam(asielzoekerNaam);
        if (vluchteling == null) {
            System.out.println("Asielzoeker niet gevonden.");
            return;
        }

        AZC gekozenAZC = besteAZC(gemeentes);
        coaMedewerker.plaatsOfVerhuisAsielzoeker(vluchteling, gekozenAZC);
    }

    private Vluchteling vindVluchtelingOpNaam(String naam) {
        for (Gemeente g : gemeentes) {
            for (AZC azc : g.getAZCs()) {
                for (Vluchteling vluchteling : azc.getBewonersManager().getBewoners()) {
                    if (vluchteling.getNaam().equalsIgnoreCase(naam)) {
                        return vluchteling;
                    }
                }
            }
        }
        return null;
    }

    public static AZC besteAZC(ArrayList<Gemeente> gemeentes) {
        System.out.println("Op zoek naar beste AZC op basis van beschikbare plekken...");

        Gemeente besteGemeente = null;
        int maxBeschikbarePlekken = 0;

        for (Gemeente gemeente : gemeentes) {
            int beschikbarePlekken = gemeente.beschikbarePlaatsen();
            if (beschikbarePlekken > maxBeschikbarePlekken) {
                maxBeschikbarePlekken = beschikbarePlekken;
                besteGemeente = gemeente;
            }
        }
        if (besteGemeente == null || maxBeschikbarePlekken == 0) {
            double minPercentage = Double.MAX_VALUE;

            for (Gemeente gemeente : gemeentes) {
                int inwoners = gemeente.getAantalInwoners();
                int beschikbarePlekken = gemeente.beschikbarePlaatsen();
                double percentage = (double) beschikbarePlekken / inwoners;

                if (percentage < minPercentage) {
                    minPercentage = percentage;
                    besteGemeente = gemeente;
                }
            }
        }
        ArrayList<AZC> beschikbareAZCs = besteGemeente.getAZCs();
        AZC gekozenAZC = null;
        int maxBeschikbareCapaciteit = 0;

        for (AZC azc : beschikbareAZCs) {
            int beschikbareCapaciteit = azc.getCapaciteit() - azc.getBewonersManager().getBezetting();
            if (beschikbareCapaciteit > maxBeschikbareCapaciteit) {
                maxBeschikbareCapaciteit = beschikbareCapaciteit;
                gekozenAZC = azc;
            }
        }

        if (gekozenAZC == null) {
            System.out.println("Geen beschikbare AZC's gevonden in " + besteGemeente.getNaam());
        }

        return gekozenAZC;
    }

}
