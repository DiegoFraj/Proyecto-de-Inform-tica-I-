package model;

import javafx.beans.property.SimpleStringProperty;

public class Alerta {

	public Paciente paciente;
	public SimpleStringProperty tipo;
	public SimpleStringProperty fechaHora;

	public Alerta(String tipo, String fechaHora) {
		this.tipo = new SimpleStringProperty(tipo);
		this.fechaHora = new SimpleStringProperty(fechaHora);
	}
	
	public String toString() {
		return tipo.get()+";"+fechaHora.get();
	}
	
	public String getTipo() {
		return tipo.get();
	}
	
	public String getFechaHora() {
		return fechaHora.get();
	}

}
