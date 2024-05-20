import java.text.DecimalFormat;
import java.util.Scanner;

import java.sql.*;

public class Lander2024 {

 
    public static void main(String[] args) throws Exception {
       
        Lander2024 l = new Lander2024();

        //l.runStructured();    // Versión clásica sin persistencia
        l.runOop();             // Versión oop con base de datos 
 
    }

    public void runOop(){
        System.out.println("Iniciando aplicación ...");
        Integer idUser = get_access();
        if (idUser!=0) {
        	
        	if (play(idUser)!=0) {
        		reset_account();
        	}
        }
        System.out.println("Terminando aplicación ...");
    }

    public int get_access() {
    	int _id = 1;					
    	
    	return _id;
    }
    
    public int play(Integer Usuario) {
    	int res = -1; // juego no terminado
		String[] opcs = {	"Elegir Modulo",
							"Elegir Escenario",
							"Iniciar Simulación",
							"Puntuaciones",
							"Creditos"};
		
		Menu mppal = new Menu(opcs);
		mppal.setTitulo("LANDER 2024");
		Boolean salir = false;
		Integer opcion = 0;
		Lander l=null;
		PerfilPot ppot =null;
		Escenario e=null;
		
		while (!salir) {
		//---------------------------------------- gestionar menú de opciones
		mppal.mostrarMenu();
		opcion = mppal.eligeOpcion();
		switch(opcion) {
			case 1:
				System.out.println("\nELIGE MODULO LUNAR");
				l = eligeLander();
				break;
			case 2:
				System.out.println("\nELIGE ESCENARIO");
				e = eligeEscenario();
				break;
			case 3:
				if ((l!= null) && (e!=null))
					runSim(Usuario,l,e);
				System.out.println("\nINICIAR SIMULACION");
				break;
			case 4:
				System.out.println("\nPUNTUACIONES");
				break;
			case 5:
				System.out.println("\nCREDITOS");
				break;
			case 0:
				salir = true;
				break;
		} // procesa opción de menú
		
		} // fin bucle principal
    	return res;
    }
    public void reset_account() {}

    
    public Lander eligeLander() {
    	
    	// Conecta a la base de datos
    	// Crea un menú con los lander disponibles
    	// Elige 1
    	// Salir
    	
    	return null;
    }
    
    public Escenario eligeEscenario() {
    	
    	// Conecta a la base de datos
    	// Crea un menú con los escenarios disponibles
    	// Elige 1
    	// Salir
    	
    	return null;
    }
    
    public void runSim(Integer user,Lander l, Escenario e) {
    	
    	Simulacion sim = new Simulacion(user,l,e);
    	sim.init();
    	while (sim.getSe().getDist()>0) {
    		sim.getSe().sim_frame();
    		sim.show();
    	}
    	
    }
 /*   
    public void runStructured(){
        double dist=0;                     // Distancia a la superficie m
        double acel=0;                     // aceleración m·s-2
        double vel=0;                      // velocidad en m· s-1
        double dist_ant=0;                 // variable auxiliar para guardar el último valor de distancia
        double vel_ant=0;                  // variable auxiliar para guardar el último valor de velocidad
        double impulso=0;                  // Impulso retrocohetes en m·s-2
        int nivel_impulso=0;               // Escala discreta de niveles de impulso
        double fuel_a_quemar;              // Fuel que se gasta en cada igninción
        double fuel_deposito;              // Fuel que lleva el lander
        int tiempo = 0;                    // Tiempo de simulación
        
        final int DT = 5;                  // Diferencial de tiempo que escojemos en segundos.
        final double GP =  1.62;           // Gravedad cuerpo : Luna = 1.62, Marte = 3.71 
        final double FUEL_INICIAL = 500.0; // Fuel de partida
        final double DIST_INICIAL = 3750.0;// Distancia de aproximación
        final double VEL_MAX = 20.0;       // Resistencia del tren de aterrrizaje m.s-1
        final double thrust_level[] =      // Impulso de los motores ( 10 niveles ) 
                    { 0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0,  8.50, 20.0};
        
        Scanner sc = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("+0000.00;-0000.00");

        // Inicializa la simulación
        fuel_deposito = FUEL_INICIAL;
        dist_ant= DIST_INICIAL;
        dist = dist_ant;
        fuel_a_quemar = 0;
        vel_ant=0;
        
        // bucle principal
        System.out.println("TIEMPO  DISTANCIA   VEL        FUEL      NIVEL IMPULSO"); // Tablero de indicadores
        System.out.println("------------------------------------------------------");
        while (dist>0){         // Comprobar si hemos llegado a la superficie
            // Muestra valores de los parámetros de la simulación, formateados en columnas
            System.out.printf("%03d    %s    %s    %04d        ",tiempo,df.format(dist),df.format(vel),(int) fuel_deposito);
            System.out.print("¿(0-9)? >");                      // Solicita nivel de impulso       
            nivel_impulso = sc.nextInt();                       // Lectura de teclado
            impulso = thrust_level[nivel_impulso];              // Elijo, en función del nivel el impulso instantáneo
            if (fuel_deposito == 0) nivel_impulso =0;           // Si no queda fuel , no tiene efecto la elección
            fuel_a_quemar = impulso * 2;                        // No es una simulación realista
            fuel_deposito = fuel_deposito - fuel_a_quemar;      // Actualizo la reserva de fuel
            if (fuel_deposito<0) fuel_deposito = 0;             // Eliminar incosistencias en el cálculo
            // Simulación física de la aceleración, velocidad y distancia en cada intervalo de tiempo
            acel =impulso-GP;                   
            vel = vel_ant+ acel*DT;
            dist = dist_ant+ vel*DT;
            tiempo = tiempo +DT;                                // Actualizo el tiempo
            vel_ant = vel;                                      // Actualizo variables temporales
            dist_ant = dist;

        }// wend
        // Comprobar la condiciones de aterrizaje y mostrar información sobre el mismo.
        if (Math.abs(vel)>VEL_MAX){
                System.out.println("\nHAS ESTRELLADO LA NAVE");
                System.out.println("------------------------------------------------");
                System.out.println("VELOCIDAD DE ENTRADA    : "+ df.format(vel) + " m/s");
                System.out.println("HAS HECHO UN CRATER DE  : "+ df.format(Math.abs(dist)) + " m");
                System.out.println("------------------------------------------------");
            }
        else {
                System.out.println("\nATERRIZAJE EXITOSO!!");
                System.out.println("------------------------------------------------");
                System.out.println("TIEMPO DE ATERRIZAJE : " + tiempo + " s");
                System.out.println("FUEL EN DEPOSITO     : " + fuel_deposito + " l");
                System.out.println("------------------------------------------------");

        }
        sc.close();

    }
*/
}
