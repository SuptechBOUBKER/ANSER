/*package Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.But;
import model.Equipe;
import model.Joueur;
import model.Match;
import Dao.*;

public class ButDaoImpl implements ButDao{
	private MatchDao matchDao;
	private EquipesDao equipeDao;
	private JoueurDao joueurDao;
	public ButDaoImpl() {
	        // Initialisation des DAO ici
	        this.joueurDao = new JoueurDaoImpl();
	        this.equipeDao = new EquipesDaoImpl();
	        this.matchDao = new MatchDaoImpl();
	        // Assurez-vous d'adapter ces initialisations selon vos classes DAO réelles
	    }
	private Connection getConnection()throws SQLException{
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/Suptech2", "root", "");
	}
	private void closeResources(Connection connection,Statement statement, ResultSet resultSet) {
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
			// TODO: handle exception
			ex.printStackTrace();
		}
	}*/
	/*@Override
	public List<But> getAllBut() throws SQLException {
		Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<But> buts=new ArrayList<>();
        try {
        	 connection = getConnection();
             statement = connection.createStatement();
             resultSet = statement.executeQuery("SELECT IDBut, minute, IDEquipe, IDJoueur,IDMatch FROM but");
             
             while (resultSet.next()){
            	 int IDBut = resultSet.getInt("IDBut");
            	 int minute=resultSet.getInt("minute");
            	 int equipe=resultSet.getInt("Equipe");
            	 int equipe1=resultSet.getInt("Equipe_D");
            	 int equipe2=resultSet.getInt("Equipe_V");
            	 int joueur=resultSet.getInt("Joueur");
            	 
            	 Joueur IDJoueur1=joueurDao.getJoueureById(joueur);
            	 Equipe IDEquipe2 = equipeDao.getEquipeById(equipe);
            	 Equipe IDEquipe3 = equipeDao.getEquipeById1(equipe1);
            	 Equipe IDEquipe4 = equipeDao.getEquipeById2(equipe2);
            	 //Match match = matchDao.getMatchById(match);
            	 
            	 But but = new But(IDBut, minute,equipe,equipe1,equipe2);
            	 buts.add(but);
             }
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}finally {
			closeResources(connection, statement, resultSet);
		}
		return buts;
	}*/
	/*@Override
	public List<But> getAllBut() throws SQLException {
	    Connection connection = null;
	    Statement statement = null;
	    ResultSet resultSet = null;
	    List<But> buts = new ArrayList<>();

	    try {
	        connection = getConnection();
	        statement = connection.createStatement();
	        resultSet = statement.executeQuery("SELECT IDBut, minute, IDEquipe, IDEquipe_D, IDEquipe_V, IDJoueur FROM but");

	        while (resultSet.next()) {
	            int IDBut = resultSet.getInt("IDBut");
	            int minute = resultSet.getInt("minute");
	            int equipeID = resultSet.getInt("IDEquipe");
	            int equipeID1 = resultSet.getInt("IDEquipe_D");
	            int equipeID2 = resultSet.getInt("IDEquipe_V");
	            int joueurID = resultSet.getInt("IDJoueur");

	            Joueur joueur = joueurDao.getJoueureById(joueurID);
	            Equipe equipe = equipeDao.getEquipeById(equipeID);
	            Equipe equipe1 = equipeDao.getEquipeById1(equipeID1);
	            Equipe equipe2 = equipeDao.getEquipeById2(equipeID2);

	            But but = new But(IDBut, minute, joueur, equipe, equipe1, equipe2);
	            buts.add(but);
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    } finally {
	        closeResources(connection, statement, resultSet);
	    }
	    return buts;
	}

	@Override
	public void addBut(But but) throws SQLException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    try {
	        connection = getConnection();
	        String query = "INSERT INTO but (IDBut, minute, IDEquipe, IDEquipe_D, IDEquipe_V, IDJoueur) VALUES (?,?,?,?,?,?)";
	        preparedStatement = connection.prepareStatement(query); // Création du PreparedStatement à partir de la requête SQL

	        // Définition des valeurs des paramètres
	        preparedStatement.setInt(1, but.getIDBut());
	        preparedStatement.setInt(2, but.getMinute());
	        preparedStatement.setInt(3, but.getEquipe().getIDEquipe()); // Utilisation de l'ID de l'équipe
	        preparedStatement.setInt(4, but.getEquipe1().getIDEquipe()); // Utilisation de l'ID de l'équipe
	        preparedStatement.setInt(5, but.getEquipe2().getIDEquipe()); // Utilisation de l'ID de l'équipe
	        preparedStatement.setInt(6, but.getJoueur().getIDJoueur()); // Utilisation de l'ID du joueur

	        preparedStatement.executeUpdate();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        throw ex;
	    } finally {
	        closeResources(connection, preparedStatement, null);
	    }
	}


	@Override
	public void deleteBut(int IDBut) throws SQLException {
		Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            String query = "DELETE FROM but WHERE IDBut = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, IDBut);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            closeResources(connection, preparedStatement, null);
        }
    }
	public List<But> getButsByVictories(int equipe, int joueur, int equipe1, int equipe2) throws SQLException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    List<But> buts = new ArrayList<>();

	    try {
	        connection = getConnection();

	        String query = "SELECT b.IDBut, b.minute, b.equipe, b.joueur, b.equipe1, b.equipe2" +
	                       "FROM but b " +
	                       "INNER JOIN joueur j ON b.nomJoueur = j.nomJoueur " +
	                       "INNER JOIN equipe e ON j.nomEquipe = e.nomEquipe " +
	                       "INNER JOIN equipe1 m ON m.nomEquipe = m.nomEquipe" +
	                       "INNER JOIN equipe2 a ON a.nomEquipe = a.nomEquipe" +
	                       "WHERE e.nomEquipe = ? AND e.nombreVictoires >= ?" +
	                       "WHERE j.nomJoueur = ? AND e.nombreVictoires >= ?" +
	                       "WHERE m.nomEquipe = ? AND e.nombreVictoires >= ?" +
	                       "WHERE a.nomEquipe = ? AND e.nombreVictoires >= ?";

	        preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setInt(1, equipe);
	        preparedStatement.setInt(2, joueur);
	        preparedStatement.setInt(3, equipe1);
	        preparedStatement.setInt(4, equipe2);
	        resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            int IDBut = resultSet.getInt("IDBut");
	            int minute = resultSet.getInt("minute");
	            String equipeID = resultSet.getString("IDJoueur");
	            String joueurID = resultSet.getString("IDMatch");
	            String equipeID1= resultSet.getString("IDEquipe_D");
	            String equipeID2 = resultSet.getString("IDEquipe_S");
	            But but = new But(IDBut, minute, equipeID, joueurID, equipeID1, equipeID2);
	            buts.add(but);
	        }
	    } finally {
	        closeResources(connection, preparedStatement, resultSet);
	    }

	    return buts;
	}
}*/

		/*ResultSet result= statement.executeQuery("select IDBut,minute,"
				+ "E.IDEquipe,nomEquipe,pays from but E, equipe F "
				//+ "E.IDJoueur,nomJoueur,poste from but E , joueur J"
				+ //"where E.IDJoueur=J.IDJoueur AND 
				"where E.IDEquipe=F.IDEquipe");*/
		
		//##############"""
		/*ResultSet result = statement.executeQuery("SELECT J.IDBut, J.minute, " +
			    "E.IDEquipe, E.nomEquipe, E.pays, M.IDJoueur " +
			    "FROM but J, equipe E, but P, joueur M " +
			    "WHERE J.IDEquipe = E.IDEquipe " +
			    "AND J.IDBut = P.IDBut " +
			    "AND P.IDJoueur = M.IDJoueur");


		ResultSet result = statement.executeQuery("SELECT IDBut, minute, E.IDEquipe, J.IDJoueur " +
		        "FROM but E, equipe F, joueur J " +
		        "WHERE E.IDEquipe = F.IDEquipe AND E.IDJoueur = J.IDJoueur");

		List<But> buts=new ArrayList<>();
		while(result.next()) {
			int IDBut=result.getInt(1);
			int minute=result.getInt(2);
			int IDEquipe=result.getInt(3);
			String nomEquipe=result.getString(4);
			String pays=result.getString(5); 
			int IDJoueur=result.getInt(6);
			String nomJoueur=result.getString(7);
			String poste=result.getString(8);
			Equipe equipe=new Equipe(IDEquipe,nomEquipe,pays);
			Joueur joueur=new Joueur(IDJoueur,nomJoueur,poste,equipe);
			Equipe equipe1=new Equipe(IDEquipe,nomEquipe,pays);
			Equipe equipe2=new Equipe(IDEquipe,nomEquipe,pays);
			But but=new But(IDBut,minute,equipe,equipe1,equipe2,joueur);
			buts.add(but);
		}
		connection.close();
		return buts;
	}

	@Override
	public void addBut(But but) throws SQLException {
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Suptech2","root","");
		String query = "insert into but values(?,?,?,?,?)";
		PreparedStatement ps=connection.prepareStatement(query);
		ps.setInt(1,but.getIDBut());
		ps.setInt(2,but.getMinute());
		ps.setInt(3, but.getJoueur().getIDJoueur());
		ps.setInt(3,but.getEquipe().getIDEquipe());
		ps.setInt(4, but.getEquipe().getIDEquipe());
		ps.setInt(5, but.getEquipe().getIDEquipe());
		ps.executeUpdate();
		connection.close();
	}*/
package Dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.But;
import model.Equipe;
import model.Joueur;
import model.*;
public class ButDaoImpl implements ButDao{
	@Override
	public List<But> getAllBut() throws SQLException {
	    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Suptech2", "root", "");
	    Statement statement = connection.createStatement();
	    ResultSet result = statement.executeQuery("SELECT J.IDBut, J.minute, " +
	            "X.IDMatch, X.IDEquipeD, X.IDEquipeV, X.IDStade," +
	            "S.nomStade, S.pays AS paysStade, S.ville, " +
	            "M.IDJoueur, M.nomJoueur, M.poste, " +
	            "E1.nomEquipe, E1.pays AS paysEquipe, " +
	            "E2.nomEquipe AS nomEquipeV, E2.pays AS paysEquipeV " +
	            "FROM but J " +
	            "JOIN matchs X ON J.IDMatch = X.IDMatch " +
	            "JOIN joueur M ON J.IDJoueur = M.IDJoueur " +
	            "JOIN stade S ON X.IDStade = S.IDStade " +
	            "JOIN equipe E1 ON X.IDEquipeD = E1.IDEquipe " +
	            "JOIN equipe E2 ON X.IDEquipeV = E2.IDEquipe");

	    List<But> buts = new ArrayList<>();
	    while (result.next()) {
	        int IDBut = result.getInt(1);
	        int minute = result.getInt(2);
	        int IDMatch = result.getInt(3);
	        int IDEquipeD = result.getInt(4);
	        int IDEquipeV = result.getInt(5);
	        int IDStade = result.getInt(6);
	        String nomStade = result.getString(7);
	        String paysStade = result.getString(8);
	        String ville = result.getString(9);
	        int IDJoueur = result.getInt(10);
	        String nomJoueur = result.getString(11);
	        String poste = result.getString(12);
	        String nomEquipe = result.getString(13);
	        String paysEquipe = result.getString(14);
	        String nomEquipeV = result.getString(15);
	        String paysEquipeV = result.getString(16);

	        Equipe equipe = new Equipe(IDEquipeD, nomEquipe, paysEquipe);
	        Equipe equipe1 = new Equipe(IDEquipeV, nomEquipeV, paysEquipeV);
	        Stade stade = new Stade(IDStade, nomStade, paysStade, ville);
	        Match match = new Match(IDMatch, equipe, equipe1, stade);
	        Joueur joueur = new Joueur(IDJoueur, nomJoueur, poste, equipe);
	        But but = new But(IDBut, minute, match, equipe, equipe1, joueur);
	        buts.add(but);
	    }
	    connection.close();
	    return buts;
	}
	
	@Override
	public void addBut(But but) throws SQLException {
	    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Suptech2", "root", "");
	    String query = "INSERT INTO but VALUES (?, ?, ?, ?, ?, ?)";
	    PreparedStatement ps = connection.prepareStatement(query);
	    ps.setInt(1, but.getIDBut());
	    ps.setInt(2, but.getMinute());
	    ps.setInt(3, but.getMatch().getIDMatch());
	    ps.setInt(4, but.getEquipeD().getIDEquipeD()); // ID de l'équipe à domicile
	    ps.setInt(5, but.getEquipeV().getIDEquipeD()); // ID de l'équipe visiteuse
	    ps.setInt(6, but.getJoueur().getIDJoueur());
	    ps.executeUpdate();
	    connection.close();
	}


	@Override
	public void deleteBut(int IDBut) throws SQLException {
		 Connection connection = null;
	        PreparedStatement preparedStatement = null;

	        try {
	            // Obtenir la connexion à la base de données
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Suptech2", "root", "");

	            // Préparer la requête de suppression en utilisant l'ID de l'équipe
	            String query = "DELETE FROM but WHERE IDBut = ?";
	            preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setInt(1, IDBut);

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
/*public class ButDaoImpl implements ButDao{
	@Override
	public List<But> getAllBut() throws SQLException {
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Suptech2","root","");
		Statement statement=connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT J.IDBut, J.minute, " +
		        "E.IDEquipeD, E.nomEquipe, E.pays, E.IDEquipeV, E.nomEquipeV, E.paysV," +
		        "X.IDMatch, X.IDEquipeD, X.IDEquipeV, X.IDStade, " +
		        "M.IDJoueur, M.nomJoueur, M.poste " +
		        "FROM but J " +
		        "JOIN equipe E ON J.IDEquipe = E.IDEquipeD " +
		        "JOIN matchs X ON J.IDMatch  = X.IDMatch " +
		        "JOIN joueur M ON J.IDJoueur = M.IDJoueur");

		List<But> buts =new ArrayList<>();
		while (result.next()) {
		    int IDBut = result.getInt(1);
		    int minute = result.getInt(2);
		    int IDEquipeD = result.getInt(3);
		    String nomEquipe = result.getString(4);
		    String pays = result.getString(5);
		    String IDEquipeV = result.getString(6);
		    String nomEquipeV = result.getString(7);
		    String paysV = result.getString(8);
		    int IDMatch = result.getInt(9);
		    /*String equipeD =result.getString(9);
		    String equipeV =result.getString(10);
		    String stade =result.getString(11);*/
		   /* int IDJoueur = result.getInt(10);
		    String nomJoueur = result.getString(11);
		    String poste = result.getString(12);
		    int IDStade = result.getInt(13);
		    String nomStade = result.getString(14);
		    String pays1 = result.getString(15);
		    String ville = result.getString(16);
		    Equipe equipe = new Equipe(IDEquipeD, nomEquipe, pays,IDEquipeV,nomEquipeV,paysV);
		    //Equipe equipe1 = new Equipe(IDEquipe, nomEquipe, pays);
		    //Equipe equipe2 = new Equipe(IDEquipe, nomEquipe, pays);
		    Stade stade = new Stade(IDStade,nomStade,pays1,ville);
		    Match match = new Match(IDMatch, equipe, equipe, stade);
		    Joueur joueur = new Joueur(IDJoueur, nomJoueur, poste, equipe);
		    But but = new But(IDBut, minute, equipe, match, match, joueur);
		    buts.add(but);
		}
		connection.close();
		return buts;
	}
		@Override
		public void addBut(But but) throws SQLException {
		    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Suptech2", "root", "");
		    String query = "INSERT INTO but VALUES (?, ?, ?, ?, ?, ?)";
		    PreparedStatement ps = connection.prepareStatement(query);
		    ps.setInt(1, but.getIDBut());
		    ps.setInt(2, but.getMinute());
		    ps.setInt(3, but.getEquipe().getIDEquipeD());
		    ps.setInt(4, but.getMatch().getEquipeDomicile());
		    ps.setInt(5, but.getMatch1().getEquipeVisiteur());
		    ps.setInt(6, but.getJoueur().getIDJoueur());
		    ps.executeUpdate();
		    connection.close();
		}

	@Override
	public void deleteBut(int IDBut) throws SQLException {
		 Connection connection = null;
	        PreparedStatement preparedStatement = null;

	        try {
	            // Obtenir la connexion à la base de données
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Suptech2", "root", "");

	            // Préparer la requête de suppression en utilisant l'ID de l'équipe
	            String query = "DELETE FROM but WHERE IDBut = ?";
	            preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setInt(1, IDBut);

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
