package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.jeu.Joueur;
import view.AccueilPanel;
import view.OptionsJeuPanel;
import view.Panels;

public class OptionsJeuController extends Controller implements PanelControllerInterface {
	
	private Controller mC;
	private OptionsJeuPanel optionsJeuPanel;
	
	private JComboBox modeComboBox;
	private JComboBox thematiqueComboBox;
	private JComboBox difficulteComboBox;
	
	
	public OptionsJeuController(Controller mC) {
		
		this.mC = mC;
		this.optionsJeuPanel = mC.view.getOptionsJeuPanel();
		this.optionsJeuPanel.addButtonListener(new ButtonListener());
		
	}
	
	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			JButton annulerButton = optionsJeuPanel.getAnnulerButton();
			JButton jouerButton = optionsJeuPanel.getJouerButton();
			JButton[] boutonsAR = optionsJeuPanel.getBoutonsAR();
			JLabel[] playerLabel = optionsJeuPanel.getPlayerLabel();
			
			if (e.getSource() == annulerButton)
				mC.panelController.changeView(Panels.ACCUEIL);
			else if (e.getSource() == jouerButton) {
				if (enableToPlay()) {
					setOptionsPartie();
					mC.panelController.changeView(Panels.JEU);
				}
			}
			
			for (int i = 0;i<boutonsAR.length;i++)
				if (e.getSource() == boutonsAR[i])
					optionsJeuPanel.setPlayer(i, !playerLabel[i].isVisible());
				
		}
	}

	private boolean enableToPlay() {
		JTextField[] playerTextField = optionsJeuPanel.getPlayerTextField();

		JComboBox modeComboBox = optionsJeuPanel.getModeComboBox();
		JComboBox thematiqueComboBox = optionsJeuPanel.getThematiqueComboBox();
		JComboBox difficulteComboBox = optionsJeuPanel.getDifficulteComboBox();	
		
		for (int i = 0;i<playerTextField.length;i++)
			if (playerTextField[i].isVisible())
				if (playerTextField[i].getText().equals(""))
					return false;
		
		if (thematiqueComboBox.getItemCount() == 0 || modeComboBox.getSelectedItem().toString().equals(""))
			return false;
		
		if (thematiqueComboBox.getItemCount() == 0 || thematiqueComboBox.getSelectedItem().toString().equals(""))
			return false;
		
		if (thematiqueComboBox.getItemCount() == 0 || difficulteComboBox.getSelectedItem().toString().equals(""))
			return false;
		
		return true;
	}
	
	private void setOptionsPartie() {

		JTextField[] playerTextField = optionsJeuPanel.getPlayerTextField();
		
		// Ajout des joueurs
		mC.model.getJeu().getGestionnaireJoueurs().getJoueurs().clear();
		for (int i = 0;i<playerTextField.length;i++)
			if (playerTextField[i].isVisible())
				mC.model.getJeu().getGestionnaireJoueurs().addJoueur(new Joueur(playerTextField[i].getText()));
		
		// difficulte		
		if (difficulteComboBox.getSelectedItem().toString().equals("Facile"))
			mC.model.getJeu().setDifficulte(1);
		else if (difficulteComboBox.getSelectedItem().toString().equals("Normale"))
			mC.model.getJeu().setDifficulte(2);
		else if (difficulteComboBox.getSelectedItem().toString().equals("Difficile"))
			mC.model.getJeu().setDifficulte(3);
		
		// thematique
			
		mC.model.getJeu().getThematique().setThematiqueActuel(thematiqueComboBox.getSelectedItem().toString());
	}
	
	@Override
	public void initPanel() {
		
		modeComboBox = optionsJeuPanel.getModeComboBox();
		thematiqueComboBox = optionsJeuPanel.getThematiqueComboBox();
		difficulteComboBox = optionsJeuPanel.getDifficulteComboBox();	
		
		optionsJeuPanel.resetThematiqueComboBox();
		
		mC.model.getJeu().getThematique().listeThematique();
		for (int i = 0;i<mC.model.getJeu().getThematique().nbThematique();i++) 
			optionsJeuPanel.addToThematiqueComboBox(mC.model.getJeu().getThematique().getThematique(i));
	}
	
}
