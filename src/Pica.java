public abstract class Pica {
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

    public String getNosaukums() {
        return nosaukums;
    }

    public double getCena() {
        return cena;
    }

    public String getIzmers() {
        return izmers;
    }

    public String getPiedevas() {
        String all = piedevas;
        if (papildusPiedevas != null && !papildusPiedevas.isEmpty()) {
            all += ", " + papildusPiedevas;
        }
        return all;
    }

    @Override
    public String toString() {
        return nosaukums + " | " + izmers + " | " + getPiedevas() + " | " + cena + " EUR";
    }
}
