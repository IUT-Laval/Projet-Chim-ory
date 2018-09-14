package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

import controller.PanelController;
import model.ModelTableClassement;
import ui.StylingUI;

public class AccueilPanel extends JPanel {

	//private PanelController panelController;

	private JPanel contentPanel;
	private JButton optionsJeuButton;
	private JButton optionsButton;
	private JButton classementButton;
	private JLabel titreLabel;
	private JLabel classementLabel;
	private JTable scoreTable;

	//public AccueilPanel(PanelController panelController) {
	public AccueilPanel() {
		
		//this.panelController = panelController;
		StylingUI.panelStyle(this);
		initComponents();

	}

	private void initComponents() {
		
		
		
		contentPanel = new JPanel();
		StylingUI.contentPanelStyle(contentPanel);

		titreLabel = new JLabel("CHIM'ORY");
		classementLabel = new JLabel("CLASSEMENT");

		StylingUI.titleLabelStyle(titreLabel);
		StylingUI.subtitleLabelStyle(classementLabel);
		
		StylingUI.buttonStyle(optionsJeuButton = new JButton("JOUER"));
		StylingUI.buttonStyle(optionsButton = new JButton("OPTIONS"));
		StylingUI.seeMoreButtonStyle(classementButton = new JButton("+"));
		
		try {
			Image img = ImageIO.read(new FileInputStream("ico/gamew.png"));
			img = img.getScaledInstance(35, 35, Image.SCALE_DEFAULT);
			optionsJeuButton.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			Image img = ImageIO.read(new FileInputStream("ico/gearw.png"));
			img = img.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
			optionsButton.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			Image img = ImageIO.read(new FileInputStream("ico/web.png"));
			img = img.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
			classementLabel.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		scoreTable = new JTable();
		scoreTable.setModel(new ModelTableClassement(10));
		System.out.println(scoreTable.getModel().getRowCount() +" test");
		StylingUI.tableStyle(scoreTable);

		optionsJeuButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, optionsJeuButton.getMinimumSize().height));
		optionsButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, optionsButton.getMinimumSize().height));
		classementButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, classementButton.getMinimumSize().height));

		contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		contentPanel.add(titreLabel);
		contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		contentPanel.add(optionsJeuButton);
		contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		contentPanel.add(optionsButton);
		contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		contentPanel.add(classementLabel);
		contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		contentPanel.add(scoreTable.getTableHeader());
		contentPanel.add(scoreTable);
		contentPanel.add(classementButton);

		this.add(contentPanel);

		/*
		optionsJeuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelController.changeView(Panels.OPTIONS_JEU);
			}
		});

		optionsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelController.changeView(Panels.OPTIONS);
			}
		});

		classementButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelController.changeView(Panels.CLASSEMENT);
			}
		});
		*/

	}
	

	public JTable getScoreTable() {
		return scoreTable;
	}

	public void addButtonListener(ActionListener listener) {
		optionsJeuButton.addActionListener(listener);
		optionsButton.addActionListener(listener);
		classementButton.addActionListener(listener);
	}

	public JButton getOptionsJeuButton() {
		return optionsJeuButton;
	}
	public JButton getOptionsButton() {
		return optionsButton;
	}
	public JButton getClassementButton() {
		return classementButton;
	}
	
	

}
