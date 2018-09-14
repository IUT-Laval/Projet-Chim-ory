package model.jeu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

import model.dao.ConnectionMySql;
import ui.StylingUI;

public class Option {
	private String thematiqueDefaut;
	private Boolean son;
	private Boolean tutoriel;
	private int volumeMusique;
	private int volumeSon;
	private String resolution;
	private String urlBDD;
	private String user;
	private String password;
	
	
	String[] resolutions = {"2560x1440","1920*1080","1680x1050","1600x900","1440x900","1366x768","1280x1024","1280x800","1280x720 ","1024x768"};

	public Option() {
		load();
	}

	public void load() {
		try {

			File fXmlFile = new File("options.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			NodeList option;
			option = doc.getElementsByTagName("thematiqueDefaut");
			this.thematiqueDefaut = option.item(0).getTextContent();

			NodeList resolution;
			resolution = doc.getElementsByTagName("resolution");
			this.resolution = resolution.item(0).getTextContent();
			StringTokenizer resol = new StringTokenizer(this.resolution, "x");
			StylingUI.windowsWidth = Integer.parseInt(resol.nextToken());
			StylingUI.windowsHeight = Integer.parseInt(resol.nextToken());
			
			NodeList urlBDD;
			urlBDD = doc.getElementsByTagName("urlBDD");
			this.urlBDD = urlBDD.item(0).getTextContent();
			ConnectionMySql.url = this.urlBDD;
			
			NodeList user;
			user = doc.getElementsByTagName("user");
			this.user = user.item(0).getTextContent();
			ConnectionMySql.user = this.user;
			
			NodeList password;
			password = doc.getElementsByTagName("password");
			this.password = password.item(0).getTextContent();
			ConnectionMySql.password = this.password;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getUrlBDD() {
		return urlBDD;
	}

	public void setUrlBDD(String urlBDD) {
		this.urlBDD = urlBDD;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getThematiqueDefaut() {
		return thematiqueDefaut;
	}

	public void save() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document document = builder.newDocument();
		Element options = document.createElement("Options");
		document.appendChild(options);

		Node node;

		node = document.createElement("thematiqueDefaut");
		node.setTextContent(thematiqueDefaut);
		options.appendChild(node);

		node = document.createElement("resolution");
		node.setTextContent(resolution);
		options.appendChild(node);
		
		node = document.createElement("urlBDD");
		node.setTextContent(urlBDD);
		options.appendChild(node);
		
		node = document.createElement("user");
		node.setTextContent(user);
		options.appendChild(node);
		
		node = document.createElement("password");
		node.setTextContent(password);
		options.appendChild(node);

		DomSerializer serializer = new DomSerializer(document);
		try {
			serializer.serialize("options.xml");
		} catch (ClassCastException | ClassNotFoundException | InstantiationException | IllegalAccessException
				| IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setThematiqueDefaut(String thematiqueDefaut) {
		this.thematiqueDefaut = thematiqueDefaut;
	}

	public void setResolution(String string) {
		this.resolution = string;

	}

	public String getResolution() {
		return resolution;
	}

	public String[] getResolutionsDisponibles() {
		return resolutions;
	}
}

class DomSerializer {

	private Document document;

	public DomSerializer(Document document) {
		super();
		this.document = document;
	}

	public void serialize(String filename) throws IOException, ClassCastException, ClassNotFoundException,
			InstantiationException, IllegalAccessException {

		// teste la compatibilité DOM3
		DOMImplementationLS implLS = null;
		if (document.getImplementation().hasFeature("LS", "3.0")) {
			implLS = (DOMImplementationLS) document.getImplementation();
		} else {
			DOMImplementationRegistry implementation = DOMImplementationRegistry.newInstance();
			implLS = (DOMImplementationLS) implementation.getDOMImplementation("LS 3.0");
		}
		if (implLS == null) {
			System.out.println("API DOM Load and Save non supportée");
			return;
		}

		// Ecriture du document
		LSSerializer serializer = implLS.createLSSerializer();
		LSOutput output = implLS.createLSOutput();
		FileOutputStream writer = new FileOutputStream(filename);
		output.setByteStream(writer);
		serializer.getDomConfig().setParameter("format-pretty-print", true);
		serializer.write(document, output);
	}

	@SuppressWarnings("unused")
	private void __test_serialize() throws TransformerException {
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		Result output = new StreamResult(new File("output.xml"));
		Source input = new DOMSource(this.document);

		transformer.transform(input, output);
	}
}
