package view.jeuPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ui.StylingUI;

public class AssociationTrouveePanel extends JPanel {

	JScrollPane reactifSubstratPanel;
	JScrollPane produitPanel;
	JScrollPane reactionPanel;
	
	JPanel reactifSubstratPanelFlow;
	JPanel produitPanelFlow;
	JPanel reactionPanelFlow;

	public AssociationTrouveePanel(Dimension dimension) {

		this.setPreferredSize(dimension);
		//this.setBackground(Color.red);

		((FlowLayout) this.getLayout()).setVgap(0);
		((FlowLayout) this.getLayout()).setHgap(0);

		reactifSubstratPanelFlow = new JPanel();
		produitPanelFlow = new JPanel();
		reactionPanelFlow = new JPanel();
		
		reactifSubstratPanel = new JScrollPane(reactifSubstratPanelFlow);
		produitPanel = new JScrollPane(produitPanelFlow);
		reactionPanel = new JScrollPane(reactionPanelFlow);

		Dimension pDimension = new Dimension((int) dimension.getWidth(), (int) dimension.getHeight() / 3);

		reactifSubstratPanel.setPreferredSize(pDimension);
		produitPanel.setPreferredSize(pDimension);
		reactionPanel.setPreferredSize(pDimension);

		reactifSubstratPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		reactifSubstratPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		produitPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		produitPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		reactionPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		reactionPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

		reactifSubstratPanel.setBackground(Color.black);
		produitPanel.setBackground(Color.red);
		reactionPanel.setBackground(Color.green);
		
		this.add(reactifSubstratPanel);
		this.add(produitPanel);
		this.add(reactionPanel);
	}
	
	public void reset() {

		reactifSubstratPanelFlow.removeAll();
		produitPanelFlow.removeAll();
		reactionPanelFlow.removeAll();
		
		reactifSubstratPanelFlow.validate();
		produitPanelFlow.validate();
		reactionPanelFlow.validate();
		
	}
	
	public void ajouterCartesReactifSubstrat(String nom, String image) {
		double carteHeight = this.getPreferredSize().getHeight()/3-40;
		double carteWidth = carteHeight*0.75;
		
		JPanel carte = new JPanel();
		carte.setPreferredSize(new Dimension((int) carteWidth, (int) carteHeight));
		carte.setBackground(StylingUI.reactifSubstratCouleur);

		JLabel jlb = new JLabel(nom);
		StylingUI.carteStyle(jlb);
		carte.add(jlb);
		JLabel nlbl;
		try {
			Image img = ImageIO.read(new FileInputStream(image));
			img = img.getScaledInstance(carte.getPreferredSize().width, 
					carte.getPreferredSize().width*img.getHeight(null)/img.getWidth(null), 
					Image.SCALE_DEFAULT);
			nlbl = new JLabel(new ImageIcon(img));
		} catch (IOException e) {
			nlbl = new JLabel();
		}
		carte.add(nlbl);
		
		reactifSubstratPanelFlow.add(carte);
		reactifSubstratPanelFlow.validate();
		reactifSubstratPanelFlow.repaint();
	}
	
	public void ajouterCartesProduit(String nom, String image) {
		double carteHeight = this.getPreferredSize().getHeight()/3-40;
		double carteWidth = carteHeight*0.75;
		
		JPanel carte = new JPanel();
		carte.setPreferredSize(new Dimension((int) carteWidth, (int) carteHeight));
		carte.setBackground(StylingUI.produitCouleur);

		JLabel jlb = new JLabel(nom);
		StylingUI.carteStyle(jlb);
		carte.add(jlb);
		JLabel nlbl;
		try {
			Image img = ImageIO.read(new FileInputStream(image));
			img = img.getScaledInstance(carte.getPreferredSize().width, 
					carte.getPreferredSize().width*img.getHeight(null)/img.getWidth(null), 
					Image.SCALE_DEFAULT);
			nlbl = new JLabel(new ImageIcon(img));
		} catch (IOException e) {
			nlbl = new JLabel();
		}
		carte.add(nlbl);
		
		produitPanelFlow.add(carte);
		produitPanelFlow.validate();
		produitPanelFlow.repaint();
	}
	public void ajouterCartesReaction(String nom, String image) {
		double carteHeight = this.getPreferredSize().getHeight()/3-40;
		double carteWidth = carteHeight*0.75;
		
		JPanel carte = new JPanel();
		carte.setPreferredSize(new Dimension((int) carteWidth, (int) carteHeight));
		carte.setBackground(StylingUI.reactionCouleur);

		JLabel jlb = new JLabel(nom);
		StylingUI.carteStyle(jlb);
		carte.add(jlb);
		JLabel nlbl;
		try {
			Image img = ImageIO.read(new FileInputStream(image));
			img = img.getScaledInstance(carte.getPreferredSize().width, 
					carte.getPreferredSize().width*img.getHeight(null)/img.getWidth(null), 
					Image.SCALE_DEFAULT);
			nlbl = new JLabel(new ImageIcon(img));
		} catch (IOException e) {
			nlbl = new JLabel();
		}
		carte.add(nlbl);
		
		reactionPanelFlow.add(carte);
		reactionPanelFlow.validate();
		reactionPanelFlow.repaint();
	}

}
