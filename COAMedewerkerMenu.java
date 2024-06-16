import java.util.ArrayList;
import java.util.Scanner;

public class COAMedewerkerMenu {
    private Scanner scanner;
    private ArrayList<Gemeente> gemeentes;
    private COAMedewerker coaMedewerker;

    public COAMedewerkerMenu(Scanner scanner, ArrayList<Gemeente> gemeentes, COAMedewerker coaMedewerker) {
        this.scanner = scanner;
        this.gemeentes = (ArrayList<Gemeente>) DataSeeder.gemeentes;
        this.coaMedewerker = coaMedewerker;
    }

    public void showMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println("COA-medewerker menu:");
            System.out.println("1. Vluchteling registreren");
            System.out.println("2. Asielzoeker plaatsen of verhuizen");
            System.out.println("3. Dossier bijwerken");
            System.out.println("4. Vertrek registreren voor vluchteling");
            System.out.println("5. Vertrek registreren voor familie");
            System.out.println("6. Familie registreren");
            System.out.println("7. Familie plaatsen of verhuizen");
            System.out.println("8. Terug naar hoofdmenu");
            System.out.print("Voer uw keuze in: ");

            int keuze = scanner.nextInt();
            scanner.nextLine();

            switch (keuze) {
                case 1:
                    new VluchtelingActies(scanner, gemeentes, coaMedewerker).registreer();
                    break;
                case 2:
                    new VluchtelingActies(scanner, gemeentes, coaMedewerker).plaatsenOfVerhuizen();
                    break;
                case 3:
                    new DossierActies(scanner, gemeentes, coaMedewerker).bijwerken();
                    break;
                case 4:
                    new VertrekActies(scanner, gemeentes, coaMedewerker).registreren();
                    break;
                case 5:
                    new FamilieVertrekActies(scanner, gemeentes, coaMedewerker).registreren();
                    break;
                case 6:
                    new FamilieActies(scanner, gemeentes, coaMedewerker).registreren();
                    break;
                case 7:
                    new FamilieActies(scanner, gemeentes, coaMedewerker).plaatsenOfVerhuizen();
                    break;
                case 8:
                    exit = true;
                    break;
                default:
                    System.out.println("Ongeldige keuze, probeer opnieuw.");
            }
        }
    }
}
