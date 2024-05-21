import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

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
	public LocalDateTime getTimestamp() {		return timestamp;	}
	public void setTimestamp(LocalDateTime timestamp) {		this.timestamp = timestamp;	}
	public SimEngine getSe() {		return se;	}
	public void setSe(SimEngine se) {		this.se = se;	}
	public Lander getLander() {		return lander;	}
	public void setLander(Lander lander) {		this.lander = lander;	}
	public Player getUser() {		return user;	}
	public void setUser(Player user) {		this.user = user;	}
	public Escenario getPlanet() {		return planet;	}
	public void setPlanet(Escenario planet) {		this.planet = planet;	}
	public ArrayList<DatosSim> getSimData() {		return simData;	}
	public void setSimData(ArrayList<DatosSim> simData) {		this.simData = simData;	}

	// Métodos
	
	/**
	 * Inicializa la simulación
	 * @return
	 */
	public void init() {
		// Valores iniciales
		this.se = new SimEngine();		// Valores del motor inicializados
		this.se.setG(planet.getG());			// Fijar gravedad
		this.se.setVel(planet.getVe());		// fijar velocidad de entrada
		this.se.setDist(planet.getHe());		// fijar punto de entrada (altitud)
	}
	
	/**
	 * Muestra panel de indicadores
	 */
	public void muestraPanel() {
		Double vel = se.getVel();
		Double dist =se.getDist();
		Integer tiempo = se.getTiempo();
		Double fuel_deposito = lander.getFuel_deposito();
		DecimalFormat df = new DecimalFormat("+0000.00;-0000.00");
		
		if (tiempo == 0) { // inicio de la simulación 
	        System.out.println("TIEMPO  DISTANCIA   VEL        FUEL      NIVEL IMPULSO"); // Tablero de indicadores
	        System.out.println("------------------------------------------------------");
		}
		 // Muestra valores de los parámetros de la simulación, formateados en columnas
         System.out.printf("%03d    %s    %s    %04d        ",
        		           tiempo,df.format(dist),df.format(vel),fuel_deposito.intValue()
        		          );
	}
	
	public void aplicaMotor(Lander l) {
		
		Double impulso=0.0;
		Integer nivel_impulso = 0;
		Scanner sc = new Scanner(System.in);
        System.out.print("¿(0-9)? >");                      	// Solicita nivel de impulso       
		nivel_impulso = sc.nextInt();                       	// Lectura de teclado
		impulso = lander.getPerfPot(nivel_impulso);        		// Elijo, en función del nivel el impulso instantáneo
		if (lander.getFuel_deposito() == 0) nivel_impulso =0;   // Si no queda fuel , no tiene efecto la elección
		// Consumo de combustible
        lander.setFuel_a_quemar( impulso * 2);      			// No es una simulación realista
        lander.setFuel_deposito(lander.getFuel_deposito() - lander.getFuel_a_quemar());      // Actualizo la reserva de fuel
		if (lander.getFuel_deposito()<0) 						// Eliminar incosistencias en el cálculo
				lander.setFuel_deposito(0);             	
	}
	
	public void show_result() {
		
        // Comprobar la condiciones de aterrizaje y mostrar información sobre el mismo.
		Double vel_fin = se.getVel();
		Double dist_fin =se.getDist();
		Integer tiempo = se.getTiempo();
		Double fuel_deposito = lander.getFuel_deposito();
		DecimalFormat df = new DecimalFormat("+0000.00;-0000.00");
		
        if (Math.abs(se.getVel())>lander.getRes_tren()){
                System.out.println("\nHAS ESTRELLADO LA NAVE");
                System.out.println("------------------------------------------------");
                System.out.println("VELOCIDAD DE ENTRADA    : "+ df.format(vel_fin) + " m/s");
                System.out.println("HAS HECHO UN CRATER DE  : "+ df.format(Math.abs(dist_fin)) + " m");
                System.out.println("------------------------------------------------");
            }
        else {
                System.out.println("\nATERRIZAJE EXITOSO!!");
                System.out.println("------------------------------------------------");
                System.out.println("TIEMPO DE ATERRIZAJE : " + tiempo + " s");
                System.out.println("FUEL EN DEPOSITO     : " + fuel_deposito + " l");
                System.out.println("------------------------------------------------");
        }
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
