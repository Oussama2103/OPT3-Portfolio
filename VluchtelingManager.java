import java.util.ArrayList;

public class VluchtelingManager {
    private ArrayList<Gemeente> gemeentes;
    private KamerManager kamerManager;
    private DossierManager dossierManager;

    public VluchtelingManager() {
        this.gemeentes = (ArrayList<Gemeente>) DataSeeder.gemeentes;
        this.kamerManager = new KamerManager();
        this.dossierManager = new DossierManager();
    }

    public void registreerVluchteling(Vluchteling vluchteling, boolean heeftPaspoort) {
        if (heeftPaspoort) {
            vluchteling.setDossier(new Dossier(vluchteling));
            dossierManager.updateDossier(vluchteling, false, "Geen", false, false);
        }
        plaatsOfVerhuisAsielzoeker(vluchteling, vindTerApel());
        System.out.println("Asielzoeker " + vluchteling.getNaam() + " is succesvol geregistreerd in 'Aanmeldcentrum Ter Apel'");
    }

    public void plaatsOfVerhuisAsielzoeker(Vluchteling vluchteling, AZC besteAZC) {
        if (vluchteling.getVerblijfplaats() != null) {
            vluchteling.getVerblijfplaats().getBewonersManager().verwijderBewoner(vluchteling);
        }
        besteAZC.getBewonersManager().voegVluchtelingToe(vluchteling);
        vluchteling.setVerblijfplaats(besteAZC);
        besteAZC.getGemeente().addVluchteling(vluchteling);
        System.out.println("Asielzoeker " + vluchteling.getNaam() + " is verhuisd naar " + besteAZC.getGemeente().getNaam() + " naar " + besteAZC.getNaam());
        besteAZC.getBerichtenBox().voegBerichtToe(new Bericht("plaatsing", vluchteling, null, "Nieuwe asielzoeker geplaatst", besteAZC.getNaam(), false));

    }

    public void registreerVertrek(Vluchteling vertrekVluchteling) {
        vertrekVluchteling.getDossier().setTeruggekeerdNaarHerkomstland(true);
        System.out.println("Het vertrek van de vluchteling " + vertrekVluchteling.getNaam() + " is geregistreerd.");
        verwijderVluchtelingUitAZC(vertrekVluchteling);
        for (AZC azc : getAZCs()) {
            azc.getBerichtenBox().voegBerichtToe(new Bericht("vertrek", vertrekVluchteling, null, "de asielzoeker is vertrokken naar het land van de herkomst.", "", false));
        }
    }

    private AZC vindTerApel() {
        for (Gemeente g : gemeentes) {
            for (AZC azc : g.getAZCs()) {
                if (azc.getNaam().equalsIgnoreCase("Aanmeldcentrum Ter Apel")) {
                    return azc;
                }
            }
        }
        return null;
    }

    private void verwijderVluchtelingUitAZC(Vluchteling vluchteling) {
        for (Gemeente gemeente : gemeentes) {
            for (AZC azc : gemeente.getAZCs()) {
                if (azc.getBewonersManager().bevatVluchteling(vluchteling)) {
                    azc.getBewonersManager().verwijderBewoner(vluchteling);
                    System.out.println("Vluchteling " + vluchteling.getNaam() + " is verwijderd uit " + azc.getNaam());
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
