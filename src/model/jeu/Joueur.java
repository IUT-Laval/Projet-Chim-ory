package model.jeu;

import java.util.ArrayList;
import java.util.List;

import model.jeu.interfaces.CarteListener;
import model.jeu.interfaces.JoueurListener;
import model.jeu.interfaces.JoueursListener;

public class Joueur implements Comparable<Joueur> {

	private int points;
	private long temps;
	private String pseudo;
	
    private List<JoueurListener> listeners = new ArrayList<>();
    
    public void addListener(JoueurListener l) {
        listeners.add(l);
    }
    public void removeListener() {
    	listeners.clear();
    }
    public boolean listenersContains(JoueurListener joueurListener) {
    	return listeners.contains(joueurListener);
    }
    
	public Joueur(String pseudo) {
		this.pseudo = pseudo;
		points = 0;
		temps = 0;
	}
	/**
	 * reinitialise le temps et le score du joueur
	 */
	public void resetJoueur() {
		points = 0;
		temps = 0;
	}
	
	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public int getPoints() {
		return points;
	}

	public long getTemps() {
		return temps;
	}

	public void ajouterPoint(int points) {
		
		this.points += points;
		
		for (JoueurListener l : listeners)
            l.pointsChange(this.points);
	}
	public void ajouterTemps(long temps) {
		this.temps += temps;
		for (JoueurListener l : listeners)
            l.tempsChange(this.temps);
	}	
	
	@Override
	public String toString() {
		return pseudo+" ("+points+" points)";
	}
	
	@Override
	public int compareTo(Joueur joueur) {
		if(this.getPoints() == joueur.getPoints())
			return (int) (joueur.getTemps() - this.getTemps());
		return this.getPoints() - joueur.getPoints();
	}

}