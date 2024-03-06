package model;

public class Equipe {
	int IDEquipeD;
	String nomEquipe;
	String pays;
	public Equipe() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Equipe(int iDEquipeD, String nomEquipe, String pays) {
		super();
		IDEquipeD = iDEquipeD;
		this.nomEquipe = nomEquipe;
		this.pays = pays;
	}
	public int getIDEquipeD() {
		return IDEquipeD;
	}
	public void setIDEquipeD(int iDEquipeD) {
		IDEquipeD = iDEquipeD;
	}
	public String getNomEquipe() {
		return nomEquipe;
	}
	public void setNomEquipe(String nomEquipe) {
		this.nomEquipe = nomEquipe;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	
}
/*package doa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Equipe;
import model.Joueur;

public class JoueurDaolmpl implements JoueurDao {

    @Override
    public List<Joueur> getAllJoueur() throws SQLException {
        List<Joueur> joueurs = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_football", "root", "");
             PreparedStatement statement = connection.prepareStatement("SELECT E.id_joueur, E.nomJoueur, E.poste, F.id_equipe, F.nom, F.ville " +
                     "FROM joueurs E INNER JOIN equipe F ON E.id_Equipe = F.id_equipe")) {

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int id = result.getInt(1);
                String nomJoueur = result.getString(2);
                String poste = result.getString(3);
                int id_equipe = result.getInt(4);
                String nom = result.getString(5);
                String ville = result.getString(6);
                Equipe equipe = new Equipe(id_equipe, nom, ville);
                joueurs.add(new Joueur(id, nomJoueur, poste, equipe));
            }
        }
        return joueurs;
    }

    @Override
    public void addJoueur(Joueur joueur) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_football", "root", "");
             PreparedStatement ps = connection.prepareStatement("INSERT INTO joueurs (nomJoueur, poste, id_equipe) VALUES (?, ?, ?, ?)")) {
        	ps.setInt(1, joueur.getIDJoueur());
            ps.setString(2, joueur.getNomJoueur());
            ps.setString(3, joueur.getPoste());
            ps.setString(4, joueur.getEquipe().getNom());
           
            ps.executeUpdate();
        }
    }

    @Override
    public void deleteJoueur(Joueur joueur) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_football", "root", "");
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM joueurs WHERE id_joueur = ?")) {

            preparedStatement.setInt(1, joueur.getId());
            preparedStatement.executeUpdate();
        }
    }
}*/


