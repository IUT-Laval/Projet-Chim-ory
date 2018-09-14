package model.jeu;

import java.util.ArrayList;
import java.util.List;
/**
 * Classe d�signant une association.
 * Elle associe les 3 cartes de la r�action, des caract�ristiques, et une difficult�.
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
	 * @param carteProduit d�signe la carte produit de l'association ou la carte de l'association pr�sente dans le 1er paquet
	 * @param carteReactifSubstrat d�signe la carte r�actif + substrat de l'association ou la carte de l'associtaion pr�sente dans le 2�me paquet
	 * @param carteReaction d�signe la carte r�acton de l'association  ou la carte de l'assocition pr�sente dans le 3eme paquet
	 * @param difficulte d�signe la difficult� de l'association. Si la 
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
	 * @return retourne true si les 2 cartes d�sign�es en param�tres sont associ�s � l'association, sinon false
	 */
	public boolean isAssociation(Carte carteProduit, Carte carteReactifSubstrat) {
		return (this.carteProduit.equals(carteProduit) && this.carteReactifSubstrat.equals(carteReactifSubstrat));
	}
	/**
	 * 
	 * @return retourne true si l'assocition contient la caract�risque donn�e en param�tre.
	 */
	public boolean containsCaracteristique(Caracteristique caracteristique) {
		return caracteristiques.contains(caracteristique);
	}

	@Override
	public String toString() {
		return "[" + carteProduit + ", " + carteReactifSubstrat + ", " + carteReaction + "]";
	}
	/**
	 * Ajoute � l'association la caract�rique donn�e en param�tre.
	 */
	public void addCaracteristique(Caracteristique caracteristique) {
		caracteristiques.add(caracteristique);
	}
	/**
	 *  Supprime de l'association la caract�rique donn�e en param�tre.
	 */
	public void removeCaracteristique(int index) {
		caracteristiques.remove(index);
	}
	/**
	 * Permet d�signer une association comme "trouv�e". Une fois trouv�e, elle n'est plus consultable
	 */
	public void trouve() {
		trouvee = true;
	}
	/**
	 * 
	 * @return Retourne l'�tat de l'association : true si elle est trouv�e, false si elle ne l'est pas
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
	 * @return Retourne la difficult� de l'association
	 */
	public int getDifficulte() {
		return difficulte;
	}

}
