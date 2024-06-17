import java.util.ArrayList;
import java.util.Scanner;

public class COAMedewerker {
    private String naam;
    private VluchtelingManager vluchtelingManager;
    private AZCManager azcManager;
    private DossierManager dossierManager;
    private CommunicationManager communicationManager;
    private ArrayList <Gemeente> gemeentes;

    public COAMedewerker(String naam, ArrayList<Gemeente> gemeentes) {
        this.naam = naam;
        this.azcManager = new AZCManager(gemeentes);
        this.vluchtelingManager = new VluchtelingManager(azcManager);
        this.dossierManager = new DossierManager(azcManager);
        this.communicationManager = new CommunicationManager(gemeentes);
        this.gemeentes = gemeentes;
    }

    public void registreerVluchteling() {
        vluchtelingManager.registreerVluchteling();
    }

    public void plaatsOfVerhuisFamilie() {
        vluchtelingManager.plaatsOfVerhuisFamilie();
    }

    public void registreerVertrekFamilie() {
        vluchtelingManager.registreerVertrekFamilie(communicationManager);
    }

    public void plaatsOfVerhuisVluchteling() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Voer naam van de vluchteling in: ");
        String naam = scanner.nextLine();

        Vluchteling vluchteling = azcManager.vindVluchteling(naam, gemeentes);

        if (vluchteling != null) {
            AZC besteAZC = azcManager.vindBesteAZC();
            if (besteAZC != null) {
                azcManager.plaatsVluchtelingInAZC(vluchteling, besteAZC);
                System.out.println("Vluchteling " + naam + " is verhuisd naar " + besteAZC.getNaam());
            } else {
                System.out.println("Geen beschikbare AZC gevonden.");
            }
        } else {
            System.out.println("Vluchteling " + naam + " niet gevonden.");
        }
    }

    public void updateDossier() {
        dossierManager.updateDossier();
    }
}
