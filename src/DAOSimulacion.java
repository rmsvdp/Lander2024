import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.Date;

public class DAOSimulacion {

	
	public static Connection _c;
	public static String _MODO;
	public Integer curr_id_sim; // identificador de la simulación sobre la que se está trabajando
	

	public DAOSimulacion(String Modo) {
		_MODO=Modo;
		DAOMySql dms = new DAOMySql(Modo);
		if (dms.c!=null) {
			_c = dms.c;
		}
		else _c= null;
	}


	
	/**
	 * Almacena en base de datos , la simulación y sus datos
	 * así como la puntuación si es preciso en la tabla de puntaciones
	 * @param s
	 * @return
	 */
	public boolean saveSimulacion(Simulacion s) {
		
		// Hacerlo transaccional, mediante el resultado
		Player p = s.getUser();
		Lander l = s.getLander();
		Escenario es = s.getPlanet();
		DAOPlayer dp = new DAOPlayer(_MODO);
		DAOLander dl = new DAOLander(_MODO);
		DAOEscenario de = new DAOEscenario(_MODO);
		
		try {
			Integer id_player =dp.getIdbyPlayer(p);
			Integer id_lander = dl.getIdbyLander(l);
			Integer id_escenario = de.getIdbyEscenario(es);
			
			if (salva_objeto(id_player,id_lander,id_escenario)) {// Salva Simulación
				
				if (salva_datos(curr_id_sim)) {// Salva Datos simulacion
					salva_puntuacion(curr_id_sim);// Salva Puntuación
				}
			}
			
			de._c.close();
			dl._c.close();
			dp._c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		

		
		return true;
	}
	
	public boolean salva_objeto(Integer id_player,Integer id_lander,Integer id_escenario) {
		boolean result = false;
		try {
			Statement st = _c.createStatement();
			LocalDateTime _now = LocalDateTime.now();
			String ssql = "INSERT (id_usuario,id_lander,id_escenario,fecha) INTO simulacion values(";

			ssql = ssql + id_player+ "," + id_lander+ "," +id_escenario;
			ssql = ssql + "," + _now + ")";
			result = st.execute(ssql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	public boolean salva_datos(Integer id_simulacion) {
		boolean result = false;
		return result;
	}
	
	public boolean salva_puntuacion(Integer id_simulacion) {
		boolean result = false;
		return result;
	}
	
	/** 
	 * Trae de la base de datos, todas las 10 últimas simulaciones
	 * @return
	 */
	public ArrayList<Simulacion> getSimulaciones(Integer idUsuario){
		
		return null;
	}
	/**
	 * Recupera los datos de una simulación
	 * @return
	 */
	public ArrayList<DatosSim> getDatos(){
		
		return null;
	}
}
