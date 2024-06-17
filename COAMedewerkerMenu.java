import java.util.ArrayList;
import java.util.Scanner;

public class COAMedewerkerMenu {
    private Scanner scanner;
    private ArrayList<Gemeente> gemeentes;
    private COAMedewerker coaMedewerker;

    public COAMedewerkerMenu(Scanner scanner, ArrayList<Gemeente> gemeentes) {
        this.scanner = scanner;
        this.gemeentes = gemeentes;
        this.coaMedewerker = new COAMedewerker("COA Medewerker", gemeentes); // Assuming a default name
    }

    public void showMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println("COA-medewerker menu:");
            System.out.println("1. Vluchteling registreren");
            System.out.println("2. Familie plaatsen of verhuizen");
            System.out.println("3. Dossier bijwerken");
            System.out.println("4. Vertrek registreren voor familie");
            System.out.println("5. Asielzoeker plaatsen of verhuizen");
            System.out.println("6. Terug naar hoofdmenu");
            System.out.print("Voer uw keuze in: ");

            int keuze = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (keuze) {
                case 1:
                    coaMedewerker.registreerVluchteling();
                    break;
                case 2:
                    coaMedewerker.plaatsOfVerhuisFamilie();
                    break;
                case 3:
                    coaMedewerker.updateDossier();
                    break;
                case 4:
                    coaMedewerker.registreerVertrekFamilie();
                    break;
                case 5:
                    coaMedewerker.plaatsOfVerhuisVluchteling();
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Ongeldige keuze, probeer opnieuw.");
            }
        }
    }
}