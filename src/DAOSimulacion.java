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
			
			if (salva_objeto(s,id_player,id_lander,id_escenario)) {// Salva Simulación
				
				if (salva_datos(s)) {// Salva Datos simulacion
					salva_puntuacion(s,id_player);// Salva Puntuación
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
	
	public boolean salva_objeto(Simulacion _s,Integer id_player,Integer id_lander,Integer id_escenario) {
		boolean result = false;
		try {
			Statement st = _c.createStatement();
			long millis=System.currentTimeMillis(); 
			java.sql.Date _now = new java.sql.Date(millis);
			String ssql = "INSERT (id_usuario,id_lander,id_escenario,fecha) INTO simulacion values(";

			ssql = ssql + id_player+ "," + id_lander+ "," +id_escenario;
			ssql = ssql + "," + _now + ")";
			result = st.execute(ssql);
			if (result) {
				// Rcuperar el Id generado por el motor de la base de datos
				ssql = "SELECT id_sim FROM simulacion WHERE id_usuario="+id_player + "AND " +
				       "id_lander = " + id_lander + " AND id_escenario= "+ id_escenario + " AND " +
					   "fecha = " + _now;	
				ResultSet rs = st.executeQuery(ssql);
				rs.next();
				_s.setId(rs.getInt(1)); // Ahora el objeto tiene la clave de persistencia en base de datos
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
	public boolean salva_datos(Simulacion _s) {
		boolean result = false;
		try {
			Statement st = _c.createStatement();
			String ssql ="";
			// Volcamos estructura de memoria en base de datos
			for (DatosSim ds : _s.getSimData()) {
					ssql = "INSERT (id_sim,tiempo,vel,fuel,dist) INTO datos_sim values(";
					ssql = ssql + _s.getId()+ "," + ds.getAcel()+ "," +ds.getVel()+","+ds.getImpulso()+","+ds.getFuel()+","+ds.getTiempo();
					ssql = ssql + ")";
					result = st.execute(ssql);
				if (!result) break;  // En cuanto se produzca un error, abandonamos
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		
		return result;
	}
	
	public boolean salva_puntuacion(Simulacion _s,Integer id_player) {
		boolean result = false;
		
		try {
			Statement st = _c.createStatement();
			String ssql ="";
			long millis=System.currentTimeMillis(); 
			java.sql.Date _date = new java.sql.Date(millis);
					ssql = "INSERT (id_usuario,id_simulacion,tiempo,fuel,fecha) INTO puntuacion values(";
					ssql = ssql + _s.getId()+ "," + _s.getSe().getTiempo()+ "," +_s.getLander().getFuel_deposito()+","
					+_date + ")";
					result = st.execute(ssql);
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		
		
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
