import java.time.LocalDateTime;
import java.util.ArrayList;

public class Simulacion {

	
	private LocalDateTime timestamp;		// Fecha de la simulación
	private SimEngine se;					// Motor de simulación que se utiliza
	private Eagle lander;					// Módulo que se utiliza
	private Player user;					// Jugador que la efectúa
	private Escenario planet;				//	Escenario de la simulación
	private ArrayList<DatosSim> simData = new ArrayList<DatosSim>();	// Datos de la simulación

}
