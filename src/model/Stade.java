package model;

public class Stade {
	int IDStade;
	String nomStade;
	String pays;
	String ville;
	public Stade() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Stade(int iDStade, String nomStade, String pays, String ville) {
		super();
		IDStade = iDStade;
		this.nomStade = nomStade;
		this.pays = pays;
		this.ville = ville;
	}
	public int getIDStade() {
		return IDStade;
	}
	public void setIDStade(int iDStade) {
		IDStade = iDStade;
	}
	public String getNomStade() {
		return nomStade;
	}
	public void setNomStade(String nomStade) {
		this.nomStade = nomStade;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	
}
