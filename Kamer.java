import java.util.ArrayList;
import java.util.List;

public class Kamer {
    private int kamerNummer;
    private int totalePlaatsen;
    private KamerType type;
    private String gender;
    private boolean voorVeiligLanders;
    private List<Vluchteling> bewoners;

    public Kamer(int kamerNummer, int totalePlaatsen, KamerType type, String gender, boolean voorVeiligLanders) {
        this.kamerNummer = kamerNummer;
        this.totalePlaatsen = totalePlaatsen;
        this.type = type;
        this.gender = gender;
        this.voorVeiligLanders = voorVeiligLanders;
        this.bewoners = new ArrayList<>();
    }

    public int getKamerNummer() {
        return kamerNummer;
    }

    public int getTotalePlaatsen() {
        return totalePlaatsen;
    }

    public KamerType getType() {
        return type;
    }

    public List<Vluchteling> getBewoners() {
        return bewoners;
    }

    public int getVrijePlaatsen() {
        return totalePlaatsen - bewoners.size();
    }

    public void addBewoner(Vluchteling vluchteling) {
        bewoners.add(vluchteling);
    }

    public void removeBewoner(Vluchteling vluchteling) {
        bewoners.remove(vluchteling);
    }

    public boolean isGeschiktVoor(Vluchteling vluchteling) {
        switch (type) {
            case NONBINAIRKAMER:
                return vluchteling.isNonBinair() && (vluchteling.getLeeftijd() > 60 || vluchteling.getFamilie() != null);
            case VEILIGELANDKAMER:
                return vluchteling.getLandVanHerkomst() && voorVeiligLanders && heeftVrijePlaats();
            case JONGERENKAMER:
                return vluchteling.getLeeftijd() < 18 && heeftVrijePlaats();
            case GEZINSKAMER:
                return vluchteling.getFamilie() != null && heeftVrijePlaats() && getVrijePlaatsen() >= vluchteling.getFamilie().getLeden().size() + 1;
            case EENPERSOONSKAMER:
                return heeftVrijePlaats();
            default:
                return false;
        }
    }

    private boolean heeftVrijePlaats() {
        return getVrijePlaatsen() > 0;
    }

    public boolean isGezinsKamer() {
        return type == KamerType.GEZINSKAMER;
    }

    public boolean isNonBinair() {
        return type == KamerType.NONBINAIRKAMER;
    }

    public boolean isEenpersoons() {
        return type == KamerType.EENPERSOONSKAMER;
    }

    public boolean isJongerenKamer() {
        return type == KamerType.JONGERENKAMER;
    }

    public boolean isVeiligeLandKamer() {
        return type == KamerType.VEILIGELANDKAMER;
    }

    public String getGender() {
        return gender;
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
