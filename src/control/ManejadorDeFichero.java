package control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import model.*;

public class ManejadorDeFichero {

	public void escribirUsuarios(String fichero, String separador, ArrayList<Usuario> listaUsuarios) {
		String textOut = "";

		for (Usuario u : listaUsuarios) {
			textOut += u.toString() + "\n";
		}

		File f = new File(fichero);

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(f));
			writer.write(textOut);
			writer.close();
		} catch (Exception e) {
			System.out.println("ERROR:" + e.getMessage());
		}
	}

	public ArrayList<Usuario> leerUsuarios(String fichero, String separador) {
		ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
		
		File f = new File(fichero);
		String line;

		try {
			BufferedReader lector = new BufferedReader(new FileReader(f));
			line = lector.readLine();

			// Lee el texto linea a linea
			while (line != null) {
				Usuario u;
				char tipo = line.charAt(0);		// tipo
				line = lector.readLine();		// dni
				if(tipo == 's') {
					u = new Supervisor(line);
				} else {
					u = new Paciente(line);
				}
				line = lector.readLine();		// nombre y apellido
				String[] palabras = line.split(separador);
				u.setNombre(palabras[0]);
				u.setApellido(palabras[1]);
				line = lector.readLine(); 		// edad
				u.edad = Integer.parseInt(line);
				line = lector.readLine();		// direccion y localidad
				palabras = line.split(separador);
				u.direccion = palabras[0];
				u.localidad = palabras[1];
				line = lector.readLine();		// telefono
				u.telefono = Integer.parseInt(line);
				line = lector.readLine();		// email y password
				palabras = line.split(separador);
				u.email = palabras[0];
				u.password = palabras[1];
				line = lector.readLine();		// fotoURL
				u.fotoUrl = line;
				
				listaUsuarios.add(u);
				
				line = lector.readLine();
			} // fin while
			lector.close();
		} catch (Exception e) {
			System.out.println("ERROR:" + e.getMessage());
		}

		return listaUsuarios;
	}

	public ArrayList<Supervisor> leerPacientes(String fichero, String separador) {
		ArrayList<Supervisor> supervisoresPacientes = new ArrayList<Supervisor>();
		Supervisor s = null;

		File f = new File(fichero);
		String line;
		StringTokenizer stoken;

		try {
			BufferedReader lector = new BufferedReader(new FileReader(f));
			line = lector.readLine();
			// Lee el texto linea a linea
			while (line != null) {
				if (line.contains("#")) {
					s = new Supervisor(line.replace("#", ""));
				} else {
					stoken = new StringTokenizer(line, separador);
					while (stoken.hasMoreTokens()) {
						s.asignarPaciente(new Paciente(stoken.nextToken()));
					}
					supervisoresPacientes.add(s);
				}
				line = lector.readLine();
			} // fin while
			lector.close();
		} catch (Exception e) {
			System.out.println("ERROR:" + e.getMessage());
		}
		return supervisoresPacientes;
	}

	// Escribe cada supervisor y su lista de pacientes en un txt
	public void escribirPacientes(String fichero, String separador, ArrayList<Supervisor> supervisoresPacientes) {
		String textOut = "";

		if(supervisoresPacientes == null || supervisoresPacientes.isEmpty()) {
			return;
		}
		
		for (Supervisor s : supervisoresPacientes) {
			textOut += "#"+s.dni.get() + "\n";
			for (int i = 0; i < s.listaPacientes.size(); i++) {
				textOut += s.listaPacientes.get(i).dni.get();
				if (i != s.listaPacientes.size() - 1)
					textOut += separador;
			}
			textOut += "\n";
		}

		File f = new File(fichero);

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(f));
			writer.write(textOut);
			writer.close();
		} catch (Exception e) {
			System.out.println("ERROR:" + e.getMessage());
		}
	}

	public ArrayList<Paciente> leerAlertas(String fichero, String separador) {
		ArrayList<Paciente> pacientesAlertas = new ArrayList<Paciente>();
		Paciente p = null;

		File f = new File(fichero);
		String line;
		String[] lineAlerta;

		try {
			BufferedReader lector = new BufferedReader(new FileReader(f));
			line = lector.readLine();
			// Lee el texto linea a linea
			while (line != null) {
				if (line.contains("#")) {
					p = new Paciente(line.replace("#", ""));
					pacientesAlertas.add(p);
				} else {
					lineAlerta = line.split(separador);
					p.estadisticas.alertas.add(new Alerta(lineAlerta[0], lineAlerta[1]));
				}
				line = lector.readLine();
			} // fin while
			lector.close();
		} catch (Exception e) {
			System.out.println("ERROR:" + e);
		}
		return pacientesAlertas;
	}

	// Lee todos los pulsos de cada paciente (60 ppm por paciente, experimento de 1
	// hora)
	// Devuelve una lista de pacientes que contienen los pulsometros de cada uno
	public ArrayList<Paciente> leerPulsometros(String fichero, String separador) {
		ArrayList<Paciente> pacientesPulsometros = new ArrayList<Paciente>();
		Paciente p = null;

		File f = new File(fichero);
		String line = "";
		int i = 0;

		try {
			BufferedReader lector = new BufferedReader(new FileReader(f));
			// Lee el texto linea a linea
			line = lector.readLine(); // Lee linea
			while (line != null) {
				if (line.contains("#")) { // Si la linea es un dni (mas de 3 digitos, no es ppm)
					p = new Paciente(line.replace("#", "")); // Se crea nuevo paciente con el dni
					pacientesPulsometros.add(p);
					i = 0; // reinicio contador
				} else {
					String[] ppmHora = line.split(separador);
					p.estadisticas.ppm[i] = Integer.parseInt(ppmHora[0]); 	// Lee el pulso
					p.estadisticas.horas[i] = ppmHora[1];					// y su hora
					i++; // Contador incrementa
				}
				line = lector.readLine(); // Lee linea
			} // fin while
			lector.close();
		} catch (Exception e) {
			System.out.println("ERROR:" + e.getMessage());
		}
		return pacientesPulsometros;
	}
	
	public ArrayList<Chat> leerChats(String fichero, String separador) {
		ArrayList<Chat> chats = new ArrayList<Chat>();
		Chat chat = null;
		Mensaje mensaje = null;
		Supervisor supervisor;
		Paciente paciente;

		File f = new File(fichero);
		String line = "";

		try {
			BufferedReader lector = new BufferedReader(new FileReader(f));
			// Lee el texto linea a linea
			line = lector.readLine(); // Lee linea
			while (line != null) {
				if (line.charAt(0) == '#') { 
					if(chat != null) {
						chats.add(chat);
					}
					line = line.replace("#", "");
					String[] sp = line.split(separador);
					supervisor = new Supervisor(sp[0]);
					paciente = new Paciente(sp[1]);
					chat = new Chat(supervisor, paciente);
				} else {
					String[] mensajeData = line.split(separador);
					mensaje = new Mensaje(mensajeData[0].charAt(0), mensajeData[1], mensajeData[2], mensajeData[3], Integer.parseInt(mensajeData[4]));
					chat.addMensaje(mensaje);
				}
				line = lector.readLine(); // Lee linea
			} // fin while
			if(chat != null) {
				chats.add(chat);
			}
			lector.close();
		} catch (Exception e) {
			System.out.println("ERROR:" + e.getMessage());
		}
		return chats;
	}
	
	public void escribirChats(String fichero, String separador, ArrayList<Chat> chats) {
		String textOut = "";

		if(chats == null || chats.isEmpty()) {
			return;
		}
		for (Chat chat : chats) {
			textOut += chat.toString();
		}

		File f = new File(fichero);

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(f));
			writer.write(textOut);
			writer.close();
		} catch (Exception e) {
			System.out.println("ERROR:" + e.getMessage());
		}
	}
}
