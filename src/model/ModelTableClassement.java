package model;

import java.sql.ResultSet;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.dao.ScoreDAO;
import model.jeu.Score;

public class ModelTableClassement extends AbstractTableModel{
	String[] titles = { "ID", "Pseudo", "Score", "Temps", "Thematique",
			"Nb de Joueurs", "Mode de Jeu", "Difficulté", "Date" };
	private List<Score> scores;
	public ModelTableClassement(int lim){
		ScoreDAO scoreDAO = new ScoreDAO();
		this.scores = scoreDAO.toList(lim);
	}

	@Override
	public int getColumnCount() {
		return 9;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return scores.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0)
			return scores.get(rowIndex).getIdentifiant();
		if (columnIndex == 1)
			return scores.get(rowIndex).getPseudo();
		if (columnIndex == 2)
			return scores.get(rowIndex).getScore();
		if (columnIndex == 3)
			return scores.get(rowIndex).getTemps();
		if (columnIndex == 4)
			return scores.get(rowIndex).getThematique();
		if (columnIndex == 5)
			return scores.get(rowIndex).getNbJoueurs();
		if (columnIndex == 6)
			return scores.get(rowIndex).getModeJeu();
		if (columnIndex == 7)
			return scores.get(rowIndex).getDifficulte();
		if (columnIndex == 8)
			return scores.get(rowIndex).getDateHeure();
		return null;
	}

	@Override
	public String getColumnName(int column) {
		return titles[column];
	}
}
