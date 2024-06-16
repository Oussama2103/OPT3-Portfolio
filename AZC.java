import java.util.ArrayList;
import java.util.Observer;
import java.util.Observable;

public class AZC implements Observer {
    private String naam;
    private String adres;
    private Gemeente gemeente;
    private int capaciteit;
    private KamerManager kamerManager;
    private BewonersManager bewonersManager;
    private BerichtenBox berichtenBox;

    public AZC(String naam, String adres, Gemeente gemeente, int capaciteit) {
        this.naam = naam;
        this.adres = adres;
        this.gemeente = gemeente;
        this.capaciteit = capaciteit;
        this.kamerManager = new KamerManager();
        this.berichtenBox = new BerichtenBox();
        this.bewonersManager = new BewonersManager();
    }


    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getAdres() {
        return adres;
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

    public BewonersManager getBewonersManager() {
        return bewonersManager;
    }

    public BerichtenBox getBerichtenBox() {
        return berichtenBox;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Bericht) {
            Bericht bericht = (Bericht) arg;
            berichtenBox.voegBerichtToe(bericht);
            System.out.println("Bericht ontvangen in AZC " + naam + ": " + bericht.getInhoud());
        }
    }

    public int getCapaciteit() {
        return capaciteit;
    }

    public int beschikbarePlaatsen (int capaciteit) {
        return capaciteit - getBewonersManager().getBezetting();
    }
}
