public class DossierManager {

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

    }

}
