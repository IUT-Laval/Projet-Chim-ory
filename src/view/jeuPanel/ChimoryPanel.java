package view.jeuPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.StylingUI;
import view.JeuPanel;

public class ChimoryPanel extends JPanel {

	private JeuPanel jP;
	
	private static final int ICONESIZE = 30;
	
	private JPanel top;
	private JButton pauseButton;
	private JLabel time;

	private JPanel reactifSubstrat;
	private List<JPanel> cartesReactifSubstrat = new ArrayList<>();
	private List<JLabel> cartesReactifSubstratNom = new ArrayList<>();
	private List<JLabel> cartesReactifSubstratImageLabel = new ArrayList<>();
	private List<String> cartesReactifSubstratImage = new ArrayList<>();
	private JLabel labelReactifSubstrat;

	private JPanel produit;
	private List<JPanel> cartesProduit = new ArrayList<>();
	private List<JLabel> cartesProduitNom = new ArrayList<>();
	private List<JLabel> cartesProduitImageLabel = new ArrayList<>();
	private List<String> cartesProduitImage = new ArrayList<>();
	private JLabel labelProduit;

	private JPanel reaction;
	private List<JPanel> cartesReaction = new ArrayList<>();
	private List<JLabel> cartesReactionNom = new ArrayList<>();
	private List<JLabel> cartesReactionImageLabel = new ArrayList<>();
	private List<String> cartesReactionImage = new ArrayList<>();
	private JLabel labelReaction;

	private int nbCartesReactifSubstrat = 10;
	private int nbCartesProduit = 10;
	private int nbCartesReaction = 10;
	
	private CentreJeuPanel center;
	private ClassementJeuPanel classement;
	private AssociationTrouveePanel found;
	
	public ChimoryPanel(JeuPanel jP) {
		
		this.jP = jP;
		StylingUI.panelStyle(this);
		initComponents();
	}

	private void initComponents() {

		this.setBackground(StylingUI.gray);

		this.setLayout(new FlowLayout());
		((FlowLayout) this.getLayout()).setVgap(0);
		((FlowLayout) this.getLayout()).setHgap(0);
		pauseButton = new JButton();

		try {
			Image img = ImageIO.read(new FileInputStream("ico/pause.png"));
			img = img.getScaledInstance(ICONESIZE, ICONESIZE, Image.SCALE_DEFAULT);
			pauseButton.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}

		pauseButton.setLayout(null);
		pauseButton.setBorder(null);
		pauseButton.setContentAreaFilled(false);
		pauseButton.setBounds(0, 0, ICONESIZE, ICONESIZE);

		top = new JPanel();
		top.setBackground(StylingUI.gray);
		top.setLayout(new BorderLayout());
		
		pauseButton.setHorizontalAlignment(JButton.LEFT);
		
		time = new JLabel("00:00:00");
		time.setFont(new Font("Haettenschweiler", Font.PLAIN, 35));
		time.setHorizontalAlignment(JLabel.CENTER);
		
		top.add(pauseButton, BorderLayout.WEST);
		top.add(time, BorderLayout.CENTER);
		top.add(Box.createRigidArea(new Dimension(ICONESIZE, ICONESIZE)), BorderLayout.EAST);
		top.setPreferredSize(new Dimension(this.getWidth(), top.getPreferredSize().height));

		reactifSubstrat = new JPanel();
		produit = new JPanel();
		reaction = new JPanel();
		reactifSubstrat.setBackground(StylingUI.gray);
		produit.setBackground(StylingUI.gray);
		reaction.setBackground(StylingUI.gray);
		labelReactifSubstrat = new JLabel("Réactif + Substrat");
		labelProduit = new JLabel("Produit");
		labelReaction = new JLabel("Réaction complète");
		
		//initCartes();
		//initPaquets(reactifSubstrat, cartesReactifSubstrat, nbCartesReactifSubstrat, labelReactifSubstrat, new Color(228, 196, 196));
		//initPaquets(produit, cartesProduit, nbCartesProduit, labelProduit, new Color(196, 225, 228));
		//initPaquets(reaction, cartesReaction, nbCartesReaction, labelReaction, new Color(196, 228, 197));

		Dimension panelDimension = new Dimension(this.getWidth() / 3, (this.getHeight() - top.getPreferredSize().height) / 2);

		center = new CentreJeuPanel(panelDimension);
		classement = new ClassementJeuPanel(panelDimension);
		found = new AssociationTrouveePanel(panelDimension);
		
		this.add(top);
		this.add(reactifSubstrat);
		this.add(center);
		this.add(produit);
		this.add(classement);
		this.add(reaction);
		this.add(found);

		/*
		pauseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				pause.setVisible(true);
			}
		});
		*/
		
	}

	public void initCartesReactifSubstrat(String nom, String image) {
		JLabel jlb = new JLabel(nom);
		StylingUI.carteStyle(jlb);
		cartesReactifSubstratNom.add(jlb);
		cartesReactifSubstratImage.add(image);
	}
	public void initCartesProduit(String nom, String image) {
		JLabel jlb = new JLabel(nom);
		StylingUI.carteStyle(jlb);
		cartesProduitNom.add(jlb);
		cartesProduitImage.add(image);
	}
	public void initCartesReaction(String nom, String image) {
		JLabel jlb = new JLabel(nom);
		StylingUI.carteStyle(jlb);
		cartesReactionNom.add(jlb);
		cartesReactionImage.add(image);
	}
	
	public void initCartes() {
		initPaquets(reactifSubstrat, cartesReactifSubstrat, nbCartesReactifSubstrat, labelReactifSubstrat, StylingUI.reactifSubstratCouleur);
		initPaquets(produit, cartesProduit, nbCartesProduit, labelProduit, StylingUI.produitCouleur);
		initPaquets(reaction, cartesReaction, nbCartesReaction, labelReaction, StylingUI.reactionCouleur);
	}
	public void resetCartes() {
		cartesReactifSubstratNom.clear();
		cartesReactifSubstratImage.clear();
		cartesReactifSubstratImageLabel.clear();
		
		cartesProduitNom.clear();
		cartesProduitImage.clear();
		cartesProduitImageLabel.clear();
		
		cartesReactionNom.clear();
		cartesReactionImage.clear();
		cartesReactionImageLabel.clear();
	}
	
	private void initPaquets(JPanel panel, List<JPanel> cartes, int nbCartes, JLabel label, Color color) {
		
		panel.removeAll();
		cartes.clear();
		
		JPanel panelLabel = new JPanel();
		JPanel panelCartes = new JPanel();
		panelLabel.setBackground(StylingUI.gray);
		panelCartes.setBackground(StylingUI.gray);

		panelLabel.setLayout(new BorderLayout());

		label.setFont(new Font("Haettenschweiler", Font.PLAIN, 30));
		panelLabel.add(label);

		panel.setPreferredSize(
				new Dimension(this.getWidth() / 3, (this.getHeight() - top.getPreferredSize().height) / 2));
		panelCartes.setPreferredSize(new Dimension((int) panel.getPreferredSize().getWidth(),
				(int) (panel.getPreferredSize().getHeight() - panelLabel.getPreferredSize().getHeight())));

		int diff = (int) (panel.getPreferredSize().getWidth() - panel.getPreferredSize().getHeight());
		//double cardsPS = Math.sqrt(
		//		(panel.getPreferredSize().getHeight() - diff) * (panel.getPreferredSize().getWidth()) / nbCartes);
		double cardsPS = Math.sqrt(
				(panelCartes.getPreferredSize().getHeight() - diff) * (panelCartes.getPreferredSize().getWidth()) / nbCartes);
		double cardsHeight = cardsPS - 10;
		double cardsWidth = cardsPS * 0.75 - 10;

		panel.setLayout(new FlowLayout());

		((FlowLayout) panel.getLayout()).setVgap(0);
		((FlowLayout) panel.getLayout()).setHgap(0);

		panelCartes.setLayout(new FlowLayout());

		((FlowLayout) panelCartes.getLayout()).setVgap(10);
		((FlowLayout) panelCartes.getLayout()).setHgap(10);

		for (int a = 0; a < nbCartes; a++) {
			cartes.add(new JPanel());
			cartes.get(a).setPreferredSize(new Dimension((int) cardsWidth, (int) cardsHeight));
			//cartes.get(a).setBackground(new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256)));
			cartes.get(a).setBackground(color);
			if (cartes.equals(cartesReactifSubstrat)) {
				cartes.get(a).add(cartesReactifSubstratNom.get(a));
				try {
					Image img = ImageIO.read(new FileInputStream(cartesReactifSubstratImage.get(a)));
					img = img.getScaledInstance(cartes.get(a).getPreferredSize().width, 
							cartes.get(a).getPreferredSize().width*img.getHeight(null)/img.getWidth(null), 
							Image.SCALE_DEFAULT);
					JLabel nlbl = new JLabel(new ImageIcon(img));
					cartesReactifSubstratImageLabel.add(nlbl);
				} catch (IOException e) {
					JLabel nlbl = new JLabel();
					cartesReactifSubstratImageLabel.add(nlbl);
				}
				cartes.get(a).add(cartesReactifSubstratImageLabel.get(a));
			} else if (cartes.equals(cartesProduit)) {
				cartes.get(a).add(cartesProduitNom.get(a));
				try {
					Image img = ImageIO.read(new FileInputStream(cartesProduitImage.get(a)));
					img = img.getScaledInstance(cartes.get(a).getPreferredSize().width, 
							cartes.get(a).getPreferredSize().width*img.getHeight(null)/img.getWidth(null), 
							Image.SCALE_DEFAULT);
					JLabel nlbl = new JLabel(new ImageIcon(img));
					cartesProduitImageLabel.add(nlbl);
				} catch (IOException e) {
					JLabel nlbl = new JLabel();
					cartesProduitImageLabel.add(nlbl);
				}
				cartes.get(a).add(cartesProduitImageLabel.get(a));
			} else if (cartes.equals(cartesReaction)) {
				cartes.get(a).add(cartesReactionNom.get(a));
				try {
					Image img = ImageIO.read(new FileInputStream(cartesReactionImage.get(a)));
					img = img.getScaledInstance(cartes.get(a).getPreferredSize().width, 
							cartes.get(a).getPreferredSize().width*img.getHeight(null)/img.getWidth(null), 
							Image.SCALE_DEFAULT);
					JLabel nlbl = new JLabel(new ImageIcon(img));
					cartesReactionImageLabel.add(nlbl);
				} catch (IOException e) {
					JLabel nlbl = new JLabel();
					cartesReactionImageLabel.add(nlbl);
				}
				cartes.get(a).add(cartesReactionImageLabel.get(a));
			}
			panel.add(cartes.get(a));
			panelCartes.add(cartes.get(a));
			final int b = a;
		}
		panel.add(panelLabel);
		panel.add(panelCartes);
	}
	

	public void retourne(List<JPanel> cartes, int idCartes) {

		if (cartes.equals(cartesReactifSubstrat)) {
			cartesReactifSubstratNom.get(idCartes).setVisible(!cartesReactifSubstratNom.get(idCartes).isVisible());
			cartesReactifSubstratImageLabel.get(idCartes).setVisible(!cartesReactifSubstratImageLabel.get(idCartes).isVisible());
		} else if (cartes.equals(cartesProduit)) {
			cartesProduitNom.get(idCartes).setVisible(!cartesProduitNom.get(idCartes).isVisible());
			cartesProduitImageLabel.get(idCartes).setVisible(!cartesProduitImageLabel.get(idCartes).isVisible());
		} else if (cartes.equals(cartesReaction)) {
			cartesReactionNom.get(idCartes).setVisible(!cartesReactionNom.get(idCartes).isVisible());
			cartesReactionImageLabel.get(idCartes).setVisible(!cartesReactionImageLabel.get(idCartes).isVisible());
		}
		
		if (!cartes.get(idCartes).getBackground().equals(StylingUI.gray))
			cartes.get(idCartes).setBackground(StylingUI.gray);
		else
			if (cartes.equals(cartesReactifSubstrat))
				cartes.get(idCartes).setBackground(StylingUI.reactifSubstratCouleur);
			else if (cartes.equals(cartesProduit))
				cartes.get(idCartes).setBackground(StylingUI.produitCouleur);
			else if (cartes.equals(cartesReaction))
				cartes.get(idCartes).setBackground(StylingUI.reactionCouleur);
	}

	public void addButtonListener(ActionListener listener) {
		pauseButton.addActionListener(listener);
		center.addButtonListener(listener);
	}
	
	public void addCarteListener(MouseListener listener) {
		for (int i = 0;i<cartesReactifSubstrat.size();i++)
			cartesReactifSubstrat.get(i).addMouseListener(listener);
		
		for (int i = 0;i<cartesProduit.size();i++)
			cartesProduit.get(i).addMouseListener(listener);
		
		for (int i = 0;i<cartesReaction.size();i++)
			cartesReaction.get(i).addMouseListener(listener);
	}

	public JButton getPauseButton() {
		return pauseButton;
	}
	public List<JPanel> getCartesReactifSubstrat() {
		return cartesReactifSubstrat;
	}
	public List<JPanel> getCartesProduit() {
		return cartesProduit;
	}
	public List<JPanel> getCartesReaction() {
		return cartesReaction;
	}
	public CentreJeuPanel getCenter() {
		return center;
	}
	public ClassementJeuPanel getClassement() {
		return classement;
	}
	public AssociationTrouveePanel getFound() {
		return found;
	}
	
	public void setNbCartesReactifSubstrat(int nbCartesReactifSubstrat) {
		this.nbCartesReactifSubstrat = nbCartesReactifSubstrat;
	}
	public void setNbCartesProduit(int nbCartesProduit) {
		this.nbCartesProduit = nbCartesProduit;
	}
	public void setNbCartesReaction(int nbCartesReaction) {
		this.nbCartesReaction = nbCartesReaction;
	}
	
}
