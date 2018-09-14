package view.jeuPanel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.StylingUI;
import view.JeuPanel;

public class PausePanel extends JPanel {

	private JeuPanel jP;

	private JButton reprendreButton;
	private JButton optionsButton;
	private JButton quitterButton;
	private JLabel pauseLabel;
	
	public PausePanel(JeuPanel jP) {

		this.jP = jP;
		StylingUI.panelStyle(this);
		initComponents();

	}

	private void initComponents() {

		this.setLayout(new FlowLayout(FlowLayout.CENTER));
	    this.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, StylingUI.blue));
		this.setVisible(false);

		((FlowLayout) this.getLayout()).setVgap(20);
		
		StylingUI.buttonStyle(reprendreButton = new JButton("Reprendre"));
		StylingUI.buttonStyle(optionsButton = new JButton("Options"));
		StylingUI.buttonStyle(quitterButton = new JButton("Quitter"));

		this.setSize(new Dimension(this.getSize().width / 2, this.getSize().height / 2));
		this.setLocation(this.getSize().width / 2, this.getSize().height / 2);
		;
		reprendreButton.setPreferredSize(new Dimension((int) (this.getSize().getWidth()*0.9), (int) reprendreButton.getPreferredSize().getHeight())); 
		optionsButton.setPreferredSize(new Dimension((int) (this.getSize().getWidth()*0.9), (int) reprendreButton.getPreferredSize().getHeight())); 
		quitterButton.setPreferredSize(new Dimension((int) (this.getSize().getWidth()*0.9), (int) reprendreButton.getPreferredSize().getHeight())); 

		pauseLabel = new JLabel("PAUSE");
		pauseLabel.setFont(new Font("Haettenschweiler", Font.PLAIN, 35));
		
		this.add(pauseLabel);
		this.add(reprendreButton);
		this.add(optionsButton);
		this.add(quitterButton);

		//this.setSize(new Dimension(this.getSize().width / 2, this.getSize().height / 2));
		//this.setLocation(this.getSize().width / 2, this.getSize().height / 2);

		/*
		reprendreButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pause.setVisible(false);
			}
		});
		*/
		
		/*
		optionsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelController.changeView(Panels.OPTIONS);
			}
		});
		quitterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelController.changeView(Panels.ACCUEIL);
			}
		});
		*/

	}

	public void addButtonListener(ActionListener listener) {
		reprendreButton.addActionListener(listener);
		optionsButton.addActionListener(listener);
		quitterButton.addActionListener(listener);
	}

	public JButton getReprendreButton() {
		return reprendreButton;
	}
	public JButton getOptionsButton() {
		return optionsButton;
	}
	public JButton getQuitterButton() {
		return quitterButton;
	}

	
}
