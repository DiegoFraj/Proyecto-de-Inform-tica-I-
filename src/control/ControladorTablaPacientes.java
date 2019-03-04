package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.ObservadorCampoDeTexto;
import model.Paciente;
import model.Supervisor;
import model.Usuario;

public class ControladorTablaPacientes implements ObservadorCampoDeTexto {

	// Componentes de la interfaz del Tabla de Pacientes
	@FXML
	private TableView<Paciente> tablaPacientes;
	@FXML
	private TableColumn<Paciente, String> apellidosColumn;
	@FXML
	private TableColumn<Paciente, String> nombreColumn;
	@FXML
	private TableColumn<Paciente, String> dniColumn;
	@FXML
	private JFXButton verPulsometro;
	@FXML
	private JFXButton verAlertas;
	@FXML
	private JFXButton borrarSeleccion;
	ControladorMainTest cmt;


	public ControladorTablaPacientes(ControladorMainTest cmt, JFXButton verAlertas, JFXButton verPulsometro, JFXButton borrarSeleccion,
			TableView<Paciente> tablaPacientes, TableColumn<Paciente, String> nombreColumn,
			TableColumn<Paciente, String> apellidosColumn, TableColumn<Paciente, String> dniColumn) {
		this.verPulsometro = verPulsometro;
		this.verAlertas = verAlertas;
		this.borrarSeleccion = borrarSeleccion;
		this.tablaPacientes = tablaPacientes;
		this.apellidosColumn = apellidosColumn;
		this.nombreColumn = nombreColumn;
		this.dniColumn = dniColumn;	
		this.cmt = cmt;
		
		this.verPulsometro.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {

			Paciente p = tablaPacientes.getSelectionModel().getSelectedItem();
			if(p == null) return;
			cmt.mostrarPulsometroPaciente(p);
		});
		this.verAlertas.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {

			Paciente p = tablaPacientes.getSelectionModel().getSelectedItem();
			if(p == null) return;
			cmt.mostrarAlertasPaciente(p);
		});
		this.borrarSeleccion.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {

			Supervisor s = (Supervisor) ControladorLogin.usuarioActivo;
			Paciente p = tablaPacientes.getSelectionModel().getSelectedItem();
			if (p == null)
				return;
			if (!s.eliminarPaciente(p)) {
				Main.alerta("El paciente no se encuentra en la lista");
				return;
			}
			ArrayList<Supervisor> supervisores = new ArrayList<Supervisor>();
			for(Usuario u : Usuario.listaUsuarios) {
				if(u.tipo == 's') {
					supervisores.add((Supervisor) u);
				}
			}
			ManejadorDeFichero mf = new ManejadorDeFichero();
			mf.escribirPacientes("./data/pacientes.txt", ";", supervisores);
			actualizarTabla();
		});
		
		
		}



	
	

	public void cargarTablaPacientes() {
		Supervisor s = (Supervisor) ControladorLogin.usuarioActivo;

		
		ManejadorDeFichero mf = new ManejadorDeFichero();
		ArrayList<Supervisor> supervisoresPacientes = mf.leerPacientes("./data/pacientes.txt", ";");
		for ( Supervisor supervisor : supervisoresPacientes ) {
			if(s.equals(supervisor)) {
				for ( Paciente p : supervisor.listaPacientes ) {
					s.asignarPaciente((Paciente) Usuario.getUsuario(p));
				}
			}
		}
		
		tablaPacientes.setPlaceholder(new Label("No existe ningún paciente asignado."));
		apellidosColumn.setCellValueFactory(new PropertyValueFactory<Paciente, String>("apellido"));
		nombreColumn.setCellValueFactory(new PropertyValueFactory<Paciente, String>("nombre"));
		dniColumn.setCellValueFactory(new PropertyValueFactory<Paciente, String>("dni"));
		tablaPacientes.setItems((FXCollections.observableArrayList(s.listaPacientes)));

		tablaPacientes.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(tablaPacientes.getSelectionModel().getSelectedItem() == null) return;
				if (event.getClickCount() > 1) {
					accederPaciente();
				}
				
			}

		});
	}

	@Override
	public void introducirCampoDeTexto(String texto) {
		Supervisor s = (Supervisor) ControladorLogin.usuarioActivo;
		Usuario paciente = Usuario.getUsuario(new Usuario(texto));
		if (paciente == null) {
			Main.alerta("El DNI introducido no se encuentra.");
			return;
		}
		if (paciente.tipo != 'p') {
			Main.alerta("El usuario introducido no es un paciente.");
			return;
		}
		if(((Paciente) paciente).supervisor != null) {
			Main.alerta("El paciente ya está supervisado por un supervisor.");
			return;
		}
		if(s.asignarPaciente((Paciente) paciente)) {
			ArrayList<Supervisor> supervisores = new ArrayList<Supervisor>();
			for(Usuario u : Usuario.listaUsuarios) {
				if(u.tipo == 's') {
					supervisores.add((Supervisor) u);
				}
			}
			ManejadorDeFichero mf = new ManejadorDeFichero();
			mf.escribirPacientes("./data/pacientes.txt", ";", supervisores);
			Main.alerta("Paciente asignado correctamente");
		}
		actualizarTabla();
	}

	public void actualizarTabla() {
		Supervisor s = (Supervisor) ControladorLogin.usuarioActivo;
		tablaPacientes.setItems((FXCollections.observableArrayList(s.listaPacientes)));
		tablaPacientes.refresh();
	}
	
	public void asignarPaciente(ActionEvent e) {
		ControladorCampoDeTexto ct = new ControladorCampoDeTexto(this);
		Main.abrirVentana(ct, "/view/VentanaCampoDeTexto.fxml", "Asignar nuevo paciente", false, false, 0, 0);
		ct.tituloMensaje.setText("Introduzca DNI del paciente:");
	}

	private void accederPaciente() {
		Paciente p = tablaPacientes.getSelectionModel().getSelectedItem();
		if(p == null) return;
		cmt.mostrarPerfilSeleccionado(p);
	}
}
