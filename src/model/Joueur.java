package model;

public class Joueur {
	int IDJoueur;
	String nomJoueur;
	String poste;
	Equipe equipe;
	public Joueur() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Joueur(int iDJoueur, String nomJoueur, String poste, Equipe equipe) {
		super();
		IDJoueur = iDJoueur;
		this.nomJoueur = nomJoueur;
		this.poste = poste;
		this.equipe = equipe;
		
	}
	public int getIDJoueur() {
		return IDJoueur;
	}
	public void setIDJoueur(int iDJoueur) {
		IDJoueur = iDJoueur;
	}
	public String getNomJoueur() {
		return nomJoueur;
	}
	public void setNomJoueur(String nomJoueur) {
		this.nomJoueur = nomJoueur;
	}
	public String getPoste() {
		return poste;
	}
	public void setPoste(String poste) {
		this.poste = poste;
	}
	public Equipe getEquipe() {
		return equipe;
	}
	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
	
	
}
