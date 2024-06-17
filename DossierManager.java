import java.util.ArrayList;
import java.util.Scanner;

public class DossierManager {
    private AZCManager azcManager;
    private ArrayList <Gemeente> gemeentes;

    public DossierManager(AZCManager azcManager) {
        this.azcManager = azcManager;
        this.gemeentes = (ArrayList<Gemeente>) DataSeeder.gemeentes;
    }

    public void updateDossier() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Dossier bijwerken:");
        System.out.print("Voer naam van de asielzoeker in: ");
        String dossierNaam = scanner.nextLine();
        Vluchteling dossierVluchteling = azcManager.vindVluchteling(dossierNaam, gemeentes);
        if (dossierVluchteling == null) {
            System.out.println("Asielzoeker niet gevonden.");
            return;
        }

        System.out.print("Is de asielaanvraag afgerond? (true/false): ");
        boolean asielAanvraagAfgerond = scanner.nextBoolean();
        scanner.nextLine();

        Dossier dossier = dossierVluchteling.getDossier();
        if (dossier != null) {
            dossier.setAsielAanvraagAfgerond(asielAanvraagAfgerond);

            if (asielAanvraagAfgerond) {
                System.out.print("Wat is de uitspraak van de IND? (geen/verblijfsvergunning/afgewezen): ");
                String uitspraakIND = scanner.nextLine();
                dossier.setUitspraakIND(uitspraakIND);

                if ("verblijfsvergunning".equalsIgnoreCase(uitspraakIND)) {
                    dossier.setStatusEigenWoning("nee");
                    System.out.print("Is de plaatsing in eigen woning gestart? (true/false): ");
                    boolean plaatsingEigenWoningGestart = scanner.nextBoolean();
                    scanner.nextLine();
                    if (plaatsingEigenWoningGestart) {
                        dossier.setStatusEigenWoning("gestart");
                    }
                }
            }

            System.out.print("Is de asielzoeker teruggekeerd naar het land van herkomst? (true/false): ");
            boolean teruggekeerdNaarHerkomstland = scanner.nextBoolean();
            dossier.setTeruggekeerdNaarHerkomstland(teruggekeerdNaarHerkomstland);
            Bericht bericht = new Bericht("Vertrek", dossierVluchteling, null, "vluchteling is vertrokken", "", false);
            CommunicationManager communicationManager = new CommunicationManager(gemeentes);
            communicationManager.verstuurBerichtNaarAZCs(bericht);
            dossierVluchteling.getVerblijfplaats().getBewonersManager().verwijderBewoner(dossierVluchteling);

            System.out.println("Het dossier is succesvol bijgewerkt.");
        } else {
            System.out.println("Dossier niet gevonden voor vluchteling: " + dossierNaam);
        }
    }
}
