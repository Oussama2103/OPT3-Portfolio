import java.util.ArrayList;
import java.util.Scanner;

public class FamilieActies {
    private Scanner scanner;
    private ArrayList<Gemeente> gemeentes;
    private COAMedewerker coaMedewerker;

    public FamilieActies(Scanner scanner, ArrayList<Gemeente> gemeentes, COAMedewerker coaMedewerker) {
        this.scanner = scanner;
        this.gemeentes = gemeentes;
        this.coaMedewerker = coaMedewerker;
    }

    public void registreren() {
        System.out.println("Familie registreren:");
        System.out.print("Voer familienaam in: ");
        String familienaam = scanner.nextLine();
        Familie familie = new Familie(familienaam);

        boolean toevoegen = true;
        while (toevoegen) {
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
            Vluchteling vluchteling = new Vluchteling(naam, leeftijd, gender, landVanHerkomst, null);
            familie.voegLidToe(vluchteling);

            System.out.print("Wilt u nog een lid toevoegen? (ja/nee): ");
            String keuze = scanner.nextLine();
            toevoegen = keuze.equalsIgnoreCase("ja");
        }

        System.out.print("Heeft de familie paspoorten?: ");
        String paspoortenInvoer = scanner.nextLine();
        boolean hebbenPaspoorten = paspoortenInvoer.equalsIgnoreCase("ja");
        coaMedewerker.registreerFamilie(familie, hebbenPaspoorten);
    }

    public void plaatsenOfVerhuizen() {
        System.out.println("Familie plaatsen of verhuizen:");
        System.out.print("Naam van familie: ");
        String naam = scanner.nextLine();
        Familie familie = vindFamilieOpNaam(naam);

        if (familie != null) {
            AZC besteAZC = kiesAZC(scanner, gemeentes);
            coaMedewerker.plaatsOfVerhuisFamilie(familie, besteAZC);
        } else {
            System.out.println("Familie niet gevonden.");
        }
    }

    private Familie vindFamilieOpNaam(String naam) {
        for (Gemeente g : gemeentes) {
            for (AZC azc : g.getAZCs()) {
                for (Familie familie : azc.getBewonersManager().getFamilies()) {
                    if (familie.getNaam().equalsIgnoreCase(naam)) {
                        return familie;
                    }
                }
            }
        }
        return null;
    }

    public static AZC kiesAZC(Scanner scanner, ArrayList<Gemeente> gemeentes) {
        System.out.println("Beschikbare AZC's:");
        int gemeenteIndex = 1;
        for (Gemeente gemeente : gemeentes) {
            System.out.println(gemeenteIndex + ". " + gemeente.getNaam());
            int azcIndex = 1;
            for (AZC azc : gemeente.getAZCs()) {
                System.out.println("  " + gemeenteIndex + "-" + azcIndex + ": " + azc.getNaam());
                azcIndex++;
            }
            gemeenteIndex++;
        }

        System.out.print("Kies een gemeente en AZC (formaat: gemeenteNummer-AZCNummer): ");
        String keuze = scanner.nextLine();

        String[] indices = keuze.split("-");
        if (indices.length == 2) {
            try {
                int gekozenGemeenteIndex = Integer.parseInt(indices[0]) - 1;
                int gekozenAZCIndex = Integer.parseInt(indices[1]) - 1;

                if (gekozenGemeenteIndex >= 0 && gekozenGemeenteIndex < gemeentes.size()) {
                    Gemeente gekozenGemeente = gemeentes.get(gekozenGemeenteIndex);
                    if (gekozenAZCIndex >= 0 && gekozenAZCIndex < gekozenGemeente.getAZCs().size()) {
                        return gekozenGemeente.getAZCs().get(gekozenAZCIndex);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Ongeldige invoer, probeer opnieuw.");
            }
        }

        System.out.println("Ongeldige keuze, probeer opnieuw.");
        return kiesAZC(scanner, gemeentes);
    }
}
