import java.util.ArrayList;

public class CommunicationManager {
    private ArrayList<Gemeente> gemeentes;

    public CommunicationManager(ArrayList<Gemeente> gemeentes) {
        this.gemeentes = gemeentes;
    }

    public void verstuurBerichtNaarAZCs(Bericht bericht) {
        for (Gemeente gemeente : gemeentes) {
            for (AZC azc : gemeente.getAZCs()) {
                azc.getBerichtenBox().voegBerichtToe(bericht);
            }
        }
    }
}
