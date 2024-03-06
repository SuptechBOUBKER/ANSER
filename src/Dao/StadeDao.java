package Dao;

import java.sql.SQLException;
import java.util.List;

import model.Stade;

public interface StadeDao {
	public List<Stade> getAllStade() throws SQLException;
	public void addStade (Stade stade) throws SQLException;
	public void deleteStade(int IDStade) throws SQLException;
	public Stade getStadeById(int iDStade);
	
}
