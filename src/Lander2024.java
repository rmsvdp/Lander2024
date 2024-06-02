import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import java.sql.*;

public class Lander2024 {

	public final String MODO = "local";
    public static void main(String[] args) throws Exception {
       
        Lander2024 l = new Lander2024();
        //l.runStructured();    // Versión clásica sin persistencia
        l.runOop();             // Versión oop con base de datos 
 
    }

    public void runOop(){
        System.out.println("Iniciando aplicación ...");
        Player itUser = get_access();
        if (itUser!=null) {       	
        	if (play(itUser)!=0) {
        		reset_account();
        	}
        }
        System.out.println("Terminando aplicación ...");
    }

    /**
     * Concede acceso a la plataforma
     * @return
     */
    // TODO pedir usuario / contraseña y crear objeto player si OK
    public Player get_access() {
    	int _id ;	
    	Player p=null;
    	
    	System.out.println("Accediendo a la plataforma ...");
    	DAOMySql dms = new DAOMySql(MODO);
    	if (dms.c == null) {
    		System.out.println("Plataforma no disponible!");
    		p=null;
    	}
    	else {
    		Scanner sc = new Scanner(System.in);
    		System.out.print("Introduce nombre de usuario: ");
    		String _nick = sc.nextLine();
    		System.out.print("Introduce contraseña: ");
    		String _pass = sc.nextLine();
    		DAOPlayer dp = new DAOPlayer(MODO);
    		p = dp.getPlayerbyNickPass(_nick,_pass);
    		if (p!=null) {
        		System.out.print ("Acceso concedido! ");
        		System.out.println(p);
        		try {
    				dms.c.close();
    			} catch (SQLException e) {
    				e.printStackTrace();
    				p = null;
    			}
    		}
    		else {
    			System.out.println ("Credenciales incorrectas! ");
    			p=null;
    			}
    	}
    	return p;
    } //Player get_access() 
    
    public int play(Player Usuario) {
    	int res = -1; // juego no terminado
		String[] opcs = {	"Elegir Modulo",
							"Elegir Escenario",
							"Iniciar Simulación",
							"Puntuaciones",
							"Ultimas 5 simulaciones"};
		
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
				//System.out.println("\nELIGE MODULO LUNAR");
				l = eligeLander();
				if (l!=null)
					System.out.println("MODULO --> "+l);
				break;
			case 2:
				//System.out.println("\nELIGE ESCENARIO");
				e = eligeEscenario();
				if (e!=null)
					System.out.println("ESCENARIO --> "+e);
				break;
			case 3:
				if ((l!= null) && (e!=null))
					runSim(Usuario,l,e);
				else
					System.out.println("Elija primero módulo y escenario válidos");
				//System.out.println("\nINICIAR SIMULACION");
				break;
			case 4:
				System.out.println("\nPUNTUACIONES");
				System.out.println("\n** No implementado");
				break;
			case 5:
				System.out.println("\n 5 Últimas simulaciones con éxito");
				System.out.println("-----------------------------------");
				show_simulations(Usuario);
				break;
			case 0:
				salir = true;
				break;
		} // procesa opción de menú
		
		} // fin bucle principal
    	return res;
    }
    
    public void show_simulations(Player _p) {
    	
    	DAOSimulacion ds = new DAOSimulacion(MODO);
    	ArrayList<String> lastSim;
    	ArrayList<DatosSim> datosSimElegidos;
    	lastSim = ds.getSimulaciones(_p);
    	Integer id_simulacion = 0;
    	String [] opciones = new String [lastSim.size()];	// Tamaño de la array para el menu
    	Integer [] ids = new Integer[lastSim.size()]; 
		int i = 0;
		for(String sim : lastSim){ // Guardar los nombre y cantidad del depósito de los modulos en la array para el menu
			String reg[]=sim.split("#");
			opciones[i] = reg[1]; // características
			ids[i]=Integer.parseInt(reg[0]); // id_sim
			i++;
		}
		Menu menu = new Menu(opciones);
		menu.setTitulo("FECHA / ESCENARIO / MODULO ");

			menu.mostrarMenu();
			int opt = menu.eligeOpcion();
			if(opt !=0){
				id_simulacion= ids[opt-1];
			}
			else {
				System.out.println("Elección cancelada");
				id_simulacion=0;
			}
		if (id_simulacion!=0) {
			
			datosSimElegidos = ds.getDatosSimbyId(id_simulacion);
	        System.out.println("TIEMPO  DISTANCIA   VEL        FUEL    "); // Tablero de indicadores
	        System.out.println("---------------------------------------");
			DecimalFormat df = new DecimalFormat("+0000.00;-0000.00");
	        for (DatosSim _datos : datosSimElegidos) {
				
				 // Muestra valores de los parámetros de la simulación, formateados en columnas
		         System.out.printf("%03d    %s    %s    %04d        ",
		        		           _datos.getTiempo(),df.format(_datos.getDist()),df.format(_datos.getVel()),_datos.getFuel()
		        		          );
			} //recorre simulacion
			
		} // Si elegida una 
		
    }  //show_simulations(Player _p)
    
    
    public void reset_account() {
    	
    	
    }

    
    public Lander eligeLander() {
    	
    	boolean boo = false; 
    	// Conecta a la base de datos
    	DAOLander dl = new DAOLander(MODO);
		Lander myLander = null;
    	// TODO Crea un menú con los lander disponibles
		ArrayList <Lander> Arraylander = dl.getLanders();
		String [] opciones = new String [Arraylander.size()];// Tamaño de la array para el menu
		int i = 0;
		for(Lander lan : Arraylander){ // Guardar los nombre y cantidad del depósito de los modulos en la array para el menu
			opciones[i] = "Modulo: "+lan.getNombre() + ", Depósito de combustible: "+ lan.getFuel_deposito();
			i++;
		}
		Menu menu = new Menu(opciones);
    	try {
			while (!boo) {
				menu.mostrarMenu();
				int opt = menu.eligeOpcion();
				if(opt <= Arraylander.size() && opt > 0){
					myLander = dl.getLanders().get(opt-1);
					boo = true;// pongo que salga automaticamente al elegir
				}
				else if (opt == 0){
					boo = true;
					System.out.println("Salir");
				}
				else{
					System.out.println("La opción no existe");
				}
				
			}
			dl._c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return myLander;
    }
    
    public Escenario eligeEscenario(){ // Devuelve un escenario
		
		Escenario escElegido = null;
		
		DAOEscenario de = new DAOEscenario("local");		
		
		ArrayList<Escenario> escPosibles = de.getEscenarios();
		int tam = escPosibles.size();
		
		Scanner teclado = new Scanner(System.in);	
		
		String[] opcEsc = new String[tam]; //Formamos las opciones
		
			for(int i=0; i<opcEsc.length; i++){
				opcEsc[i] = escPosibles.get(i).getNombre() + " con una gravedad de "+
							escPosibles.get(i).getG()+" g";
			}
		
		Menu mesc = new Menu(opcEsc);
		mesc.setTitulo("ESCENARIOS");
		Integer opEsc = 0;
		boolean salirEsc = false;
		
			while(!salirEsc){
				
				mesc.mostrarMenu();
				opEsc = mesc.eligeOpcion();
				String conf=" ";
				
					if(opEsc>=0 && opEsc<=opcEsc.length){
						
						if(opEsc==0){
							escElegido=null;
							salirEsc=true;							
						}
						
						else{
							escElegido = escPosibles.get(opEsc-1);
							System.out.println("El escenario elegido es: "+escElegido.getNombre()+
										" con una gravedad de "+escElegido.getG()+" g");
						
							System.out.println("¿Confirmación? "+"\t"+"S: Aceptar"); //Pedimos confirmación por si queremos cambiar de escenario
							System.out.println("               "+"\t"+"Cualquier otra tecla: Cancelar");
							
							conf = teclado.next();
								
								if(conf.equalsIgnoreCase("S")){
								salirEsc=true;
								
								}
						}
					}
			}
						
			return escElegido;
			
	}
    
    public void runSim(Player user,Lander l, Escenario e) {
    	
    	Double altura = 0.0;
    	Simulacion sim = new Simulacion(user,l,e);
		sim.addSimData(); 			// Registra los datos de inicio de la simulación
    	do {						// bucle principal de la simulación
    		
    		sim.muestraPanel();					// Mostrar los resultados
    		sim.aplicaMotor(l);					// Acciona motores
    		sim.getSe().sim_frame();			// Calcula física
    		sim.addSimData(); 					// Registra los datos generados
    		altura = sim.getSe().getDist();		// Comprobar altura
    	}
    	while (altura>0 && !(sim.__break)) ;
    	 						// Resultado Final de la simulación
    	if (sim.show_result())
    		sim.saveSim(MODO);						// Salva resultado en BBDD
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
