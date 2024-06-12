import java.util.ArrayList;

public class COAMedewerker implements Observer {
    private String naam;
    private ArrayList<Gemeente> gemeentes;
    private KamerManager kamerManager;

    public COAMedewerker(String naam) {
        this.naam = naam;
        this.gemeentes = (ArrayList<Gemeente>) DataSeeder.gemeentes;
        this.kamerManager = new KamerManager();
        DataSeeder.seedData();
    }

    public void registreerVluchteling(Vluchteling vluchteling, boolean heeftPaspoort) {
        if (heeftPaspoort) {
            vluchteling.setDossier(new Dossier(vluchteling));
            updateDossier(vluchteling, false, "Geen", false, false);
        }

        AZC terApel = vindTerApel();
        if (terApel != null) {
            plaatsOfVerhuisAsielzoeker(vluchteling, terApel);
            System.out.println("Asielzoeker " + vluchteling.getNaam() + " is succesvol geregistreerd in 'Aanmeldcentrum Ter Apel'");
        } else {
            System.out.println("Aanmeldcentrum Ter Apel niet gevonden.");
        }
    }

    public void registreerFamilie(Familie familie, boolean hebbenPaspoorten) {
        for (Vluchteling lid : familie.getLeden()) {
            if (lid.getLandVanHerkomst()) {
                lid.setDossier(new Dossier(lid));
                updateDossier(lid, false, "Geen", false, false);
            }
            registreerVluchteling(lid, hebbenPaspoorten);
        }
    }

    private AZC vindTerApel() {
        for (Gemeente g : gemeentes) {
            for (AZC azc : g.getAZCs()) {
                if (azc.getNaam().equalsIgnoreCase("Aanmeldcentrum Ter Apel")) {
                    return azc;
                }
            }
        }
        return null;
    }

    public Gemeente besteGemeente() {
        Gemeente besteGemeente = null;
        int meestePlekken = 0;
        for (Gemeente g : gemeentes) {
            if (g.getBeschikbarePlaatsen() > meestePlekken) {
                besteGemeente = g;
                meestePlekken = g.getBeschikbarePlaatsen();
            }
        }
        return besteGemeente;
    }

    public AZC besteAZC(Gemeente gemeente) {
        AZC besteAZC = null;
        int minstBezetting = Integer.MAX_VALUE;
        for (AZC azc : gemeente.getAZCs()) {
            if (azc.getBewonersManager().getBezetting() < minstBezetting) {
                besteAZC = azc;
                minstBezetting = azc.getBewonersManager().getBezetting();
            }
        }
        return besteAZC;
    }

    public void plaatsOfVerhuisAsielzoeker(Vluchteling vluchteling, AZC besteAZC) {
        if (vluchteling.getVerblijfplaats() != null) {
            vluchteling.getVerblijfplaats().getBewonersManager().verwijderBewoner(vluchteling);
        }
        besteAZC.getBewonersManager().voegVluchtelingToe(vluchteling);
        vluchteling.setVerblijfplaats(besteAZC);
        besteAZC.getGemeente().addVluchteling(vluchteling);

        System.out.println("Asielzoeker " + vluchteling.getNaam() + " is verhuisd naar " + besteAZC.getGemeente().getNaam() + " naar " + besteAZC.getNaam());

        verstuurBericht(new Bericht("plaatsing", vluchteling, null, "Nieuwe asielzoeker geplaatst", besteAZC.getNaam()), besteAZC);
    }

    public void plaatsOfVerhuisFamilie(Familie familie, AZC besteAZC) {
        for (Vluchteling lid : familie.getLeden()) {
            plaatsOfVerhuisAsielzoeker(lid, besteAZC);
            verstuurBericht(new Bericht("plaatsing", lid, familie, "Nieuwe asielzoeker geplaatst in AZC", besteAZC.getNaam()), besteAZC);
        }
        System.out.println("Familie " + familie.getNaam() + " is verhuisd naar " + besteAZC.getNaam());
    }

    public void plaatsInKamer(AZC azc, Vluchteling vluchteling) {
        Kamer kamer = kamerManager.vindGeschikteKamer(vluchteling);
        if (kamer != null && kamer.addBewoner(vluchteling)) {
            vluchteling.setVerblijfplaats(azc);
            azc.getGemeente().addVluchteling(vluchteling);

            System.out.println("Asielzoeker " + vluchteling.getNaam() + " is geplaatst in kamer in " + azc.getNaam());

            verstuurBericht(new Bericht("plaatsing", vluchteling, null, "Nieuwe asielzoeker geplaatst in AZC", azc.getNaam()), azc);
        } else {
            System.out.println("Geen geschikte kamer gevonden in AZC " + azc.getNaam() + ".");
        }
    }

    public void updateDossier(Vluchteling vluchteling, boolean asielaanvraagAfgerond, String uitspraakIND, boolean plaatsingEigenWoningGestart, boolean isTeruggekeerd) {
        Dossier dossier = vluchteling.getDossier();
        if (dossier != null) {
            dossier.setAsielAanvraagAfgerond(asielaanvraagAfgerond);
            if (asielaanvraagAfgerond) {
                dossier.setUitspraakIND(uitspraakIND);
                if (uitspraakIND.equalsIgnoreCase("Verblijfsvergunning")) {
                    dossier.setStatusEigenWoning(plaatsingEigenWoningGestart ? "ja" : "nee");
                }
            }
            dossier.setTeruggekeerdNaarHerkomstland(isTeruggekeerd);
        }

        System.out.println("Het dossier is succesvol bijgewerkt.");

        if (vluchteling.getVerblijfplaats() != null) {
            verstuurBericht(new Bericht("vertrek", vluchteling, null, "Dossier update voor asielzoeker", vluchteling.getVerblijfplaats().getNaam()), vluchteling.getVerblijfplaats());
        }
    }

    public void registreerVertrek(Vluchteling vertrekVluchteling) {
        vertrekVluchteling.getDossier().setTeruggekeerdNaarHerkomstland(true);
        System.out.println("Het vertrek van de vluchteling " + vertrekVluchteling.getNaam() + " is geregistreerd.");

        for (Gemeente gemeente : gemeentes) {
            for (AZC azc : gemeente.getAZCs()) {
                if (azc.getBewonersManager().bevatVluchteling(vertrekVluchteling)) {
                    azc.getBewonersManager().verwijderBewoner(vertrekVluchteling);
                    System.out.println("Vluchteling " + vertrekVluchteling.getNaam() + " is verwijderd uit " + azc.getNaam());

                    verstuurBericht(new Bericht("vertrek", vertrekVluchteling, null, "Vertrek van asielzoeker geregistreerd", null), azc);
                    return;
                }
            }
        }
    }

    private void verstuurBericht(Bericht bericht, AZC azc) {
        azc.update(bericht);
    }

    @Override
    public void update(Bericht bericht) {
        if (bericht.getType().equals("plaatsing")) {
            Gemeente gemeente = bericht.getGemeente();
            if (gemeente != null) {
                System.out.println("Update ontvangen van gemeente " + gemeente.getNaam() + ": " + bericht.getDetails());
            } else {
                System.out.println("Update ontvangen van een ongeldige gemeente: " + bericht.getDetails());
            }
        } else if (bericht.getType().equals("vertrek")) {
            System.out.println("Update ontvangen van AZC " + bericht.getAzcNaam() + ": " + bericht.getDetails());
        }
    }
}
