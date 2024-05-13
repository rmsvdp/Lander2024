


import java.util.Scanner;

public class Menu {

	private String[] opciones;
	private int eleccion=0;  // 0 sin elegir opcion
	private String titulo ="MENU DE OPCIONES";

	/**
	 * Contructor vacío
	 */
	public Menu() {
		
	}
	/**
	 * Contructor al que se le pasan las opciones
	 */
	public Menu(String[] opc) {
		
		int tam = opc.length;
		this.opciones = new String[tam];
		for (int j=0;j<tam;j++) {
		this.opciones[j]=opc[j];
		} // for
	}
	
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String[] getOpciones() {
		return opciones;
	}
	public void setOpciones(String[] opciones) {
		this.opciones = opciones;
	}
	public int getEleccion() {
		return eleccion;
	}
	public void setEleccion(int eleccion) {
		this.eleccion = eleccion;
	}
	
	public void mostrarMenu() {
		
		System.out.println();
		System.out.println(this.titulo);
		
		System.out.println("-".repeat(this.titulo.length()));
		for (int j=0;j<this.opciones.length;j++) {
			System.out.println(j+1 + ".- " +this.opciones[j]);
			} // for
		    System.out.println("0.- Salir\n");
	}
	
	public int eligeOpcion() {
		int opc=0;
		boolean valido=false;
		Scanner scr = new Scanner(System.in);
		while (!valido) {
			System.out.print("Elige opcion:" );
			opc = scr.nextInt();
			if ((opc>=0) && (opc <= opciones.length)){
			      valido = true;
				System.out.println();
			}
			else {
				System.out.println("Opcion no valida");
			}
		}
		return opc;
	}
	/**
	 * Muestra el literal correspondiente a la opcion elegida
	 * @param opc : opción que devuelve el metodo elige opcion
	 * @return
	 */
	public String muestraLiteral(int opc) {
		if (opc == 0) {
			return "Salir";
		}
		else {
			return this.opciones[opc-1];
		}
	}
	
}
