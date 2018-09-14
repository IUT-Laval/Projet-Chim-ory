package view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.PanelController;
import ui.StylingUI;

public class ClassementPanel extends JPanel {

	//private PanelController panelController;
	
	private JPanel contentPanel;
	private JButton accueilButton;
	private JLabel titreLabel;
	private JLabel filtresLabel;
	
	private GridBagConstraints c;
	private JLabel thematiqueLabel;
	private JLabel nombreJoueursLabel;
	private JComboBox thematiqueComboBox;
	private JComboBox nombreJoueursComboBox;
	
	private JTable scoreTable;
	
	//public ClassementPanel(PanelController panelController) {
	public ClassementPanel() {

		//this.panelController = panelController;
		StylingUI.panelStyle(this);
		initComponents();

	}

	private void initComponents() {
		
		contentPanel = new JPanel();
		StylingUI.contentPanelStyle(contentPanel);
		
		StylingUI.buttonStyle(accueilButton = new JButton("ACCUEIL"));

		StylingUI.titleLabelStyle(titreLabel = new JLabel("CLASSEMENT"));
		StylingUI.subtitleLabelStyle(filtresLabel = new JLabel("FILTRES"));

		StylingUI.labelStyle(thematiqueLabel = new JLabel("THEMATIQUE"));
		StylingUI.labelStyle(nombreJoueursLabel = new JLabel("NOMBRE DE JOUEURS"));
		
		thematiqueComboBox = new JComboBox<>();
		nombreJoueursComboBox = new JComboBox<>();
		thematiqueComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		nombreJoueursComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		thematiqueComboBox.setMaximumSize(new Dimension(150, 25));
		nombreJoueursComboBox.setMaximumSize(new Dimension(150, 25));
		
		Object[][] data = { { "1", "Tux", "150", "00:04:35" }, { "2", "James Hughes", "150", "00:04:42" },
				{ "3", "Linus Torvalds", "150", "00:04:51" }, { "4", "Ian Murdock", "150", "00:05:10" },
				{ "5", "Dinosaure", "150", "00:05:15" }, { "6", "Diplodocus", "150", "00:05:23" },
				{ "7", "Diplodocus", "150", "00:05:52" }, { "8", "Stegosaurus", "150", "00:06:37" },
				{ "9", "Lapin", "150", "00:07:24" }, { "10", "Compsognathus", "150", "00:07:45" } };
		String title[] = { "Position", "Pseudo", "Points", "Temps" };
		
		scoreTable = new JTable(data, title);
		JScrollPane scrolltable = new JScrollPane(scoreTable);
		StylingUI.tableStyle(scoreTable);
		
		contentPanel.add(titreLabel);
		contentPanel.add(filtresLabel);
		contentPanel.add(thematiqueLabel);
		contentPanel.add(thematiqueComboBox);
		contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		contentPanel.add(nombreJoueursLabel);
		contentPanel.add(nombreJoueursComboBox);
		contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		contentPanel.add(scoreTable.getTableHeader());
		contentPanel.add(scrolltable);
		contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		contentPanel.add(accueilButton);
		
		this.add(contentPanel);

		/*
		accueilButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelController.lastView();
			}
		});
		*/
		
	}
	
	public void addButtonListener(ActionListener listener) {
		accueilButton.addActionListener(listener);
	}
	
	public JButton getAccueilButton() {
		return accueilButton;
	}

	public JTable getScoreTable() {
		// TODO Auto-generated method stub
		return scoreTable;
	}
}
