import java.util.ArrayList;

public class BewonersManager {
    private ArrayList<Vluchteling> bewoners;

    public BewonersManager() {
        this.bewoners = new ArrayList<>();
    }

    public ArrayList<Vluchteling> getBewoners() {
        return bewoners;
    }

    public void setBewoners(ArrayList<Vluchteling> bewoners) {
        this.bewoners = bewoners;
    }

    public int getBezetting() {
        return bewoners.size();
    }

    public boolean bevatVluchteling(Vluchteling vluchteling) {
        return bewoners.contains(vluchteling);
    }

    public void voegVluchtelingToe(Vluchteling vluchteling) {
        bewoners.add(vluchteling);
    }

    public void verwijderBewoner(Vluchteling vluchteling) {
        bewoners.remove(vluchteling);
    }
}
