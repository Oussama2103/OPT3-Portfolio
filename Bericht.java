public class Bericht {
    private String type;
    private Vluchteling vluchteling;
    private Familie familie;
    private String inhoud;
    private String azcNaam;
    private boolean processed;

    public Bericht(String type, Vluchteling vluchteling, Familie familie, String inhoud, String azcNaam, boolean processed) {
        this.type = type;
        this.vluchteling = vluchteling;
        this.familie = familie;
        this.inhoud = inhoud;
        this.azcNaam = azcNaam;
        this.processed = false;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Vluchteling getVluchteling() {
        return vluchteling;
    }

    public void setVluchteling(Vluchteling vluchteling) {
        this.vluchteling = vluchteling;
    }

    public Familie getFamilie() {
        return familie;
    }

    public void setFamilie(Familie familie) {
        this.familie = familie;
    }

    public String getInhoud() {
        return inhoud;
    }

    public void setInhoud(String inhoud) {
        this.inhoud = inhoud;
    }

    public String getAzcNaam() {
        return azcNaam;
    }

    public void setAzcNaam(String azcNaam) {
        this.azcNaam = azcNaam;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public void toonBericht() {
        if (vluchteling.getFamilie() == null) {
            System.out.println("Vluchteling " + vluchteling.getNaam());
            System.out.println("Type: " + getType());
            System.out.println("Inhoud: " + getInhoud());
            System.out.println("AZC Naam: " + getAzcNaam());
            System.out.println("--------------------");
        }
        else if (vluchteling.getFamilie() != null) {
            System.out.println("Familie " + familie.getNaam());
            System.out.println("Type: " + getType());
            System.out.println("Inhoud: " + getInhoud());
            System.out.println("AZC Naam: " + getAzcNaam());
            System.out.println("--------------------");
        }
    }
}
