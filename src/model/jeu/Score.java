package model.jeu;

import java.sql.Date;
import java.sql.Time;

public class Score {

	private int identifiant;
	private String pseudo;
	private int score;
	private Time temps;
	private String thematique;
	private int nbJoueurs;
	private String modeJeu;
	private int difficulte;
	private Date dateHeure;

	public int getIdentifiant() {
		return this.identifiant;
	}

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

	public String getPseudo() {
		return this.pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Time getTemps() {
		return this.temps;
	}

	public void setTemps(Time temps) {
		this.temps = temps;
	}

	public String getThematique() {
		return this.thematique;
	}

	public void setThematique(String thematique) {
		this.thematique = thematique;
	}

	public int getNbJoueurs() {
		return this.nbJoueurs;
	}

	public void setNbJoueurs(int nbJoueurs) {
		this.nbJoueurs = nbJoueurs;
	}

	public String getModeJeu() {
		return this.modeJeu;
	}

	public void setModeJeu(String modeJeu) {
		this.modeJeu = modeJeu;
	}

	public int getDifficulte() {
		return this.difficulte;
	}

	public void setDifficulte(int difficulte) {
		this.difficulte = difficulte;
	}

	public Date getDateHeure() {
		return this.dateHeure;
	}

	public void setDateHeure(Date dateHeure) {
		this.dateHeure = dateHeure;
	}

}
