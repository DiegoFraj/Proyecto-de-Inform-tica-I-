package control;

import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Usuario;

public class Main extends Application  {
	
	
	@Override
	public void start(Stage arg0) throws Exception {
		
		// Prueba del primer Sprint
		// fin primer sprint
		
		
		ManejadorDeFichero mf = new ManejadorDeFichero();
		Usuario.listaUsuarios = mf.leerUsuarios("./data/usuarios.txt", ";");
		abrirVentana(new ControladorLogin(), "/view/VentanaLogin.fxml", "Login", false, false, 390, 480);
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void abrirVentana(Object controlador, String ventanaFxml, String ventanaNombre,
			boolean resizable, boolean maximized, double minHeight, double minWidth) {
		// Read file fxml and draw interface.
		FXMLLoader loader = null;
		Parent root = null;
		Scene scene = null;
		Stage stage = null;
		
		try {
			loader = new FXMLLoader(controlador.getClass().getResource(ventanaFxml));
			loader.setController(controlador);
			root = loader.load();
			scene = new Scene(root);
			// scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
			stage = new Stage();
			stage.setTitle(ventanaNombre);
			stage.setScene(scene);
			stage.setResizable(resizable);
			stage.setMaximized(maximized);
			stage.setMinHeight(minHeight);
			stage.setMinWidth(minWidth);
			Image icono = new Image("file:img/smarthome-logo.png");
			stage.getIcons().addAll(icono);
			stage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void alerta(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}

}
