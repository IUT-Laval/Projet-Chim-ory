import controller.Controller;
import model.Model;
import view.Window;

public class Main {

	public static void main(String[] args) {
		
		// http://www.developpez.net/forums/d881330/java/interfaces-graphiques-java/awt-swing/fenetres-dialogues/question-cardlayout-changement-panel/
		// http://thebadprogrammer.com/swing-uimanager-keys/
		// http://www.duncanjauncey.com/java/ui/uimanager/UIDefaults_Java1.7.0_Linux_2.6.38-11-server_CDE_Motif.html
		
		/*
		 * Faire des listener sur le model dans le controlleur pour quae quand
		 * le model change la vue soit notifier et change aussi
		 */
		
		// https://github.com/koppelbw/TicTacToe/tree/master/src
		
		
		Model model = new Model();
		Window vue = new Window();
		Controller controller = new Controller(model, vue);
		
		vue.setVisible(true);

	}

}
