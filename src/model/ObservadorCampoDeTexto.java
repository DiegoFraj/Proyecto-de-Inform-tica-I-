package model;

// Interfaz que implementaran todos los controladores que utilicen la ventana Introducir Campo De Texto
// en alguna de sus funcionalidades para introducir algun texto
public interface ObservadorCampoDeTexto {

	// Este metodo sera llamado por el controlador de la ventana Introducir Campo De
	// Texto una vez se
	// haya introducido un texto y se haya aceptado
	// Cada controlador tendra su propia implementacion del metodo
	public void introducirCampoDeTexto(String texto);
}
