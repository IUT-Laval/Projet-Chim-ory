package model.jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Gère une liste de caractéristiques
 * @author Nicolas
 *
 */
public class GestionnaireCaracteristiques {
	
	private List<Caracteristique> caracteristiques = new ArrayList<>();

	public void addCaracteristique(Caracteristique caracteristique) {
		caracteristiques.add(caracteristique);
	}
	
	public void removeAll() {
		caracteristiques.clear();
	}
	
	public Caracteristique getCaracteristique(int index) {
		return caracteristiques.get(index);
	}
	
	public int getNbCaracteristiques() {
		return caracteristiques.size();
	}
	
	@Override
	public String toString() {
		String rtrString = "";
		for (int i = 0;i<caracteristiques.size();i++) {
			rtrString += "- ("+i+") : "+caracteristiques.get(i)+"\n";
		}
		return rtrString;
	}
	public void shuffle() {
		Collections.shuffle(caracteristiques);
	}
	
	
}
