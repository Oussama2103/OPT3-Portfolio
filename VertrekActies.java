import java.util.ArrayList;
import java.util.Scanner;

public class VertrekActies {
    private Scanner scanner;
    private ArrayList<Gemeente> gemeentes;
    private COAMedewerker coaMedewerker;

    public VertrekActies(Scanner scanner, ArrayList<Gemeente> gemeentes, COAMedewerker coaMedewerker) {
        this.scanner = scanner;
        this.gemeentes = gemeentes;
        this.coaMedewerker = coaMedewerker;
    }

    public void registreren() {
        System.out.println("Vertrek registreren:");
        System.out.print("Voer naam van de asielzoeker in:");
        String vertrekNaam = scanner.nextLine();
        Vluchteling vertrekVluchteling = vindVluchtelingOpNaam(vertrekNaam);
        if (vertrekVluchteling == null) {
            System.out.println("Asielzoeker niet gevonden.");
            return;
        }
        coaMedewerker.registreerVertrek(vertrekVluchteling);
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
}
