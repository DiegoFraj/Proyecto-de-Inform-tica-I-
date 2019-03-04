package control;

import java.net.URL;
import java.util.ResourceBundle;

import org.omg.Messaging.SyncScopeHelper;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Circle;
import model.Paciente;

public class ControladorDrawer implements Initializable {

	@FXML
    private JFXButton b1;
    @FXML
    private JFXButton b2;
    @FXML
    private JFXButton b3;
    @FXML
    private JFXButton b4;
    @FXML
    private JFXButton b5;
    
    private ControladorMainTest cMainTest; 
    
    public ControladorDrawer(ControladorMainTest cmt) {
    	cMainTest = cmt;
    }
    
    public void initialize(URL url, ResourceBundle rb) {
    	if(ControladorLogin.usuarioActivo.tipo == 's') {
    		b1.setText("Tabla de Pacientes");
    		b2.setText("Notificaciones");
    		b3.setText("Chat");
    		b4.setText("Perfil");
    		b5.setText("Gestión");
    		cMainTest.pacientes.setVisible(true);
        	cMainTest.ctp.cargarTablaPacientes();
    	} else {
    		b1.setText("Bienvenida");
    		b1.setVisible(false);
    		b2.setText("Pulsometro");
    		b3.setText("Chat");
    		b4.setText("Perfil");
    		b5.setText("Alertas");
	    	cMainTest.cg.cargarPulsometro((Paciente)ControladorLogin.usuarioActivo);
    		cMainTest.pulsometro.setVisible(true);
	    	cMainTest.volverPulsometro.setVisible(false);
    	}
    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }
    
    @FXML
    private void startb1(ActionEvent event) {
    	cMainTest.burgerTask2.setRate(cMainTest.burgerTask2.getRate() * -1);
    	cMainTest.burgerTask2.play();
		cMainTest.drawer.close();
		cMainTest.drawer.setMouseTransparent(true);
    	if(ControladorLogin.usuarioActivo.tipo == 's') {
        	cMainTest.pacientes.setVisible(true);
        	cMainTest.atrasPerfilNotificaciones = false;
        	cMainTest.notificaciones.setVisible(false);
        	cMainTest.perfil.setVisible(false);
        	cMainTest.chat.setVisible(false);
        	cMainTest.pulsometro.setVisible(false);
        	cMainTest.alertas.setVisible(false);
        	cMainTest.gestion.setVisible(false);
        	cMainTest.menuGestion.setVisible(false);
        	cMainTest.registro.setVisible(false);
        	cMainTest.asociar.setVisible(false);
        	cMainTest.ctp.cargarTablaPacientes();
    	} else {
	        cMainTest.inicio.setVisible(true);
	        cMainTest.perfil.setVisible(false);
	        cMainTest.chat.setVisible(false);
	    	cMainTest.pulsometro.setVisible(false);
	    	cMainTest.alertas.setVisible(false);
    	}
    }
    
    @FXML
    private void startb2(ActionEvent event) {
    	cMainTest.burgerTask2.setRate(cMainTest.burgerTask2.getRate() * -1);
    	cMainTest.burgerTask2.play();
		cMainTest.drawer.close();
		cMainTest.drawer.setMouseTransparent(true);
    	if(ControladorLogin.usuarioActivo.tipo == 's') {
        	cMainTest.pacientes.setVisible(false);
        	cMainTest.notificaciones.setVisible(true);
        	cMainTest.atrasPerfilNotificaciones = true;
        	cMainTest.chat.setVisible(false);
        	cMainTest.perfil.setVisible(false);
        	cMainTest.pulsometro.setVisible(false);
        	cMainTest.alertas.setVisible(false);
        	cMainTest.gestion.setVisible(false);
        	cMainTest.menuGestion.setVisible(false);
        	cMainTest.registro.setVisible(false);
        	cMainTest.asociar.setVisible(false);
    		cMainTest.cn.cargarNotificaciones();
    	} else {
    		cMainTest.inicio.setVisible(false);
	        cMainTest.perfil.setVisible(false);
	        cMainTest.chat.setVisible(false);
	    	cMainTest.pulsometro.setVisible(true);
	    	cMainTest.alertas.setVisible(false);
	    	cMainTest.cg.cargarPulsometro((Paciente)ControladorLogin.usuarioActivo);
    	}
    }
    
    @FXML
    private void startb3(ActionEvent event) {
    	cMainTest.burgerTask2.setRate(cMainTest.burgerTask2.getRate() * -1);
    	cMainTest.burgerTask2.play();
		cMainTest.drawer.close();
		cMainTest.drawer.setMouseTransparent(true);
    	if(ControladorLogin.usuarioActivo.tipo == 's') {
    		cMainTest.pacientes.setVisible(false);
        	cMainTest.notificaciones.setVisible(false);
        	cMainTest.chat.setVisible(true);    	
        	cMainTest.perfil.setVisible(false);
        	cMainTest.pulsometro.setVisible(false);
        	cMainTest.alertas.setVisible(false);
        	cMainTest.gestion.setVisible(false);
        	cMainTest.menuGestion.setVisible(false);
        	cMainTest.registro.setVisible(false);
        	cMainTest.asociar.setVisible(false);
    	} else {
    		cMainTest.inicio.setVisible(false);
	        cMainTest.perfil.setVisible(false);
	        cMainTest.chat.setVisible(true);
	    	cMainTest.pulsometro.setVisible(false);
	    	cMainTest.alertas.setVisible(false);
    	}
		cMainTest.cc.cargarDatosChat();
    }
    
    @FXML
    private void startb4(ActionEvent event) {
    	cMainTest.burgerTask2.setRate(cMainTest.burgerTask2.getRate() * -1);
    	cMainTest.burgerTask2.play();
		cMainTest.drawer.close();
		cMainTest.drawer.setMouseTransparent(true);
    	if(ControladorLogin.usuarioActivo.tipo == 's') {
    		cMainTest.pacientes.setVisible(false);
        	cMainTest.notificaciones.setVisible(false);
        	cMainTest.chat.setVisible(false); 
        	cMainTest.perfil.setVisible(true);
        	cMainTest.pulsometro.setVisible(false);
        	cMainTest.alertas.setVisible(false);
        	cMainTest.gestion.setVisible(false);
        	cMainTest.menuGestion.setVisible(false);
        	cMainTest.registro.setVisible(false);
        	cMainTest.asociar.setVisible(false);
        	
    	} else {
    		cMainTest.inicio.setVisible(false);
	        cMainTest.perfil.setVisible(true);
	        cMainTest.chat.setVisible(false);
	    	cMainTest.pulsometro.setVisible(false);
	    	cMainTest.alertas.setVisible(false);
    	}
    	cMainTest.cpu.cargarPerfilUsuario(ControladorLogin.usuarioActivo);
    }
    
    @FXML
    private void startb5(ActionEvent event) {
    	cMainTest.burgerTask2.setRate(cMainTest.burgerTask2.getRate() * -1);
    	cMainTest.burgerTask2.play();
    	cMainTest.drawer.close();
    	cMainTest.drawer.setMouseTransparent(true);
    	if(ControladorLogin.usuarioActivo.tipo == 's') {
    		cMainTest.pacientes.setVisible(false);
        	cMainTest.notificaciones.setVisible(false);
        	cMainTest.chat.setVisible(false); 
        	cMainTest.perfil.setVisible(false);
        	cMainTest.gestion.setVisible(true);
        	cMainTest.menuGestion.setVisible(true);
        	cMainTest.registro.setVisible(false);
        	cMainTest.asociar.setVisible(false);
        	cMainTest.volverGestion.setVisible(false);
    	} else {
    		cMainTest.inicio.setVisible(false);
	        cMainTest.perfil.setVisible(false);
	        cMainTest.chat.setVisible(false);
	    	cMainTest.pulsometro.setVisible(false);
	    	cMainTest.alertas.setVisible(true);
	    	cMainTest.volverAlertas.setVisible(false);	
	    	cMainTest.cta.cargarHistorialAlertas((Paciente)ControladorLogin.usuarioActivo);
    	}
    }

}
