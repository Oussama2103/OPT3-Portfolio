import java.util.Scanner;

public class AZCMedewerkerMenu {
    private Scanner scanner;
    private AZCMedewerker azcMedewerker;

    public AZCMedewerkerMenu(Scanner scanner, AZCMedewerker azcMedewerker) {
        this.scanner = scanner;
        this.azcMedewerker = azcMedewerker;
    }

    public void showMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println("AZC-medewerker menu:");
            System.out.println("1. Niet verwerkte berichten inzien");
            System.out.println("2. Bericht selecteren en verwerken");
            System.out.println("3. Terug naar hoofdmenu");
            int keuze = scanner.nextInt();
            scanner.nextLine();

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
