package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import controller.PanelController;
import javafx.scene.layout.Border;
import ui.StylingUI;
import view.jeuPanel.AssociationTrouveePanel;
import view.jeuPanel.CentreJeuPanel;
import view.jeuPanel.ChimoryPanel;
import view.jeuPanel.ClassementJeuPanel;
import view.jeuPanel.PausePanel;

public class JeuPanel extends JPanel {

	private ChimoryPanel chimoryPanel;
	private PausePanel pausePanel;

	public JeuPanel() {
		
		StylingUI.panelStyle(this);
		initComponents();

	}

	private void initComponents() {

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(StylingUI.windowsWidth, StylingUI.windowsHeight));

		chimoryPanel = new ChimoryPanel(this);
		pausePanel = new PausePanel(this);
		
		layeredPane.add(chimoryPanel, new Integer(1));
		layeredPane.add(pausePanel, new Integer(2));

		pausePanel.setVisible(false);

		this.add(layeredPane);

	}
	
	public void initJeu() {
		chimoryPanel.setVisible(true);
		pausePanel.setVisible(false);
	}
	
	public void afficherPause(boolean afficher) {
		pausePanel.setVisible(afficher);
	}
	
	public void addButtonListener(ActionListener listener) {
		pausePanel.addButtonListener(listener);
		chimoryPanel.addButtonListener(listener);
	}

	public void addCarteListener(MouseListener listener) {
		chimoryPanel.addCarteListener(listener);
	}
	
	
	public ChimoryPanel getChimoryPanel() {
		return chimoryPanel;
	}
	public PausePanel getPausePanel() {
		return pausePanel;
	}
	
}
