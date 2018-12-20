package ch.fhnw.projectbois.registration;

import java.net.URL;

import ch.fhnw.projectbois._mvc.Controller;
import ch.fhnw.projectbois._mvc.View;

public class RegistrationView extends View<RegistrationModel> {

	public RegistrationView(RegistrationModel model) {
		super(model);
	}

	@Override
	protected URL getFXML() {
		return this.getClass().getResource("RegistrationView.fxml");
	}
	
	@Override
	public <T extends Controller<RegistrationModel, ? extends View<RegistrationModel>>> void loadRoot(T controller) {
		super.loadRoot(controller);

		String css = ClassLoader.getSystemClassLoader().getResource("stylesheets/Login_Registration_Profile.css").toExternalForm();
		this.root.getStylesheets().add(css);
	}

}
