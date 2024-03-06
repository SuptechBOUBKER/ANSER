package Dao;

import java.sql.SQLException;
import java.util.List;

import model.But;

public interface ButDao {
	public List<But> getAllBut() throws SQLException;
	public void addBut(But but) throws SQLException;
	public void deleteBut(int IDBut) throws SQLException;
}
