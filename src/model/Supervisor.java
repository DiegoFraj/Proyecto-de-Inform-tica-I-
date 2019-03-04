package model;

import java.util.ArrayList;

import control.Main;

public class Supervisor extends Usuario {

	// Atributos--------------------------------------------------------------------------------
	public ArrayList<Paciente> listaPacientes; // Lista de pacientes asignados al supervisor
	public ArrayList<Chat> chats;

	// Constructor------------------------------------------------------------------------------
	public Supervisor(String dni) {
		super(dni);
		this.tipo = 's';
		listaPacientes = new ArrayList<Paciente>();
		chats = new ArrayList<Chat>();
	}

	// Metodos----------------------------------------------------------------------------------

	@Override
	public boolean enviarMensaje(Mensaje mensaje, Usuario receptor) {
		if(!super.enviarMensaje(mensaje, receptor)) {
			return false;
		}
		
		boolean existeChat = false;
		Chat chat = null;
		for(Chat c : chats) {
			System.out.println(receptor.getNombre());
			if(c.paciente.equals(receptor)) {
				chat = c;
				existeChat = true;
				break;
			} 
		}
		
		if(!existeChat) {
			chat = new Chat(this, (Paciente)receptor);
		}
		chat.addMensaje(mensaje);
		if(!existeChat) {
			chats.add(chat);
			((Paciente)receptor).chat = chat;
		}
		return true;
	}
	
	// Asigna un paciente ya registrado al supervisor (lo a�ade a su lista de
	// pacientes) y
	// asigna el supervisor al paciente
	public boolean asignarPaciente(Paciente p) {
		if (listaPacientes.contains(p))
			return false;
		if(p.supervisor != null) {
			Main.alerta("El paciente ya tiene un supervisor asignado.");
			return false;
		}
		if (listaPacientes.add(p)) {
			listaPacientes.sort(new UsuarioComparator());
			p.supervisor = this;
			return true;
		}
		return false;
	}

	// Elimina un paciente de la lista de pacientes del supervisor
	public boolean eliminarPaciente(Paciente p) {
		p.supervisor = null;
		return listaPacientes.remove(p);
	}

}
