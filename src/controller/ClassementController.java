package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTable;

import model.ModelTableClassement;
import view.AccueilPanel;
import view.ClassementPanel;
import view.Panels;

public class ClassementController extends Controller implements PanelControllerInterface {
	
	private Controller mC;
	private ClassementPanel classementPanel;
	
	public ClassementController(Controller mC) {
		
		this.mC = mC;
		this.classementPanel = mC.view.getClassementPanel();
		this.classementPanel.addButtonListener(new ButtonListener());
		
	}
	
	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			JButton accueilButton = classementPanel.getAccueilButton();
			
			if (e.getSource() == accueilButton)
				mC.panelController.changeView(Panels.ACCUEIL);
		}
	}

	@Override
	public void initPanel() {
		JTable scoreTable = classementPanel.getScoreTable();
		scoreTable.setModel(new ModelTableClassement(100));
		
		System.out.println(scoreTable.getModel().getRowCount() +" test");
	}
	
}
