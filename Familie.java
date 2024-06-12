import java.util.ArrayList;

public class Familie {
    private String naam;
    private ArrayList<Vluchteling> leden;

    public Familie(String naam) {
        this.leden = new ArrayList<>();
        this.naam = naam;
    }

    public ArrayList<Vluchteling> getLeden() {
        return leden;
    }

    public void setLeden(ArrayList<Vluchteling> leden) {
        this.leden = leden;
    }

    public void voegLidToe(Vluchteling lid) {
        leden.add(lid);
    }

    public void verwijderLid(Vluchteling lid) {
        leden.remove(lid);
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }
}
