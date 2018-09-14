package model.jeu.interfaces;

import model.jeu.Carte;

public interface CartesListener {

	public void retourne(int index, Carte carte);
	
	public void trouve(int index, Carte carte);
	
}
