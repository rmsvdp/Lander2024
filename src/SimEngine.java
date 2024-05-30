
public class SimEngine {

	
	private Double dist=0.0;                     // Distancia a la superficie m
	private Double acel=0.0;                     // aceleración m·s-2
	private Double vel=0.0;                      // velocidad en m· s-1
	private Double dist_ant;                 // variable auxiliar para guardar el último valor de distancia
	private Double vel_ant;                  // variable auxiliar para guardar el último valor de velocidad
	private Double impulso=0.0;                  // Impulso retrocohetes en m·s-2
	private int tiempo = 0;                    // Tiempo de simulación   
    private Double G;					// Gravedad a aplicar en la simulación
    private Integer dt;						// Diferencial de tiempo de la simulación
    final Integer DT = 5;					// Diferencial de tiempo que escojemos en segundos.
    
    /*
                     
    final double GMOON =  1.62;        // Gravedad cuerpo : Luna = 1.62
    final double GMARS =  3.71;        // Gravedad cuerpo : Marte = 3.71 
    */
	   
    public SimEngine() {}
    


	public SimEngine(double dist_ant, double vel_ant,  double g) {
		super();
		this.dist_ant = dist_ant;		// Distancia de entrada a la simulación
		this.vel_ant = vel_ant;			// velocidad de entrada
		G = g;							// G del planeta / satélite
		this.dt = DT;					// Valor del dt
	}
	public Double getDistAnt() 				{	return dist_ant;		}
	public Double getDist() 				{	return dist;			}
	public void setDist(double dist) 		{	this.dist = dist;		}
	public Double getAcel() 				{	return acel;			}
	public void setAcel(double acel) 		{	this.acel = acel;		}
	public Double getVel() 					{	return vel;				}
	public void setVel(double vel) 			{	this.vel = vel;			}
	public Integer getTiempo() 				{	return tiempo;			}
	public void setTiempo(int tiempo) 		{	this.tiempo = tiempo;	}
	public Double getG() 					{	return G;				}
	public void setG(double g) 				{	G = g;					}
	public Integer getDt() 					{	return dt;				}
	public void setDt(int dt) 				{	this.dt = dt;			}
	public Double getImpulso() 				{	return impulso;			}
	public void setImpulso(double impulso) 	{	this.impulso = impulso;	}
	
	// guarda los datos del instante actual
	// no se tiene fuel,por lo que se inicializa con valor 0.0
	public DatosSim getSimData() {
		DatosSim ds = new DatosSim(dist,acel,vel,impulso,0.0,tiempo); // No tenemos el fuel desde aquí
		return ds;
	}
	
    // Simulación física de la aceleración, velocidad y distancia en cada intervalo de tiempo
    // El valor del impulso se establece desde el objeto que es propietario de este motor
    // de simulación (la simulación lanzada por el usuario).
    // desde el metodo aplica_motor()
    
	public void sim_frame() {
		
        acel =(Double) (impulso-G);                   
        vel = vel_ant+ (acel* Double.valueOf(dt));
        dist = dist_ant+  (vel* Double.valueOf(dt));
        tiempo = tiempo +dt;                                // Actualizo el tiempo
        vel_ant = vel;                                      // Actualizo variables temporales
        dist_ant = dist;
	}
	
}
