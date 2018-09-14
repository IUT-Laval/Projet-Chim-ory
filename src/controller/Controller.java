package controller;

import java.util.ArrayList;
import java.util.HashMap;

import model.Model;
import view.AccueilPanel;
import view.Panels;
import view.Window;

public class Controller {

	protected Window view;
	protected Model model;
	protected HashMap<Panels, PanelControllerInterface> controllers = new HashMap<>();
	protected PanelController panelController;
	
	public Controller() {
		
	}
	
	public Controller(Model gameModel, Window view) {
		this();
		this.view = view;
		this.model = gameModel;
		
		controllers.put(Panels.ACCUEIL, new AccueilController(this));
		controllers.put(Panels.OPTIONS_JEU, new OptionsJeuController(this));
		controllers.put(Panels.OPTIONS, new OptionsController(this));
		controllers.put(Panels.JEU, new JeuController(this));
		controllers.put(Panels.CLASSEMENT, new ClassementController(this));
		
		panelController = new PanelController(this);
		
	}
	
}
