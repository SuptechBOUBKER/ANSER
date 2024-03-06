package Dao;

import java.sql.SQLException;
import java.util.List;

import model.Equipe;
import model.Joueur;

public interface JoueurDao {
	public List<Joueur> getAllJoueur()throws SQLException;
	public void addJoueur (Joueur joueur) throws SQLException;
	public void deleteJoueur (int IDJoueur) throws SQLException;
	//Equipe getEquipeByName(String IDEquipe) throws SQLException;
	//public Joueur getJoueureById(int joueur);

}
