import java.util.ArrayList;
import java.util.Scanner;

public class UserInputHandler {
    private Scanner scanner;

    public UserInputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public int kiesBericht(ArrayList<Bericht> berichten) {
        System.out.println("Beschikbare berichten:");
        for (int i = 0; i < berichten.size(); i++) {
            System.out.println((i + 1) + ". " + berichten.get(i));
        }

        System.out.print("Kies een bericht: ");
        int keuze = scanner.nextInt();
        scanner.nextLine();
        return keuze;
    }

    public boolean isGeldigeKeuze(int keuze, int grootte) {
        return keuze >= 1 && keuze <= grootte;
    }

    public void toonBerichtDetails(Bericht bericht) {
        System.out.println("Type: " + bericht.getType());
        System.out.println("Naam vluchteling: " + bericht.getVluchteling().getNaam());
        if (bericht.getFamilie() != null) {
            System.out.println("Gezinsleden: " + bericht.getFamilie().getLeden());
        }
        System.out.println("Details: " + bericht.getDetails());
    }

    public int kiesActie() {
        System.out.println("1. Toepassen");
        System.out.println("2. Annuleren");
        System.out.print("Kies een optie: ");

        int actie = scanner.nextInt();
        scanner.nextLine();
        return actie;
    }
}
