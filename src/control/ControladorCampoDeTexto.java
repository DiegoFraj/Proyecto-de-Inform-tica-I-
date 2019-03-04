package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.ObservadorCampoDeTexto;

public class ControladorCampoDeTexto implements Initializable {

	private ObservadorCampoDeTexto observador; // Controlador que espera a que se le introduzca un texto

	// Componentes
	@FXML
	public Label tituloMensaje;
	@FXML
	private TextField inputField;

	public ControladorCampoDeTexto(ObservadorCampoDeTexto observador) {
		this.observador = observador;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	// Llama a la funcion introducirCampoDeTexto del observador, pasandole el texto
	// introducido
	public void aceptar(ActionEvent e) {

		System.out.println(observador.getClass());
		observador.introducirCampoDeTexto(inputField.getText());

		Stage stage = (Stage) inputField.getScene().getWindow();
		stage.close();
	}

}
