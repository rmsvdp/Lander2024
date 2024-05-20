
public class SimEngine {

	
	private double dist=0;                     // Distancia a la superficie m
	private double acel=0;                     // aceleración m·s-2
	private double vel=0;                      // velocidad en m· s-1
	private double dist_ant=0;                 // variable auxiliar para guardar el último valor de distancia
	private double vel_ant=0;                  // variable auxiliar para guardar el último valor de velocidad
	private double impulso=0;                  // Impulso retrocohetes en m·s-2
	private int tiempo = 0;                    // Tiempo de simulación
    
    private double G;					// Gravedad a aplicar en la simulación
    private int dt;						// Diferencial de tiempo de la simulación
    
    
    /*
    final int DT = 5;                  // Diferencial de tiempo que escojemos en segundos.
    final double GMOON =  1.62;        // Gravedad cuerpo : Luna = 1.62
    final double GMARS =  3.71;        // Gravedad cuerpo : Marte = 3.71 
    */
	   
    public SimEngine() {}
    
	public void sim_frame() {
		
        // Simulación física de la aceleración, velocidad y distancia en cada intervalo de tiempo
        acel =impulso-G;                   
        vel = vel_ant+ acel*dt;
        dist = dist_ant+ vel*dt;
        tiempo = tiempo +dt;                                // Actualizo el tiempo
        vel_ant = vel;                                      // Actualizo variables temporales
        dist_ant = dist;
	}

	public SimEngine(double dist_ant, double vel_ant,  double g, int dt) {
		super();
		this.dist_ant = dist_ant;		// Distancia de entrada a la simulación
		this.vel_ant = vel_ant;			// velocidad de entrada
		G = g;							// G del planeta / satélite
		this.dt = dt;					// Valor del dt
	}

	public double getDist() 			{	return dist;	}
	public void setDist(double dist) 	{	this.dist = dist;	}
	public double getAcel() 			{	return acel;	}
	public void setAcel(double acel) 	{	this.acel = acel;	}
	public double getVel() 				{	return vel;	}
	public void setVel(double vel) 		{	this.vel = vel;	}
	public int getTiempo() 				{	return tiempo;	}
	public void setTiempo(int tiempo) 	{	this.tiempo = tiempo;	}
	public double getG() 				{	return G;	}
	public void setG(double g) 			{	G = g;	}
	public int getDt() 					{	return dt;	}
	public void setDt(int dt) 			{	this.dt = dt;	}
	
	
}
