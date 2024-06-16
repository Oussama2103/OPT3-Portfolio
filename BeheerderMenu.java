import java.util.ArrayList;
import java.util.Scanner;

public class BeheerderMenu {
    private Scanner scanner;
    private ArrayList<Gemeente> gemeentes;
    private Beheerder beheerder;

    public BeheerderMenu(Scanner scanner, ArrayList<Gemeente> gemeentes, Beheerder beheerder) {
        this.scanner = scanner;
        this.gemeentes = gemeentes;
        this.beheerder = beheerder;
    }

    public void showMenu() {
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
}
