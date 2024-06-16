import java.util.ArrayList;
import java.util.Scanner;

public class DossierActies {
    private Scanner scanner;
    private ArrayList<Gemeente> gemeentes;
    private COAMedewerker coaMedewerker;


    public DossierActies(Scanner scanner, ArrayList<Gemeente> gemeentes, COAMedewerker coaMedewerker) {
        this.scanner = scanner;
        this.gemeentes = gemeentes;
        this.coaMedewerker = coaMedewerker;
    }

    public void bijwerken() {
        System.out.println("Dossier bijwerken:");
        System.out.print("Voer naam van de asielzoeker in: ");
        String dossierNaam = scanner.nextLine();
        Vluchteling dossierVluchteling = vindVluchtelingOpNaam(dossierNaam);
        if (dossierVluchteling == null) {
            System.out.println("Asielzoeker niet gevonden.");
            return;
        }
        System.out.print("Is de asielaanvraag afgerond? (true/false): ");
        boolean asielAanvraagAfgerond = scanner.nextBoolean();
        scanner.nextLine();
        System.out.print("Wat is de uitspraak van de IND? (geen/verblijfsvergunning/afgewezen): ");
        String uitspraakIND = scanner.nextLine();
        System.out.print("Wat is de status van plaatsing in eigen woning? (nee/gestart/ja): ");
        String statusEigenWoningInput = scanner.nextLine();
        boolean statusEigenWoning = statusEigenWoningInput.equalsIgnoreCase("ja");
        System.out.print("Is de asielzoeker teruggekeerd naar het land van herkomst? (true/false): ");
        boolean teruggekeerdNaarHerkomstland = scanner.nextBoolean();
        coaMedewerker.updateDossier(dossierVluchteling, asielAanvraagAfgerond, uitspraakIND, statusEigenWoning, teruggekeerdNaarHerkomstland);
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
