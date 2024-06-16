import java.util.ArrayList;

public class BerichtenBox {
    private ArrayList<Bericht> berichten;

    public BerichtenBox() {
        this.berichten = new ArrayList<>();
    }

    public void voegBerichtToe(Bericht bericht) {
        berichten.add(bericht);
    }

    public ArrayList<Bericht> getBerichten() {
        return berichten;
    }

}
