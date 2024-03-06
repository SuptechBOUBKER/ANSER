package model;

public class But {
	/*int IDBut;
	int minute;
	int IDJoueur;
	int IDMatch;
	int IDEquipe;
	public But() {
		super();
		// TODO Auto-generated constructor stub
	}
	public But(int iDBut, int minute, int IDJoueur, int IDMatch, int IDEquipe) {
		super();
		IDBut = iDBut;
		this.minute = minute;
		this.IDJoueur = IDJoueur;
		this.IDMatch = IDMatch;
		this.IDEquipe = IDEquipe;
	}
	public int getIDBut() {
		return IDBut;
	}
	public void setIDBut(int iDBut) {
		IDBut = iDBut;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public int getIDJoueur() {
		return IDJoueur;
	}
	public void setIDJoueur(int IDJoueur) {
		this.IDJoueur = IDJoueur;
	}
	public int getIDMatch() {
		return IDMatch;
	}
	public void setIDMatch(int IDEquipe) {
		this.IDEquipe = IDEquipe;
	}
	public int getIDEquipe() {
		return IDMatch;
	}
	public void setIDEquipe(int IDEquipe) {
		this.IDEquipe = IDEquipe;
	}*/
	
	int IDBut;
	int minute;
	Match match;
	Equipe equipeD, equipeV;
	Joueur joueur;
	public But() {
		super();
		// TODO Auto-generated constructor stub
	}
	public But(int iDBut, int minute, Match match, Equipe equipeD, Equipe equipeV, Joueur joueur) {
		super();
		IDBut = iDBut;
		this.minute = minute;
		this.match = match;
		this.equipeD = equipeD;
		this.equipeV = equipeV;
		this.joueur = joueur;
	}
	public int getIDBut() {
		return IDBut;
	}
	public void setIDBut(int iDBut) {
		IDBut = iDBut;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public Match getMatch() {
		return match;
	}
	public void setMatch(Match match) {
		this.match = match;
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
	public Joueur getJoueur() {
		return joueur;
	}
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
	
	
	
	/*int IDBut;
	int minute;
	Equipe equipe;
	Match match,match1;
	Joueur joueur;
	public But() {
		super();
		// TODO Auto-generated constructor stub
	}
	public But(int iDBut, int minute, Equipe equipe, Match match, Match match1, Joueur joueur) {
		super();
		IDBut = iDBut;
		this.minute = minute;
		this.equipe = equipe;
		this.match = match;
		this.match1 = match1;
		this.joueur = joueur;
	}
	public int getIDBut() {
		return IDBut;
	}
	public void setIDBut(int iDBut) {
		IDBut = iDBut;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public Equipe getEquipe() {
		return equipe;
	}
	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
	public Match getMatch() {
		return match;
	}
	public void setMatch(Match match) {
		this.match = match;
	}
	public Match getMatch1() {
		return match1;
	}
	public void setMatch1(Match match1) {
		this.match1 = match1;
	}
	public Joueur getJoueur() {
		return joueur;
	}
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
	*/
	
}
