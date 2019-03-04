package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Alerta;
import model.Paciente;
import model.Supervisor;
import model.Usuario;

public class ControladorNotificaciones {

	// Componentes de la interfaz del Tabla de Pacientes
		private TableView<Alerta> tablaAlertas;
		private TableColumn<Alerta, String> alertaColumn;
		private TableColumn<Alerta, String> fechaColumn;
		private ControladorMainTest cmt;
		
	public ControladorNotificaciones(ControladorMainTest cmt, TableView<Alerta> tablaNotificaciones,
				TableColumn<Alerta, String> alertaNotificaciones, TableColumn<Alerta, String> fechaNotificaciones) {
		tablaAlertas = tablaNotificaciones;
		alertaColumn = alertaNotificaciones;
		fechaColumn = fechaNotificaciones;
		this.cmt = cmt;
	}

	public void cargarNotificaciones() {
		//Lista de pacientes ya esta cargada antes de abrir esta ventana
		ManejadorDeFichero mf = new ManejadorDeFichero();
		ArrayList<Supervisor> supervisoresPacientes = mf.leerPacientes("./data/pacientes.txt", ";");
		for ( Supervisor supervisor : supervisoresPacientes ) {
			if(((Supervisor)ControladorLogin.usuarioActivo).equals(supervisor)) {
				for ( Paciente p : supervisor.listaPacientes ) {
					((Supervisor)ControladorLogin.usuarioActivo).asignarPaciente((Paciente) Usuario.getUsuario(p));
				}
			}
		}
		ArrayList<Paciente> pacientesAlertas = mf.leerAlertas("./data/alertas.txt", ";");
		ArrayList<Alerta> items = new ArrayList<Alerta>();
		for ( Supervisor supervisor : supervisoresPacientes ) {
			if(((Supervisor)ControladorLogin.usuarioActivo).equals(supervisor)) {
				for ( Paciente p : ((Supervisor)ControladorLogin.usuarioActivo).listaPacientes ) {
					((Supervisor)ControladorLogin.usuarioActivo).asignarPaciente((Paciente) Usuario.getUsuario(p));
					for ( Paciente paciente : pacientesAlertas ) {
						if(p.equals(paciente)) {
							p.estadisticas.alertas = paciente.estadisticas.alertas;
							for(Alerta alerta : p.estadisticas.alertas) {
								alerta.paciente = p;
							}
							items.addAll(p.estadisticas.alertas);

						}
					}
				}
			}
		}
		
		tablaAlertas.setPlaceholder(new Label("No existe ninguna alerta."));
		alertaColumn.setCellValueFactory(new PropertyValueFactory<Alerta, String>("tipo"));
		fechaColumn.setCellValueFactory(new PropertyValueFactory<Alerta, String>("fechaHora"));
		tablaAlertas.setItems(FXCollections.observableArrayList(items));
		
		tablaAlertas.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(tablaAlertas.getSelectionModel().getSelectedItem() == null) return;
				Paciente p = tablaAlertas.getSelectionModel().getSelectedItem().paciente;
				if(p == null) return;
				if (event.getClickCount() > 1) {
					cmt.mostrarPerfilSeleccionado(p);
					//Main.abrirVentana(new ControladorPerfilUsuario(p), "/view/VentanaPerfilUsuario.fxml", "Perfil de Usuario", false, false, 0, 0);
				}
			}

		});
	}

}
