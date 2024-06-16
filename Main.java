import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Gemeente> gemeentes;
    private static ArrayList<AZC> azcs;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Wat is uw naam: ");
        String naam = scanner.nextLine();
        MenuHandler menuHandler = new MenuHandler(scanner, gemeentes, azcs, naam);

        menuHandler.showMainMenu();
    }
}
