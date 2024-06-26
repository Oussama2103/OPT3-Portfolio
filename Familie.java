import java.util.ArrayList;

public class Familie {
    private String naam;
    private ArrayList<Vluchteling> leden;

    public Familie(String naam) {
        this.naam = naam;
        this.leden = new ArrayList<>();
    }

    public ArrayList<Vluchteling> getLeden() {
        return leden;
    }

    public void voegLidToe(Vluchteling lid) {
        leden.add(lid);
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }
}