package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.jeu.Option;
import model.jeu.Thematique;
import view.OptionsPanel;

public class OptionsController extends Controller implements PanelControllerInterface {

	private Controller mC;
	private OptionsPanel optionsPanel;
	
	public OptionsController(Controller mC) {
		
		this.mC = mC;
		this.optionsPanel = mC.view.getOptionsPanel();
		this.optionsPanel.addButtonListener(new ButtonListener());
		
	}
	
	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			JButton sauvegarderButton = optionsPanel.getSauvegarderButton();
			JButton annulerButton = optionsPanel.getAnnulerButton();
			
			if(e.getSource() == sauvegarderButton){
				mC.model.getOption().setThematiqueDefaut(optionsPanel.getThematiqueComboBox().getSelectedItem().toString());
				mC.model.getOption().setResolution(optionsPanel.getResolutionComboBox().getSelectedItem().toString());
				mC.model.getOption().setUrlBDD(optionsPanel.getHotePortTextField().getText());
				mC.model.getOption().setUser(optionsPanel.getIdentifiantTextField().getText());
				mC.model.getOption().setPassword(optionsPanel.getPasswordTextField().getText());
				mC.model.getOption().save();
				mC.panelController.lastView();
			}
			else if(e.getSource() == annulerButton)
				mC.panelController.lastView();
			
		}
	}

	@Override
	public void initPanel() {
		//reset
		optionsPanel.getThematiqueComboBox().removeAllItems();
		optionsPanel.getResolutionComboBox().removeAllItems();
		
		//
		mC.model.getOption().load();
		mC.model.getJeu().getThematique().listeThematique();
		
		//remplissage
		for (int i = 0;i<mC.model.getJeu().getThematique().nbThematique();i++){ 
			optionsPanel.addToThematiqueComboBox(mC.model.getJeu().getThematique().getThematique(i));
		}
		for (int i = 0; i < mC.model.getOption().getResolutionsDisponibles().length; i++) {
			optionsPanel.addToResolutionComboBox(mC.model.getOption().getResolutionsDisponibles()[i]);
		}
		optionsPanel.getHotePortTextField().setText(mC.model.getOption().getUrlBDD());
		optionsPanel.getIdentifiantTextField().setText(mC.model.getOption().getUser());
		optionsPanel.getPasswordTextField().setText(mC.model.getOption().getPassword());
		
		
		//préselection selon les options du XML
		optionsPanel.getThematiqueComboBox().setSelectedItem(mC.model.getOption().getThematiqueDefaut());
		optionsPanel.getResolutionComboBox().setSelectedItem(mC.model.getOption().getResolution());
	}
	
}
