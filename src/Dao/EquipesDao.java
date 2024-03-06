/*package Dao;
import java.sql.SQLException;
import java.util.List;
import model.Equipe;
public interface EquipesDao {
public List<Equipe> getAllEquipe(int iDEquipeDomicile) throws SQLException;
public void addEquipe (Equipe equipe) throws SQLException;
public void deleteEquipe (int IDEquipe) throws SQLException;
List<Equipe> getAllEquipe() throws SQLException;
}*/
package Dao;
import java.sql.SQLException;
import java.util.List;
import model.Equipe;

// Interface définissant les opérations CRUD (Create, Read, Update, Delete) pour les équipes
public interface EquipesDao {
  // Récupère toutes les équipes en fonction de l'ID de l'équipe à domicile
  public List<Equipe> getAllEquipe(int iDEquipeDomicile) throws SQLException;

  // Ajoute une nouvelle équipe à la base de données
  public void addEquipe(Equipe equipe) throws SQLException;

  // Supprime une équipe en fonction de son ID
  public void deleteEquipe(int IDEquipe) throws SQLException;

  // Récupère toutes les équipes (sans spécifier l'ID de l'équipe à domicile)
  List<Equipe> getAllEquipe() throws SQLException;

}

