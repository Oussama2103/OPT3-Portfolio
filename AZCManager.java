import java.util.ArrayList;

public class AZCManager {
    private ArrayList<Gemeente> gemeentes;

    public AZCManager(ArrayList<Gemeente> gemeentes) {
        this.gemeentes = (ArrayList<Gemeente>) DataSeeder.gemeentes;
    }

    public AZC vindBesteAZC() {
        Gemeente besteGemeente = null;
        int maxBeschikbarePlekken = 0;

        for (Gemeente gemeente : gemeentes) {
            int beschikbarePlekken = gemeente.beschikbarePlaatsen();
            if (beschikbarePlekken > maxBeschikbarePlekken) {
                maxBeschikbarePlekken = beschikbarePlekken;
                besteGemeente = gemeente;
            }
        }

        if (besteGemeente == null || maxBeschikbarePlekken == 0) {
            double minPercentage = Double.MAX_VALUE;

            for (Gemeente gemeente : gemeentes) {
                int inwoners = gemeente.getAantalInwoners();
                int beschikbarePlekken = gemeente.beschikbarePlaatsen();
                double percentage = (double) beschikbarePlekken / inwoners;

                if (percentage < minPercentage) {
                    minPercentage = percentage;
                    besteGemeente = gemeente;
                }
            }
        }

        if (besteGemeente != null) {
            for (AZC azc : besteGemeente.getAZCs()) {
                if (azc.getCapaciteit() > azc.getBewonersManager().getBezetting()) {
                    return azc;
                }
            }
        }

        return null;
    }



    public void plaatsVluchtelingInAZC(Vluchteling vluchteling, AZC azc) {
        azc.getBewonersManager().voegVluchtelingToe(vluchteling);
        vluchteling.setVerblijfplaats(azc);
        azc.getBerichtenBox().voegBerichtToe(new Bericht("plaatsing", vluchteling, null, "Nieuwe asielzoeker geplaatst in AZC", azc.getNaam(), false));
    }

    public static Vluchteling vindVluchteling(String naam, ArrayList<Gemeente> gemeentes) {
        for (Gemeente gemeente : gemeentes) {
            for (AZC azc : gemeente.getAZCs()) {
                for (Vluchteling vluchteling : azc.getBewonersManager().getBewoners()) {
                    if (vluchteling.getNaam().equalsIgnoreCase(naam)) {
                        return vluchteling;
                    }
                }
            }
        }
        return null;
    }
}
