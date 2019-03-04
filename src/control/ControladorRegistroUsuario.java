package control;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Paciente;
import model.Usuario;


public class ControladorRegistroUsuario {
	
	@FXML
	private JFXTextField dniRegistroField;
	@FXML
	private JFXTextField nombreRegistroField;
	@FXML
	private JFXTextField apellidoRegistroField;
	@FXML
	private JFXTextField edadRegistroField;
	@FXML
	private JFXTextField direccionRegistroField;
	@FXML
	private JFXTextField localidadRegistroField;
	@FXML
	private JFXTextField telefonoRegistroField;
	@FXML
	private JFXTextField emailRegistroField;
	@FXML
	private Button confirmRegistro;
	
	public ControladorRegistroUsuario(JFXTextField dni, JFXTextField nombre, JFXTextField apellido, JFXTextField edad, JFXTextField direccion, JFXTextField localidad, JFXTextField telefono, JFXTextField email, Button confirmRegistro) {
		this.dniRegistroField = dni;
		this.nombreRegistroField = nombre;
		this.apellidoRegistroField = apellido;
		this.edadRegistroField = edad;
		this.direccionRegistroField = direccion;
		this.localidadRegistroField = localidad;
		this.telefonoRegistroField = telefono;
		this.emailRegistroField = email;
		this.confirmRegistro = confirmRegistro;
		
		this.confirmRegistro.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
			registrarUsuario();
		});
	}
	
	public void registrarUsuario() {
		
		if (dniRegistroField.getText() == null || nombreRegistroField.getText() == null 
				|| apellidoRegistroField.getText() == null || edadRegistroField.getText() == null 
				|| direccionRegistroField.getText() == null || localidadRegistroField.getText() == null 
				|| telefonoRegistroField.getText() == null || emailRegistroField.getText() == null) {
			Main.alerta("Rellena todos los campos");
			return;
		}
		if(!Usuario.validarDni(dniRegistroField.getText()) 
				|| !Usuario.validarNombre(nombreRegistroField.getText())
				|| !Usuario.validarNombre(apellidoRegistroField.getText())
				|| !Usuario.validarNumero(edadRegistroField.getText())
				|| !Usuario.validarNombre(localidadRegistroField.getText())
				|| !Usuario.validarNumero(telefonoRegistroField.getText())
				|| !Usuario.validarEmail(emailRegistroField.getText()))
			return;
		
		Paciente p = new Paciente(dniRegistroField.getText());
		p.nombre.set(nombreRegistroField.getText().toUpperCase());
		p.apellido.set(apellidoRegistroField.getText().toUpperCase());
		p.edad = Integer.parseInt(edadRegistroField.getText());
		p.direccion = direccionRegistroField.getText();
		p.localidad = localidadRegistroField.getText();
		p.telefono = Integer.parseInt(telefonoRegistroField.getText());
		p.email =  emailRegistroField.getText();
		
		if(Usuario.ingresarUsuario(p)) {
			ManejadorDeFichero mf = new ManejadorDeFichero();
			mf.escribirUsuarios("./data/usuarios.txt", ";", Usuario.listaUsuarios);
			Main.alerta("Paciente registrado correctamente");
		}	
	}
}
