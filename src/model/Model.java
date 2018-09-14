package model;

import model.jeu.Jeu;
import model.jeu.Option;

public class Model {

	private Jeu jeu;
	private Option option;
	

	public Model() {
		jeu = new Jeu();
		this.option = new Option();
	}
	
	public Jeu getJeu() {
		return jeu;
	}
	
	public Option getOption() {
		return option;
	}
}
