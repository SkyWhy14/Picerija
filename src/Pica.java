public abstract class  Pica  {
	private String nosaukums;
	private double cena;
	private String izmērs;
	private String piedevas;
	private String papildusPiedevas;

	public Pica(String nosaukums, double cena, String izmērs, String papildusPiedevas) {
	
		this.cena = cena;
		this.izmērs = izmērs;
		this.papildusPiedevas = papildusPiedevas;
	}

	

	public double getCena() {
		return cena;
	}

	public String getIzmērs() {
		return izmērs;
	}
	public String getPiedevas() {
		return piedevas;
	}
	@Override
	public String toString() {
		return "Pica: " + nosaukums + ", Izmērs: " + izmērs +"Piedavas: "+piedevas+ ", Cena: " + cena + " EUR";
	}
	
	  
}
