package model;

import java.util.ArrayList;

// Contiene los datos de los sensores de cada paciente 
// (pulsometro, alertas por fuego o accidente con fecha y hora)
public class Estadisticas {

	public ArrayList<Alerta> alertas;
	
	public int[] ppm;	// Pulsos por minuto (minimo 40, maximo 80)
	public String[] horas;	
	
	public Estadisticas() {
		alertas = new ArrayList<Alerta>();
		ppm = new int[30]; 			// vamos a registrar pulsos durante media hora (30 pulsos)
		horas = new String[30];
	}
}
