import java.util.ArrayList;

public class AZCMedewerker extends MessageHandler {
    private String naam;
    private AZC azc;
    private UserInputHandler userInputHandler;

    public AZCMedewerker(String naam, AZC azc, UserInputHandler userInputHandler) {
        this.naam = naam;
        this.azc = azc;
        this.userInputHandler = userInputHandler;
    }

    public void bekijkNietVerwerkteBerichten() {
        ArrayList<Bericht> berichten = getNietVerwerkteBerichten();
        toonNietVerwerkteBerichten(berichten);
    }

    private ArrayList<Bericht> getNietVerwerkteBerichten() {
        return azc.getBerichtManager().getNietVerwerkteBerichten(this);
    }

    private void toonNietVerwerkteBerichten(ArrayList<Bericht> berichten) {
        System.out.println("Niet verwerkte berichten in de berichtenbox van het AZC:");
        for (Bericht bericht : berichten) {
            System.out.println(bericht);
        }
    }

    public void selecteerBerichtEnVerwerk() {
        ArrayList<Bericht> berichten = getNietVerwerkteBerichten();
        if (berichten.isEmpty()) {
            System.out.println("Geen niet-verwerkte berichten beschikbaar.");
            return;
        }

        int keuze = userInputHandler.kiesBericht(berichten);
        if (userInputHandler.isGeldigeKeuze(keuze, berichten.size())) {
            verwerkBericht(berichten.get(keuze - 1));
        } else {
            System.out.println("Ongeldige keuze, probeer opnieuw.");
        }
    }

    private void verwerkBericht(Bericht bericht) {
        System.out.println("Geselecteerd bericht:");
        userInputHandler.toonBerichtDetails(bericht);

        int actie = userInputHandler.kiesActie();

        if (actie == 1) {
            processSpecificMessage(bericht);
            bericht.setProcessed(true);
        } else {
            System.out.println("Actie geannuleerd.");
        }
    }

    @Override
    protected boolean validateMessage(Bericht bericht) {
        return !bericht.isProcessed() && bericht.getAzcNaam().equals(azc.getNaam()) &&
                ("plaatsing".equals(bericht.getType()) || "vertrek".equals(bericht.getType()));
    }

    @Override
    protected void processSpecificMessage(Bericht bericht) {
        switch (bericht.getType()) {
            case "plaatsing":
                plaatsVluchteling(bericht.getVluchteling(), bericht.getFamilie());
                break;
            case "vertrek":
                vertrekVluchteling(bericht.getVluchteling(), bericht.getFamilie());
                break;
            default:
                System.out.println("Onbekend berichttype: " + bericht.getType());
        }
    }

    private void plaatsVluchteling(Vluchteling vluchteling, Familie familie) {
        Kamer kamer = vindGeschikteKamer(vluchteling, familie);
        if (kamer != null) {
            kamer.addBewoner(vluchteling);
            if (familie != null) {
                for (Vluchteling gezinslid : familie.getLeden()) {
                    kamer.addBewoner(gezinslid);
                }
            }
            System.out.println("Vluchteling " + vluchteling.getNaam() + " en gezin geplaatst in kamer " + kamer.getKamerNummer());
        } else {
            System.out.println("Geen geschikte kamer gevonden voor vluchteling " + vluchteling.getNaam());
        }
    }

    private Kamer vindGeschikteKamer(Vluchteling vluchteling, Familie familie) {
        for (Kamer kamer : azc.getKamerManager().getKamers()) {
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

    private void vertrekVluchteling(Vluchteling vluchteling, Familie familie) {
        Kamer kamer = azc.getKamerManager().vindKamerVanBewoner(vluchteling);
        if (kamer != null) {
            kamer.removeBewoner(vluchteling);
            if (familie != null) {
                for (Vluchteling gezinslid : familie.getLeden()) {
                    kamer.removeBewoner(gezinslid);
                }
            }
            System.out.println("Vluchteling " + vluchteling.getNaam() + " en gezin vertrokken uit kamer " + kamer.getKamerNummer());
        } else {
            System.out.println("Vluchteling " + vluchteling.getNaam() + " was niet gevonden in het AZC.");
        }
    }
}
