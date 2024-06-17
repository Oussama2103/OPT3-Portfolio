import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class VluchtelingManager {
    private HashMap<String, Familie> geregistreerdeFamilies;
    private AZCManager azcManager;
    private BewonersManager bewonersManager;

    public VluchtelingManager(AZCManager azcManager) {
        this.geregistreerdeFamilies = new HashMap<>();
        this.azcManager = azcManager;
        this.bewonersManager = new BewonersManager();

    }

    public void registreerVluchteling() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Voer naam van de vluchteling in: ");
        String naam = scanner.nextLine();

        System.out.print("Voer leeftijd van de vluchteling in: ");
        int leeftijd = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Voer gender van de vluchteling in: ");
        String gender = scanner.nextLine();

        System.out.print("Is het land van herkomst veilig? (true/false): ");
        boolean landVanHerkomstVeilig = scanner.nextBoolean();
        scanner.nextLine();

        System.out.print("Heeft de vluchteling een paspoort? (true/false): ");
        boolean heeftPaspoort = scanner.nextBoolean();
        scanner.nextLine();

        System.out.println();

        Familie familie = null;
        System.out.print("Voer familienaam in (indien van toepassing, anders laat leeg): ");
        String familienaam = scanner.nextLine();
        if (familienaam != null && !familienaam.isEmpty()) {
            if (!geregistreerdeFamilies.containsKey(familienaam)) {
                geregistreerdeFamilies.put(familienaam, new Familie(familienaam));
            }
            familie = geregistreerdeFamilies.get(familienaam);
            bewonersManager.voegFamilieToe(familie);
        }

        Vluchteling vluchteling = new Vluchteling(naam, leeftijd, gender, landVanHerkomstVeilig, familie);
        bewonersManager.voegVluchtelingToe(vluchteling);
        Dossier dossier = null;
        if (heeftPaspoort) {
            dossier = new Dossier(vluchteling);
        }
        AZC besteAZC = DataSeeder.azcs.get(6);
        azcManager.plaatsVluchtelingInAZC(vluchteling, besteAZC);
        System.out.println("Vluchteling " + naam + " is geplaatst in " + besteAZC.getNaam());

    }

    public void plaatsOfVerhuisFamilie() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Voer familienaam in: ");
        String familienaam = scanner.nextLine();

        Familie familie = geregistreerdeFamilies.get(familienaam);
        if (familie != null) {
            AZC besteAZC = azcManager.vindBesteAZC();
            if (besteAZC != null) {
                for (Vluchteling lid : familie.getLeden()) {
                    azcManager.plaatsVluchtelingInAZC(lid, besteAZC);
                }
                System.out.println("Familie " + familienaam + " is geplaatst in " + besteAZC.getNaam());
                besteAZC.getBerichtenBox().voegBerichtToe(new Bericht("Plaatsing",null , familie, "familie is geplaatst", besteAZC.getNaam(), false));
            } else {
                System.out.println("Geen beschikbare AZC gevonden.");
            }
        } else {
            System.out.println("Familie " + familienaam + " niet gevonden.");
        }
    }

    public void registreerVertrekFamilie(CommunicationManager communicationManager) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Voer familienaam in: ");
        String familienaam = scanner.nextLine();

        Familie familie = geregistreerdeFamilies.get(familienaam);
        if (familie != null) {
            for (Vluchteling lid : familie.getLeden()) {
                if (lid.getDossier() != null) {
                    lid.getDossier().setTeruggekeerdNaarHerkomstland(true);
                }
            }
            geregistreerdeFamilies.remove(familienaam);
            communicationManager.verstuurBerichtNaarAZCs(new Bericht("vertrek", null, familie, "Vertrek van familie geregistreerd", null, false));
            System.out.println("Familie " + familienaam + " is geregistreerd als vertrokken.");
        } else {
            System.out.println("Familie " + familienaam + " niet gevonden.");
        }
    }
}
