package controller;

import java.awt.CardLayout;

import javax.swing.JPanel;

import view.Panels;

public class PanelController extends Controller {

	private Panels[] lastPanel = {Panels.ACCUEIL, Panels.ACCUEIL};
	private Controller mC;
	
	public PanelController(Controller mC) {
		this.mC = mC;
	}
	
	public void changeView(Panels panel) {
		lastPanel[0] = lastPanel[1];
		lastPanel[1] = panel;
		mC.controllers.get(panel).initPanel();
		mC.view.getCardLayout().show(mC.view.getView(), panel.toString());
	}
	
	public void lastView() {
		changeView(lastPanel[0]);
	}

}
