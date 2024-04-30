import java.time.LocalDateTime;
import java.util.ArrayList;

public class Simulacion {

	
	private LocalDateTime timestamp;		// Fecha de la simulaci�n
	private SimEngine se;					// Motor de simulaci�n que se utiliza
	private Eagle lander;					// M�dulo que se utiliza
	private Player user;					// Jugador que la efect�a
	private Escenario planet;				//	Escenario de la simulaci�n
	private ArrayList<DatosSim> simData = new ArrayList<DatosSim>();	// Datos de la simulaci�n

}
