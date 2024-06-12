import java.util.ArrayList;

public class BerichtManager {
    private ArrayList<Bericht> berichten;

    public BerichtManager() {
        this.berichten = new ArrayList<>();
    }

    public ArrayList<Bericht> getBerichten() {
        return berichten;
    }

    public void setBerichten(ArrayList<Bericht> berichten) {
        this.berichten = berichten;
    }

    public ArrayList<Bericht> getNietVerwerkteBerichten(MessageHandler handler) {
        ArrayList<Bericht> nietVerwerkteBerichten = new ArrayList<>();
        for (Bericht bericht : berichten) {
            if (!bericht.isProcessed() && handler.validateMessage(bericht)) {
                nietVerwerkteBerichten.add(bericht);
            }
        }
        return nietVerwerkteBerichten;
    }

    public void ontvangBericht(Bericht bericht) {
        this.berichten.add(bericht);
    }
}
