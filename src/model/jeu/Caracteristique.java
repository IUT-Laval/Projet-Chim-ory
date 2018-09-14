package model.jeu;
/**
 * Classe repr�sentant une caract�ristique.
 * Une caract�ristique a simplement un nom.
 * @author Nicolas
 *
 */
public class Caracteristique {

	private String nom;
	private String couleur;
	/**
	 * 
	 * @param nom Le nom de la caract�ristique
	 */
	public Caracteristique(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return nom;
	}
	
}
