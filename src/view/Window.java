package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.StylingUI;

public class Window extends JFrame {

	private CardLayout cardLayout;
	private JPanel view;
	//private PanelController panelController;

	private JPanel accueilPanel;
	private JPanel optionsPanel;
	private JPanel optionsJeuPanel;
	private JPanel jeuPanel;
	private JPanel classementPanel;

	public Window() {

		this.setTitle("Chim'ory");
		this.setSize(StylingUI.windowsWidth, StylingUI.windowsHeight);
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		cardLayout = new CardLayout();
		view = new JPanel(cardLayout);

		//panelController = new PanelController(view, cardLayout);
		/*
		accueilPanel = new AccueilPanel(panelController);
		optionsPanel = new OptionsPanel(panelController);
		optionsJeuPanel = new OptionsJeuPanel(panelController);
		jeuPanel = new JeuPanel(panelController);
		classementPanel = new ClassementPanel(panelController);
		*/
		accueilPanel = new AccueilPanel();
		optionsPanel = new OptionsPanel();
		optionsJeuPanel = new OptionsJeuPanel();
		jeuPanel = new JeuPanel();
		classementPanel = new ClassementPanel();

		view.add(accueilPanel, Panels.ACCUEIL.toString());
		view.add(optionsJeuPanel, Panels.OPTIONS_JEU.toString());
		view.add(optionsPanel, Panels.OPTIONS.toString());
		view.add(jeuPanel, Panels.JEU.toString());
		view.add(classementPanel, Panels.CLASSEMENT.toString());

		this.add(view, BorderLayout.CENTER);

		this.pack();
		
		//panelController.changeView(Panels.ACCUEIL);
		//panelController.changeView(Panels.JEU);
		
		this.setVisible(true);

	}

	
	public JPanel getView() {
		return view;
	}
	public CardLayout getCardLayout() {
		return cardLayout;
	}
	public AccueilPanel getAccueilPanel() {
		return (AccueilPanel) accueilPanel;
	}
	public OptionsPanel getOptionsPanel() {
		return (OptionsPanel) optionsPanel;
	}
	public OptionsJeuPanel getOptionsJeuPanel() {
		return (OptionsJeuPanel) optionsJeuPanel;
	}
	public JeuPanel getJeuPanel() {
		return (JeuPanel) jeuPanel;
	}
	public ClassementPanel getClassementPanel() {
		return (ClassementPanel) classementPanel;
	}
	
	

	/*
	 * public static void changePanel(Panels name) { cardLayout.show(view,
	 * name.toString()); }
	 */

}
