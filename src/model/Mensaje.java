package model;

public class Mensaje {
	
	public char tipoUsuario;
	public String mensaje;
	public String hora;
	public String fecha;
	public int leido; // 0 leido, 1 no leido
	
	public Mensaje(char tipoUsuario, String mensaje, String hora, String fecha, int leido) {
		this.tipoUsuario = tipoUsuario;
		this.mensaje = mensaje;
		this.hora = hora;
		this.fecha = fecha;
		this.leido = leido;
	}
	
	@Override
	public String toString() {
		return tipoUsuario + ";" + mensaje + ";" + hora + ";" + fecha + ";" + leido;
	}
}
