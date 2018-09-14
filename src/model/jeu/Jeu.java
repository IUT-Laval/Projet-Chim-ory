package model.jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Gère une partie
 * @author Nicolas
 *
 */
public class Jeu {
	//!\\ ATTRIBUTS //!\\
	
	public static final int pointCaracteristiqueTrouvee = 10;

	private GroupeCartes cartesProduit = new GroupeCartes();
	private GroupeCartes cartesReactifSubstrat = new GroupeCartes();
	private GroupeCartes cartesReaction = new GroupeCartes();
	private Carte carteProduitSelectionnee = null;
	private Carte carteReactifSubstratSelectionne = null;
	private Carte carteReactionSelectionne = null;
	private GestionnaireAssociations gestionnaireAssociations = new GestionnaireAssociations();
	private GestionnaireJoueurs gestionnaireJoueurs = new GestionnaireJoueurs();
	private GestionnaireCaracteristiques gestionnaireCaracteristiques = new GestionnaireCaracteristiques();
	private int difficulte = 1;
	private Thematique thematique;
	private List<Caracteristique> caracteristiques;
	private Association associationTrouvee = null;
	
	//!\\ CONSTRUCTEUR //!\\
	public Jeu() {
		thematique = new Thematique(this);
	}

	/**
	 * Initialise la partie : 
	 * - Initialise tous les scores et temps de chaque joueurs
	 * - Charge les thématiques
	 * - Mélange les groupes des cartes
	 */
	public void initialiserPartie() {

		carteProduitSelectionnee = null;
		carteReactifSubstratSelectionne = null;
		carteReactionSelectionne = null;
		
		getGestionnaireJoueurs().resetJoueurs();
		getGestionnaireJoueurs().setJoueurActuel(getGestionnaireJoueurs().getJoueur((int)(getGestionnaireJoueurs().nombreJoueurs() * Math.random())));
		getThematique().loadThematique();
		
		getCartesProduit().shuffle();
		getCartesReactifSubstrat().shuffle();
		getCartesReaction().shuffle();
		//getGestionnaireCaracteristiques().shuffle();
	}
	/**
	 * Déroulement de la partie, jusqu'à ce que chaque association soit trouvée
	 */



	/**
	 * mode console
	 */
	public void parseCaracteristiques(String st) {
		caracteristiques = new ArrayList<>();
		st = st.replace(" ", "");
		String[] ss = st.split(",");
		for(int i = 0;i<ss.length;i++)
			if (Integer.parseInt(ss[i])>=0 &&Integer.parseInt(ss[i])<getGestionnaireCaracteristiques().getNbCaracteristiques())
				caracteristiques.add(getGestionnaireCaracteristiques().getCaracteristique(Integer.parseInt(ss[i])));
		
	}
	/**
	 * 
	 * @return Retourne la thématique de la partie
	 */
	public Thematique getThematique() {
		return thematique;
	}
	
	public GestionnaireJoueurs getGestionnaireJoueurs() {
		return gestionnaireJoueurs;
	}

	public GroupeCartes getCartesProduit() {
		return cartesProduit;
	}

	public GroupeCartes getCartesReactifSubstrat() {
		return cartesReactifSubstrat;
	}

	public GroupeCartes getCartesReaction() {
		return cartesReaction;
	}

	public GestionnaireAssociations getGestionnaireAssociations() {
		return gestionnaireAssociations;
	}

	public GestionnaireCaracteristiques getGestionnaireCaracteristiques() {
		return gestionnaireCaracteristiques;
	}

	public int getDifficulte() {
		return difficulte;
	}
	public void setDifficulte(int difficulte) {
		if (difficulte>=0 && difficulte<=3)
			this.difficulte = difficulte;
	}

	/*
	 * ROUND
	 */
	/**
	 * Mode Console
	 * Permet la sélection de la carte Produit
	 * @param index Index de la carte dans le groupe
	 * @return Retourne true si une carte est sélectionnée, false sinon
	 */
	public boolean selectionneCarteProduit(int index) {
		if (index < 0 || index >= cartesProduit.nombreCarte())
			return false;
		if (cartesProduit.getCarte(index).isTrouvee())
			return false;
		carteProduitSelectionnee = cartesProduit.getCarte(index);
		carteProduitSelectionnee.retourne();
		return true;
	}
	/**
	 * Mode Console
	 * Permet la sélection de la carte Réactif + Substrat
	 * @param index Index de la carte dans le groupe
	 * @return Retourne true si une carte est sélectionnée, false sinon
	 */
	public boolean selectionneCarteReactifSubstrat(int index) {
		if (index < 0 || index >= cartesReactifSubstrat.nombreCarte())
			return false;
		if (cartesReactifSubstrat.getCarte(index).isTrouvee())
			return false;
		carteReactifSubstratSelectionne = cartesReactifSubstrat.getCarte(index);
		carteReactifSubstratSelectionne.retourne();
		return true;
	}
	/**
	 * Mode Console
	 * Permet la sélection de la carte Réaction
	 * @param index Index de la carte dans le groupe
	 * @return Retourne true si une carte est sélectionnée, false sinon
	 */
	public boolean selectionneCarteReaction(int index) {
		if (index < -1 || index >= cartesReaction.nombreCarte())
			return false;

		if (carteProduitSelectionnee == null || carteReactifSubstratSelectionne == null)
			return false;
			
		if (index == -1) {
			carteReactionSelectionne = null;
		} else {
			if (cartesReaction.getCarte(index).isTrouvee())
				return false;
			carteReactionSelectionne = cartesReaction.getCarte(index);
			carteReactionSelectionne.retourne();
		}
		return true;
	}
	/**
	 * Verifie les cartes sélectionnée.
	 * Attribue des points au joueur actuel si une association est trouvée.
	 * Retire des points si une carte Réaction est sélectionné et qu'aucune association ne correspond.
	 * @return Retourne l'association si une association est trouvée, null sinon.
	 */
	public Association verifieSelection() {
		Association associationReel = getGestionnaireAssociations()
				.getAssociation(
						new Association(
								carteProduitSelectionnee, 
								carteReactifSubstratSelectionne, null, -1));
		if (associationReel == null && carteReactionSelectionne != null) {
			getGestionnaireJoueurs().getJoueurActuel().ajouterPoint(-25);
			carteProduitSelectionnee.retourne();
			carteReactifSubstratSelectionne.retourne();
			
			carteReactionSelectionne.retourne();
			getGestionnaireJoueurs().joueurSuivant();
			carteProduitSelectionnee = null;
			carteReactifSubstratSelectionne = null;
			carteReactionSelectionne = null;
			return null;
		} else if (associationReel != null && carteReactionSelectionne != null
				&& carteReactionSelectionne.equals(associationReel.getCarteReaction()) && associationReel.isTrouvee() == false) {
			carteProduitSelectionnee.trouve();
			carteReactifSubstratSelectionne.trouve();
			carteReactionSelectionne.trouve();
			getGestionnaireJoueurs().getJoueurActuel().ajouterPoint(100);
			return associationReel;
		} else {
			carteProduitSelectionnee.retourne();
			carteReactifSubstratSelectionne.retourne();
			if (carteReactionSelectionne != null)
				carteReactionSelectionne.retourne();
			getGestionnaireJoueurs().joueurSuivant();
			carteProduitSelectionnee = null;
			carteReactifSubstratSelectionne = null;
			carteReactionSelectionne = null;
			return null;
		}
	}
	/**
	 * 
	 * @param association L'association où les caractéristiques sont vérifiées
	 * @param caracteristiques Liste de caractériques à vérifier
	 * @return	Retourne le nombre de points supplémentaire (+10 par caractéristiques correspondantes et -10 par caractérisques qui ne correspondent pas.
	 */
	public int verifieCaracteristiques(Association association, List<Caracteristique> caracteristiques) {
		int points = 0;
		for(Caracteristique caracteristique : caracteristiques) {
			if (association.containsCaracteristique(caracteristique))
				points += pointCaracteristiqueTrouvee;
			else
				points -= pointCaracteristiqueTrouvee;
		}
		gestionnaireJoueurs.getJoueurActuel().ajouterPoint(points);
		carteProduitSelectionnee = null;
		carteReactifSubstratSelectionne = null;
		carteReactionSelectionne = null;
		return points;
	}

	/**
	 * Ne fonctionne PAS, a voir avec l'interface
	 * Affiche le tableau des scores et donne le vainqueur
	 */
	public void terminerPartie() {
		vainqueur();	// a voir avec interface
	}

	/**
	 * NE fonctionne pas
	 * a voir avec l'nterface
	 * @return Retourne la liste des vainqueurs (il peut y en avoir plusieurs)
	 */
	public Joueur vainqueur() {
		int min = Integer.MAX_VALUE;
		int meilleurScore = meilleurScore();
		Joueur vainqueur=null;
		for (Joueur joueur : gestionnaireJoueurs.getJoueurs())
			if (min > joueur.getTemps() && joueur.getPoints() == meilleurScore)
				vainqueur=joueur;
			
		return vainqueur;
	
	}

	/**
	 * Calcule le meilleur score actuel de la partie
	 * @return Retourne le meilleur score
	 */
	public int meilleurScore(){
		int max = Integer.MIN_VALUE;
		
		for (Joueur joueur : gestionnaireJoueurs.getJoueurs())
			if (max < joueur.getPoints())
				max = joueur.getPoints();
			
		
		return max;
	}
	
	public Carte getCarteProduitSelectionnee() {
		return carteProduitSelectionnee;
	}

	public Carte getCarteReactifSubstratSelectionne() {
		return carteReactifSubstratSelectionne;
	}

	public Carte getCarteReactionSelectionne() {
		return carteReactionSelectionne;
	}
	
	public List<Joueur> classement() {
		ArrayList<Joueur> lesJoueurs= new ArrayList<Joueur>(this.gestionnaireJoueurs.getJoueurs());
		Collections.sort(lesJoueurs);
		return lesJoueurs;
	}

}
