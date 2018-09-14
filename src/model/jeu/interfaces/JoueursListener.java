package model.jeu.interfaces;

public interface JoueursListener {

	public void joueurActuelChange(int indexJoueur);
	
	public void pointsJoueurChange(int indexJoueur, int points);
	
	public void tempsJoueurChange(int indexJoueur, long temps);
}
