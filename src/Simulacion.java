import java.time.LocalDateTime;
import java.util.ArrayList;

public class Simulacion {

	
	private LocalDateTime timestamp;		// Fecha de la simulaci�n
	private SimEngine se;					// Motor de simulaci�n que se utiliza
	private Lander lander;					// M�dulo que se utiliza
	private Player user;					// Jugador que la efect�a
	private Escenario planet;				//	Escenario de la simulaci�n
	private ArrayList<DatosSim> simData = new ArrayList<DatosSim>();	// Datos de la simulaci�n
	
	// Constructor
	
	
	public Simulacion(Integer user, Lander lander, Escenario planet) {
		super();
		this.lander = lander;
		this.user = new Player(user);
		this.planet = planet;
		this.timestamp = LocalDateTime.now();
		this.se = new SimEngine();
		
	}
	
	
	// Getter y Setter
	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public SimEngine getSe() {
		return se;
	}
	public void setSe(SimEngine se) {
		this.se = se;
	}
	public Lander getLander() {
		return lander;
	}
	public void setLander(Lander lander) {
		this.lander = lander;
	}
	public Player getUser() {
		return user;
	}
	public void setUser(Player user) {
		this.user = user;
	}
	public Escenario getPlanet() {
		return planet;
	}
	public void setPlanet(Escenario planet) {
		this.planet = planet;
	}
	public ArrayList<DatosSim> getSimData() {
		return simData;
	}
	public void setSimData(ArrayList<DatosSim> simData) {
		this.simData = simData;
	}

	// M�todos
	
	/**
	 * Inicializa la simulaci�n
	 * @return
	 */
	public boolean init() {
		
		return true;
	}
	
	/**
	 * Salva los datos de simulaci�n en base de datos
	 * @return
	 */
	public boolean saveSim() {
		
		return true;
	}
	
	/**
	 * Registra el marcador obtenido en la base de datos
	 * Es llamado de forma interna por saveSim()
	 * @return
	 */
	private boolean saveScore() {
		
		return true;
	}
	
	public void show() {
		
		// Salida por pantalla;
	}
}
