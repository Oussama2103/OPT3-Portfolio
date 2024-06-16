import java.util.ArrayList;
import java.util.Scanner;

public class VluchtelingHandelingen {
    private Scanner scanner;
    private ArrayList<Gemeente> gemeentes;

    public VluchtelingHandelingen(Scanner scanner, ArrayList<Gemeente> gemeentes) {
        this.scanner = scanner;
        this.gemeentes = gemeentes;
    }

    public void statusOpvragen() {
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
    }

    public void nieuwAdresRegistreren() {
        System.out.print("Voer de naam van de vluchteling in: ");
        String naam = scanner.nextLine();
        Vluchteling vluchteling = vindVluchteling(naam, gemeentes);

        if (vluchteling != null) {
            if (vluchteling.getDossier().getStatusEigenWoning().equalsIgnoreCase("opgestart")) {
                System.out.print("Voer het nieuwe adres in: ");
                String nieuwAdres = scanner.nextLine();

                vluchteling.setAdres(nieuwAdres);
                vluchteling.getDossier().setStatusEigenWoning("afgerond");

                vluchteling.getVerblijfplaats().getBerichtenBox().voegBerichtToe(new Bericht("vetrek",vluchteling, vluchteling.getFamilie(), "Vluchteling is vertrokken van deze azc", vluchteling.getVerblijfplaats().getNaam(), false));
                for (Gemeente g : gemeentes) {
                    for (AZC azc : g.getAZCs()) {
                        azc.getBerichtenBox().voegBerichtToe(new Bericht("verhuising", vluchteling , vluchteling.getFamilie(), "vluchteling is naar eigen woning vehuisd", azc.getNaam(), false));
                    }
                }
                vluchteling.getVerblijfplaats().getBewonersManager().verwijderBewoner(vluchteling);
            }
        } else {
            System.out.println("Vluchteling niet gevonden");
        }
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

}