package model.jeu;

import java.util.ArrayList;
import java.util.List;

import model.jeu.interfaces.JoueurListener;
import model.jeu.interfaces.JoueursListener;
/**
 * Gère une liste de joueurs.
 * @author Nicolas
 *
 */
public class GestionnaireJoueurs {

	private List<Joueur> joueurs = new ArrayList<>();
	private Joueur joueurActuel;
	private int indexJoueurActuel;
	
	private long temps = 0;
	
    private List<JoueursListener> listeners = new ArrayList<>();
    
    public void addListener(JoueursListener l) {
        listeners.add(l);
    }
    public void removeListener() {
    	listeners.clear();
    }
    
	/**
	 * Permet l'ajout de joueurs jusqu'à une maximum de 4
	 * @return Retourne true si la joueur à été ajouté, false sinon
	 */
	public boolean addJoueur(Joueur joueur) {
		if (joueurs.size() < 4) {
			joueurs.add(joueur);
			joueur.addListener(new JoueurListener() {
				@Override
				public void tempsChange(long temps) {
					for (JoueursListener l : listeners)
						for (int i = 0;i<joueurs.size();i++)
							if (joueurs.get(i).listenersContains(this)) {
								l.tempsJoueurChange(i, temps);
							}
				}
				@Override
				public void pointsChange(int points) {
					for (JoueursListener l : listeners)
						for (int i = 0;i<joueurs.size();i++)
							if (joueurs.get(i).listenersContains(this)) {
								l.pointsJoueurChange(i, points);
							}
				}
			});
			return true;
		} else {
			return false;
		}
	}
	/**
	 * @return Retourne true si le joueur à été supprimé, false sinon
	 */
	public boolean removeJoueur(int index) {
		if (nombreJoueurs() <= 1)
			return false;
		joueurs.remove(index);
		joueurs.get(index).removeListener();
		return true;
	}
	
	public Joueur getJoueur(int index) {
		return joueurs.get(index);
	}
	public List<Joueur> getJoueurs(){
		return joueurs;
	}
	
	public int nombreJoueurs() {
		return joueurs.size();
	}
	/**
	 * 
	 * @return Retourne le joueur actuel
	 */
	public Joueur getJoueurActuel() {
		return joueurActuel;
	}
	
	public int getIndexJoueurActuel() {
		for (int i = 0;i<joueurs.size();i++)
			if (joueurs.get(i).equals(joueurActuel))
				return i;
		return 0;
	}
	
	public void setJoueurActuel(Joueur joueurActuel) {
		this.joueurActuel = joueurActuel;
		this.indexJoueurActuel = joueurs.indexOf(joueurActuel);
	}
	/**
	 * Permet de passer au joueur suivant
	 */
	public void joueurSuivant() {
		
		joueurActuel.ajouterTemps(System.currentTimeMillis()-temps);
		
		this.temps = System.currentTimeMillis();
		System.out.println("test :"+joueurActuel.getTemps()/1000);
		indexJoueurActuel++;
		if (indexJoueurActuel>=joueurs.size())
			indexJoueurActuel = 0;
		joueurActuel = joueurs.get(indexJoueurActuel);
		
		for (JoueursListener l : listeners)
            l.joueurActuelChange(indexJoueurActuel);
		
	}
	
	public void resetJoueurs() {
		for(int i = 0;i<joueurs.size();i++)
			joueurs.get(i).resetJoueur();
		joueurActuel = joueurs.get(0);
		temps = System.currentTimeMillis();
	}
	
	@Override
	public String toString() {
		String rtrString = "";
		for (int i = 0;i<joueurs.size();i++) {
			rtrString += "- "+joueurs.get(i)+"\n";
		}
		return rtrString;
	}
}
