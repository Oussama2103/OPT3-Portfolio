public class Bericht {
    private String type;
    private Vluchteling vluchteling;
    private Familie familie;
    private String details;
    private boolean isProcessed;
    private String azcNaam;
    private Gemeente gemeente;

    public Bericht(String type, Vluchteling vluchteling, Familie familie, String details, String azcNaam) {
        this.type = type;
        this.vluchteling = vluchteling;
        this.familie = familie;
        this.details = details;
        this.azcNaam = azcNaam;
        this.isProcessed = false;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Gemeente getGemeente() {
        return gemeente;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public void setProcessed(boolean isProcessed) {
        this.isProcessed = isProcessed;
    }

    public String getAzcNaam() {
        return azcNaam;
    }

    public void setAzcNaam(String azcNaam) {
        this.azcNaam = azcNaam;
    }

}
