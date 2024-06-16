import java.util.ArrayList;

public class Kamer {
    private int kamerNummer;
    private int totalePlaatsen;
    private String type;
    private String gender;
    private boolean voorVeiligLanders;
    private ArrayList<Vluchteling> bewoners;

    public Kamer(int kamerNummer, int totalePlaatsen, String type, String gender, boolean voorVeiligeLanders) {
        this.kamerNummer = kamerNummer;
        this.totalePlaatsen = totalePlaatsen;
        this.type = type;
        this.gender = gender;
        this.voorVeiligLanders = voorVeiligeLanders;
        this.bewoners = new ArrayList<>();
    }

    public int getKamerNummer() {
        return kamerNummer;
    }

    public int getTotalePlaatsen() {
        return totalePlaatsen;
    }

    public void setTotalePlaatsen(int totalePlaatsen) {
        this.totalePlaatsen = totalePlaatsen;
    }

    public String getType() {
        return type;
    }

    public ArrayList<Vluchteling> getBewoners() {
        return bewoners;
    }

    public int getVrijePlaatsen() {
        return totalePlaatsen - bewoners.size();
    }

    public int getBezetting() {
        return bewoners.size();
    }

    public boolean addBewoner(Vluchteling vluchteling) {
        if (getVrijePlaatsen() > 0) {
            bewoners.add(vluchteling);
            return true;
        }
        return false;
    }

    public void removeBewoner(Vluchteling vluchteling) {
        bewoners.remove(vluchteling);
    }

    public boolean bevatBewoner(Vluchteling vluchteling) {
        return bewoners.contains(vluchteling);
    }

    public boolean isGeschiktVoor(Vluchteling vluchteling) {
        if (vluchteling.isNonBinair() || vluchteling.getLeeftijd() > 60) {
            return isEenpersoons() && heeftVrijePlaats();
        }
        else if (vluchteling.getLandVanHerkomst()) {
            return isVeiligeLandKamer() && heeftVrijePlaats();
        } else if (vluchteling.getLeeftijd() < 18) {
                return isJongerenKamer() && heeftVrijePlaats();
        } else {
            return heeftVrijePlaats();
        }
    }

    private boolean heeftVrijePlaats() {
        return getVrijePlaatsen() > 0;
    }

    public boolean isGezinsKamer() {
        return "Gezinskamer".equalsIgnoreCase(type);
    }

    public boolean isNonBinair() {
        return "NonBinair".equalsIgnoreCase(type);
    }

    public boolean isEenpersoons() {
        return "Eenpersoons".equalsIgnoreCase(type);
    }

    public boolean isJongerenKamer() {
        return "Jongerenkamer".equalsIgnoreCase(type);
    }

    public boolean isVeiligeLandKamer() {
        return "VeiligeLandKamer".equalsIgnoreCase(type);
    }

    public String getGender() {
        return type;
    }

    public boolean isVoorVeiligLanders() {
        return voorVeiligLanders;
    }

    public void setVoorVeiligLanders(boolean voorVeiligLanders) {
        this.voorVeiligLanders = voorVeiligLanders;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
