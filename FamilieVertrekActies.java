import java.util.ArrayList;
import java.util.Scanner;

public class FamilieVertrekActies {
    private Scanner scanner;
    private ArrayList<Gemeente> gemeentes;
    private COAMedewerker coaMedewerker;

    public FamilieVertrekActies(Scanner scanner, ArrayList<Gemeente> gemeentes, COAMedewerker coaMedewerker) {
        this.scanner = scanner;
        this.gemeentes = gemeentes;
        this.coaMedewerker = coaMedewerker;
    }

    public void registreren() {
        System.out.println("Familie vertrek registreren:");
        System.out.print("Naam van familie: ");
        String naam = scanner.nextLine();
        Familie familie = vindFamilieOpNaam(naam);

        if (familie != null) {
            coaMedewerker.registreerVertrekFamilie(familie);
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
}
