import java.util.ArrayList;

public class Escenario {

	private String nombre;		// nombre del planeta o satélite
	private Double g;			// gravedad del escenario
	private Double ve;			// velocidad de entrada
	private Double he;			// Distancia de aproximación
	
	
	
	public Escenario(String nombre, Double g, Double ve, Double he) {
		super();
		this.nombre = nombre;
		this.g = g;
		this.ve = ve;
		this.he = he;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getG() {
		return g;
	}
	public void setG(Double g) {
		this.g = g;
	}
	public Double getVe() {
		return ve;
	}
	public void setVe(Double ve) {
		this.ve = ve;
	}
	public Double getHe() {
		return he;
	}
	public void setHe(Double he) {
		this.he = he;
	}
	
    public String toString() {
    	return nombre + "  (G)  "+ g + "  (Ve)  "+ ve+ "  (He)  "+ he;
    }
	
}
