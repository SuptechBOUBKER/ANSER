package Dao;

import java.sql.SQLException;
import java.util.List;

import model.Joueur;
import model.Match;

public interface MatchDao {
	public List<Match> getAllMatch() throws SQLException;
	public void addMatch(Match match) throws SQLException;
	public void deleteMatch(int IDMatch) throws SQLException;
	public Match getMatchById(Match match);
	Match getMatchById(int iDMatch);
}
