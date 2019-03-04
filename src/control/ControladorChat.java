package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Chat;
import model.Mensaje;
import model.Paciente;
import model.Supervisor;
import model.Usuario;

public class ControladorChat {

	// Componentes JavaFx

    private ListView<String> lvChatWindow;
    private ListView<String> lvHoraWindow;
    private ListView<Paciente> friendList;
    private TextField tfUser;
    private JFXButton enviarMensaje;
    

	
	
	// Atributos	
	Usuario usuarioChatSeleccionado;
	
	// Metodos
	
	public ControladorChat(JFXListView<String> lvChatWindow, JFXListView<String> lvHoraWindow,
			JFXListView<Paciente> friendList, JFXTextField tfUser, JFXButton enviarMensaje) {
		this.lvChatWindow = lvChatWindow;
		this.lvHoraWindow = lvHoraWindow;
		this.friendList = friendList;
		this.tfUser = tfUser;
		this.enviarMensaje = enviarMensaje;
		this.enviarMensaje.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
			enviarMensaje();
		});
        
	}

	public void cargarDatosChat() {
		cargarChats();
		cargarListaAmigos();
		if(ControladorLogin.usuarioActivo.tipo == 'p') {
			cargarChat();
		}
	}
	
	public void cargarChats() {
		ArrayList<Chat> chats = new ArrayList<Chat>();
		
		ManejadorDeFichero mf = new ManejadorDeFichero();
		chats = mf.leerChats("./data/chats.txt", ";");
		
		if(ControladorLogin.usuarioActivo.tipo == 's') {
			ArrayList<Supervisor> supervisoresPacientes = mf.leerPacientes("./data/pacientes.txt", ";");
			for ( Supervisor supervisor : supervisoresPacientes ) {
				if(((Supervisor)ControladorLogin.usuarioActivo).equals(supervisor)) {
					for ( Paciente p : supervisor.listaPacientes ) {
						((Supervisor)ControladorLogin.usuarioActivo).asignarPaciente((Paciente) Usuario.getUsuario(p));
					}
				}
			}
		} else {
			ArrayList<Supervisor> supervisoresPacientes = mf.leerPacientes("./data/pacientes.txt", ";");
			for ( Supervisor supervisor : supervisoresPacientes ) {
				for ( Paciente p : supervisor.listaPacientes ) {
					if(((Paciente)ControladorLogin.usuarioActivo).equals(p))
					((Supervisor)Usuario.getUsuario(supervisor)).asignarPaciente((Paciente)ControladorLogin.usuarioActivo);
				}
			}
		}
		
		for(Chat c : chats) {
			c.supervisor = (Supervisor)Usuario.getUsuario(c.supervisor);
			c.supervisor.chats.add(c);
			c.paciente = (Paciente)Usuario.getUsuario(c.paciente);
			c.paciente.chat = c;			
		}
	}
	
	private void cargarListaAmigos() {
		if(ControladorLogin.usuarioActivo.tipo == 's') {
			ArrayList<String> nombresPacientes = new ArrayList<String>();
			for(Paciente p : ((Supervisor)ControladorLogin.usuarioActivo).listaPacientes) {
				nombresPacientes.add(p.nombre.get());
			}
			ObservableList<Paciente> listaAmigos = FXCollections.observableArrayList(
					((Supervisor)ControladorLogin.usuarioActivo).listaPacientes);
			friendList.setItems(listaAmigos);
			friendList.setCellFactory(e -> new PacienteCell());
			
			friendList.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					if (event.getClickCount() > 1) {
						usuarioChatSeleccionado = friendList.getSelectionModel().getSelectedItem();
						cargarChat();
					}
				}

			});
		} else {
			usuarioChatSeleccionado = ((Paciente)ControladorLogin.usuarioActivo).supervisor;
		}
	}
	
	private void cargarChat() {
		ArrayList<String> mensajesChat = new ArrayList<String>();
		ArrayList<String> horasMensajes = new ArrayList<String>();
		Chat chat = null;
		String fechaActual = "";
		if(ControladorLogin.usuarioActivo.tipo == 's') {
			chat = ((Paciente)usuarioChatSeleccionado).chat;
		} else {
			chat = ((Paciente)ControladorLogin.usuarioActivo).chat;
		}
		if(chat == null) {
			lvChatWindow.setItems(null);			
			lvHoraWindow.setItems(null);			
			return;
		}
		for (Mensaje m : chat.listaMensajes) {
			if(fechaActual.isEmpty()) {
				fechaActual = m.fecha;
				mensajesChat.add(fechaActual);
				horasMensajes.add(" ");
			} else if(fechaActual.compareTo(m.fecha) == -1) {
				fechaActual = m.fecha;
				mensajesChat.add(fechaActual);
				horasMensajes.add(" ");
			}
			mensajesChat.add(m.mensaje);
			horasMensajes.add(m.hora);
		}
		ObservableList<String> mensajesChatLista = FXCollections.observableArrayList(mensajesChat);
		ObservableList<String> horasMensajesLista = FXCollections.observableArrayList(horasMensajes);
		
		lvChatWindow.setItems(mensajesChatLista);
		lvHoraWindow.setItems(horasMensajesLista);
	}
	
	public void enviarMensaje() {
		if(usuarioChatSeleccionado == null) {
			return;
		}
		Calendar now = Calendar.getInstance();
		
		int numeroMinuto = now.get(Calendar.MINUTE);
		String minuto;
		if(numeroMinuto < 10) {
			minuto = "0"+numeroMinuto;
		} else {
			minuto = ""+numeroMinuto;
		}

		String hora = ""+now.get(Calendar.HOUR_OF_DAY);
		String horaMinuto = hora + ":" + minuto;			// Hora formato 24:00
		
		String dia = ""+now.get(Calendar.DAY_OF_MONTH);
		int numeroMes = now.get(Calendar.MONTH)+1;
		String mes;
		if(numeroMes < 10) {
			mes = "0"+numeroMes;
		} else {
			mes = ""+numeroMes;
		}
		String year = ""+ now.get(Calendar.YEAR);
		String fecha =  year + "/" + mes + "/" + dia;		// Fecha formato dia/mes/año
		
		String mensajeString = ControladorLogin.usuarioActivo.nombre.get()+": "+tfUser.getText();
		Mensaje mensaje = new Mensaje(ControladorLogin.usuarioActivo.tipo, mensajeString, 
				horaMinuto, fecha, 1);
		if(ControladorLogin.usuarioActivo.enviarMensaje(mensaje, usuarioChatSeleccionado)){
			ManejadorDeFichero mf = new ManejadorDeFichero();
			mf.escribirChats("./data/chats.txt", ";", getChats());
			cargarChat();
		}
		tfUser.clear();
	}
	
	private ArrayList<Chat> getChats(){
		ArrayList<Chat> listaChats = new ArrayList<Chat>();
		
		for(Usuario u : Usuario.listaUsuarios) {
			if(u.tipo == 's') {
				Supervisor s = (Supervisor) u;
				listaChats.addAll(s.chats);
			}
		}
		
		return listaChats;
	}
}

class PacienteCell extends ListCell<Paciente>{
	 @Override
	 protected void updateItem(Paciente item, boolean empty) {
		 super.updateItem(item, empty);
		 
		 if(!empty) {
			 setText(item.getNombre() + " " + item.getApellido());
		 }
	 }
}
