import java.util.ArrayList;

class Berichtenbox {
    private ArrayList<Bericht> berichten;

    public Berichtenbox() {
        this.berichten = new ArrayList<>();
    }

    public ArrayList<Bericht> getBerichten() {
        return berichten;
    }

    public void setBerichten(ArrayList<Bericht> berichten) {
        this.berichten = berichten;
    }

    public void addBericht(Bericht bericht) {
        this.berichten.add(bericht);
    }
}
