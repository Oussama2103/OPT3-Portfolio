import java.util.ArrayList;

public class Gemeente {
    private String naam;
    private int aantalInwoners;
    private ArrayList<Vluchteling> vluchtelingen;
    private ArrayList<AZC> azcs;
    private KamerManager kamerManager;

    public Gemeente(String naam, int aantalInwoners) {
        this.naam = naam;
        this.aantalInwoners = aantalInwoners;
        this.azcs = new ArrayList<>();
        this.vluchtelingen = new ArrayList<>();
        this.kamerManager = new KamerManager();
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getAantalInwoners() {
        return aantalInwoners;
    }

    public void setAantalInwoners(int aantalInwoners) {
        this.aantalInwoners = aantalInwoners;
    }

    public ArrayList<AZC> getAZCs() {
        return azcs;
    }

    public ArrayList<Vluchteling> getVluchtelingen() {
        return vluchtelingen;
    }

    public void addVluchteling(Vluchteling vluchteling) {
        vluchtelingen.add(vluchteling);
    }

    public void removeVluchteling(Vluchteling vluchteling) {
        vluchtelingen.remove(vluchteling);
    }

    public void addAZC(AZC azc) {
        azcs.add(azc);
    }

    public void removeAZC(AZC azc) {
        azcs.remove(azc);
    }

    public int getBeschikbarePlaatsen() {
        int beschikbarePlaatsen = 0;
        for (AZC azc : azcs) {
            for (Kamer kamer : kamerManager.getKamers()) {
                beschikbarePlaatsen += kamer.getVrijePlaatsen();
            }
        }
        return beschikbarePlaatsen;
    }

    public int getDaadwerkelijkePlaatsing() {
        int daadwerkelijkePlaatsing = 0;
        for (AZC azc : azcs) {
            for (Kamer kamer : kamerManager.getKamers()) {
                daadwerkelijkePlaatsing += kamer.getBewoners().size();
            }
        }
        return daadwerkelijkePlaatsing;
    }
}
