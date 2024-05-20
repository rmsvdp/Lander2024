import java.time.LocalDateTime;
import java.util.ArrayList;

public class Simulacion {

	
	private LocalDateTime timestamp;		// Fecha de la simulación
	private SimEngine se;					// Motor de simulación que se utiliza
	private Lander lander;					// Módulo que se utiliza
	private Player user;					// Jugador que la efectúa
	private Escenario planet;				//	Escenario de la simulación
	private ArrayList<DatosSim> simData = new ArrayList<DatosSim>();	// Datos de la simulación
	
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

	// Métodos
	
	/**
	 * Inicializa la simulación
	 * @return
	 */
	public boolean init() {
		
		return true;
	}
	
	/**
	 * Salva los datos de simulación en base de datos
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
