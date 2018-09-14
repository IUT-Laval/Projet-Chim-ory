package ui;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class StylingUI {

	public static int windowsWidth = 1300;
	public static int windowsHeight = 800;
	//public final static int windowsWidth = 1100;
	//public final static int windowsHeight = 650;
	public final static Color blue = new Color(52, 152, 219);
	public final static Color gray = new Color(236, 240, 241);
	
	public final static Color reactifSubstratCouleur = new Color(228, 196, 196);
	public final static Color produitCouleur = new Color(196, 225, 228);
	public final static Color reactionCouleur = new Color(196, 228, 197);
	
	public static void buttonStyle(JButton button) {

		button.setForeground(Color.WHITE);
		button.setBackground(blue);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		Border line = new LineBorder(Color.BLACK);
		Border margin = new EmptyBorder(5, 15, 5, 15);
		Border compound = new CompoundBorder(line, margin);
		button.setBorder(compound);
		button.setFont(new Font("Haettenschweiler", Font.PLAIN, 35));
		
	}
	
	public static void buttonStyleSmall(JButton button) {

		button.setForeground(Color.WHITE);
		button.setBackground(blue);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		Border line = new LineBorder(Color.BLACK);
		Border margin = new EmptyBorder(2, 10, 2, 10);
		Border compound = new CompoundBorder(line, margin);
		button.setBorder(compound);
		button.setFont(new Font("Haettenschweiler", Font.PLAIN, 25));
		
	}
	
	public static void seeMoreButtonStyle(JButton button) {

		button.setForeground(Color.BLACK);
		button.setBackground(StylingUI.gray);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		Border line = new LineBorder(Color.BLACK);
		Border margin = new EmptyBorder(0, 0, 0, 0);
		Border compound = new CompoundBorder(line, margin);
		button.setBorder(null);
		button.setFont(new Font("Dialog", Font.PLAIN, 20));
		
	}
	
	public static void arButtonStyle(JButton button) {

		button.setForeground(Color.WHITE);
		button.setBackground(blue);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setPreferredSize(new Dimension(30, 30));
		button.setBorder(null);
		button.setFont(new Font("Haettenschweiler", Font.PLAIN, 20));
		
	}
	
	
	
	
	
	public static void panelStyle(JPanel panel) {
		
		panel.setBackground(StylingUI.gray);
		((FlowLayout) panel.getLayout()).setVgap(0);
		panel.setPreferredSize(new Dimension(StylingUI.windowsWidth, StylingUI.windowsHeight));
		panel.setBounds(0, 0, StylingUI.windowsWidth, StylingUI.windowsHeight);
		
	}
	
	public static void contentPanelStyle(JPanel panel) {
		
		panel.setBackground(StylingUI.gray);
		panel.setPreferredSize(new Dimension(StylingUI.windowsWidth / 2, StylingUI.windowsHeight));
		panel.setBounds(0, 0, StylingUI.windowsWidth / 2, StylingUI.windowsHeight);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		//panel.setLayout(new FlowLayout());
	}
	
	
	
	
	public static void tableStyle(JTable table) {
		
		table.setBackground(StylingUI.gray);
		table.setRowHeight(25);
		table.getTableHeader().setBackground(StylingUI.gray);
		table.setShowGrid(false);
		table.setAlignmentX(Component.LEFT_ALIGNMENT);
		table.getTableHeader().setAlignmentX(Component.LEFT_ALIGNMENT);
		
	}
	
	
	
	
	public static void titleLabelStyle(JLabel label) {

		label.setFont(new Font("Haettenschweiler", Font.PLAIN, 55));
		
	}
	
	public static void subtitleLabelStyle(JLabel label) {

		label.setFont(new Font("Haettenschweiler", Font.PLAIN, 35));
		
	}
	
	public static void labelStyle(JLabel label) {

		label.setFont(new Font("Haettenschweiler", Font.PLAIN, 25));
		
	}
	
	public static void carteStyle(JLabel label) {

		label.setFont(new Font("Arial", Font.PLAIN, 8));
		
	}
	
	public static void jTextFieldStyle(JTextField jtf){
		jtf.setFont(new Font("Arial", Font.PLAIN, 14));
	}
	public static void carteStyleCentre(JLabel label) {

		label.setFont(new Font("Arial", Font.PLAIN, 12));
		
	}
	
}
