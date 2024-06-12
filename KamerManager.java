import java.util.ArrayList;

public class KamerManager {
    private ArrayList<Kamer> kamers;

    public KamerManager() {
        this.kamers = new ArrayList<>();
    }

    public void addKamer(Kamer kamer) {
        kamers.add(kamer);
    }

    public Kamer vindGeschikteKamer(Vluchteling vluchteling) {
        for (Kamer kamer : kamers) {
            if (kamer.isGeschiktVoor(vluchteling)) {
                return kamer;
            }
        }
        return null;
    }

    public ArrayList<Kamer> getKamers() {
        return kamers;
    }

    public Kamer vindKamerVanBewoner(Vluchteling vluchteling) {
        for (Kamer kamer : kamers) {
            if (kamer.bevatBewoner(vluchteling)) {
                return kamer;
            }
        }
        return null;
    }
}
