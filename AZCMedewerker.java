import java.util.ArrayList;
import java.util.Scanner;

public class AZCMedewerker extends MessageHandler {
    private String naam;
    private AZC azc;


    public AZCMedewerker(String naam, AZC azc) {
        this.naam = naam;
        this.azc = azc;

    }

    public void bekijkNietVerwerkteBerichten() {
        ArrayList<Bericht> berichten = getNietVerwerkteBerichten();
        toonNietVerwerkteBerichten(berichten);
    }

    private ArrayList<Bericht> getNietVerwerkteBerichten() {
        ArrayList<Bericht> berichten = azc.getBerichtenBox().getBerichten();

        ArrayList <Bericht> gefitreerdeBerichten = new ArrayList<>();

        for (Bericht b : berichten) {
            if (validateMessage(b)) {
                gefitreerdeBerichten.add(b);
            }
        }
        return gefitreerdeBerichten;
    }

    private void toonNietVerwerkteBerichten(ArrayList<Bericht> berichten) {
        if (berichten.isEmpty()) {
            System.out.println("Geen niet-verwerkte berichten beschikbaar.");
        } else {
            System.out.println("Niet verwerkte berichten in de berichtenbox van het AZC:");
            for (Bericht bericht : berichten) {
                bericht.toonBericht();
            }
        }
    }

    public void selecteerBerichtEnVerwerk() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Bericht> berichten = getNietVerwerkteBerichten();
        if (berichten.isEmpty()) {
            System.out.println("Geen niet-verwerkte berichten beschikbaar.");
            return;
        }

        for (int i = 0; i < berichten.size(); i++) {
            System.out.println(i+1 + ". " + berichten.get(i).getAzcNaam() + ": " + berichten.get(i).getType() );
        }
        System.out.print("Selecteer een bericht: ");
        int keuze = scanner.nextInt();
        scanner.nextLine();
        if (keuze >= 1 && keuze <= berichten.size()) {
            verwerkBericht(berichten.get(keuze - 1));
        } else {
            System.out.println("Ongeldige keuze, probeer opnieuw.");
        }
    }

    private void verwerkBericht(Bericht bericht) {
        System.out.println("Bericht verwerken...");


        processSpecificMessage(bericht);
        bericht.setProcessed(true);
    }

    @Override
    protected boolean validateMessage(Bericht bericht) {
        return !bericht.isProcessed() && bericht.getAzcNaam().equals(azc.getNaam()) &&
                ("plaatsing".equalsIgnoreCase(bericht.getType()) || "vertrek".equalsIgnoreCase(bericht.getType()) || "verhuising".equalsIgnoreCase(bericht.getType()));
    }

    @Override
    protected void processSpecificMessage(Bericht bericht) {
        switch (bericht.getType()) {
            case "plaatsing":
                plaatsen(bericht.getVluchteling(), bericht.getFamilie(), bericht);
                break;
            case "vertrek":
                vertrekVluchteling(bericht.getVluchteling(), bericht.getFamilie());
                break;
            default:
                System.out.println("Onbekend berichttype: " + bericht.getType());
        }
    }

    private void plaatsen(Vluchteling vluchteling, Familie familie, Bericht bericht) {
        Kamer gevestigdKamer = null;
        if (vluchteling.getFamilie() == null) {
            plaatsenVluchteling(vluchteling, gevestigdKamer, bericht);
        }
        else {
           plaatsenGezin(vluchteling, familie, gevestigdKamer);
        }
    }

    private void plaatsenVluchteling (Vluchteling vluchteling, Kamer kamer, Bericht bericht) {
        kamer = vindGeschikteKamer(vluchteling, null);
        if (kamer != null) {
            kamer.addBewoner(vluchteling);
            System.out.println("Vluchteling " + vluchteling.getNaam() + " geplaatst in kamer " + kamer.getKamerNummer());
        } else {
            System.out.println("Geen geschikte kamer gevonden voor vluchteling " + vluchteling.getNaam());
            bericht.setProcessed(false);
        }
    }

    private void plaatsenGezin (Vluchteling vluchteling, Familie familie, Kamer kamer) {
        kamer = vindGeschikteKamer(vluchteling, familie);
        if (kamer != null) {
            for (Vluchteling f : familie.getLeden()) {
                kamer.addBewoner(f);
            }
            System.out.println("Gezin " + familie.getNaam() + " geplaatst in kamer " + kamer.getKamerNummer());
        } else {
            System.out.println("Geen geschikte kamer gevonden voor gezin " + familie.getNaam());
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
