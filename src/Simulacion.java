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
        System.out.println("TIEMPO  DISTANCIA   VEL        FUEL      NIVEL IMPULSO"); // Tablero de indicadores
        System.out.println("------------------------------------------------------");
		return true;
	}
	
	/**
	 * Muestra panel de indicadores
	 */
	public void muestraPanel() {
		/*
		 * 
		 DecimalFormat df = new DecimalFormat("+0000.00;-0000.00");
		 // Muestra valores de los parámetros de la simulación, formateados en columnas
         System.out.printf("%03d    %s    %s    %04d        ",tiempo,df.format(dist),df.format(vel),(int) fuel_deposito);
		 */
	}
	
	public int aplicaMotor() {
		
		int impulso=0;
		int nivel_impulso = 0;
		
		Scanner sc = new Scanner(System.in);
		
        System.out.print("¿(0-9)? >");                      // Solicita nivel de impulso       
		nivel_impulso = sc.nextInt();                       // Lectura de teclado
        /*
		impulso = lander.getPerfPot(nivel_impulso);         // Elijo, en función del nivel el impulso instantáneo
        if (fuel_deposito == 0) nivel_impulso =0;           // Si no queda fuel , no tiene efecto la elección
		// Consumo de combustible
        fuel_a_quemar = impulso * 2;                        // No es una simulación realista
        fuel_deposito = fuel_deposito - fuel_a_quemar;      // Actualizo la reserva de fuel
        if (fuel_deposito<0) fuel_deposito = 0;             // Eliminar incosistencias en el cálculo
        */
		return impulso;
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
