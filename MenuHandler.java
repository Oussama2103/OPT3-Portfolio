import java.util.ArrayList;
import java.util.Scanner;

public class MenuHandler {
    private Scanner scanner;
    private ArrayList<Gemeente> gemeentes;
    private ArrayList<AZC> azcs;
    private String naam;

    public MenuHandler(Scanner scanner, ArrayList<Gemeente> gemeentes, ArrayList<AZC> azcs, String naam) {
        this.scanner = scanner;
        DataSeeder.seedData();
        this.gemeentes = (ArrayList<Gemeente>) DataSeeder.gemeentes;
        this.azcs = (ArrayList<AZC>) DataSeeder.azcs;
        this.naam = naam;
    }

    public void showMainMenu() {
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
                    new BeheerderMenu(scanner, gemeentes, beheerder).showMenu();
                    break;
                case 2:
                    COAMedewerker coaMedewerker = new COAMedewerker(naam, gemeentes);
                    new COAMedewerkerMenu(scanner, gemeentes).showMenu();
                    break;
                case 3:
                    new VluchtelingMenu(scanner, gemeentes).showMenu();
                    break;
                case 4:
                    showAZCMenu();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Ongeldige keuze, probeer opnieuw.");
            }
        }
    }

    private void showAZCMenu() {
        System.out.println("Beschikbare AZC's:");
        for (int i = 0; i < azcs.size(); i++) {
            System.out.println((i + 1) + ". " + azcs.get(i).getNaam());
        }
        System.out.print("Kies een AZC (nummer): ");
        int azcKeuze = scanner.nextInt();
        scanner.nextLine();
        if (azcKeuze >= 1 && azcKeuze <= azcs.size()) {
            AZC gekozenAZC = azcs.get(azcKeuze - 1);
            AZCMedewerker azcMedewerker = new AZCMedewerker(naam, gekozenAZC);
            new AZCMedewerkerMenu(scanner, azcMedewerker).showMenu();
        } else {
            System.out.println("Ongeldige keuze, probeer opnieuw.");
        }
    }
}
