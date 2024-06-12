class Vluchteling extends Subject {
    private String naam;
    private int leeftijd;
    private String gender;
    private boolean landVanHerkomst;
    private Familie familie;
    private Dossier dossier;
    private AZC verblijfplaats;

    public Vluchteling(String naam, int leeftijd, String gender, boolean landVanHerkomst, Familie familie) {
        this.naam = naam;
        this.leeftijd = leeftijd;
        this.gender = gender;
        this.landVanHerkomst = landVanHerkomst;
        this.familie = familie;
        this.dossier = new Dossier(this);
    }

    public String getNaam() {
        return naam;
    }

    public int getLeeftijd() {
        return leeftijd;
    }

    public String getGender() {
        return gender;
    }

    public boolean getLandVanHerkomst() {
        return landVanHerkomst;
    }

    public Familie getFamilie() {
        return familie;
    }

    public void setFamilie(Familie familie) {
        this.familie = familie;
    }

    public Dossier getDossier() {
        return dossier;
    }

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }

    public AZC getVerblijfplaats() {
        return verblijfplaats;
    }

    public void setVerblijfplaats(AZC verblijfplaats) {
        this.verblijfplaats = verblijfplaats;
    }

    public Dossier dossierInzien(Vluchteling vluchteling) {
        return vluchteling.getDossier();
    }

    public void registreerNieuwAdres(Vluchteling vluchteling, String nieuwAdres) {
        if (dossier != null && "ja".equalsIgnoreCase(dossier.getStatusEigenWoning())) {
            dossier.setStatusEigenWoning("afgerond");
            // Bericht aan AZC's sturen
            System.out.println("Adres geregistreerd: " + nieuwAdres);
            System.out.println("Plaatsing in eigen woning is afgerond.");
            stuurBerichtNaarAZCs("Adres geregistreerd: " + nieuwAdres);

            // Specifiek bericht naar verblijfplaats AZC als vertrekmelding
            if (this.verblijfplaats != null) {
                this.verblijfplaats.getBerichtManager().ontvangBericht(new Bericht("Vertrek", this, null, "Vluchteling vertrekt naar eigen woning", this.verblijfplaats.getNaam()));
            }
        } else {
            System.out.println("Plaatsing in eigen woning is niet opgestart.");
        }
    }

    private void stuurBerichtNaarAZCs(String berichtDetails) {
        // Logica om een bericht naar alle relevante AZC's te sturen
        Bericht bericht = new Bericht("Verhuizing", this, null, berichtDetails, null);
        this.notifyObservers(bericht); // Notify all observers with the bericht
    }

    public boolean isNonBinair() {
        return "non-binair".equalsIgnoreCase(this.gender);
    }
}
