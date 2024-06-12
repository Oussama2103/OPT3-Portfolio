import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    private static ArrayList<Gemeente> gemeentes;
    private static ArrayList<AZC> azcs;
    public static void main(String[] args) {

        DataSeeder.seedData();
        gemeentes = (ArrayList<Gemeente>) DataSeeder.gemeentes;
        azcs = (ArrayList<AZC>) DataSeeder.azcs;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Wat is uw naam: ");
        String naam = scanner.nextLine();
        boolean exit = false;

        while (!exit) {
            System.out.println("Hoofdmenu:");
            System.out.println("1. Beheerder");
            System.out.println("2. COA-medewerker");
            System.out.println("3. Asielzoeker");
            System.out.println("4. Medewerker van een AZC");
            System.out.println("5. Exit");
            System.out.print("Voer uw keuze in: ");
            int keuze = scanner.nextInt();
            scanner.nextLine();

            switch (keuze) {
                case 1:
                    Beheerder beheerder = new Beheerder(naam);
                    beheerderMenu(scanner, gemeentes, beheerder);
                    break;
                case 2:
                    COAMedewerker coaMedewerker = new COAMedewerker(naam);
                    coaMedewerkerMenu(scanner, gemeentes, coaMedewerker);
                    break;
                case 3:
                    asielzoekerMenu(scanner);
                    break;
                case 4:
                    System.out.println("Beschikbare AZC's:");
                    for (int i = 0; i < azcs.size(); i++) {
                        System.out.println((i + 1) + ". " + azcs.get(i).getNaam());
                    }
                    System.out.print("Kies een AZC (nummer): ");
                    int azcKeuze = scanner.nextInt();
                    scanner.nextLine();
                    if (azcKeuze >= 1 && azcKeuze <= azcs.size()) {
                        AZC gekozenAZC = azcs.get(azcKeuze - 1);
                        AZCMedewerker azcMedewerker = new AZCMedewerker(naam, gekozenAZC, new UserInputHandler());
                        azcMedewerkerMenu(scanner, azcMedewerker);
                    } else {
                        System.out.println("Ongeldige keuze, probeer opnieuw.");
                    }
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Ongeldige keuze, probeer opnieuw.");
            }
        }
    }

    public static void beheerderMenu(Scanner scanner, ArrayList<Gemeente> gemeentes, Beheerder beheerder) {
        boolean exit = false;

        while (!exit) {
            System.out.println("Beheerder menu:");
            System.out.println("1. Gemeentes en AZC’s rapporteren");
            System.out.println("2. Uitkeringen rapporteren");
            System.out.println("3. Terug naar hoofdmenu");
            System.out.print("Voer uw keuze in: ");
            int keuze = scanner.nextInt();
            scanner.nextLine();

            switch (keuze) {
                case 1:
                    beheerder.rapporteerGemeentesEnAZCs();
                    break;
                case 2:
                    beheerder.rapporteerUitkeringen();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Ongeldige keuze, probeer opnieuw.");
            }
        }
    }

    public static void coaMedewerkerMenu(Scanner scanner, ArrayList<Gemeente> gemeentes, COAMedewerker coaMedewerker) {
        boolean exit = false;

        while (!exit) {
            System.out.println("COA-medewerker menu:");
            System.out.println("1. Vluchteling registreren");
            System.out.println("2. Asielzoeker plaatsen of verhuizen");
            System.out.println("3. Dossier bijwerken");
            System.out.println("4. Vertrek registreren");
            System.out.println("5. Familie registreren");
            System.out.println("6. Familie plaatsen of verhuizen");
            System.out.println("7. Terug naar hoofdmenu");
            System.out.print("Voer uw keuze in: ");

            int keuze = scanner.nextInt();
            scanner.nextLine();

            switch (keuze) {
                case 1:
                    vluchtelingRegistreren(scanner, gemeentes, coaMedewerker);
                    break;
                case 2:
                    asielzoekerPlaatsenOfVerhuizen(scanner, gemeentes, coaMedewerker);
                    break;
                case 3:
                    dossierBijwerken(scanner, gemeentes, coaMedewerker);
                    break;
                case 4:
                    vertrekRegistreren(scanner, gemeentes, coaMedewerker);
                    break;
                case 5:
                    familieRegistreren(scanner, gemeentes, coaMedewerker);
                    break;
                case 6:
                    familiePlaatsenOfVerhuizen(scanner, gemeentes, coaMedewerker);
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Ongeldige keuze, probeer opnieuw.");
            }
        }
    }

    public static void vluchtelingRegistreren(Scanner scanner, ArrayList<Gemeente> gemeentes, COAMedewerker coaMedewerker) {
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

    public static void familieRegistreren(Scanner scanner, ArrayList<Gemeente> gemeentes, COAMedewerker coaMedewerker) {
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


    public static void asielzoekerPlaatsenOfVerhuizen(Scanner scanner, ArrayList<Gemeente> gemeentes, COAMedewerker coaMedewerker) {
        System.out.print("Voer naam van de asielzoeker in: ");
        String asielzoekerNaam = scanner.nextLine();
        Vluchteling vluchteling = vindVluchteling(asielzoekerNaam, gemeentes);
        if (vluchteling == null) {
            System.out.println("Asielzoeker niet gevonden.");
            return;
        }

        AZC gekozenAZC = kiesAZC(scanner, gemeentes);
        coaMedewerker.plaatsOfVerhuisAsielzoeker(vluchteling, gekozenAZC);
    }

    public static void familiePlaatsenOfVerhuizen(Scanner scanner, ArrayList<Gemeente> gemeentes, COAMedewerker coaMedewerker) {
        System.out.print("Voer familienaam in: ");
        String familienaam = scanner.nextLine();
        Familie familie = vindFamilie(familienaam, gemeentes);
        if (familie == null) {
            System.out.println("Familie niet gevonden.");
            return;
        }

        AZC gekozenAZC = kiesAZC(scanner, gemeentes);
        coaMedewerker.plaatsOfVerhuisFamilie(familie, gekozenAZC);
    }

    public static Familie vindFamilie(String familienaam, ArrayList<Gemeente> gemeentes) {
        Familie gevondenFamilie = new Familie(familienaam);
        boolean familieGevonden = false;
        for (Gemeente gemeente : gemeentes) {
            for (AZC azc : gemeente.getAZCs()) {
                for (Vluchteling vluchteling : azc.getBewonersManager().getBewoners()) {
                    if (vluchteling.getNaam().equalsIgnoreCase(familienaam)) {
                        gevondenFamilie.voegLidToe(vluchteling);
                        familieGevonden = true;
                    }
                }
            }
        }
        return familieGevonden ? gevondenFamilie : null;
    }


    public static void dossierBijwerken(Scanner scanner, ArrayList<Gemeente> gemeentes, COAMedewerker coaMedewerker) {
        System.out.print("Voer naam van de asielzoeker in: ");
        String dossierNaam = scanner.nextLine();
        Vluchteling dossierVluchteling = vindVluchteling(dossierNaam, gemeentes);
        if (dossierVluchteling == null) {
            System.out.println("Asielzoeker niet gevonden.");
            return;
        }
        System.out.print("Is de asielaanvraag afgerond? (true/false): ");
        boolean asielAanvraagAfgerond = scanner.nextBoolean();
        scanner.nextLine();
        System.out.print("Wat is de uitspraak van de IND? (geen/verblijfsvergunning/afgewezen): ");
        String uitspraakIND = scanner.nextLine();
        System.out.print("Wat is de status van plaatsing in eigen woning? (nee/ja): ");
        String statusEigenWoningInput = scanner.nextLine();
        boolean statusEigenWoning = statusEigenWoningInput.equalsIgnoreCase("ja");
        System.out.print("Is de asielzoeker teruggekeerd naar het land van herkomst? (true/false): ");
        boolean teruggekeerdNaarHerkomstland = scanner.nextBoolean();
        coaMedewerker.updateDossier(dossierVluchteling, asielAanvraagAfgerond, uitspraakIND, statusEigenWoning, teruggekeerdNaarHerkomstland);
    }

    public static void vertrekRegistreren(Scanner scanner, ArrayList<Gemeente> gemeentes, COAMedewerker coaMedewerker) {
        // Selecteer een vluchteling en registreer vertrek
        System.out.print("Voer naam van de asielzoeker in:");
        String vertrekNaam = scanner.nextLine();
        Vluchteling vertrekVluchteling = vindVluchteling(vertrekNaam, gemeentes);
        if (vertrekVluchteling == null) {
            System.out.println("Asielzoeker niet gevonden.");
            return;
        }
        coaMedewerker.registreerVertrek(vertrekVluchteling);
    }

    public static Vluchteling vindVluchteling(String naam, ArrayList<Gemeente> gemeentes) {
        for (Gemeente gemeente : gemeentes) {
            for (AZC azc : gemeente.getAZCs()) {
                for (Vluchteling vluchteling : azc.getBewonersManager().getBewoners()) {
                    if (vluchteling.getNaam().equalsIgnoreCase(naam)) {
                        return vluchteling;
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



    public static void asielzoekerMenu(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            System.out.println("Asielzoeker menu:");
            System.out.println("1. Status dossier opvragen");
            System.out.println("2. Nieuw adres registreren");
            System.out.println("3. Terug naar hoofdmenu");
            System.out.print("Voer uw keuze in: ");

            int keuze = scanner.nextInt();
            scanner.nextLine();


            switch (keuze) {
                case 1:
                    System.out.print("Voer uw naam in: ");
                    String naam = scanner.nextLine();
                    Vluchteling vluchteling = vindVluchteling(naam, gemeentes);
                    if (vluchteling != null) {
                        Dossier dossier = vluchteling.dossierInzien(vluchteling);
                        if (dossier != null) {
                            System.out.println("Dossier status:");
                            System.out.println("Asielaanvraag is afgerond: " + dossier.isAsielAanvraagAfgerond());
                            System.out.println("Uitspraak IND: " + dossier.getUitspraakIND());
                            System.out.println("Plaatsing in eigen woning: " + dossier.getStatusEigenWoning());
                            System.out.println("Teruggekeerd naar het land van herkomst: " + dossier.isTeruggekeerdNaarHerkomstland());
                        } else {
                            System.out.println("Geen dossier gevonden voor deze vluchteling.");
                        }
                    } else {
                        System.out.println("Vluchteling niet gevonden.");
                    }
                    break;
                case 2:
                    System.out.print("Voer uw naam in: ");
                    naam = scanner.nextLine();
                    vluchteling = vindVluchteling(naam, gemeentes);
                    if (vluchteling != null) {
                        System.out.print("Voer uw nieuwe adres in: ");
                        String nieuwAdres = scanner.nextLine();
                        vluchteling.registreerNieuwAdres(vluchteling, nieuwAdres);
                    } else {
                        System.out.println("Vluchteling niet gevonden.");
                    }
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Ongeldige keuze, probeer opnieuw.");
            }
        }
    }

    public static void azcMedewerkerMenu(Scanner scanner, AZCMedewerker azcMedewerker) {

        boolean exit = false;

        while (!exit) {
            System.out.println("AZC-medewerker menu:");
            System.out.println("1. Niet verwerkte berichten inzien");
            System.out.println("2. Bericht selecteren en verwerken");
            System.out.println("3. Terug naar hoofdmenu");

            int keuze = scanner.nextInt();

            switch (keuze) {
                case 1:

                    azcMedewerker.bekijkNietVerwerkteBerichten();
                    break;
                case 2:
                    azcMedewerker.selecteerBerichtEnVerwerk();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Ongeldige keuze, probeer opnieuw.");
            }
        }
    }
}