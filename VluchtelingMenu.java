import java.util.ArrayList;
import java.util.Scanner;

public class VluchtelingMenu {
    private Scanner scanner;
    private ArrayList<Gemeente> gemeentes;

    public VluchtelingMenu(Scanner scanner, ArrayList<Gemeente> gemeentes) {
        this.scanner = scanner;
        this.gemeentes = gemeentes;
    }

    public void showMenu() {
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
                    new VluchtelingHandelingen(scanner, gemeentes).statusOpvragen();
                    break;
                case 2:
                    new VluchtelingHandelingen(scanner, gemeentes).nieuwAdresRegistreren();
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
