package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import model.Paciente;

public class ControladorGrafica {
	
	private LineChart<String, Number> lineChart;
	private Label pulsometroNombre;
	
	private Paciente p;
	
	public ControladorGrafica(LineChart<String, Number> lineChart, Label pulsometroNombre) {
		this.lineChart = lineChart;
		this.pulsometroNombre = pulsometroNombre;
	}

	public void cargarPulsometro(Paciente p) {
		this.p = p;
		pulsometroNombre.setText(p.nombre.get() + " " + p.apellido.get());
		ManejadorDeFichero mf = new ManejadorDeFichero();
		ArrayList<Paciente> pacientesPulsometros = mf.leerPulsometros("./data/pulsometros.txt", ";");
		for(Paciente paciente : pacientesPulsometros) {
			if(p.equals(paciente)) {
				p.estadisticas.ppm = paciente.estadisticas.ppm;
				p.estadisticas.horas = paciente.estadisticas.horas;
			}
		}
		
		Series<String, Number> series = new XYChart.Series<String, Number>();
		if(p.estadisticas.ppm[0] == 0) {
			Main.alerta("No tiene el pulsómetro conectado.");
			return;
		}
		for (int i = 0; i < p.estadisticas.ppm.length; i++) {
			series.getData().add(new XYChart.Data<String, Number>(p.estadisticas.horas[i], p.estadisticas.ppm[i]));
		}
		lineChart.setLegendVisible(false);
		lineChart.getData().add(series);
	}
}