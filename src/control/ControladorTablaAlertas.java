package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Alerta;
import model.Paciente;

public class ControladorTablaAlertas {

	// Componentes de la interfaz del Tabla de Pacientes
	private TableView<Alerta> tablaAlertas;
	private TableColumn<Alerta, String> alertaColumn;
	private TableColumn<Alerta, String> fechaColumn;
	private Label alertasNombre;
	
		
	// Atributos
	private Paciente p;
	
	public ControladorTablaAlertas(TableView<Alerta> tablaAlertas, TableColumn<Alerta, String> alertaColumn, TableColumn<Alerta, String> fechaColumn, Label alertasNombre) {
		this.tablaAlertas = tablaAlertas;
		this.alertaColumn = alertaColumn;
		this.fechaColumn = fechaColumn;
		this.alertasNombre = alertasNombre;
	}
	
	public void cargarHistorialAlertas(Paciente p) {
		this.p = p;
		alertasNombre.setText(p.nombre.get() + " " + p.apellido.get());
		ManejadorDeFichero mf = new ManejadorDeFichero();
		ArrayList<Paciente> pacientesAlertas = mf.leerAlertas("./data/alertas.txt", ";");
		for ( Paciente paciente : pacientesAlertas ) {
			if(p.equals(paciente)) {
				p.estadisticas.alertas = paciente.estadisticas.alertas;
				for(Alerta alerta : p.estadisticas.alertas) {
					alerta.paciente = p;
				}
			}
		}
		
		tablaAlertas.setPlaceholder(new Label("No existe ninguna alerta."));
		alertaColumn.setCellValueFactory(new PropertyValueFactory<Alerta, String>("tipo"));
		fechaColumn.setCellValueFactory(new PropertyValueFactory<Alerta, String>("fechaHora"));
		tablaAlertas.setItems((FXCollections.observableArrayList(p.estadisticas.alertas)));
	}
	
	public Paciente getPaciente() {
		return this.p;
	}

}
