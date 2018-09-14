package model.jeu;

import java.util.*;

import model.jeu.interfaces.CarteListener;
import model.jeu.interfaces.CartesListener;
/**
 * Désigne un Groupe de carte dans le jeu. Le jeu est constitué de 3 groupes.
 * Chaque association à une carte présente dans chaque groupe
 * @author Nicolas
 *
 */
public class GroupeCartes {

	public List<Carte> cartes = new ArrayList<>();
	
    private List<CartesListener> listeners = new ArrayList<>();
    private List<CarteListener> Carteslisteners = new ArrayList<>();
    
    public void addListener(CartesListener l) {
        listeners.add(l);
    }
    public void removeListener() {
    	listeners.clear();
    }
    
	/**
	 * Ajoute une carte au groupe
	 */
	public void addCarte(Carte carte) {
		cartes.add(carte);
		carte.addListener(new CarteListener() {
			@Override
			public void trouve(Carte carte) {
				for (CartesListener l : listeners)
					for (int i = 0;i<cartes.size();i++)
						if (cartes.get(i).listenersContains(this))
							l.trouve(i, carte);
			}
			
			@Override
			public void retourne(Carte carte) {
				for (CartesListener l : listeners)
					for (int i = 0;i<cartes.size();i++)
						if (cartes.get(i).listenersContains(this))
							l.retourne(i, carte);
			}
		});
	}

	public void removeCarte(int index) {
		cartes.remove(index);
		cartes.get(index).removeListener();
	}

	public Carte getCarte(int index) {
		return cartes.get(index);
	}

	public int getNbCarte() {
		return cartes.size();
	}

	public void removeAll() {
		cartes.clear();
	}
	/**
	 * Melange le groupe de carte
	 */
	public void shuffle() {
		Collections.shuffle(cartes);
	}
	/**
	 * 
	 * @return Retourne le nombre de carte dans le groupe
	 */
	public int nombreCarte() {
		return cartes.size();
	}
	/**
	 * 
	 * @return Retourne le nombre de cartes trouvée
	 */
	public int nombreCarteTrouvee() {
		int a = 0;
		for (int i = 0;i < cartes.size();i++)
			if (cartes.get(i).isTrouvee())
				a++;
		return a;
	}

	@Override
	public String toString() {
		//return cartes.toString();
		String rtrString = "";
		for (int i = 0;i<cartes.size();i++) {
			rtrString += "- ("+i+") : "+cartes.get(i)+"\n";
		}
		return rtrString;
	}
}
