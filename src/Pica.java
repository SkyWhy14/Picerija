public abstract class Pica {
    protected String nosaukums;
    protected double cena;
    protected String izmers;
    protected String piedevas;
    protected String papildus;
    protected String klientaVards;
    protected String adrese;
    protected String telefons;
    protected String piegade;
    protected String merce;
    protected String dzeriens;
    protected String tilpums;
    protected String uzkoda;

    public Pica(String nosaukums, double cena, String izmers, String piedevas) {
        this.nosaukums = nosaukums;
        this.cena = cena;
        this.izmers = izmers;
        this.piedevas = piedevas;
    }

    public String getNosaukums(){ return nosaukums; }
    public double getCena(){ return cena; }
    public String getIzmers(){ return izmers; }
    public String getPiedevas(){ return piedevas; }
    public String getPapildus(){ return papildus; }
    public String getVards(){ return klientaVards; }
    public String getAdrese(){ return adrese; }
    public String getTel(){ return telefons; }
    public String getMauce(){ return merce; }
    public String getDrinks(){ return dzeriens; }
    public String getTilpums(){ return tilpums; }
    public String getUzkoda(){ return uzkoda; }
    public String getPiegade(){ return piegade; }

    public void setKlientaInfo(String v, String a, String t, String pieg) {
        klientaVards = v;
        adrese = a;
        telefons = t;
        piegade = pieg;
    }

    public void setPapildus(String x) { papildus = x; }
    public void setMerce(String m) { merce = m; }
    public void setDrinks(String d) { dzeriens = d; }
    public void setTilpums(String t) { tilpums = t; }
    public void setUzkoda(String u) { uzkoda = u; }
}
