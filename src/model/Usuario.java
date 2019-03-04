package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import control.Main;
import javafx.beans.property.SimpleStringProperty;

public class Usuario {

	// Atributos--------------------------------------------------------------------------
	public char tipo; // 's' supervisor, 'p' paciente
	public final SimpleStringProperty dni;
	public SimpleStringProperty nombre;
	public SimpleStringProperty apellido;
	public int edad;
	public String direccion;
	public String localidad;
	public int telefono;
	public String email;
	public String password;
	public String fotoUrl;

	// Lista de Usuarios registrados en el sistema
	public static ArrayList<Usuario> listaUsuarios;
	

	// Constructor------------------------------------------------------------------------
	public Usuario(String dni) {
		super();
		tipo = 'n';
		this.dni = new SimpleStringProperty(dni);
		nombre = new SimpleStringProperty("null");
		apellido = new SimpleStringProperty("null");
		edad = 0;
		direccion = "null";
		localidad = "null";
		telefono = 0;
		email = "null";
		password = "null";
		fotoUrl = "./img/usuario.png";
	}

	// Metodos----------------------------------------------------------------------------
	
	public boolean enviarMensaje(Mensaje mensaje, Usuario receptor) {	
		if(mensaje.mensaje.contains(";")) {
			Main.alerta("No se admite el signo ;");
			return false;
		}
		return true;
	}
	
	// Guarda todos los datos del usuario en un string, en formato para almacenarlo
	// en txt.
	public String toString() {
		return tipo + "\n" + dni.get() + "\n" + nombre.get() + ";" + apellido.get() + "\n" + edad + "\n" + direccion + ";" + localidad
				+ "\n" + telefono + "\n" + email + ";" + password + "\n" + fotoUrl;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (other == this)
			return true;
		if (!(other instanceof Usuario))
			return false;
		Usuario otherUsuario = (Usuario) other;
		return (this.dni.get().equals(otherUsuario.dni.get()));
	}
	
	public String getDni() {
		return dni.get();
	}
	
	public void setDni(String dni) {
		this.dni.set(dni);
	}
	
	public String getNombre() {
		return nombre.get();
	}
	
	public void setNombre(String nombre) {
		this.nombre.set(nombre);
	}
	
	public String getApellido() {
		return apellido.get();
	}
	
	public void setApellido(String apellido) {
		this.apellido.set(apellido);
	}

	// A�ade un usuario a la lista de usuarios y los ordena por apellidos (Ver
	// Comparator)
	public static boolean ingresarUsuario(Usuario u) {
		if (listaUsuarios == null)
			listaUsuarios = new ArrayList<Usuario>();
		else if (listaUsuarios.contains(u)) {
			Main.alerta("Ya existe este usuario");
			return false;
		}
		if (listaUsuarios.add(u)) {
			u.password = generarPassword();
			listaUsuarios.sort(new UsuarioComparator());
			return true;
		}
		return false;
	}

	// Elimina un usuario de la lista de usuarios
	public static boolean eliminarUsuario(Usuario u) {
		return listaUsuarios.remove(u);
	}
	
	// Genera contrase�a aleatoria de 6 d�gitos num�ricos (contrase�a por defecto)
	private static String generarPassword() {
		int minimo = 100000;
		int maximo = 999999;
		Random random = new Random();
		int password = minimo + random.nextInt(maximo - minimo + 1);
		return ""+password;
	}

	// Devuelve el usuario con el mismo dni que el que se busca
	public static Usuario getUsuario(Usuario u) {
		for (Usuario u2 : listaUsuarios) {
			if (u2.equals(u))
				return u2;
		}
		return null;
	}

	// Metodos de validacion, comprueban que cada campo tenga el formato correcto.
	public static boolean validarNombre(String nombre) {
		if(nombre.isEmpty()) {
			Main.alerta("Texto vac�o. Debe rellenar el campo.");
		}
		if (!nombre.matches("[A-Z a-z]+")) {
			Main.alerta("Solo se aceptan letras a-zA-Z.");
			return false;
		}
		return true;
	}

	public static boolean validarEmail(String email) {
		if (!email.matches("[A-Za-z0-9_+-]+@[A-Za-z0-9]+[.]+[A-Za-z]{2,4}")) {
			Main.alerta("Email incorrecto.");
			return false;
		}
		return true;
	}
	
	public static boolean validarNumero(String texto) {
		if(texto.isEmpty()) {
			Main.alerta("Texto vac�o. Debe rellenar el campo.");
		}
		if(!texto.matches("[0-9]+")){
			Main.alerta("Solo se aceptan d�gitos num�ricos.");
			return false;
		}
		return true;
	}

	public static boolean validarDni(String dni) {
		if(dni.isEmpty()) {
			Main.alerta("Texto vac�o. Debe rellenar el campo.");
		}
		if (!dni.matches("[0-9]{8}[A-Za-z]{1}")) {
			Main.alerta("Solo se acepta el formato de 8 d�gitos y 1 letra.");
			return false;
		}
		return true;
	}

	public static boolean validarPassword(String password) {
		if (!password.matches("[A-Za-z0-9]+")) {
			Main.alerta("Solo acepta letras a-zA-Z y n�meros 0-9.");
			return false;
		}
		if (password.length() < 8) {
			Main.alerta("La contrase�a debe tener m�nimo 8 caracteres.");
			return false;
		}
		return true;
	}

}

// Comparador de Usuarios, para ordenar por apellidos.
class UsuarioComparator implements Comparator<Usuario> {

	@Override
	public int compare(Usuario o1, Usuario o2) {
		return o1.apellido.get().compareTo(o2.apellido.get());
	}

}
