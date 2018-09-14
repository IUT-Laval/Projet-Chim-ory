package model.jeu;

import java.util.ArrayList;
import java.util.List;
/**
 * Classe désignant une association.
 * Elle associe les 3 cartes de la réaction, des caractéristiques, et une difficulté.
 * @author Nicolas
 * 
 * 
 */
public class Association {
	
	private Carte carteProduit;
	private Carte carteReactifSubstrat;
	private Carte carteReaction;
	private List<Caracteristique> caracteristiques = new ArrayList<>();
	private boolean trouvee = false;
	private int difficulte;
	/**
	 * 
	 * @param carteProduit désigne la carte produit de l'association ou la carte de l'association présente dans le 1er paquet
	 * @param carteReactifSubstrat désigne la carte réactif + substrat de l'association ou la carte de l'associtaion présente dans le 2ème paquet
	 * @param carteReaction désigne la carte réacton de l'association  ou la carte de l'assocition présente dans le 3eme paquet
	 * @param difficulte désigne la difficulté de l'association. Si la 
	 */
	public Association(Carte carteProduit, Carte carteReactifSubstrat, Carte carteReaction, int difficulte) {
		this.carteProduit = carteProduit;
		this.carteReactifSubstrat = carteReactifSubstrat;
		this.carteReaction = carteReaction;
		this.difficulte = difficulte;
	}
	
	@Override
	public boolean equals(Object association) {
		if (association == null)
			return false;
		if (association == this)
			return true;
		if (!(association instanceof Association))
			return false;
		Association myAssociation = (Association) association;
		if (this.carteReaction == null && this.difficulte == -1)
			return (this.carteProduit.equals(myAssociation.getCarteProduit())
					&& this.carteReactifSubstrat.equals(myAssociation.getCarteReactifSubstrat()));
		else
			return (this.carteProduit.equals(myAssociation.getCarteProduit())
					&& this.carteReactifSubstrat.equals(myAssociation.getCarteReactifSubstrat())
					&& this.carteReaction.equals(myAssociation.getCarteReaction()));
	}
	/**
	 * 
	 * @param carteProduit
	 * @param carteReactifSubstrat
	 * @return retourne true si les 2 cartes désignées en paramètres sont associés à l'association, sinon false
	 */
	public boolean isAssociation(Carte carteProduit, Carte carteReactifSubstrat) {
		return (this.carteProduit.equals(carteProduit) && this.carteReactifSubstrat.equals(carteReactifSubstrat));
	}
	/**
	 * 
	 * @return retourne true si l'assocition contient la caractérisque donnée en paramètre.
	 */
	public boolean containsCaracteristique(Caracteristique caracteristique) {
		return caracteristiques.contains(caracteristique);
	}

	@Override
	public String toString() {
		return "[" + carteProduit + ", " + carteReactifSubstrat + ", " + carteReaction + "]";
	}
	/**
	 * Ajoute à l'association la caractérique donnée en paramètre.
	 */
	public void addCaracteristique(Caracteristique caracteristique) {
		caracteristiques.add(caracteristique);
	}
	/**
	 *  Supprime de l'association la caractérique donnée en paramètre.
	 */
	public void removeCaracteristique(int index) {
		caracteristiques.remove(index);
	}
	/**
	 * Permet désigner une association comme "trouvée". Une fois trouvée, elle n'est plus consultable
	 */
	public void trouve() {
		trouvee = true;
	}
	/**
	 * 
	 * @return Retourne l'état de l'association : true si elle est trouvée, false si elle ne l'est pas
	 */
	public boolean isTrouvee() {
		return trouvee;
	}
	/**
	 * 
	 * @return Retourne la carteProduit de l'association
	 */
	public Carte getCarteProduit() {
		return carteProduit;
	}
	/**
	 * 
	 * @return Retourne la carteReactifSubstrat de l'association
	 */
	public Carte getCarteReactifSubstrat() {
		return carteReactifSubstrat;
	}
	/**
	 * 
	 * @return Retourne la carteReaction de l'association
	 */
	public Carte getCarteReaction() {
		return carteReaction;
	}
	/**
	 * 
	 * @return Retourne la difficulté de l'association
	 */
	public int getDifficulte() {
		return difficulte;
	}

}
