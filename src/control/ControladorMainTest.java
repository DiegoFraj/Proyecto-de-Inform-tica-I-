package control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Alerta;
import model.Paciente;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
public class ControladorMainTest implements Initializable {

    @FXML
    public JFXDrawer drawer;

    @FXML
    public JFXHamburger hamburger;
    public HamburgerBackArrowBasicTransition burgerTask2;

    @FXML
	public GridPane chat;
    @FXML
	public GridPane perfil;
    @FXML
	public GridPane notificaciones;
    @FXML
	public GridPane inicio;
    @FXML
	public GridPane pacientes;
    @FXML
	public GridPane alertas;
    @FXML
	public GridPane pulsometro;
    @FXML
   	public GridPane gestion;
    @FXML
   	public GridPane menuGestion;
    @FXML
   	public GridPane registro;
    @FXML
   	public GridPane asociar;
    @FXML
    public LineChart<String, Number> graficaPulsometro;

	@FXML
	private TableView<Alerta> tablaAlertas;
	@FXML
	private TableColumn<Alerta, String> alertColumn;
	@FXML
	private TableColumn<Alerta, String> fechaColumn;

	@FXML
	private TableView<Alerta> panelNotificaciones;
	@FXML
	private TableColumn<Alerta, String> alertaNotificacion;
	@FXML
	private TableColumn<Alerta, String> fechaNotificacion;
	
	@FXML
	private TableView<Paciente> tablaPacientes;
	@FXML
	private TableColumn<Paciente, String> apellidosColumn;
	@FXML
	private TableColumn<Paciente, String> nombreColumn;
	@FXML
	private TableColumn<Paciente, String> dniColumn;
	
	@FXML
	private JFXButton verPulsometro;
	@FXML
	private JFXButton verAlertas;
	@FXML
	private JFXButton borrarSeleccion;
	
	@FXML
	private Label alertasNombre;
	@FXML
	private Label pulsometroNombre;
	
	@FXML
	public JFXButton volverPerfil;
	@FXML
	public JFXButton volverPulsometro;
	@FXML
	public JFXButton volverAlertas;
	@FXML
	public JFXButton volverGestion;
	
	@FXML
	private ImageView fotoPrueba;
	
    @FXML private JFXListView<String> lvChatWindow;
    @FXML private JFXListView<String> lvHoraWindow;
    @FXML private JFXListView<Paciente> friendList;
    @FXML private JFXTextField tfUser;
    @FXML private JFXButton enviarMensaje;
	@FXML
	private Label dniField;
	@FXML
	private Label nombreField;
	@FXML
	private Label apellidoField;
	@FXML
	private Label edadField;
	@FXML
	private Label direccionField;
	@FXML
	private Label localidadField;
	@FXML
	private Label telefonoField;
	@FXML
	private Label emailField;
	@FXML
	private ImageView fotoImage;
	@FXML
	private ImageView lapizNombre;
	@FXML
	private ImageView lapizApellido;
	@FXML
	private ImageView lapizEdad;
	@FXML
	private ImageView lapizDireccion;
	@FXML
	private ImageView lapizLocalidad;
	@FXML
	private ImageView lapizTelefono;
	@FXML
	private ImageView lapizEmail;
	@FXML
	private ImageView lapizFoto;
	@FXML
	private Label bienvenido;
	@FXML
	private Label comoEditar;
	
	@FXML
	private JFXTextField dniRegistroField;
	@FXML
	private JFXTextField nombreRegistroField;
	@FXML
	private JFXTextField apellidoRegistroField;
	@FXML
	private JFXTextField edadRegistroField;
	@FXML
	private JFXTextField direccionRegistroField;
	@FXML
	private JFXTextField localidadRegistroField;
	@FXML
	private JFXTextField telefonoRegistroField;
	@FXML
	private JFXTextField emailRegistroField;
	@FXML
	private Button confirmRegistro;
	
	@FXML
	private JFXTextField dniAsociado;
    
    
    public ControladorChat cc;
    public ControladorGrafica cg;
    public ControladorPerfilUsuario cpu;
    public ControladorTablaAlertas cta;
    public ControladorTablaPacientes ctp;
    public ControladorNotificaciones cn;
    public ControladorRegistroUsuario cr;

    public boolean atrasPerfilNotificaciones;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		cc = new ControladorChat(this.lvChatWindow, this.lvHoraWindow, this.friendList, this.tfUser, this.enviarMensaje);
		cpu = new ControladorPerfilUsuario(this.dniField, this.nombreField, this.apellidoField, this.edadField, this.direccionField, this.localidadField, this.telefonoField, this.emailField, this.fotoImage, this.lapizNombre, this.lapizApellido, this.lapizEdad, this.lapizDireccion, this.lapizLocalidad, this.lapizTelefono, this.lapizEmail, this.lapizFoto, this.bienvenido, this.comoEditar, this.volverPerfil);
    	cg = new ControladorGrafica(this.graficaPulsometro, this.pulsometroNombre);
        cta = new ControladorTablaAlertas(this.tablaAlertas, this.alertColumn, this.fechaColumn, this.alertasNombre);
        cn = new ControladorNotificaciones(this, this.panelNotificaciones, this.alertaNotificacion, this.fechaNotificacion);
        ctp = new ControladorTablaPacientes(this, this.verAlertas, this.verPulsometro, this.borrarSeleccion, this.tablaPacientes, this.nombreColumn, this.apellidosColumn, this.dniColumn);
        cr = new ControladorRegistroUsuario(this.dniRegistroField, this.nombreRegistroField, this.apellidoRegistroField, this.edadRegistroField, this.direccionRegistroField, this.localidadRegistroField, this.telefonoRegistroField, this.emailRegistroField, this.confirmRegistro);
		
		try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DrawerContent.fxml"));
			loader.setController(new ControladorDrawer(this));
            VBox box = loader.load();
            drawer.setSidePane(box);				
			drawer.setMouseTransparent(true);

            burgerTask2 = new HamburgerBackArrowBasicTransition(hamburger);
			burgerTask2.setRate(-1);
			hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
				burgerTask2.setRate(burgerTask2.getRate() * -1);
				burgerTask2.play();
				
				if (drawer.isClosed()) {
					drawer.open();
					drawer.setMouseTransparent(false);
			}
				else {
					drawer.close();
					drawer.setMouseTransparent(true);
				}

				
			});

		} catch (IOException e1) {
			e1.printStackTrace();
		}        
	}
	

    public void mostrarPerfilSeleccionado(Paciente p) {
    	notificaciones.setVisible(false);
    	pacientes.setVisible(false);
    	cpu.cargarPerfilUsuario(p);
    	perfil.setVisible(true);
    }
    public void mostrarPulsometroPaciente(Paciente p) {
    	cg.cargarPulsometro(p);
    	cg.cargarPulsometro(p);
    	
    	if(p.estadisticas.ppm[0] != 0) {
        	pacientes.setVisible(false);
    		pulsometro.setVisible(true);
    	}
    }
    public void mostrarAlertasPaciente(Paciente p) {
    	pacientes.setVisible(false);
    	cta.cargarHistorialAlertas(p);
    	alertas.setVisible(true);
    }
    public void accederPaciente(Paciente p) {
    	pacientes.setVisible(false);
    	cpu.cargarPerfilUsuario(p);
    	perfil.setVisible(true);
    }
    @FXML
    public void registrar(ActionEvent e) {
    	menuGestion.setVisible(false);
    	registro.setVisible(true);
    	asociar.setVisible(false);
    	volverGestion.setVisible(true);
    }
    @FXML
    public void asociar(ActionEvent e) {
    	menuGestion.setVisible(false);
    	registro.setVisible(false);
    	asociar.setVisible(true);
    	volverGestion.setVisible(true);
    }
    @FXML
    public void confirmAsociar(ActionEvent e) {
    	ctp.introducirCampoDeTexto(dniAsociado.getText());
    }
    @FXML
    public void atrasPerfil(ActionEvent e) {
    	perfil.setVisible(false);
    	if(atrasPerfilNotificaciones) {
    		cn.cargarNotificaciones();
    		notificaciones.setVisible(true); 
    	} else {
    		ctp.cargarTablaPacientes();
    		pacientes.setVisible(true);
    	}
    }
    @FXML
    public void atrasPulsometro(ActionEvent e) {
    	pulsometro.setVisible(false);
    	ctp.cargarTablaPacientes();
		pacientes.setVisible(true);   
	}
    @FXML
    public void atrasAlertas(ActionEvent e) {
    	alertas.setVisible(false);
    	ctp.cargarTablaPacientes();
		pacientes.setVisible(true);     }
    @FXML
    public void atrasGestion(ActionEvent e) {
    	registro.setVisible(false);
    	asociar.setVisible(false);
    	menuGestion.setVisible(true);
    	volverGestion.setVisible(false);
    }	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}