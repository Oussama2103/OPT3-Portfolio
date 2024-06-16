import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataSeeder {

    public static List<Land> landen = new ArrayList<>();
    public static List<Gemeente> gemeentes = new ArrayList<>();
    public static List<AZC> azcs = new ArrayList<>();
    public static List<Kamer> kamers = new ArrayList<>();
    public static List<Vluchteling> vluchtelingen = new ArrayList<>();

    public static void seedData() {
        seedLanden();
        seedGemeentes();
        seedAZCs();
        seedKamers();
        seedVluchtelingen();
    }

    private static void seedLanden() {
        Land nederland = new Land("Nederland", true);
        Land syrie = new Land("Syrië", false);
        Land irak = new Land("Irak", false);
        Land afghanistan = new Land("Afghanistan", false);
        landen.addAll(Arrays.asList(nederland, syrie, irak, afghanistan));
    }

    private static void seedGemeentes() {
        Gemeente amsterdam = new Gemeente("Amsterdam", 1000000);
        Gemeente rotterdam = new Gemeente("Rotterdam", 800000);
        Gemeente denHaag = new Gemeente("Den Haag", 600000);
        Gemeente utrecht = new Gemeente("Utrecht", 400000);
        gemeentes.addAll(Arrays.asList(amsterdam, rotterdam, denHaag, utrecht));
    }

    private static void seedAZCs() {
        azcs.clear();

        Gemeente amsterdam = gemeentes.get(0);
        Gemeente rotterdam = gemeentes.get(1);
        Gemeente denHaag = gemeentes.get(2);
        Gemeente utrecht = gemeentes.get(3);

        AZC azc1 = new AZC("AZC Amsterdam 1", "Straat 1", amsterdam, 100);
        AZC azc2 = new AZC("AZC Amsterdam 2", "Straat 2", amsterdam, 200);
        AZC azc3 = new AZC("AZC Rotterdam 1", "Straat 3", rotterdam, 300);
        AZC azc4 = new AZC("AZC Rotterdam 2", "Straat 4", rotterdam, 40);
        AZC azc5 = new AZC("AZC Den Haag 1", "Straat 5", denHaag, 50);
        AZC azc6 = new AZC("AZC Utrecht 1", "Straat 6", utrecht, 60);
        AZC azc7 = new AZC("Aanmeldcentrum Ter Apel", "Straat 7", utrecht, 150);

        amsterdam.getAZCs().addAll(Arrays.asList(azc1, azc2));
        rotterdam.getAZCs().addAll(Arrays.asList(azc3, azc4));
        denHaag.getAZCs().addAll(Arrays.asList(azc5));
        utrecht.getAZCs().addAll(Arrays.asList(azc6, azc7));

        azcs.addAll(Arrays.asList(azc1, azc2, azc3, azc4, azc5, azc6, azc7));
    }

    private static void seedKamers() {
        kamers.clear();

        for (AZC azc : azcs) {
            for (int i = 1; i <= 8; i++) {
                Kamer kamer = new Kamer(i, i % 2 == 0 ? 2 : 1, "Type " + (i % 4 + 1));
                azc.getKamerManager().getKamers().add(kamer);
                kamers.add(kamer);
            }
        }
    }

    private static void seedVluchtelingen() {
        Vluchteling vluchteling1 = new Vluchteling("Mohammed", 25, "Man", true, null);
        Vluchteling vluchteling2 = new Vluchteling("Fatima", 22, "Vrouw", false, null);
        Vluchteling vluchteling3 = new Vluchteling("Ahmed", 28, "Man", false, null);
        Vluchteling vluchteling4 = new Vluchteling("Aisha", 30, "Vrouw", false, null);
        Vluchteling vluchteling5 = new Vluchteling("Omar", 65, "Man", true, null);
        Vluchteling vluchteling6 = new Vluchteling("Sara", 32, "Vrouw", false, null);
        vluchtelingen.addAll(Arrays.asList(vluchteling1, vluchteling2, vluchteling3, vluchteling4, vluchteling5, vluchteling6));
    }
}
