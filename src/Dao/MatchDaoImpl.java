/*package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Equipe;
import model.Match;
import model.Stade;
import model.Match;
public class MatchDaoImpl implements MatchDao{

	@Override
	public List<Match> getAllMatch() throws SQLException {
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Suptech2","root","");
		Statement statement=connection.createStatement();
		ResultSet result= statement.executeQuery("select IDMatch,"
				+ "A.IDequipe,nomEquipe,pays from match A, equipe B "
				+ "where A.IDequipe=B.IDequipe"
				);
		List<Match> matchs=new ArrayList<>();
		while (result.next()) {
			int IDMatch=result.getInt(1);
			int IDEquipe=result.getInt(2);
			String nomEquipe=result.getString(3);
			String pays=result.getString(4);
			int IDStade=result.getInt(5);
			String nomStade=result.getString(6);
			String pays1=result.getString(7);
			String ville=result.getString(8);
			Equipe equipe=new Equipe(IDEquipe,nomEquipe,pays);
			Stade stade= new Stade(IDStade,nomStade,pays1,ville);
			Match match=new Match(IDMatch,equipe,stade);
			matchs.add(match);
		}
		connection.close();
		return matchs;
	}

	@Override
	public void addMatch(Match match) throws SQLException {
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Suptech2","root","");
		String query = "insert into match values(?,?,?)";
		PreparedStatement ps=connection.prepareStatement(query);
		ps.setInt(1,match.getIDMatch());
		ps.setInt(2,match.getEquipe().getIDEquipe());
		ps.setInt(3,match.getStade().getIDStade());
		ps.executeUpdate();
		connection.close();
	}

	@Override
	public void deleteMatch(int IDMatch) throws SQLException {
		 Connection connection = null;
	        PreparedStatement preparedStatement = null;

	        try {
	            // Obtenir la connexion à la base de données
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Suptech2", "root", "");

	            // Préparer la requête de suppression en utilisant l'ID de l'équipe
	            String query = "DELETE FROM match WHERE IDMatch = ?";
	            preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setInt(1, IDMatch);

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

}*/
package Dao;

import java.util.ArrayList;
import java.util.List;

import model.Equipe;
import model.Joueur;
import model.Match;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Equipe;
import model.Match;
import model.Stade;
import Dao.StadeDao;
import Dao.EquipesDao;
import Dao.MatchDao;
import Dao.JoueurDao;

/*public class MatchDaoImpl implements MatchDao {
	 private EquipesDao equipeDao; // Déclaration de l'instance d'EquipeDao
	   private StadeDao stadeDao; //
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/Suptech2", "root", "");
    }

    private void closeResources(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    @Override
    public List<Match> getAllMatch() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Match> matchs = new ArrayList<>();

        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT IDMatch, IDEquipeDomicile, IDEquipeVisiteur, IDStade FROM matchs");

            while (resultSet.next()) {
                int IDMatch = resultSet.getInt("IDMatch");
                int IDEquipeDomicile = resultSet.getInt("IDEquipeDomicile");
                int IDEquipeVisiteur = resultSet.getInt("IDEquipeVisiteur");
                int IDStade = resultSet.getInt("IDStade");

                // Obtention des objets Equipe et Stade correspondant aux ID depuis votre DAO
                Equipe equipeDomicile = equipeDao(IDEquipeDomicile);
                Equipe equipeVisiteur = equipeDao(IDEquipeVisiteur);
                Stade stade = stadeDao(IDStade);
                
                // Création d'un nouvel objet Match avec les identifiants
                Match match = new Match(IDMatch, IDEquipeDomicile, IDEquipeVisiteur, IDStade);
                matchs.add(match);
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            closeResources(connection, statement, resultSet);
        }

        return matchs;
    }

    private Stade stadeDao(int iDStade) {
		// TODO Auto-generated method stub
		return null;
	}

	private Equipe equipeDao(int iDEquipeDomicile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    public void addMatch(Match match) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            String query = "INSERT INTO matchs (IDMatch, IDEquipeDomicile, IDEquipeVisiteur, IDStade) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);

         // Utilisation des identifiants des objets Equipe et Stade dans la méthode addMatch

            preparedStatement.setInt(1, match.getIDMatch());
            preparedStatement.setInt(2, match.getEquipeDomicile()); // Utilisation de l'ID de l'équipe
            preparedStatement.setInt(3, match.getEquipeVisiteur()); // Utilisation de l'ID de l'équipe
            preparedStatement.setInt(4, match.getStade()); // Utilisation de l'ID du stade

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            closeResources(connection, preparedStatement, null);
        }
    }

    @Override
    public void deleteMatch(int IDMatch) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            String query = "DELETE FROM matchs WHERE IDMatch = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, IDMatch);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            closeResources(connection, preparedStatement, null);
        }
    }

    public List<Match> getMatchesByVictories(int equipeID, int nombreVictoiresMin) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Match> matchs = new ArrayList<>();

        try {
            connection = getConnection();

            String query = "SELECT m.IDMatch, m.IDEquipeDomicile, m.IDEquipeVisiteur, m.IDStade " +
                           "FROM matchs m " +
                           "INNER JOIN equipe e ON m.IDEquipeDomicile = e.IDEquipe " +
                           "WHERE e.IDEquipe = ? AND e.nombreVictoires >= ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, equipeID);
            preparedStatement.setInt(2, nombreVictoiresMin);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int IDMatch = resultSet.getInt("IDMatch");
                int IDEquipeDomicile = resultSet.getInt("IDEquipeDomicile");
                int IDEquipeVisiteur = resultSet.getInt("IDEquipeVisiteur");
                int IDStade = resultSet.getInt("IDStade");

                Match match = new Match(IDMatch, IDEquipeDomicile, IDEquipeVisiteur, IDStade);
                matchs.add(match);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            closeResources(connection, preparedStatement, resultSet);
        }

        return matchs;
    }

	@Override
	public Match getMatchById(int iDMatch) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Match getMatchById(Match match) {
		// TODO Auto-generated method stub
		return null;
	}
}
*/
//#################
public class MatchDaoImpl implements MatchDao{

	/*@Override
	public List<Match> getAllMatch() throws SQLException {
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Suptech2","root","");
		Statement statement=connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT T.IDMatch, " +
		        "E.IDEquipe, E.nomEquipe, E.pays, E.IDEquipe1, E.nomEquipe1," +
		        "A.IDStade, A.nomStade, A.pays, A.ville " +
		        "FROM matchs T " +
		        "JOIN equipe E ON T.IDEquipe = E.IDEquipe " +
		        "JOIN stade  A ON T.IDStade  = A.IDStade  ");
		List<Match> matchs = new ArrayList<>();
		while (result.next()) {
			int IDMatch = result.getInt(1);
			int IDEquipe = result.getInt(2);
		    String nomEquipe = result.getString(3);
		    String pays = result.getString(4);
		    String IDEquipe1 = result.getString(5);
		    String nomEquipe1 = result.getString(6);
		    int IDStade = result.getInt(7);
		    String nomStade =result.getString(8);
		    String pays1 =result.getString(9);
		    String ville =result.getString(10);
		    Equipe equipe = new Equipe(IDEquipe, nomEquipe, pays,IDEquipe1,nomEquipe1);
		    //Equipe equipev = new Equipe(IDEquipe, nomEquipe, pays,IDEquipe1,nomEquipe1);
		    Stade stade = new Stade(IDStade, nomStade, pays1, ville);
		    Match match=new Match(IDMatch,equipe,equipe,stade);
		    matchs.add(match);
		}
		connection.close();
		return matchs;
	}*/
	@Override
	public List<Match> getAllMatch() throws SQLException {
	    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Suptech2", "root", "");
	    Statement statement = connection.createStatement();
	    ResultSet result = statement.executeQuery("SELECT T.IDMatch, " +
	            "ED.IDEquipe AS IDEquipeD, ED.nomEquipe AS nomEquipeD, ED.pays AS paysD, " +
	            "EV.IDEquipe AS IDEquipeV, EV.nomEquipe AS nomEquipeV, EV.pays AS paysV, " +
	            "A.IDStade, A.nomStade, A.pays AS paysStade, A.ville " +
	            "FROM matchs T " +
	            "JOIN equipe ED ON T.IDEquipeD = ED.IDEquipe " +
	            "JOIN equipe EV ON T.IDEquipeV = EV.IDEquipe " +
	            "JOIN stade  A ON T.IDStade   = A.IDStade  ");

	    List<Match> matchs = new ArrayList<>();
	    while (result.next()) {
	        int IDMatch = result.getInt(1);
	        int IDEquipeD = result.getInt(2);
	        String nomEquipeD = result.getString(3);
	        String paysD = result.getString(4);
	        int IDEquipeV = result.getInt(5);
	        String nomEquipeV = result.getString(6);
	        String paysV = result.getString(7);
	        int IDStade = result.getInt(8);
	        String nomStade = result.getString(9);
	        String paysStade = result.getString(10);
	        String ville = result.getString(11);
	        
	        Equipe equipeDomicile = new Equipe(IDEquipeD, nomEquipeD, paysD);
	        Equipe equipeVisiteur = new Equipe(IDEquipeV, nomEquipeV, paysV);
	        Stade stade = new Stade(IDStade, nomStade, paysStade, ville);
	        Match match = new Match(IDMatch, equipeDomicile, equipeVisiteur, stade);
	        matchs.add(match);
	    }
	    connection.close();
	    return matchs;
	}


	@Override
	public void addMatch(Match match) throws SQLException {
		
		 Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Suptech2", "root", "");
		    String query = "INSERT INTO matchs VALUES (?, ?, ?, ?)";
		    PreparedStatement ps = connection.prepareStatement(query);
		    ps.setInt(1, match.getIDMatch());
		    ps.setInt(2, match.getEquipeD().getIDEquipeD());
		    ps.setInt(3, match.getEquipeV().getIDEquipeD());
		    ps.setInt(4, match.getStade().getIDStade());
		    ps.executeUpdate();
		    connection.close();
	}

	@Override
	public void deleteMatch(int IDMatch) throws SQLException {
			 Connection connection = null;
		        PreparedStatement preparedStatement = null;

		        try {
		            // Obtenir la connexion à la base de données
		            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Suptech2", "root", "");

		            // Préparer la requête de suppression en utilisant l'ID de l'équipe
		            String query = "DELETE FROM matchs WHERE IDMatch = ?";
		            preparedStatement = connection.prepareStatement(query);
		            preparedStatement.setInt(1, IDMatch);

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
	public Match getMatchById(Match match) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Match getMatchById(int iDMatch) {
		// TODO Auto-generated method stub
		return null;
	}
	
}