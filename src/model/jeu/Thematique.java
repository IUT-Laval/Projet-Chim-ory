package model.jeu;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.jeu.Association;
import model.jeu.Caracteristique;
import model.jeu.Carte;
import model.jeu.Jeu;

public class Thematique {

	private Jeu jeu;
	private String thematiqueActuel;
	private List<String> thematiques = new ArrayList<>();

	public Thematique(Jeu jeu) {
		this.jeu = jeu;
	}

	public void setThematiqueActuel(String thematiqueActuel) {
		this.thematiqueActuel = thematiqueActuel;
	}

	public String getThematiqueActuel() {
		return thematiqueActuel;
	}

	public void loadThematique() {

		this.thematiqueActuel = thematiqueActuel;

		resetThematique();

		try {

			File fXmlFile = new File("thematiques/" + thematiqueActuel + "/"+thematiqueActuel+".xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			HashMap<String, Carte> cartesMap = new HashMap<String, Carte>();

			NodeList cartes = doc.getElementsByTagName("cartes").item(0).getChildNodes();
			
			for (int temp = 0; temp < cartes.getLength(); temp++) {
				Node nNode = cartes.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					cartesMap.put(eElement.getAttribute("id"),
							new Carte(eElement.getTextContent(), Integer.parseInt(eElement.getAttribute("id")), eElement.getAttribute("img")));
				}
			}

			NodeList caracteristiques = doc.getElementsByTagName("caracteristiques").item(0).getChildNodes();
			;

			for (int temp = 0; temp < caracteristiques.getLength(); temp++) {
				Node nNode = caracteristiques.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					jeu.getGestionnaireCaracteristiques()
							.addCaracteristique(new Caracteristique(eElement.getTextContent()));
				}
			}

			NodeList associations = doc.getElementsByTagName("association");

			for (int temp = 0; temp < associations.getLength(); temp++) {
				Node nNode = associations.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					if (Integer.parseInt(eElement.getAttribute("difficulte")) <= jeu.getDifficulte() || jeu.getDifficulte() == 0) {
						jeu.getCartesProduit()
								.addCarte(new Carte(cartesMap.get(eElement.getAttribute("produit")).getNom(),
										cartesMap.get(eElement.getAttribute("produit")).getIdentifiant(),
										cartesMap.get(eElement.getAttribute("produit")).getImage()
										));
						jeu.getCartesReactifSubstrat()
								.addCarte(new Carte(cartesMap.get(eElement.getAttribute("reactifSubstrat")).getNom(),
										cartesMap.get(eElement.getAttribute("reactifSubstrat")).getIdentifiant(),
										cartesMap.get(eElement.getAttribute("reactifSubstrat")).getImage()
										));
						jeu.getCartesReaction()
								.addCarte(new Carte(cartesMap.get(eElement.getAttribute("reaction")).getNom(),
										cartesMap.get(eElement.getAttribute("reaction")).getIdentifiant(),
										cartesMap.get(eElement.getAttribute("reaction")).getImage()
										));

						jeu.getGestionnaireAssociations()
								.addAssociation(new Association(
										jeu.getCartesProduit().getCarte(jeu.getCartesProduit().nombreCarte() - 1),
										jeu.getCartesReactifSubstrat()
												.getCarte(jeu.getCartesReactifSubstrat().nombreCarte() - 1),
								jeu.getCartesReaction().getCarte(jeu.getCartesReaction().nombreCarte() - 1),
								Integer.parseInt(eElement.getAttribute("difficulte"))));

						NodeList caracteristiquesOfAssociation = eElement.getElementsByTagName("caracteristique");
						for (int tempa = 0; tempa < caracteristiquesOfAssociation.getLength(); tempa++) {
							Node nNodea = caracteristiquesOfAssociation.item(tempa);
							if (nNodea.getNodeType() == Node.ELEMENT_NODE) {
								Element eElementa = (Element) nNodea;
								jeu.getGestionnaireAssociations()
										.getAssociation(jeu.getGestionnaireAssociations().getNbAssociations() - 1)
										.addCaracteristique(jeu.getGestionnaireCaracteristiques()
												.getCaracteristique(Integer.parseInt(eElementa.getTextContent())));
							}
						}

					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void resetThematique() {
		jeu.getGestionnaireAssociations().removeAll();
		jeu.getGestionnaireCaracteristiques().removeAll();
		jeu.getCartesProduit().removeAll();
		jeu.getCartesReactifSubstrat().removeAll();
		jeu.getCartesReaction().removeAll();
	}

	public void listeThematique() {
		File folder = new File("thematiques");
		File[] listOfFiles = folder.listFiles();
		thematiques.clear();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isDirectory()) {
				thematiques.add(listOfFiles[i].getName());
			}
		}
	}
	
	public int nbThematique() {
		return thematiques.size();
	}
	
	public String getThematique(int index) {
		return thematiques.get(index);
	}

}
