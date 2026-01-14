import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

// --- Abstraktā Pica klase ---
abstract class Pica {
    private String nosaukums;
    private double cena;
    private String izmers;
    private String piedevas;
    private String papildusPiedevas;

    public Pica(String nosaukums, double cena, String izmers, String piedevas, String papildusPiedevas) {
        this.nosaukums = nosaukums;
        this.cena = cena;
        this.izmers = izmers;
        this.piedevas = piedevas;
        this.papildusPiedevas = papildusPiedevas;
    }

    public double getCena() {
        return cena;
    }

    @Override
    public String toString() {
        String allPiedevas = piedevas;
        if (papildusPiedevas != null && !papildusPiedevas.isEmpty()) {
            allPiedevas += ", " + papildusPiedevas;
        }
        return nosaukums + " | Izmērs: " + izmers + " | Piedevas: " + allPiedevas + " | Cena: " + cena + " EUR";
    }
}