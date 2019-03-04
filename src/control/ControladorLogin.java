package control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import model.Usuario;

public class ControladorLogin implements Initializable {

	public static Usuario usuarioActivo;
	// Componentes de la interfaz del Perfil de Usuario
	@FXML
	private TextField userField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Label wrongLogin;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

	// Metodos---------------------------------------------------------------------------------

	public void checkLogin(ActionEvent e) {
		
		Usuario usuario = Usuario.getUsuario(new Usuario(userField.getText()));
		if (usuario == null) {
			wrongLogin.setVisible(true);
			wrongLogin.setText("Usuario o contraseña incorrecta, inténtelo de nuevo.");
			userField.setText("");
			passwordField.setText("");		}
		else if (usuario.password.equals(passwordField.getText())) {
			usuarioActivo = usuario;
			Stage stage = (Stage) userField.getScene().getWindow();
			stage.close();
			
			Main.abrirVentana(new ControladorMainTest(), "/view/BiowareFinal.fxml", "Bioware", true, true, 500, 500);
//			// Temporal tercer sprint
//			if(usuarioActivo.tipo == 's') {
//				Main.abrirVentana(new ControladorTablaPacientes(), "/view/VentanaTablaPacientes.fxml", "Tabla Pacientes",
//					true, true, 480, 800);
//				Main.abrirVentana(new ControladorNotificaciones(), "/view/VentanaNotificaciones.fxml", "Tabla Notificaciones",
//						true, true, 480, 800);
//			}
//			else{
//				ControladorPerfilUsuario perfilUsuario = new ControladorPerfilUsuario(usuarioActivo);
//				Main.abrirVentana(perfilUsuario, "/view/VentanaPerfilUsuario.fxml", "Perfil de Usuario", false, false, 0, 0);
//			}
//			Main.abrirVentana(new ControladorChat(), "/view/VentanaChat.fxml", "Chat", true, true, 480, 800);
//			// fin tercer sprint
		} else {
			wrongLogin.setVisible(true);
			wrongLogin.setText("Usuario o contraseña incorrecta, inténtelo de nuevo.");
			userField.setText("");
			passwordField.setText("");
		}
	}

}
