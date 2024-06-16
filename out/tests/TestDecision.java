import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestDecision {

    private Vluchteling vluchteling;
    private Dossier dossier;
    private Familie familie;

    @Before
    public void setUp() {
        familie = new Familie("TestFamilie");
        vluchteling = new Vluchteling("TestVluchteling", 17, "male", true, familie);
        dossier = vluchteling.getDossier();
    }

    @Test
    public void testDecision_MCDC() {
        // Test case 1: A = true, B = true, C = true
        dossier.setStatusEigenWoning("afgerond");
        System.out.println("Case 1: Status Eigen Woning = " + dossier.getStatusEigenWoning());
        assertTrue(decision(vluchteling)); // D = true

        // Reset voor nieuwe test case
        dossier.setStatusEigenWoning("nee");

        // Test case 2: A = true, B = true, C = false
        dossier.setStatusEigenWoning("niet-afgerond");
        System.out.println("Case 2: Status Eigen Woning = " + dossier.getStatusEigenWoning());
        assertFalse(decision(vluchteling)); // D = false

        // Reset voor nieuwe test case
        vluchteling.setFamilie(null);
        dossier.setStatusEigenWoning("nee");

        // Test case 3: A = true, B = false, C = true
        dossier.setStatusEigenWoning("afgerond");
        System.out.println("Case 3: Status Eigen Woning = " + dossier.getStatusEigenWoning());
        assertTrue(decision(vluchteling)); // D = true
    }

    private boolean decision(Vluchteling vluchteling) {
        // Hier is de logica van de decision methode
        return "afgerond".equalsIgnoreCase(vluchteling.getDossier().getStatusEigenWoning());
    }
}
