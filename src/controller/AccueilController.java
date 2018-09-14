package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTable;

import model.ModelTableClassement;
import model.dao.ScoreDAO;
import view.AccueilPanel;
import view.Panels;

public class AccueilController extends Controller implements PanelControllerInterface {

	private Controller mC;
	private AccueilPanel accueilPanel;
	
	public AccueilController(Controller mC) {
		
		this.mC = mC;
		this.accueilPanel = mC.view.getAccueilPanel();
		this.accueilPanel.addButtonListener(new ButtonListener());
		
	}
	
	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton optionsJeuButton = accueilPanel.getOptionsJeuButton();
			JButton optionsButton = accueilPanel.getOptionsButton();
			JButton classementButton = accueilPanel.getClassementButton();
			
			if (e.getSource() == optionsJeuButton)
				mC.panelController.changeView(Panels.OPTIONS_JEU);
			else if (e.getSource() == optionsButton)
				mC.panelController.changeView(Panels.OPTIONS);
			else if (e.getSource() == classementButton)
				mC.panelController.changeView(Panels.CLASSEMENT);
			
		}
	}

	@Override
	public void initPanel() {
		JTable scoreTable = accueilPanel.getScoreTable();
		scoreTable.setModel(new ModelTableClassement(10));
		
		System.out.println(scoreTable.getModel().getRowCount() +" test");
	}

}
