package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.UIManager;

import controller.PanelController;
import ui.StylingUI;

public class OptionsPanel extends JPanel {

	//private PanelController panelController;
	private JPanel contentPanel;
	private JPanel gridPanel;
	private GridBagConstraints c;
	private JButton sauvegarderButton;
	private JButton annulerButton;
	private JLabel titreLabel;

	private JLabel tutorielLabel;
	private JLabel sonLabel;
	private JLabel musiqueLabel;
	private JLabel sonsLabel;
	private JLabel thematiqueLabel;
	private JLabel resolutionLabel;
	private JLabel daltonismeLabel;
	private JLabel optionsAdminLabel;
	private JLabel hotePortLabel;
	private JLabel identifiantLabel;
	private JLabel passwordLabel;

	private JCheckBox tutorielCheckBox;
	private JCheckBox sonCheckBox;
	private JSlider musiqueSlider;
	private JSlider sonsSlider;
	private JComboBox thematiqueComboBox;
	private JComboBox resolutionComboBox;
	private JCheckBox daltonismeCheckBox;
	private JTextField hotePortTextField;
	private JTextField identifiantTextField;
	private JTextField passwordTextField;

	//public OptionsPanel(PanelController panelController) {
	public OptionsPanel() {
	
		//this.panelController = panelController;
		StylingUI.panelStyle(this);
		initComponents();

	}

	private void initComponents() {

		contentPanel = new JPanel();
		StylingUI.contentPanelStyle(contentPanel);

		StylingUI.buttonStyle(sauvegarderButton = new JButton("SAUVEGARDER"));
		StylingUI.buttonStyle(annulerButton = new JButton("ANNULER"));

		StylingUI.titleLabelStyle(titreLabel = new JLabel("OPTIONS"));
		StylingUI.labelStyle(tutorielLabel = new JLabel("ACTIVER LE TUTORIEL"));
		StylingUI.labelStyle(sonLabel = new JLabel("ACTIVER LE SON"));
		StylingUI.labelStyle(musiqueLabel = new JLabel("VOLUME DE LA MUSIQUE"));
		StylingUI.labelStyle(sonsLabel = new JLabel("VOLUME DES SONS"));
		StylingUI.labelStyle(thematiqueLabel = new JLabel("THEMATIQUE"));
		StylingUI.labelStyle(resolutionLabel = new JLabel("RESOLUTION"));
		StylingUI.labelStyle(daltonismeLabel = new JLabel("DALTONISME"));
		StylingUI.subtitleLabelStyle(optionsAdminLabel = new JLabel("OPTIONS ADMINISTATEUR"));
		StylingUI.labelStyle(hotePortLabel = new JLabel("HOTE:PORT"));
		StylingUI.labelStyle(identifiantLabel = new JLabel("IDENTIFIANT"));
		StylingUI.labelStyle(passwordLabel = new JLabel("MOT DE PASSE"));

		tutorielCheckBox = new JCheckBox();
		sonCheckBox = new JCheckBox();
		musiqueSlider = new JSlider();
		sonsSlider = new JSlider();
		thematiqueComboBox = new JComboBox<>();
		resolutionComboBox = new JComboBox<>();
		daltonismeCheckBox = new JCheckBox();
		hotePortTextField = new JTextField();
		identifiantTextField = new JTextField();
		passwordTextField = new JTextField();

		hotePortTextField.setColumns(30);
		identifiantTextField.setColumns(20);
		passwordTextField.setColumns(20);
		
		StylingUI.jTextFieldStyle(hotePortTextField);
		StylingUI.jTextFieldStyle(identifiantTextField);
		StylingUI.jTextFieldStyle(passwordTextField);

		gridPanel = new JPanel(new GridBagLayout());
		gridPanel.setBackground(StylingUI.gray);
		gridPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

		c = new GridBagConstraints();
		c.weightx = 2;
		c.weighty = 14;

		contentPanel.add(titreLabel);

		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 0;
		gridPanel.add(tutorielLabel, c);
		c.gridy = 1;
		gridPanel.add(sonLabel, c);
		c.gridy = 2;
		gridPanel.add(musiqueLabel, c);
		c.gridy = 3;
		c.gridwidth = 2;
		gridPanel.add(musiqueSlider, c);
		c.gridy = 4;
		c.gridwidth = 1;
		gridPanel.add(sonsLabel, c);
		c.gridy = 5;
		c.gridwidth = 2;
		gridPanel.add(sonsSlider, c);
		c.gridy = 6;
		c.gridwidth = 1;
		gridPanel.add(thematiqueLabel, c);
		c.gridy = 7;
		gridPanel.add(resolutionLabel, c);
		c.gridy = 8;
		gridPanel.add(daltonismeLabel, c);
		c.gridy = 9;
		gridPanel.add(optionsAdminLabel, c);
		c.gridy = 10;
		gridPanel.add(hotePortLabel, c);
		c.gridy = 11;
		gridPanel.add(identifiantLabel, c);
		c.gridy = 12;
		gridPanel.add(passwordLabel, c);
		c.gridy = 13;
		c.anchor = GridBagConstraints.EAST;
		gridPanel.add(annulerButton, c);

		c.gridx = 1;

		c.gridy = 0;
		gridPanel.add(tutorielCheckBox, c);
		c.gridy = 1;
		gridPanel.add(sonCheckBox, c);
		c.gridy = 6;
		gridPanel.add(thematiqueComboBox, c);
		c.gridy = 7;
		gridPanel.add(resolutionComboBox, c);
		c.gridy = 8;
		gridPanel.add(daltonismeCheckBox, c);
		c.gridy = 10;
		gridPanel.add(hotePortTextField, c);
		c.gridy = 11;
		gridPanel.add(identifiantTextField, c);
		c.gridy = 12;
		gridPanel.add(passwordTextField, c);
		c.gridy = 13;
		gridPanel.add(sauvegarderButton, c);
		
		contentPanel.add(gridPanel);
		this.add(contentPanel);
		
	}
	
	public JTextField getHotePortTextField() {
		return hotePortTextField;
	}

	public void setHotePortTextField(JTextField hotePortTextField) {
		this.hotePortTextField = hotePortTextField;
	}

	public JTextField getIdentifiantTextField() {
		return identifiantTextField;
	}

	public void setIdentifiantTextField(JTextField identifiantTextField) {
		this.identifiantTextField = identifiantTextField;
	}

	public JTextField getPasswordTextField() {
		return passwordTextField;
	}

	public void setPasswordTextField(JTextField passwordTextField) {
		this.passwordTextField = passwordTextField;
	}

	public void addButtonListener(ActionListener listener) {
		sauvegarderButton.addActionListener(listener);
		annulerButton.addActionListener(listener);
	}
	
	public JComboBox getThematiqueComboBox() {
		return thematiqueComboBox;
	}

	public void setThematiqueComboBox(JComboBox thematiqueComboBox) {
		this.thematiqueComboBox = thematiqueComboBox;
	}

	public JButton getSauvegarderButton() {
		return sauvegarderButton;
	}

	public JButton getAnnulerButton() {
		return annulerButton;
	}
	public void addToThematiqueComboBox(String thematique) {
		thematiqueComboBox.addItem(thematique);
	}

	public JComboBox getResolutionComboBox() {
		return resolutionComboBox;
	}
	public void addToResolutionComboBox(String resolution) {
		resolutionComboBox.addItem(resolution);
	}

}
