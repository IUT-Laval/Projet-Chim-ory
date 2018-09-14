package model.jeu;
/**
 * Classe représentant une caractéristique.
 * Une caractéristique a simplement un nom.
 * @author Nicolas
 *
 */
public class Caracteristique {

	private String nom;
	private String couleur;
	/**
	 * 
	 * @param nom Le nom de la caractéristique
	 */
	public Caracteristique(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return nom;
	}
	
}
