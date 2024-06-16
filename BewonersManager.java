import java.util.ArrayList;

public class BewonersManager {
    private ArrayList<Vluchteling> bewoners;
    private ArrayList<Familie> families;

    public BewonersManager() {
        this.bewoners = new ArrayList<>();
        this.families = new ArrayList<>();
    }

    public ArrayList<Vluchteling> getBewoners() {
        return bewoners;
    }

    public void voegVluchtelingToe(Vluchteling vluchteling) {
        bewoners.add(vluchteling);
    }

    public void verwijderBewoner(Vluchteling vluchteling) {
        bewoners.remove(vluchteling);
    }

    public int getBezetting() {
        return bewoners.size();
    }

    public boolean bevatVluchteling(Vluchteling vluchteling) {
        return bewoners.contains(vluchteling);
    }

    public void voegFamilieToe(Familie familie) {
        families.add(familie);
        bewoners.addAll(familie.getLeden());
    }

    public void verwijderFamilie(Familie familie) {
        families.remove(familie);
        bewoners.removeAll(familie.getLeden());
    }

    public ArrayList<Familie> getFamilies() {
        return families;
    }

    public boolean bevatFamilie(Familie familie) {
        return families.contains(familie);
    }
}
