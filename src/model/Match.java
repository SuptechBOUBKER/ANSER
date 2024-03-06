package model;

public class Match {
	/*int IDMatch;
	int equipeDomicile;
	int equipeVisiteur;
	int stade;
	public Match() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Match(int iDMatch, int equipeDomicile, int equipeVisiteur, int stade) {
		super();
		IDMatch = iDMatch;
		this.equipeDomicile = equipeDomicile;
		this.equipeVisiteur = equipeVisiteur;
		this.stade = stade;
	}
	public int getIDMatch() {
		return IDMatch;
	}
	public void setIDMatch(int iDMatch) {
		IDMatch = iDMatch;
	}
	public int getEquipeDomicile() {
		return equipeDomicile;
	}
	public void setEquipeDomicile(int equipeDomicile) {
		this.equipeDomicile = equipeDomicile;
	}
	public int getEquipeVisiteur() {
		return equipeVisiteur;
	}
	public void setEquipeVisiteur(int equipeVisiteur) {
		this.equipeVisiteur = equipeVisiteur;
	}
	public int getStade() {
		return stade;
	}
	public void setStade(int stade) {
		this.stade = stade;
	}
	*/
	
	int IDMatch;
	Equipe equipeD,equipeV;
	Stade stade;
	public Match() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Match(int iDMatch, Equipe equipeD, Equipe equipeV, Stade stade) {
		super();
		IDMatch = iDMatch;
		this.equipeD = equipeD;
		this.equipeV = equipeV;
		this.stade = stade;
	}
	public int getIDMatch() {
		return IDMatch;
	}
	public void setIDMatch(int iDMatch) {
		IDMatch = iDMatch;
	}
	public Equipe getEquipeD() {
		return equipeD;
	}
	public void setEquipeD(Equipe equipeD) {
		this.equipeD = equipeD;
	}
	public Equipe getEquipeV() {
		return equipeV;
	}
	public void setEquipeV(Equipe equipeV) {
		this.equipeV = equipeV;
	}
	public Stade getStade() {
		return stade;
	}
	public void setStade(Stade stade) {
		this.stade = stade;
	}
	public String getEquipeDomicile() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getEquipeVisiteur() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}