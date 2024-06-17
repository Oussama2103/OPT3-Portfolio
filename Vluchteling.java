import java.util.ArrayList;

public class Vluchteling extends Subject {
    private String naam;
    private int leeftijd;
    private String gender;
    private boolean landVanHerkomst;
    private Familie familie;
    private Dossier dossier;
    private AZC verblijfplaats;
    private boolean isNonBinair;


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

    public String setAdres (String adres) {
        return adres;
    }
    public boolean getLandVanHerkomst() {
        return landVanHerkomst;
    }

    public boolean setLandVanHerkomst(boolean landVanHerkomst) {
        return landVanHerkomst;
    }

    public void setFamilie(Familie familie) {
        this.familie = familie;
    }

    public Familie getFamilie() {
        return familie;
    }

    public Dossier getDossier() {
        return dossier;
    }

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }

    public boolean isNonBinair() {
        return isNonBinair;
    }

    public void setNonBinair(boolean nonBinair) {
        isNonBinair = nonBinair;
    }

    public AZC getVerblijfplaats() {
        return verblijfplaats;
    }

    public void setLeeftijd(int leeftijd) {
        this.leeftijd = leeftijd;
    }

    public void setVerblijfplaats(AZC verblijfplaats) {
        this.verblijfplaats = verblijfplaats;
    }

    public Dossier dossierInzien(Vluchteling vluchteling) {
        return vluchteling.getDossier();
    }

    public boolean isNonBinair(boolean isNonBinair) {
        return "non-binair".equalsIgnoreCase(this.gender);
    }
}
