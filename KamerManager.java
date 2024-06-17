import java.util.ArrayList;

public class KamerManager {
    private ArrayList<Kamer> kamers;

    public KamerManager() {
        this.kamers = new ArrayList<>();
    }

    public ArrayList<Kamer> getKamers() {
        return kamers;
    }

    public void voegKamerToe(Kamer kamer) {
        kamers.add(kamer);
    }

    public Kamer vindGeschikteKamer(Vluchteling vluchteling, Familie familie) {
        for (Kamer kamer : kamers) {
            if (isGeschikteKamer(kamer, vluchteling, familie)) {
                return kamer;
            }
        }
        return null;
    }

    private boolean isGeschikteKamer(Kamer kamer, Vluchteling vluchteling, Familie familie) {
        if (familie != null) {
            return kamer.isGezinsKamer() && kamer.getVrijePlaatsen() >= familie.getLeden().size() + 1;
        } else {
            return kamer.isGeschiktVoor(vluchteling);
        }
    }

    public Kamer vindKamerVanBewoner(Vluchteling vluchteling) {
        for (Kamer kamer : kamers) {
            if (kamer.getBewoners().contains(vluchteling)) {
                return kamer;
            }
        }
        return null;
    }
}
