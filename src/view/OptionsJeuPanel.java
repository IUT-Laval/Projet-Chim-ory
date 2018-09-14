package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.PanelController;
import ui.StylingUI;

public class OptionsJeuPanel extends JPanel {

	//private PanelController panelController;

	private JPanel contentPanel;
	private JButton annulerButton;
	private JButton jouerButton;
	private JLabel titreLabel;

    JPanel arPanel;
    JPanel modePanel;
    JPanel thematiquePanel;
    JPanel difficultePanel;
    JPanel buttonsPanel;
    JPanel subButtonsPanel;

    private JPanel player[] = new JPanel[4];
    private JLabel playerLabel[] = new JLabel[4];
	private JTextField playerTextField[] = new JTextField[4];
	private JButton boutonsAR[] = new JButton[4];

	private JLabel modeLabel;
	private JLabel thematiqueLabel;
	private JLabel difficulteLabel;
	private JComboBox modeComboBox;
	private JComboBox thematiqueComboBox;
	private JComboBox difficulteComboBox;
	
	//public OptionsJeuPanel(PanelController panelController) {
	public OptionsJeuPanel() {

		//this.panelController = panelController;
		StylingUI.panelStyle(this);
		initComponents();
		
	}
	
	private void initComponents() {
		
		contentPanel = new JPanel();
		StylingUI.contentPanelStyle(contentPanel);
		
		StylingUI.titleLabelStyle(titreLabel = new JLabel("OPTIONS PARTIE"));

		StylingUI.buttonStyle(annulerButton = new JButton("ANNULER"));
		StylingUI.buttonStyle(jouerButton = new JButton("JOUER"));

		JPanel players = new JPanel();
		players.setBackground(StylingUI.gray);
		players.setLayout(new BoxLayout(players, BoxLayout.LINE_AXIS));
		players.setMaximumSize(new Dimension(contentPanel.getWidth(), contentPanel.getWidth()/4));

		ImageIcon imgIcon = new ImageIcon();
		try {
			Image img = ImageIO.read(new FileInputStream("ico/people.png"));
			img = img.getScaledInstance(contentPanel.getWidth()/6, contentPanel.getWidth()/6, Image.SCALE_DEFAULT);
			imgIcon = new ImageIcon(img);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int a = 0;a<4;a++) {
			player[a] = new JPanel();
			player[a].setLayout(new BorderLayout());
			//player[a].setLayout(new FlowLayout());
			player[a].setPreferredSize(new Dimension(contentPanel.getWidth()/4, contentPanel.getWidth()/4));
			player[a].setBackground(StylingUI.gray);
			playerLabel[a] = new JLabel(imgIcon);
			playerTextField[a] = new JTextField();
			playerTextField[a].setColumns(5);
			player[a].add(playerLabel[a], BorderLayout.NORTH);
			player[a].add(playerTextField[a], BorderLayout.SOUTH);
			players.add(player[a]);
		}

		players.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		
		
		StylingUI.labelStyle(modeLabel = new JLabel("MODE DE JEU"));
		StylingUI.labelStyle(thematiqueLabel = new JLabel("THEMATIQUE"));
		StylingUI.labelStyle(difficulteLabel = new JLabel("DIFFICULTE"));
		modeComboBox = new JComboBox<>();
		thematiqueComboBox = new JComboBox<>();
		difficulteComboBox = new JComboBox<>();
		modeComboBox.setMaximumSize(new Dimension(150, 25));
		thematiqueComboBox.setMaximumSize(new Dimension(150, 25));
		difficulteComboBox.setMaximumSize(new Dimension(150, 25));

		modeComboBox.addItem("Normal");
		difficulteComboBox.addItem("Facile");
		difficulteComboBox.addItem("Normale");
		difficulteComboBox.addItem("Difficile");
		
		//thematiqueComboBox.addItem("chimie");
		
		arPanel = new JPanel();
		arPanel.setLayout(new GridBagLayout());
		arPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		arPanel.setBackground(StylingUI.gray);
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 4;
		
		boutonsAR[0] = new JButton("-");
		boutonsAR[1] = new JButton("+");
		boutonsAR[2] = new JButton("+");
		boutonsAR[3] = new JButton("+");
		
		StylingUI.arButtonStyle(boutonsAR[0]);
		StylingUI.arButtonStyle(boutonsAR[1]);
		StylingUI.arButtonStyle(boutonsAR[2]);
		StylingUI.arButtonStyle(boutonsAR[3]);

		
		c.gridx = 0;
		c.gridy = 0;
		arPanel.add(boutonsAR[0], c);
		c.gridx = 1;
		arPanel.add(boutonsAR[1], c);
		c.gridx = 2;
		arPanel.add(boutonsAR[2], c);
		c.gridx = 3;
		arPanel.add(boutonsAR[3], c);
		
		arPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, arPanel.getPreferredSize().height));
		
		
		
		
	    modePanel = new JPanel();
	    modePanel.setLayout(new BorderLayout());
	    modePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	    modePanel.setBackground(StylingUI.gray);
	    modePanel.add(modeLabel);
	    modePanel.add(modeComboBox, BorderLayout.EAST);
	    
	    thematiquePanel = new JPanel();
	    thematiquePanel.setLayout(new BorderLayout());
	    thematiquePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	    thematiquePanel.setBackground(StylingUI.gray);
	    thematiquePanel.add(thematiqueLabel);
	    thematiquePanel.add(thematiqueComboBox, BorderLayout.EAST);
	    
	    difficultePanel = new JPanel();
	    difficultePanel.setLayout(new BorderLayout());
	    difficultePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	    difficultePanel.setBackground(StylingUI.gray);
	    difficultePanel.add(difficulteLabel);
	    difficultePanel.add(difficulteComboBox, BorderLayout.EAST);

	    buttonsPanel = new JPanel();
	    subButtonsPanel = new JPanel();
	    buttonsPanel.setLayout(new BorderLayout());
	    buttonsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	    buttonsPanel.setBackground(StylingUI.gray);
	    subButtonsPanel.add(annulerButton);
	    subButtonsPanel.add(jouerButton);
	    buttonsPanel.add(subButtonsPanel, BorderLayout.LINE_END);
	    
	    modePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, modePanel.getPreferredSize().height));
	    thematiquePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, thematiquePanel.getPreferredSize().height));
	    difficultePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, difficultePanel.getPreferredSize().height));
	    buttonsPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, buttonsPanel.getPreferredSize().height));
	    
		contentPanel.add(titreLabel);
		contentPanel.add(players);
		contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		contentPanel.add(arPanel);
		contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		contentPanel.add(modePanel);
		contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		contentPanel.add(thematiquePanel);
		contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		contentPanel.add(difficultePanel);
		contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		contentPanel.add(buttonsPanel);
		//contentPanel.add(annulerButton);
		//contentPanel.add(jouerButton);
		
		setPlayer(0, true);
		setPlayer(1, false);
		setPlayer(2, false);
		setPlayer(3, false);
		
		
		this.add(contentPanel);
		
		/*
		annulerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelController.changeView(Panels.ACCUEIL);
			}
		});
		
		jouerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelController.changeView(Panels.JEU);
			}
		});
		*/
		

		/*
		for(int a = 0;a<4;a++) {
			final int b = a;
			boutonsAR[a].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setPlayer(b, !playerLabel[b].isVisible());
				}
			});
		}
		*/
	}
	
	public void setPlayer(int index, boolean state) {
		if (state) {
			playerLabel[index].setVisible(true);
			playerTextField[index].setVisible(true);
			playerTextField[index].setText("Joueur "+(index+1));;
			boutonsAR[index].setText("-");
		} else {
			playerLabel[index].setVisible(false);
			playerTextField[index].setVisible(false);
			boutonsAR[index].setText("+");
		}
	}
	
	public void addButtonListener(ActionListener listener) {
		annulerButton.addActionListener(listener);
		jouerButton.addActionListener(listener);
		boutonsAR[0].addActionListener(listener);
		boutonsAR[1].addActionListener(listener);
		boutonsAR[2].addActionListener(listener);
		boutonsAR[3].addActionListener(listener);
	}

	public JButton getAnnulerButton() {
		return annulerButton;
	}

	public JButton getJouerButton() {
		return jouerButton;
	}
	
	public JButton[] getBoutonsAR() {
		return boutonsAR;
	}
	
    public JLabel[] getPlayerLabel() {
		return playerLabel;
	}
    
	public JTextField[] getPlayerTextField() {
		return playerTextField;
	}

	public JComboBox getModeComboBox() {
		return modeComboBox;
	}

	public JComboBox getThematiqueComboBox() {
		return thematiqueComboBox;
	}

	public JComboBox getDifficulteComboBox() {
		return difficulteComboBox;
	}
	
	public void resetThematiqueComboBox() {
		thematiqueComboBox.removeAllItems();
	}
	
	public void addToThematiqueComboBox(String thematique) {
		thematiqueComboBox.addItem(thematique);
	}
	
}
