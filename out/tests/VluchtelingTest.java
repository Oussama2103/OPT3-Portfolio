import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VluchtelingTest {

    private Vluchteling vluchteling;
    private Kamer kamer;

    @BeforeEach
    public void setUp() {
        kamer = new Kamer(1, 100, "Eenpersoonskamer", "man", true);
    }


    @Test
    public void testDecision_A_and_B() {
        Vluchteling vluchteling = new Vluchteling("Vluchteling", 61, "man", true, null);
        vluchteling.setLeeftijd(61); // A is true
        vluchteling.setLandVanHerkomst(true); // B is true
        vluchteling.isNonBinair(false); // C is false
        assertEquals(true, kamer.isGeschiktVoor(vluchteling)); // (true && true) || false == true
    }

    @Test
    public void testDecision_not_A_and_B() {
        Vluchteling vluchteling = new Vluchteling("Vluchteling", 61, "man", true, null);
        vluchteling.setLeeftijd(30); // A is false
        vluchteling.setLandVanHerkomst(true); // B is true
        vluchteling.isNonBinair(false); // C is false
        assertEquals(false, kamer.isGeschiktVoor(vluchteling)); // (false && true) || false == false
    }

    @Test
    public void testDecision_A_and_not_B() {
        Vluchteling vluchteling = new Vluchteling("Vluchteling", 61, "man", true, null);
        vluchteling.setLeeftijd(61); // A is true
        vluchteling.setLandVanHerkomst(false); // B is false
        vluchteling.isNonBinair(false); // C is false
        assertEquals(false, kamer.isGeschiktVoor(vluchteling)); // (true && false) || false == false
    }

    @Test
    public void testDecision_not_A_and_not_B_and_C() {
        Vluchteling vluchteling = new Vluchteling("Vluchteling", 61, "man", true, null);
        vluchteling.setLeeftijd(30); // A is false
        vluchteling.setLandVanHerkomst(false); // B is false
        vluchteling.isNonBinair(true); // C is true
        assertEquals(true, kamer.isGeschiktVoor(vluchteling)); // (false && false) || true == true
    }

    @Test
    public void testDecision_A_and_B_and_C() {
        Vluchteling vluchteling = new Vluchteling("Vluchteling", 61, "man", true, null);
        vluchteling.setLeeftijd(61); // A is true
        vluchteling.setLandVanHerkomst(true); // B is true
        vluchteling.isNonBinair(true); // C is true
        assertEquals(true, kamer.isGeschiktVoor(vluchteling)); // (true && true) || true == true
    }

    @Test
    public void testDecision_not_A_and_B_and_C() {
        Vluchteling vluchteling = new Vluchteling("Vluchteling", 61, "man", true, null);
        vluchteling.setLeeftijd(30); // A is false
        vluchteling.setLandVanHerkomst(true); // B is true
        vluchteling.isNonBinair(true); // C is true
        assertEquals(true, kamer.isGeschiktVoor(vluchteling)); // (false && true) || true == true
    }
}
