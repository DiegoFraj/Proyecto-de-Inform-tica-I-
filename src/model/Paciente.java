package model;

public class Paciente extends Usuario {

	public Supervisor supervisor;
	public Estadisticas estadisticas;
	public Chat chat;

	public Paciente(String dni) {
		super(dni);
		this.tipo = 'p';
		
		this.estadisticas = new Estadisticas();
	}
	
	@Override
	public boolean enviarMensaje(Mensaje mensaje, Usuario receptor) {
		if(!super.enviarMensaje(mensaje, receptor)) {
			return false;
		}
		
		if(chat == null) {
			chat = new Chat((Supervisor)receptor, this);
		}
		
		chat.addMensaje(mensaje);
		return true;
	}

}
