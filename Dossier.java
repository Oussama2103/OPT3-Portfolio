public class Dossier {
    private Vluchteling vluchteling;
    private boolean asielAanvraagAfgerond;
    private String uitspraakIND; // "geen", "verblijfsvergunning" of "afgewezen"
    private String statusEigenWoning; // "gestart" of "afgerond"
    private boolean teruggekeerdNaarHerkomstland;

    public Dossier(Vluchteling vluchteling) {
        this.vluchteling = vluchteling;
        this.asielAanvraagAfgerond = false;
        this.uitspraakIND = "geen";
        this.statusEigenWoning = "nee";
        this.teruggekeerdNaarHerkomstland = false;
    }

    public Vluchteling getVluchteling() {
        return vluchteling;
    }

    public boolean isAsielAanvraagAfgerond() {
        return asielAanvraagAfgerond;
    }

    public void setAsielAanvraagAfgerond(boolean asielAanvraagAfgerond) {
        this.asielAanvraagAfgerond = asielAanvraagAfgerond;
    }

    public String getUitspraakIND() {
        return uitspraakIND;
    }

    public void setUitspraakIND(String uitspraakIND) {
        this.uitspraakIND = uitspraakIND;
    }

    public String getStatusEigenWoning() {
        return statusEigenWoning;
    }

    public void setStatusEigenWoning(String statusEigenWoning) {
        this.statusEigenWoning = statusEigenWoning;
    }

    public boolean isTeruggekeerdNaarHerkomstland() {
        return teruggekeerdNaarHerkomstland;
    }

    public void setTeruggekeerdNaarHerkomstland(boolean teruggekeerdNaarHerkomstland) {
        this.teruggekeerdNaarHerkomstland = teruggekeerdNaarHerkomstland;
    }
}
