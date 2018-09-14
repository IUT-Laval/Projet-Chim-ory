package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.dao.ScoreDAO;
import model.jeu.Association;
import model.jeu.Caracteristique;
import model.jeu.Carte;
import model.jeu.Score;
import model.jeu.interfaces.CartesListener;
import model.jeu.interfaces.JoueursListener;
import view.JeuPanel;
import view.Panels;

public class JeuController extends Controller implements PanelControllerInterface {

	private Controller mC;
	private JeuPanel jeuPanel;
	private boolean repondu = false;
	// private Association associationTrouvee = null;
	private int indexCarteReactifSubstrat = -1;
	private int indexCarteProduit = -1;
	private int indexCarteReaction = -1;

	private List<JPanel> cartesReactifSubstrat;
	private List<JPanel> cartesProduit;
	private List<JPanel> cartesReaction;

	private Association associationTrouvee = null;

	public JeuController(Controller mC) {

		this.mC = mC;
		this.jeuPanel = mC.view.getJeuPanel();

		this.jeuPanel.addButtonListener(new ButtonListener());

		// this.jeuPanel.addCarteListener(new CarteListener());

	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			JButton pauseButton = jeuPanel.getChimoryPanel().getPauseButton();
			JButton reprendreButton = jeuPanel.getPausePanel().getReprendreButton();
			JButton optionsButton = jeuPanel.getPausePanel().getOptionsButton();
			JButton quitterButton = jeuPanel.getPausePanel().getQuitterButton();

			JButton accepter = jeuPanel.getChimoryPanel().getCenter().getAccepter();
			JButton oui = jeuPanel.getChimoryPanel().getCenter().getOui();
			JButton non = jeuPanel.getChimoryPanel().getCenter().getNon();

			if (e.getSource() == pauseButton)
				jeuPanel.afficherPause(true);
			else if (e.getSource() == reprendreButton)
				jeuPanel.afficherPause(false);
			else if (e.getSource() == optionsButton)
				mC.panelController.changeView(Panels.OPTIONS);
			else if (e.getSource() == quitterButton)
				mC.panelController.changeView(Panels.ACCUEIL);
			else if (e.getSource() == oui) {

				repondu = true;
				// mC.model.getJeu().verifieSelection();
				jeuPanel.getChimoryPanel().getCenter().afficherQuestion();

			} else if (e.getSource() == non) {

				mC.model.getJeu().verifieSelection();
				jeuPanel.getChimoryPanel().getCenter().reset();

			} else if (e.getSource() == accepter) {

				List<Caracteristique> listeCaracteristique = new ArrayList<>();
				int[] lC = jeuPanel.getChimoryPanel().getCenter().getCaracteristiques();
				for (int i = 0; i < lC.length; i++) {
					listeCaracteristique
							.add(mC.model.getJeu().getGestionnaireCaracteristiques().getCaracteristique(lC[i]));
				}

				mC.model.getJeu().verifieCaracteristiques(associationTrouvee, listeCaracteristique);

				associationTrouvee = null;

				jeuPanel.getChimoryPanel().getCenter().reset();

				if ((mC.model.getJeu().getCartesReactifSubstrat().nombreCarte()
						- mC.model.getJeu().getCartesReactifSubstrat().nombreCarteTrouvee()) <= 0
						|| (mC.model.getJeu().getCartesProduit().nombreCarte()
								- mC.model.getJeu().getCartesProduit().nombreCarteTrouvee()) <= 0
						|| (mC.model.getJeu().getCartesReaction().nombreCarte()
								- mC.model.getJeu().getCartesReaction().nombreCarteTrouvee()) <= 0) {
					// ajout des scores avant de quitter la partie
					ScoreDAO scoreDAO = new ScoreDAO();
					for (int j = 0; j < mC.model.getJeu().getGestionnaireJoueurs().nombreJoueurs(); j++) {
						Score score = new Score();
						score.setDifficulte(mC.model.getJeu().getDifficulte());
						score.setModeJeu("standard");
						score.setNbJoueurs(mC.model.getJeu().getGestionnaireJoueurs().nombreJoueurs());
						score.setPseudo(mC.model.getJeu().getGestionnaireJoueurs().getJoueur(j).getPseudo());
						score.setScore(mC.model.getJeu().getGestionnaireJoueurs().getJoueur(j).getPoints());
						long temps = mC.model.getJeu().getGestionnaireJoueurs().getJoueur(j).getTemps();
						int heure = (int)temps/3600000;
						int minutes = (int)temps/60000-heure*60;
						int secondes = (int)temps/1000 - minutes*60;
						score.setTemps(new Time(heure,minutes,secondes));
						score.setThematique(mC.model.getJeu().getThematique().getThematiqueActuel());
						scoreDAO.ajouter(score);
					}

					mC.panelController.changeView(Panels.ACCUEIL);
				}

			}
		}
	}

	private class CarteListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {

			for (int i = 0; i < cartesReactifSubstrat.size(); i++)
				if (e.getSource() == cartesReactifSubstrat.get(i))
					System.out.println(mC.model.getJeu().getCartesReactifSubstrat().getCarte(i).getNom());

			for (int i = 0; i < cartesProduit.size(); i++)
				if (e.getSource() == cartesProduit.get(i))
					System.out.println(mC.model.getJeu().getCartesProduit().getCarte(i).getNom());

			for (int i = 0; i < cartesReaction.size(); i++)
				if (e.getSource() == cartesReaction.get(i))
					System.out.println(mC.model.getJeu().getCartesReaction().getCarte(i).getNom());

		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
			for (int i = 0; i < cartesReactifSubstrat.size(); i++)
				if (e.getSource() == cartesReactifSubstrat.get(i))
					if (mC.model.getJeu().getCarteReactifSubstratSelectionne() == null)
						mC.model.getJeu().selectionneCarteReactifSubstrat(i);

			for (int i = 0; i < cartesProduit.size(); i++)
				if (e.getSource() == cartesProduit.get(i))
					if (mC.model.getJeu().getCarteProduitSelectionnee() == null)
						mC.model.getJeu().selectionneCarteProduit(i);

			for (int i = 0; i < cartesReaction.size(); i++)
				if (e.getSource() == cartesReaction.get(i))
					if (mC.model.getJeu().getCarteReactionSelectionne() == null)
						if (repondu)
							mC.model.getJeu().selectionneCarteReaction(i);

		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

	}

	@Override
	public void initPanel() {
		mC.model.getJeu().getGestionnaireJoueurs().removeListener();
		mC.model.getJeu().getCartesReactifSubstrat().removeListener();
		mC.model.getJeu().getCartesProduit().removeListener();
		mC.model.getJeu().getCartesReaction().removeListener();
		mC.model.getJeu().initialiserPartie();

		jeuPanel.afficherPause(false);
		jeuPanel.getChimoryPanel().resetCartes();

		// Initialisation Classement
		mC.model.getJeu().getGestionnaireJoueurs().getJoueurs().size();

		String[] joueursNom = new String[mC.model.getJeu().getGestionnaireJoueurs().getJoueurs().size()];
		for (int i = 0; i < mC.model.getJeu().getGestionnaireJoueurs().getJoueurs().size(); i++)
			joueursNom[i] = mC.model.getJeu().getGestionnaireJoueurs().getJoueurs().get(i).getPseudo();

		jeuPanel.getChimoryPanel().getClassement().initPlayers(joueursNom);
		jeuPanel.getChimoryPanel().getClassement()
				.setJoueurActuel(mC.model.getJeu().getGestionnaireJoueurs().getIndexJoueurActuel());

		// Initialisation Cartes
		jeuPanel.getChimoryPanel().setNbCartesProduit(mC.model.getJeu().getCartesProduit().getNbCarte());
		jeuPanel.getChimoryPanel()
				.setNbCartesReactifSubstrat(mC.model.getJeu().getCartesReactifSubstrat().getNbCarte());
		jeuPanel.getChimoryPanel().setNbCartesReaction(mC.model.getJeu().getCartesReaction().getNbCarte());

		for (int i = 0; i < mC.model.getJeu().getCartesReactifSubstrat().getNbCarte(); i++)
			jeuPanel.getChimoryPanel().initCartesReactifSubstrat("", "");

		for (int i = 0; i < mC.model.getJeu().getCartesProduit().getNbCarte(); i++)
			jeuPanel.getChimoryPanel().initCartesProduit("", "");

		for (int i = 0; i < mC.model.getJeu().getCartesReaction().getNbCarte(); i++)
			jeuPanel.getChimoryPanel().initCartesReaction(mC.model.getJeu().getCartesReaction().getCarte(i).getNom(),
					"thematiques/" + mC.model.getJeu().getThematique().getThematiqueActuel() + "/images/"
							+ mC.model.getJeu().getCartesReaction().getCarte(i).getImage());

		jeuPanel.getChimoryPanel().initCartes();

		cartesReactifSubstrat = jeuPanel.getChimoryPanel().getCartesReactifSubstrat();
		cartesProduit = jeuPanel.getChimoryPanel().getCartesProduit();
		cartesReaction = jeuPanel.getChimoryPanel().getCartesReaction();

		this.jeuPanel.addCarteListener(new CarteListener());

		jeuPanel.getChimoryPanel().getCenter().reset();

		// Initialisation Caracteristiques
		jeuPanel.getChimoryPanel().getCenter().resetCaracteristiques();
		String[] caracteristiques = new String[mC.model.getJeu().getGestionnaireCaracteristiques()
				.getNbCaracteristiques()];
		for (int i = 0; i < mC.model.getJeu().getGestionnaireCaracteristiques().getNbCaracteristiques(); i++)
			caracteristiques[i] = mC.model.getJeu().getGestionnaireCaracteristiques().getCaracteristique(i).toString();
		jeuPanel.getChimoryPanel().getCenter().addCaracteristiques(caracteristiques);

		// listener
		mC.model.getJeu().getGestionnaireJoueurs().addListener(new JoueursListener() {
			@Override
			public void pointsJoueurChange(int indexJoueur, int points) {
				jeuPanel.getChimoryPanel().getClassement().setPoints(indexJoueur, points);
			}

			@Override
			public void joueurActuelChange(int indexJoueur) {
				jeuPanel.getChimoryPanel().getClassement().setJoueurActuel(indexJoueur);
			}

			@Override
			public void tempsJoueurChange(int indexJoueur, long temps) {
				jeuPanel.getChimoryPanel().getClassement().setTemps(indexJoueur, temps);
			}
		});

		mC.model.getJeu().getCartesReactifSubstrat().addListener(new CartesListener() {
			@Override
			public void trouve(int index, Carte carte) {
				jeuPanel.getChimoryPanel().getFound().ajouterCartesReactifSubstrat(carte.getNom(), carte.getImage());
			}

			@Override
			public void retourne(int index, Carte carte) {
				jeuPanel.getChimoryPanel().retourne(cartesReactifSubstrat, index);
				jeuPanel.getChimoryPanel().getCenter().afficherCarte(0, carte.getNom(), "thematiques/"
						+ mC.model.getJeu().getThematique().getThematiqueActuel() + "/images/" + carte.getImage());
				if (mC.model.getJeu().getCarteProduitSelectionnee() != null)
					jeuPanel.getChimoryPanel().getCenter().afficherQuestion();
			}
		});
		mC.model.getJeu().getCartesProduit().addListener(new CartesListener() {
			@Override
			public void trouve(int index, Carte carte) {
				jeuPanel.getChimoryPanel().getFound().ajouterCartesProduit(carte.getNom(), carte.getImage());
			}

			@Override
			public void retourne(int index, Carte carte) {
				jeuPanel.getChimoryPanel().retourne(cartesProduit, index);
				jeuPanel.getChimoryPanel().getCenter().afficherCarte(1, carte.getNom(), "thematiques/"
						+ mC.model.getJeu().getThematique().getThematiqueActuel() + "/images/" + carte.getImage());
				if (mC.model.getJeu().getCarteReactifSubstratSelectionne() != null)
					jeuPanel.getChimoryPanel().getCenter().afficherQuestion();
			}
		});
		mC.model.getJeu().getCartesReaction().addListener(new CartesListener() {
			@Override
			public void trouve(int index, Carte carte) {
				jeuPanel.getChimoryPanel().getFound().ajouterCartesReaction(carte.getNom(), carte.getImage());
			}

			@Override
			public void retourne(int index, Carte carte) {

				jeuPanel.getChimoryPanel().retourne(cartesReaction, index);
				jeuPanel.getChimoryPanel().getCenter().afficherCarte(2, carte.getNom(), "thematiques/"
						+ mC.model.getJeu().getThematique().getThematiqueActuel() + "/images/" + carte.getImage());

				if (repondu) {

					repondu = false;
					associationTrouvee = mC.model.getJeu().verifieSelection();

					if (associationTrouvee != null)
						jeuPanel.getChimoryPanel().getCenter().afficherCaracteristiques();
					else
						jeuPanel.getChimoryPanel().getCenter().reset();

				}
			}
		});

	}
}
