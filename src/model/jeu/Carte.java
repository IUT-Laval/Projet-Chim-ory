package model.jeu;

import java.util.ArrayList;
import java.util.List;

import model.jeu.interfaces.CarteListener;
import model.jeu.interfaces.JoueurListener;

/**
 * Désigne une carte.
 * @author Nicolas
 *
 */
public class Carte {

	private int indentifiant;
	private String nom;
	private String image = null;
	private boolean trouvee = false;
	private boolean retournee = false;
	
    private List<CarteListener> listeners = new ArrayList<>();
    
    public void addListener(CarteListener l) {
        listeners.add(l);
    }
    public void removeListener() {
    	listeners.clear();
    }
    public boolean listenersContains(CarteListener carteListener) {
    	return listeners.contains(carteListener);
    }
    
	/**
	 * 
	 * @param nom Le nom de la carte
	 * @param indentifiant L'identifiant de la carte
	 */
	public Carte(String nom, int indentifiant, String image) {
		this.nom = nom;
		this.indentifiant = indentifiant;
		this.image = image;
	}
	/**
	 * 
	 * @return Retourne l'identifiant de la carte
	 */
	public int getIdentifiant() {
		return indentifiant;
	}
	/**
	 * 
	 * @return Retourne le nom de la carte
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * 
	 * @return Retourne true si les 2 cartes ont le meme identifiant, sinon false
	 */
	@Override
	public boolean equals(Object carte){
	    if (carte == null) return false;
	    if (carte == this) return true;
	    if (!(carte instanceof Carte))return false;
	    Carte myCarte = (Carte)carte;
	    return (this.indentifiant == myCarte.getIdentifiant());
	}
	/**
	 * Passe la carte a l'état "trouvée"
	 */
	public void trouve() {
		trouvee = true;

		for (CarteListener l : listeners)
            l.trouve(this);
	}
	/**
	 * 
	 * @return Retourne true si la carte fait partie d'une association trouvée, sinon false
	 */
	public Boolean isTrouvee() {
		return trouvee;
	}
	/**
	 * Retourne la carte.
	 * Si elle est visible elle devient cachée, si elle est cachée elle devient visible.
	 */
	public void retourne() {
		if (retournee) {
			retournee = false;
		} else {
			retournee = true;
		}
		for (CarteListener l : listeners)
            l.retourne(this);
	}
	/**
	 * 
	 * @return true si la carte est retournée, false sinon.
	 */
	public Boolean isRetournee() {
		return retournee;
	}

	@Override
	public String toString() {
		String stateCarte = "";
		if (trouvee)
			stateCarte = "X";
		else if (retournee)
			stateCarte = "+";
		else
			stateCarte = "-";
		return "["+stateCarte+"] "+nom;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	public String getImage() {
		return image;
	}
	

}