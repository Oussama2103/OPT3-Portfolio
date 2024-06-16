import java.util.ArrayList;

public class FamilieManager {
    private ArrayList<Gemeente> gemeentes;
    private VluchtelingManager vluchtelingManager;
    private DossierManager dossierManager;

    public FamilieManager() {
        this.gemeentes = gemeentes;
        this.vluchtelingManager = new VluchtelingManager();
        this.dossierManager = new DossierManager();
    }

    public void registreerFamilie(Familie familie, boolean hebbenPaspoorten) {
        for (Vluchteling lid : familie.getLeden()) {
            if (lid.getLandVanHerkomst()) {
                lid.setDossier(new Dossier(lid));
                dossierManager.updateDossier(lid, false, "Geen", false, false);
            }
            vluchtelingManager.registreerVluchteling(lid, hebbenPaspoorten);
        }
    }

    public void plaatsOfVerhuisFamilie(Familie familie, AZC besteAZC) {
        for (Vluchteling lid : familie.getLeden()) {
            vluchtelingManager.plaatsOfVerhuisAsielzoeker(lid, besteAZC);
            besteAZC.getBerichtenBox().voegBerichtToe(new Bericht("plaatsing", lid, familie, "Nieuwe asielzoeker geplaatst in AZC", besteAZC.getNaam(), false));
        }
        System.out.println("Familie " + familie.getNaam() + " is verhuisd naar " + besteAZC.getNaam());
    }

    public void registreerVertrekFamilie(Familie familie) {
        for (Vluchteling lid : familie.getLeden()) {
            lid.getDossier().setTeruggekeerdNaarHerkomstland(true);
            System.out.println("Het vertrek van de vluchteling " + lid.getNaam() + " is geregistreerd.");
        }
        verwijderFamilieUitAZC(familie);
        for (AZC azc : getAZCs()) {
            azc.getBerichtenBox().voegBerichtToe(new Bericht("vertrek", null, familie,"Vertrek van familie geregistreerd", null, false));
        }

    }

    private void verwijderFamilieUitAZC(Familie familie) {
        for (Gemeente gemeente : gemeentes) {
            for (AZC azc : gemeente.getAZCs()) {
                if (azc.getBewonersManager().bevatFamilie(familie)) {
                    azc.getBewonersManager().verwijderFamilie(familie);
                    System.out.println("Familie " + familie.getNaam() + " is verwijderd uit " + azc.getNaam());
                    return;
                }
            }
        }
    }

    private ArrayList<AZC> getAZCs() {
        ArrayList<AZC> azcs = new ArrayList<>();
        for (Gemeente gemeente : gemeentes) {
            azcs.addAll(gemeente.getAZCs());
        }
        return azcs;
    }
}
