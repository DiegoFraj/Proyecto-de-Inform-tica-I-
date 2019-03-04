package model;

import java.util.ArrayList;

public class Chat {
	
	public Supervisor supervisor;
	public Paciente paciente;
	public ArrayList<Mensaje> listaMensajes;
	
	public Chat(Supervisor s, Paciente p) {
		supervisor = s;
		paciente = p;
		listaMensajes = new ArrayList<Mensaje>();
	}
	
	public boolean addMensaje(Mensaje m) {
		return listaMensajes.add(m);
	}
	
	public String toString() {
		String s = "#"+supervisor.dni.get()+";"+paciente.dni.get()+"\n";
		
		for(Mensaje m : listaMensajes) {
			s += m.toString() + "\n";
		}
		return s;
	}
}
