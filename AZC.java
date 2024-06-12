public class AZC implements Observer {
    private String naam;
    private String adres;
    private Gemeente gemeente;
    private KamerManager kamerManager;
    private BewonersManager bewonersManager;
    private BerichtManager berichtManager;

    public AZC(String naam, String adres, Gemeente gemeente, KamerManager kamerManager, BewonersManager bewonersManager, BerichtManager berichtManager) {
        this.naam = naam;
        this.adres = adres;
        this.gemeente = gemeente;
        this.kamerManager = kamerManager;
        this.bewonersManager = bewonersManager;
        this.berichtManager = berichtManager;
    }

    // Getters and Setters

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public Gemeente getGemeente() {
        return gemeente;
    }

    public void setGemeente(Gemeente gemeente) {
        this.gemeente = gemeente;
    }

    public KamerManager getKamerManager() {
        return kamerManager;
    }

    public void setKamerManager(KamerManager kamerManager) {
        this.kamerManager = kamerManager;
    }

    public BewonersManager getBewonersManager() {
        return bewonersManager;
    }

    public void setBewonersManager(BewonersManager bewonersManager) {
        this.bewonersManager = bewonersManager;
    }

    public BerichtManager getBerichtManager() {
        return berichtManager;
    }

    public void setBerichtManager(BerichtManager berichtManager) {
        this.berichtManager = berichtManager;
    }

    @Override
    public void update(Bericht bericht) {
        berichtManager.ontvangBericht(bericht);
    }
}
