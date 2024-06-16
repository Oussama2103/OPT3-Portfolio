import java.util.Observable;

public class COAMedewerker implements Observer{
    private String naam;
    private VluchtelingManager vluchtelingManager;
    private FamilieManager familieManager;
    private DossierManager dossierManager;


    public COAMedewerker(String naam) {
        this.naam = naam;
        this.dossierManager = new DossierManager();
        this.vluchtelingManager = new VluchtelingManager();
        this.familieManager = new FamilieManager();
    }

    public void registreerVluchteling(Vluchteling vluchteling, boolean heeftPaspoort) {
        vluchtelingManager.registreerVluchteling(vluchteling, heeftPaspoort);
    }

    public void registreerFamilie(Familie familie, boolean hebbenPaspoorten) {
        familieManager.registreerFamilie(familie, hebbenPaspoorten);
    }

    public void plaatsOfVerhuisAsielzoeker(Vluchteling vluchteling, AZC besteAZC) {
        vluchtelingManager.plaatsOfVerhuisAsielzoeker(vluchteling, besteAZC);
    }

    public void plaatsOfVerhuisFamilie(Familie familie, AZC besteAZC) {
        familieManager.plaatsOfVerhuisFamilie(familie, besteAZC);
    }

    public void registreerVertrek(Vluchteling vertrekVluchteling) {
        vluchtelingManager.registreerVertrek(vertrekVluchteling);
    }

    public void registreerVertrekFamilie(Familie familie) {
        familieManager.registreerVertrekFamilie(familie);
    }

    public void updateDossier(Vluchteling dossierVluchteling, boolean asielAanvraagAfgerond, String uitspraakIND, boolean statusEigenWoning, boolean teruggekeerdNaarHerkomstland) {
        dossierManager.updateDossier(dossierVluchteling, asielAanvraagAfgerond, uitspraakIND, statusEigenWoning, teruggekeerdNaarHerkomstland);
    }

    @Override
    public void update(Bericht bericht) {
        System.out.println("Bericht ontvangen door COA Medewerker: " + bericht.getInhoud());
        // Verwerk het bericht indien nodig
    }
}
