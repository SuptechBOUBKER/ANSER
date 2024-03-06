package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Stade;

public class StadeDaoImpl implements StadeDao{

	@Override
	public List<Stade> getAllStade() throws SQLException {
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Suptech2","root","");
		Statement statement=connection.createStatement();
		ResultSet result= statement.executeQuery("select * from stade");
		List<Stade> stade=new ArrayList<>();
		while (result.next()) {
			int IDStade = result.getInt(1);
			String nomStade=result.getString(2);
			String pays=result.getString(3);
			String ville=result.getString(4);
			stade.add(new Stade(IDStade, nomStade, pays, ville));
		}
		connection.close();
		return stade;
	}

	@Override
	public void addStade(Stade stade) throws SQLException {
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Suptech2","root","");
		String query = "insert into stade values(?,?,?,?)";
		//System.out.println("OK");
		PreparedStatement ps=connection.prepareStatement(query);
		ps.setInt(1,stade.getIDStade());
		ps.setString(2,stade.getNomStade());
		ps.setString(3,stade.getPays());
		ps.setString(4, stade.getVille());
		ps.executeUpdate();
		connection.close();
	}

	@Override
	public void deleteStade(int IDStade) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

	        try {
	            // Obtenir la connexion à la base de données
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Suptech2", "root", "");

	            // Préparer la requête de suppression en utilisant l'ID de stade
	            String query = "DELETE FROM stade WHERE IDStade = ?";
	            preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setInt(1, IDStade);

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

	@Override
	public Stade getStadeById(int iDStade) {
		// TODO Auto-generated method stub
		return null;
	}

}
