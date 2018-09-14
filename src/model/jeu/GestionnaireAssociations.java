package model.jeu;

import java.util.ArrayList;
import java.util.List;
/**
 * Permet la gestion d'une liste d'associations 
 * 
 * @author Nicolas
 *
 */
public class GestionnaireAssociations {

	private List<Association> associations = new ArrayList<>();
	/**
	 * Ajoute l'association passé en paramètre à la liste du gestionnaire
	 */
	public void addAssociation(Association association) {
		associations.add(association);
	}
	/**
	 * Supprime l'association passé en paramètre à la liste du gestionnaire
	 */
	public void removeAssociation(int index) {
		associations.remove(index);
	}
	/**
	 * @return Retourne l'association se trouvant à l'index indiqué en paramètre
	 */
	public Association getAssociation(int index) {
		return associations.get(index);
	}
	/**
	 * Réinitialise toutes les associations se trouvant dans le gestionnaire
	 */
	public void removeAll() {
		associations.clear();
	}
	/**
	 * @return Retourne le nombre d'associations se trouvant dans le gestionnaire
	 */
	public int getNbAssociations() {
		return associations.size();
	}
	/**
	 * @return Retourne la carte réaction de l'association qui correspond aux cartes passées en paramètre. S'il n'y a pas de réaction correspondant aux cartes passées en paramètre, retourne null.
	 */
	public Carte getReaction(Carte carteProduit, Carte carteReactifSubstrat) {
		return getAssociation(new Association(carteProduit, carteReactifSubstrat, null, -1)).getCarteReaction();
	}
	/**
	 * 
	 * @return Retourne l'association Si elle est présente dans le gestionnaire, sinon retourne null.
	 */
	public Association getAssociation(Association association) {
		int i = associations.indexOf(association);
		if (i == -1)
			return null;
		else
			return associations.get(i);
	}

	@Override
	public String toString() {
		return associations.toString();
	}
	/**
	 * Ajoute la caractéristique donnée en paramètre à l'association se trouvant à l'index donné en paramètre.
	 * @param index index de l'association
	 * @param caracteristique caractéristique à ajouter
	 */
	public void addCaracteristiqueToAssociation(int index, Caracteristique caracteristique) {
		associations.get(index).addCaracteristique(caracteristique);
	}
	
}
