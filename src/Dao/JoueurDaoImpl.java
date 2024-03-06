package Dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Equipe;
import model.Joueur;
public class JoueurDaoImpl implements JoueurDao{

	@Override
	public List<Joueur> getAllJoueur() throws SQLException {
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Suptech2","root","");
		Statement statement=connection.createStatement();
		ResultSet result= statement.executeQuery("select IDJoueur,nomJoueur,poste,"
				+ "E.IDEquipeD,nomEquipe,pays from joueur E, equipe F "
				+ "where E.IDEquipeD=F.IDEquipe");
		List<Joueur> joueurs=new ArrayList<>();
		//Traitement des résultats :
		while(result.next()) {
			int IDJoueur=result.getInt(1);
			String nomJoueur=result.getString(2);
			String poste=result.getString(3);
			int IDEquipeD=result.getInt(4);
			String nomEquipe=result.getString(5);
			String pays=result.getString(6);
			Equipe equipe=new Equipe(IDEquipeD,nomEquipe,pays);
			Joueur joueur=new Joueur(IDJoueur,nomJoueur,poste,equipe);
			joueurs.add(joueur);
		}
		connection.close();
		return joueurs;
	}

	@Override
	public void addJoueur(Joueur joueur) throws SQLException {
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Suptech2","root","");
		String query = "insert into joueur values(?,?,?,?)";
		PreparedStatement ps=connection.prepareStatement(query);
		ps.setInt(1,joueur.getIDJoueur());
		ps.setString(2,joueur.getNomJoueur());
		ps.setString(3,joueur.getPoste());
		ps.setInt(4, joueur.getEquipe().getIDEquipeD());
		ps.executeUpdate();
		connection.close();
	}

	@Override
	public void deleteJoueur(int IDJoueur) throws SQLException{
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;

	        try {
	            // Obtenir la connexion à la base de données
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Suptech2", "root", "");

	            // Préparer la requête de suppression en utilisant l'ID de l'équipe
	            String query = "DELETE FROM joueur WHERE IDJoueur = ?";
	            preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setInt(1, IDJoueur);

	            // Exécuter la requête de suppression
	            preparedStatement.executeUpdate();

	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            throw ex; // Redirection de l'exception pour la gestion à un niveau supérieur si nécessaire
	        } finally {
	            // Fermeture des ressources dans le bloc finally pour garantir leur libération
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        }
	}
}
