
public class Lander {

	// Artibutos del Lander
	private String nombre;
	private double impulso=0;                  // Impulso retrocohetes en m·s-2
    private int nivel_impulso=0;               // Escala discreta de niveles de impulso
    private double fuel_a_quemar;              // Fuel que se gasta en cada igninción
    private double fuel_deposito;              // Fuel que lleva el lander
    private int tiempo = 0;                    // Tiempo de simulación
    private double res_tren;				   // Resistencia del tren de aterrizaje
    private PerfilPot perfPot;				   // Nivel de potencia configurado

    // Constantes
    
    private final double VEL_MAX = 20.0;       // Resistencia del tren de aterrrizaje m.s-1
    private final double thrust_level[] =      // Impulso de los motores ( 10 niveles ) 
                { 0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0,  8.50, 20.0};
	
    public Lander(String nombre, double fuel_deposito, double res_tren) {
		super();
		this.nombre = nombre;
		this.fuel_deposito = fuel_deposito;
		this.res_tren = res_tren;
		this.perfPot = null;
	}
	
    public String getNombre() {		return nombre; }
	public void setNombre(String nombre) {	this.nombre = nombre;	}
	public double getImpulso() {return impulso;	}
	public void setImpulso(double impulso) {this.impulso = impulso;	}
	public int getNivel_impulso() {	return nivel_impulso;	}
	public void setNivel_impulso(int nivel_impulso) {	this.nivel_impulso = nivel_impulso;	}
	public double getFuel_a_quemar() {return fuel_a_quemar;	}
	public void setFuel_a_quemar(double fuel_a_quemar) {this.fuel_a_quemar = fuel_a_quemar;	}
	public double getFuel_deposito() {	return fuel_deposito;	}
	public void setFuel_deposito(double fuel_deposito) {this.fuel_deposito = fuel_deposito;	}
	public int getTiempo() {return tiempo;	}
	public void setTiempo(int tiempo) {	this.tiempo = tiempo;	}
	public double getPerfPot(int nivel) { 	return perfPot.potencia[nivel];   }
    public void setPerfPot(PerfilPot p) {  	this.perfPot = p;   }

	public double getRes_tren() {	return res_tren;	}
    
    // Auxilares ( por decidir si se usan)
    /*
    private double dist=0;                     // Distancia a la superficie m
    private double acel=0;                     // aceleración m·s-2
    private double vel=0;                      // velocidad en m· s-1
    private double dist_ant=0;                 // variable auxiliar para guardar el último valor de distancia
    private double vel_ant=0;                  // variable auxiliar para guardar el último valor de velocidad
    */


} // Lander
