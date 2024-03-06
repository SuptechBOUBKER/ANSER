package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import model.Equipe;
public class EquipesDaoImpl implements EquipesDao {
	@Override
	public List<Equipe> getAllEquipe() throws SQLException {
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Suptech2","root","");
		Statement statement=connection.createStatement();
		ResultSet result= statement.executeQuery("select * from equipe");
		List<Equipe> equipe=new ArrayList<>();
		while (result.next()) {
			int IDEquipe = result.getInt(1);
			String nomEquipe=result.getString(2);
			String pays=result.getString(3);
			equipe.add(new Equipe(IDEquipe, nomEquipe, pays));
		}
		connection.close();
		return equipe;
}
	@Override
	public void addEquipe(Equipe equipe) throws SQLException {
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Suptech2","root","");
		String query = "insert into equipe values(?,?,?)";
		//System.out.println("OK");
		PreparedStatement ps=connection.prepareStatement(query);
		ps.setInt(1,equipe.getIDEquipeD());
		ps.setString(2,equipe.getNomEquipe());
		ps.setString(3,equipe.getPays());
		ps.executeUpdate();
		connection.close();
	}
	@Override
	public void deleteEquipe(int IDEquipe) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Obtenir la connexion à la base de données
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Suptech2", "root", "");

            // Préparer la requête de suppression en utilisant l'ID de l'équipe
            String query = "DELETE FROM equipe WHERE IDEquipeD = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, IDEquipe);

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
	/*@Override
	public Equipe getEquipeByName(String nomEquipe) throws SQLException {
		 Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        Equipe equipe = null;

	        try {
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Suptech2", "root", "");

	            String query = "SELECT idEquipe, nomEquipe, pays FROM equipe WHERE nomEquipe = ?";
	            preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setString(1, nomEquipe);
	            resultSet = preparedStatement.executeQuery();

	            if (resultSet.next()) {
	                int idEquipe = resultSet.getInt("idEquipe");
	                String nom = resultSet.getString("nomEquipe");
	                String pays = resultSet.getString("pays");

	                equipe = new Equipe(idEquipe, nom, pays);
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            throw ex;
	        } finally {
	            // Fermeture des ressources
	            // Assure-toi de bien gérer les exceptions lors de la fermeture des connexions
	            // ...
	        	 if (preparedStatement != null) {
	                 preparedStatement.close();
	             }
	             if (connection != null) {
	                 connection.close();
	             }
	        }

	        return equipe;*/
	@Override
	public List<Equipe> getAllEquipe(int iDEquipeDomicile) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}