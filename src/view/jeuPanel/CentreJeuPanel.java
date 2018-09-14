package view.jeuPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import ui.SingleMouseClickSelectList;
import ui.StylingUI;

public class CentreJeuPanel extends JPanel {

	private JPanel top;
	private JPanel mid;
	private JPanel bottom;
	private JPanel qaJPanel;
	private JPanel cJPanel;
	private JPanel[] cartes;
	private JLabel[] cartesLabel;
	private JLabel[] cartesImage;
	private JButton accepter;
	private JButton oui;
	private JButton non;
	
	private DefaultListModel<String> dlm;
	private JList<String> listeCaracteristique;
	
	public CentreJeuPanel(Dimension dimension) {
		this.setPreferredSize(dimension);
		
		this.setLayout(new BorderLayout());
		
		top = new JPanel();
		bottom = new JPanel();
		top.setBackground(StylingUI.gray);
		bottom.setBackground(StylingUI.gray);
		top.setLayout(new BorderLayout());
		
		top.setPreferredSize(new Dimension((int) this.getPreferredSize().getWidth(), (int) this.getPreferredSize().getHeight()/2));
		bottom.setPreferredSize(new Dimension((int) this.getPreferredSize().getWidth(), (int) this.getPreferredSize().getHeight()/2));
		
		this.add(top, BorderLayout.NORTH);
		this.add(bottom, BorderLayout.SOUTH);
		
		cartes = new JPanel[3];
		cartesLabel = new JLabel[3];
		cartesImage = new JLabel[3];
		
		double cardsHeight = this.getPreferredSize().getHeight()/2 - 10;
		double cardsWidth = cardsHeight*0.75 - 10;
		
		cartes[0] = new JPanel();
		cartes[1] = new JPanel();
		cartes[2] = new JPanel();
		/*
		cartes[0].setLayout(new BorderLayout());
		cartes[1].setLayout(new BorderLayout());
		cartes[2].setLayout(new BorderLayout());
		*/
		cartesLabel[0] = new JLabel();
		cartesLabel[1] = new JLabel();
		cartesLabel[2] = new JLabel();
		cartesImage[0] = new JLabel();
		cartesImage[1] = new JLabel();
		cartesImage[2] = new JLabel();
		
		Dimension carteDimension = new Dimension((int) cardsWidth, (int) cardsHeight);
		cartes[0].setPreferredSize(carteDimension);
		cartes[1].setPreferredSize(carteDimension);
		cartes[2].setPreferredSize(carteDimension);
		
		cartes[0].setBackground(StylingUI.reactifSubstratCouleur);
		cartes[1].setBackground(StylingUI.produitCouleur);
		cartes[2].setBackground(StylingUI.reactionCouleur);
		/*
		cartes[0].add(cartesLabel[0], BorderLayout.NORTH);
		cartes[0].add(cartesImage[0], BorderLayout.SOUTH);
		cartes[1].add(cartesLabel[1], BorderLayout.NORTH);
		cartes[1].add(cartesImage[1], BorderLayout.SOUTH);
		cartes[2].add(cartesLabel[2], BorderLayout.NORTH);
		cartes[2].add(cartesImage[2], BorderLayout.SOUTH);
		*/
		cartes[0].add(cartesLabel[0]);
		cartes[0].add(cartesImage[0]);
		cartes[1].add(cartesLabel[1]);
		cartes[1].add(cartesImage[1]);
		cartes[2].add(cartesLabel[2]);
		cartes[2].add(cartesImage[2]);
		StylingUI.carteStyleCentre(cartesLabel[0]);
		StylingUI.carteStyleCentre(cartesLabel[1]);
		StylingUI.carteStyleCentre(cartesLabel[2]);
		
		dlm = new DefaultListModel<String>();
		listeCaracteristique = new SingleMouseClickSelectList<String>(dlm);

		listeCaracteristique.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(listeCaracteristique);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		JLabel caracteristiquesLabel = new JLabel("CARACTERISTIQUES");
		StylingUI.labelStyle(caracteristiquesLabel);
		accepter = new JButton("ACCEPTER");
		StylingUI.buttonStyleSmall(accepter);
		
		Dimension listeCaracteristiqueDimension = new Dimension(
				(int) (top.getPreferredSize().getWidth()-carteDimension.getWidth()*2), 
				(int) (top.getPreferredSize().getHeight()-accepter.getPreferredSize().getHeight()-caracteristiquesLabel.getPreferredSize().getHeight()));
		scrollPane.setPreferredSize(listeCaracteristiqueDimension);
		scrollPane.setSize(listeCaracteristiqueDimension);
		
		mid = new JPanel();
		JPanel midTop = new JPanel();
		midTop.setLayout(new BorderLayout());
		mid.setLayout(new BorderLayout());

		midTop.add(caracteristiquesLabel, BorderLayout.NORTH);
		midTop.add(scrollPane, BorderLayout.SOUTH);
		mid.add(midTop, BorderLayout.NORTH);
		mid.add(accepter, BorderLayout.SOUTH);
		
		top.add(cartes[0], BorderLayout.WEST);
		top.add(mid, BorderLayout.CENTER);
		top.add(cartes[1], BorderLayout.EAST);
		
		JPanel question = new JPanel();
		JPanel reponse = new JPanel();
		question.setBackground(StylingUI.gray);
		reponse.setBackground(StylingUI.gray);
		JLabel askAssoc = new JLabel("Y'a t'il une association ?");
		oui = new JButton("OUI");
		non = new JButton("NON");
		StylingUI.buttonStyleSmall(oui);
		StylingUI.buttonStyleSmall(non);
		StylingUI.subtitleLabelStyle(askAssoc);

		qaJPanel = new JPanel();
		cJPanel = new JPanel();
		qaJPanel.setBackground(StylingUI.gray);
		cJPanel.setBackground(StylingUI.gray);
		
		qaJPanel.setLayout(new BorderLayout());
		
		question.add(askAssoc);
		reponse.add(oui);
		reponse.add(non);
		
		qaJPanel.add(question, BorderLayout.NORTH);
		qaJPanel.add(reponse, BorderLayout.CENTER);
		cJPanel.add(cartes[2]);

		bottom.add(qaJPanel);
		qaJPanel.setVisible(false);
		bottom.add(cJPanel);
		cJPanel.setVisible(true);
		
		//top.setVisible(true);
		//cartes[1].setVisible(false);
	}
	
	public void addButtonListener(ActionListener listener) {
		accepter.addActionListener(listener);
		oui.addActionListener(listener);
		non.addActionListener(listener);
	}

	public void afficherCarte(int id, String nom, String image) {
		cartesLabel[id].setText(nom);
		try {
			Image img = ImageIO.read(new FileInputStream(image));
			img = img.getScaledInstance(cartes[id].getWidth(), cartes[id].getWidth()*img.getHeight(null)/img.getWidth(null), Image.SCALE_DEFAULT);
			cartesImage[id].setIcon(new ImageIcon(img));
		} catch (IOException e) {
			
		}
		if (id == 2)
			cJPanel.setVisible(!cartes[id].isVisible());
		cartes[id].setVisible(!cartes[id].isVisible());
	}
	
	public void reset() {
		mid.setVisible(false);
		cartes[0].setVisible(false);
		cartes[1].setVisible(false);
		cartes[2].setVisible(false);
		cJPanel.setVisible(false);
		qaJPanel.setVisible(false);
	}
	
	public void afficherCaracteristiques() {
		listeCaracteristique.clearSelection();
		mid.setVisible(true);
	}
	public int[] getCaracteristiques() {
		return listeCaracteristique.getSelectedIndices();
	}
	
	public void afficherQuestion() {
		qaJPanel.setVisible(!qaJPanel.isVisible());
	}
	
	public JButton getAccepter() {
		return accepter;
	}
	public JButton getOui() {
		return oui;
	}
	public JButton getNon() {
		return non;
	}
	
	public void resetCaracteristiques() {
		dlm.clear();
	}
	
	public void addCaracteristiques(String[] caracteristiques) {
		for (int i = 0;i<caracteristiques.length;i++)
			dlm.addElement(caracteristiques[i]);
	}
}