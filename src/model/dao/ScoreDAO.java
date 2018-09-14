package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.jeu.Score;

public class ScoreDAO{
	public ScoreDAO(){
		Statement stmt = null;
		String requete = "CREATE TABLE IF NOT EXISTS `Score` ("
				+ "`identifiant` int(11) NOT NULL AUTO_INCREMENT,"
				+ "`pseudo` varchar(255) NOT NULL,"
				+ "`score` int(11) DEFAULT NULL,"
				+ "`temps` time DEFAULT NULL,"
				+ "`thematique` varchar(255) NOT NULL,"
				+ "`nbJoueurs` int(4) NOT NULL,"
				+ "`modeJeu` varchar(255) NOT NULL,"
				+ "`difficulte` varchar(255) NOT NULL,"
				+ "`dateHeure` datetime NOT NULL,"
				+ "PRIMARY KEY (`identifiant`)"
				+ ") ENGINE=MyISAM  DEFAULT CHARSET=utf8mb4;";
		try {

			Connection conn = ConnectionMySql.getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(requete);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Score> toList(int lim) {

		List<Score> scores = new ArrayList<Score>();

		Statement stmt = null;
		ResultSet rs = null;
		try {

			Connection conn = ConnectionMySql.getConnection();
			stmt = conn.createStatement();
			String requete = "SELECT Identifiant, Pseudo, Score, Temps,  Thematique, NbJoueurs, ModeJeu, Difficulte, DateHeure "
					+ "FROM Score ORDER BY nbJoueurs DESC, difficulte DESC, score DESC, Temps ASC LIMIT 0, "+lim+";";
			rs = stmt.executeQuery(requete);
			while (rs.next()) {
				Score score = new Score();
				score.setIdentifiant(rs.getInt("Identifiant"));
				score.setPseudo(rs.getString("Pseudo"));
				score.setScore(rs.getInt("Score"));
				score.setTemps(rs.getTime("Temps"));
				score.setThematique(rs.getString("Thematique"));
				score.setNbJoueurs(rs.getInt("NbJoueurs"));
				score.setModeJeu(rs.getString("ModeJeu"));
				score.setDifficulte(rs.getInt("Difficulte"));
				score.setDateHeure(rs.getDate("DateHeure"));
				scores.add(score);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return scores;
	}

	
	public Score trouver(int id) {

		Score score = new Score();
		Statement stmt;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Connection conn = ConnectionMySql.getConnection();
			
			String requete = "SELECT Identifiant, Pseudo, Score, Temps,  Thematique, NbJoueurs, ModeJeu, Difficulte, DateHeure "
					+ "FROM Score WHERE Identifiant = ?;";
			pstmt = conn.prepareStatement(requete);
			pstmt.setLong(1, id);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				score.setIdentifiant(rs.getInt("Identifiant"));
				score.setPseudo(rs.getString("Pseudo"));
				score.setScore(rs.getInt("Score"));
				score.setTemps(rs.getTime("Temps"));
				score.setThematique(rs.getString("Thematique"));
				score.setNbJoueurs(rs.getInt("NbJoueurs"));
				score.setModeJeu(rs.getString("ModeJeu"));
				score.setDifficulte(rs.getInt("Difficulte"));
				score.setDateHeure(rs.getDate("DateHeure"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return score;
	}

	
	public void ajouter(Score scr) {

		PreparedStatement pstmt = null;
		try {
			Connection conn = ConnectionMySql.getConnection();

			String requete = "Insert Into Score(Pseudo, Score, Temps, Thematique, NbJoueurs, ModeJeu, Difficulte, DateHeure)"
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, NOW())";

			pstmt = conn.prepareStatement(requete);

			pstmt.setString(1, scr.getPseudo());
			pstmt.setInt(2, scr.getScore());
			pstmt.setTime(3, scr.getTemps());
			pstmt.setString(4, scr.getThematique());
			pstmt.setInt(5, scr.getNbJoueurs());
			pstmt.setString(6, scr.getModeJeu());
			pstmt.setInt(7, scr.getDifficulte());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
	
