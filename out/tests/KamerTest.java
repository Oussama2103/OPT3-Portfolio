import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class KamerTest {
    private Kamer gezinskamer;
    private Kamer eenpersoonskamer;
    private Kamer jongerenkamer;
    private Kamer veiligeLandKamer;
    private Vluchteling man61Jaar;
    private Vluchteling meisje17Jaar;
    private Vluchteling nonBinair18Jaar;
    private Vluchteling jongen17Jaar;
    private Familie familie;

    @Before
    public void setUp() {
        gezinskamer = new Kamer(1, 4, "Gezinskamer");
        eenpersoonskamer = new Kamer(2, 1, "Eenpersoons");
        jongerenkamer = new Kamer(3, 1, "Jongerenkamer");
        veiligeLandKamer = new Kamer(4, 1, "VeiligeLandKamer");

        man61Jaar = new Vluchteling("oussama", 61, "male", false, null);
        meisje17Jaar = new Vluchteling("ibrahime", 17, "women", false, new Familie("bro"));
        nonBinair18Jaar = new Vluchteling("sabri", 18, "women", true, familie);
        jongen17Jaar = new Vluchteling("ibo", 17, "women", false, familie);

        familie = new Familie("");
        familie.voegLidToe(new Vluchteling("Gezinlid1", 40, false, false));
        familie.voegLidToe(new Vluchteling("Gezinlid2", 38, false, false));
        familie.voegLidToe(new Vluchteling("Gezinlid3", 12, false, false));
    }

    @Test
    public void testGezinInGezinskamer() {
        assertTrue(gezinskamer.isGeschikteKamer(meisje17Jaar, familie));
    }

    @Test
    public void testMan61JaarInEenpersoonskamer() {
        assertTrue(eenpersoonskamer.isGeschikteKamer(man61Jaar, null));
    }

    @Test
    public void testMeisje17JaarMetGezinInGezinskamer() {
        assertTrue(gezinskamer.isGeschikteKamer(meisje17Jaar, familie));
    }

    @Test
    public void testNonBinair18JaarInEenpersoonsVeiligeLandKamer() {
        assertTrue(veiligeLandKamer.isGeschikteKamer(nonBinair18Jaar, null));
    }

    @Test
    public void testJongen17JaarInJongerenkamer() {
        assertTrue(jongerenkamer.isGeschikteKamer(jongen17Jaar, null));
    }
}
