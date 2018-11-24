package ch.fhnw.projectbois._mvc;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import ch.fhnw.projectbois.log.LoggerFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * Based on example from course Java 2
 * 
 * @author Rosario Brancato
 */
public abstract class View<M extends Model> {
	protected Logger logger;

	protected Parent root;
	protected M model;

	/**
	 * Set any options for the stage in the subclass constructor
	 * 
	 * @param stage
	 * @param model
	 */
	public View(M model) {
		this.logger = LoggerFactory.getLogger(this.getClass());

		this.model = model;
	}

	public Parent getRoot() {
		return this.root;
	}

	protected abstract URL getFXML();

	public <T extends Controller<M, ? extends View<M>>> void loadRoot(T controller) {
		URL url = this.getFXML();
		Locale locale = model.getLocale();
		//Multi-Language Support Resource Bundle
		ResourceBundle bundle = ResourceBundle.getBundle("language.UIResources", locale);
		FXMLLoader loader = new FXMLLoader(url, bundle);
		loader.setController(controller);

		try {
			this.root = loader.load();

		} catch (IOException e) {
			logger.log(Level.SEVERE, "View.loadRoot()", e);
		}
	}

}
