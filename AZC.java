import java.util.ArrayList;

public class AZC {
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

    public KamerManager getKamerManager() {
        return kamerManager;
    }

    public BerichtenBox getBerichtenBox() {
        return berichtenBox;
    }

    public int getCapaciteit() {
        return capaciteit;
    }

    public int beschikbarePlaatsen() {
        return capaciteit - bewonersManager.getBezetting();
    }

    public BewonersManager getBewonersManager() {
        return bewonersManager;
    }

    public boolean plaatsVluchteling(Vluchteling vluchteling, Familie familie) {
        Kamer kamer = kamerManager.vindGeschikteKamer(vluchteling, familie);
        if (kamer != null) {
            if (familie == null || !familie.getLeden().isEmpty()) {
                if (familie == null) {
                    kamer.addBewoner(vluchteling);
                    System.out.println("Vluchteling " + vluchteling.getNaam() + " geplaatst in kamer " + kamer.getKamerNummer());
                } else {
                    for (Vluchteling familielid : familie.getLeden()) {
                        kamer.addBewoner(familielid);
                    }
                    System.out.println("Gezin " + familie.getNaam() + " geplaatst in kamer " + kamer.getKamerNummer());
                }
                return true;
            } else {
                System.out.println("Geen geschikte kamer gevonden voor " + (familie == null ? "vluchteling " + vluchteling.getNaam() : "gezin " + familie.getNaam()));
                return false;
            }
        } else {
            System.out.println("Geen geschikte kamer gevonden voor " + (familie == null ? "vluchteling " + vluchteling.getNaam() : "gezin " + familie.getNaam()));
            return false;
        }
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
}
