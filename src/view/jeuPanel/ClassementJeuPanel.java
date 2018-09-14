package view.jeuPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.StylingUI;

public class ClassementJeuPanel extends JPanel {

	private JPanel joueursPanel[];
	private JLabel joueursLabelNom[];
	private JLabel joueursLabelScoreTemps[];
	private JLabel joueursLabelPosition[];
	private String joueursPoints[];
	private String joueursTemps[];
	private int indexJoueurActuel = 0;
	
	public ClassementJeuPanel(Dimension dimension) {
		this.setPreferredSize(dimension);
		this.setLayout(new FlowLayout());
		
		this.setBackground(StylingUI.gray);
		
		((FlowLayout) this.getLayout()).setVgap(0);
		
		//joueursLabelNom[0].setForeground(StylingUI.blue);
		//joueursLabelPosition[0].setForeground(StylingUI.blue);
	}
	
	public void initPlayers(String[] joueursNoms) {
		
		this.removeAll();
		
		joueursPanel = new JPanel[joueursNoms.length];
		joueursLabelNom = new JLabel[joueursNoms.length];
		joueursLabelScoreTemps = new JLabel[joueursNoms.length];
		joueursLabelPosition = new JLabel[joueursNoms.length];
		joueursPoints = new String[joueursNoms.length];
		joueursTemps = new String[joueursNoms.length];
		
		for (int a = 0; a < joueursNoms.length; a++) {
			initPlayer(a, joueursNoms[a]);
		}
		
		this.repaint();
	}
	
	public void setJoueurActuel(int indexJoueurActuel) {
		joueursLabelNom[this.indexJoueurActuel].setForeground(Color.DARK_GRAY);
		joueursLabelPosition[this.indexJoueurActuel].setForeground(Color.DARK_GRAY);
		this.indexJoueurActuel = indexJoueurActuel;
		joueursLabelNom[indexJoueurActuel].setForeground(StylingUI.blue);
		joueursLabelPosition[indexJoueurActuel].setForeground(StylingUI.blue);
	}
	
	public void setTemps(int player, long temps) {
		String tempsJoueur = "";
		
		int minutes = (int)temps/60000;
		int secondes = (int)temps/1000 - minutes*60;
		if (minutes < 10)
			tempsJoueur = "0" + minutes+":";
		else tempsJoueur = minutes+":";
		if (secondes <10)
			tempsJoueur+="0"+secondes;
		else tempsJoueur+= secondes;
		joueursTemps[player] = tempsJoueur;
		setScore(player);
	}
	public void setPoints(int player, int points) {
		joueursPoints[player] = Integer.toString(points);
		setScore(player);
	}
	public void setScore(int player) {
		joueursLabelScoreTemps[player].setText("POINTS : "+joueursPoints[player]+" | TEMPS : "+joueursTemps[player]);
	}
	
	public void initPlayer(int index, String joueurNom) {
		
		joueursPanel[index] = new JPanel();
		joueursPanel[index].setBackground(StylingUI.gray);
		joueursPanel[index].setLayout(new FlowLayout(FlowLayout.LEFT));

		((FlowLayout) joueursPanel[index].getLayout()).setVgap(0);
		
		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		leftPanel.setBackground(StylingUI.gray);
		rightPanel.setBackground(StylingUI.gray);
		
		leftPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		rightPanel.setLayout(new BorderLayout());

		joueursLabelNom[index] = new JLabel(joueurNom);
		joueursPoints[index] = "0";
		joueursTemps[index] = "00:00";
		joueursLabelScoreTemps[index] = new JLabel();
		setScore(index);
		joueursLabelPosition[index] = new JLabel("4");
		joueursLabelNom[index].setFont(new Font("Haettenschweiler", Font.PLAIN, 40));
		joueursLabelScoreTemps[index].setFont(new Font("Haettenschweiler", Font.PLAIN, 15));
		joueursLabelPosition[index].setFont(new Font("Haettenschweiler", Font.PLAIN, 50));
		
		joueursLabelNom[index].setForeground(Color.DARK_GRAY);
		joueursLabelPosition[index].setForeground(Color.DARK_GRAY);
		joueursLabelScoreTemps[index].setForeground(Color.DARK_GRAY);

		leftPanel.setPreferredSize(new Dimension(50, leftPanel.getPreferredSize().height));
		
		rightPanel.add(joueursLabelNom[index], BorderLayout.NORTH);
		rightPanel.add(joueursLabelScoreTemps[index], BorderLayout.SOUTH);

		leftPanel.setPreferredSize(new Dimension((int) rightPanel.getPreferredSize().getHeight(), (int) rightPanel.getPreferredSize().getHeight()));
		
		leftPanel.add(joueursLabelPosition[index]);
		
		joueursPanel[index].add(leftPanel);
		joueursPanel[index].add(rightPanel);

		joueursPanel[index].setPreferredSize(new Dimension((int) this.getPreferredSize().getWidth(),
				(int) joueursPanel[index].getPreferredSize().getHeight()));	
		
		this.add(joueursPanel[index]);
		
	}

}
