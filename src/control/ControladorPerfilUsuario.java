package control;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.ObservadorCampoDeTexto;
import model.Usuario;

public class ControladorPerfilUsuario implements ObservadorCampoDeTexto {

	private Usuario u; // Usuario del perfil que se ha abierto

	private String editar; // para gestionar la edicion de los campos de texto del usuario
	
//	public ControladorTablaPacientes tp; // para actualizar la tabla en el caso de que existiera durante
										 // una edicion en el perfil

	// Componentes de la interfaz del Perfil de Usuario
	private Label dniField;
	private Label nombreField;
	private Label apellidoField;
	private Label edadField;
	private Label direccionField;
	private Label localidadField;
	private Label telefonoField;
	private Label emailField;
	private ImageView fotoImage;
	private ImageView lapizNombre;
	private ImageView lapizApellido;
	private ImageView lapizEdad;
	private ImageView lapizDireccion;
	private ImageView lapizLocalidad;
	private ImageView lapizTelefono;
	private ImageView lapizEmail;
	private ImageView lapizFoto;
	private Label bienvenido;
	private Label comoEditar;
	private JFXButton volverPerfil;

	public ControladorPerfilUsuario(Label dniField, Label nombreField, Label apellidoField, Label edadField, Label direccionField, Label localidadField, Label telefonoField, Label emailField, ImageView fotoImage, ImageView lapizNombre, ImageView lapizApellido, ImageView lapizEdad, ImageView lapizDireccion, ImageView lapizLocalidad, ImageView lapizTelefono, ImageView lapizEmail, ImageView lapizFoto, Label bienvenido, Label comoEditar, JFXButton volverPerfil) {
		this.dniField = dniField;
		this.nombreField = nombreField;
		this.apellidoField = apellidoField;
		this.edadField = edadField;
		this.direccionField = direccionField;
		this.localidadField = localidadField;
		this.telefonoField = telefonoField;
		this.emailField = emailField;
		this.fotoImage = fotoImage;
		
		this.lapizNombre = lapizNombre;
		this.lapizApellido = lapizApellido;
		this.lapizEdad = lapizEdad;
		this.lapizDireccion = lapizDireccion;
		this.lapizLocalidad = lapizLocalidad;
		this.lapizTelefono = lapizTelefono;
		this.lapizEmail = lapizEmail;
		this.lapizFoto = lapizFoto;
		
		this.bienvenido = bienvenido;
		this.comoEditar = comoEditar;
		this.volverPerfil = volverPerfil;
	}

	// Metodos---------------------------------------------------------------------------------

	// Inicia todos los campos de la ventana con el valor correspondiente
	public void cargarPerfilUsuario(Usuario u) {
		this.u = u;
		dniField.setText(u.dni.get());
		nombreField.setText(u.nombre.get());
		apellidoField.setText(u.apellido.get());
		edadField.setText(u.edad + "");
		direccionField.setText(u.direccion);
		localidadField.setText(u.localidad);
		telefonoField.setText(u.telefono + "");
		emailField.setText(u.email);
		if(!u.fotoUrl.equals("null"))
			fotoImage.setImage(new Image(new File(u.fotoUrl).toURI().toString()));
		
		if(!ControladorLogin.usuarioActivo.equals(u)) {
			lapizNombre.setVisible(false);
			lapizApellido.setVisible(false);
			lapizEdad.setVisible(false);
			lapizDireccion.setVisible(false);
			lapizTelefono.setVisible(false);
			lapizLocalidad.setVisible(false);
			lapizEmail.setVisible(false);
			lapizFoto.setVisible(false);
			bienvenido.setVisible(false);
			comoEditar.setVisible(false);
			volverPerfil.setVisible(true);
		} else {
			lapizNombre.setVisible(true);
			lapizApellido.setVisible(true);
			lapizEdad.setVisible(true);
			lapizDireccion.setVisible(true);
			lapizTelefono.setVisible(true);
			lapizLocalidad.setVisible(true);
			lapizEmail.setVisible(true);
			lapizFoto.setVisible(true);
			bienvenido.setVisible(true);
			comoEditar.setVisible(true);
			volverPerfil.setVisible(false);
		}
		
		lapizNombre.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				editarNombre();
			}
			
		});
		lapizApellido.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				editarApellido();
			}
			
		});
		lapizEdad.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				editarEdad();
			}
			
		});
		lapizDireccion.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				editarDireccion();
			}
			
		});
		lapizLocalidad.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				editarLocalidad();
			}
			
		});
		lapizTelefono.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				editarTelefono();
			}
			
		});
		lapizEmail.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				editarEmail();
			}
			
		});
		lapizFoto.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				editarFoto();
			}
			
		});
	}

	@Override
	public void introducirCampoDeTexto(String texto) {

		if (texto.isEmpty()) {
			Main.alerta("El texto está vacío.");
			return;
		}

		switch (editar) {
			case "nombre": {
				if (!Usuario.validarNombre(texto))
					return;
				texto = texto.toUpperCase();
				u.nombre.set(texto);
				nombreField.setText(texto);
				break;
			}
			case "apellido": {
				if (!Usuario.validarNombre(texto))
					return;
				texto = texto.toUpperCase();
				u.apellido.set(texto);
				apellidoField.setText(texto);
				break;
			}
			case "edad": {
				if (!Usuario.validarNumero(texto )) {
					return;
				}
				u.edad = Integer.parseInt(texto) + 0;
				edadField.setText(texto);
				break;
			}
			case "direccion": {
				u.direccion = texto;
				direccionField.setText(texto);
				break;
			}
			case "localidad": {
				if (!Usuario.validarNombre(texto)) {
					Main.alerta("Localidad incorrecta.");
					return;
				}
				u.localidad = texto;
				localidadField.setText(texto);
				break;
			}
			case "telefono": {
				if (!Usuario.validarNumero(texto)) {
					return;
				}
				u.telefono = Integer.parseInt(texto);
				telefonoField.setText(texto);
				break;
			}
			case "email": {
				if (!Usuario.validarEmail(texto))
					return;
				u.email = texto;
				emailField.setText(texto);
				break;
			}
			case "password": {
				if (!Usuario.validarPassword(texto))
					return;
				u.password = texto;
				break;
			}
		}
		ManejadorDeFichero mf = new ManejadorDeFichero();
		mf.escribirUsuarios("./data/usuarios.txt", ";", Usuario.listaUsuarios);
		
//		if(tp != null)
//			tp.actualizarTabla();
	}

	public void editarNombre() {
		editar = "nombre";
		ControladorCampoDeTexto ct = new ControladorCampoDeTexto(this);
		Main.abrirVentana(ct, "/view/VentanaCampoDeTexto.fxml", "Editar nombre", false, false, 0, 0);
		ct.tituloMensaje.setText("Introduzca nuevo nombre:");
	}

	public void editarApellido() {
		editar = "apellido";
		ControladorCampoDeTexto ct = new ControladorCampoDeTexto(this);
		Main.abrirVentana(ct, "/view/VentanaCampoDeTexto.fxml", "Editar apellido", false, false, 0, 0);
		ct.tituloMensaje.setText("Introduzca nuevo apellido:");
	}

	public void editarEdad() {
		editar = "edad";
		ControladorCampoDeTexto ct = new ControladorCampoDeTexto(this);
		Main.abrirVentana(ct, "/view/VentanaCampoDeTexto.fxml", "Editar edad", false, false, 0, 0);
		ct.tituloMensaje.setText("Introduzca nueva edad:");
	}

	public void editarDireccion() {
		editar = "direccion";
		ControladorCampoDeTexto ct = new ControladorCampoDeTexto(this);
		Main.abrirVentana(ct, "/view/VentanaCampoDeTexto.fxml", "Editar direccion", false, false, 0, 0);
		ct.tituloMensaje.setText("Introduzca nueva direccion:");
	}

	public void editarLocalidad() {
		editar = "localidad";
		ControladorCampoDeTexto ct = new ControladorCampoDeTexto(this);
		Main.abrirVentana(ct, "/view/VentanaCampoDeTexto.fxml", "Editar localidad", false, false, 0, 0);
		ct.tituloMensaje.setText("Introduzca nueva localidad:");
	}

	public void editarTelefono() {
		editar = "telefono";
		ControladorCampoDeTexto ct = new ControladorCampoDeTexto(this);
		Main.abrirVentana(ct, "/view/VentanaCampoDeTexto.fxml", "Editar telefono", false, false, 0, 0);
		ct.tituloMensaje.setText("Introduzca nuevo telefono:");
	}

	public void editarEmail() {
		editar = "email";
		ControladorCampoDeTexto ct = new ControladorCampoDeTexto(this);
		Main.abrirVentana(ct, "/view/VentanaCampoDeTexto.fxml", "Editar email", false, false, 0, 0);
		ct.tituloMensaje.setText("Introduzca nuevo email:");
	}

	public void cambiarPassword() {
		editar = "password";
		ControladorCampoDeTexto ct = new ControladorCampoDeTexto(this);
		Main.abrirVentana(ct, "/view/VentanaCampoDeTexto.fxml", "Editar contraseña", false, false, 0, 0);
		ct.tituloMensaje.setText("Introduzca nueva contraseña:");
	}

	public void editarFoto() {
		// JFileChooser, explorador de archivos para seleccionar foto
		FileChooser fileChooser = new FileChooser();
		File fotoSeleccionada = fileChooser.showOpenDialog(null);
		this.fotoImage.getScene().getWindow().requestFocus();
		if (fotoSeleccionada == null)
			System.out.println("\nNo has seleccionado ninguna foto");
		else {
			if (!fotoSeleccionada.getName().contains(".png")) {
				System.out.println("\nDebes seleccionar una imagen .png");
				Main.alerta("Solo acepta archivos .png");
				return;
			}
			System.out.println("\nHas seleccionado " + fotoSeleccionada);

			// Elimina la foto actual de la carpeta img
			File fotoActual = new File(u.fotoUrl);
			if (fotoActual.exists()) {
				if(!u.fotoUrl.equals("./img/usuario.png")) {
					System.out.println("Borrando foto actual de la carpeta img...");
					fotoActual.delete();
				}
			}
			// Copia y pega la nueva foto en la carpeta img
			try {
				System.out.println("Copiando nueva foto en la carpeta img...");
				Files.copy(Paths.get(fotoSeleccionada.getAbsolutePath()), // Copia URL origen
						Paths.get("./img/" + fotoSeleccionada.getName()), // Pega en URL destino
						StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// Reemplaza la foto actual del usuario por la nueva
			u.fotoUrl = "./img/" + fotoSeleccionada.getName();
			System.out.println(u.fotoUrl);
			fotoImage.setImage(new Image(new File(u.fotoUrl).toURI().toString()));
			
			ManejadorDeFichero mf = new ManejadorDeFichero();
			mf.escribirUsuarios("./data/usuarios.txt", ";", Usuario.listaUsuarios);
		}
	}
	
	public void finalizar(ActionEvent e) {
		Stage stage = (Stage)this.dniField.getScene().getWindow();
		stage.close();
	}

}
