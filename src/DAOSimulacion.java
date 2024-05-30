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
		Integer id_player = s.getUser().getId();
		Integer id_lander = s.getLander().getId();
		Integer id_escenario = s.getPlanet().getId();
		Boolean result = true;
		
			if (salva_objeto(s,id_player,id_lander,id_escenario)) {// Salva Simulación
				System.out.println("sim objeto");
				if (salva_datos(s)) {// Salva Datos simulacion
					System.out.println("sim datos");
					result =salva_puntuacion(s);// Salva Puntuación
					System.out.println("sim ptos");
				}
				else result = false;
			}
			else result = false;
		return result;
	}
	
	public boolean salva_objeto(Simulacion _s,Integer id_player,Integer id_lander,Integer id_escenario) {
		boolean result = true;
		try {
			Statement st = _c.createStatement();
			long millis=System.currentTimeMillis(); 
			java.sql.Date _now = new java.sql.Date(millis);
			String ssql = "INSERT INTO simulacion (id_usuario,id_lander,id_escenario,fecha)  values(";

			ssql = ssql + id_player+ "," + id_lander+ "," +id_escenario;
			ssql = ssql + ",'" + _now + "')";
			st.execute(ssql);

				// Recuperar el Id generado por el motor de la base de datos
				ssql = "SELECT id_sim FROM simulacion WHERE id_usuario="+id_player + " AND " +
				       "id_lander = " + id_lander + " AND id_escenario= "+ id_escenario + " AND " +
					   "fecha = '" + _now+"'";	
				ResultSet rs = st.executeQuery(ssql);
				rs.next();
				_s.setId(rs.getInt(1)); // Ahora el objeto tiene la clave de persistencia en base de datos

		
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
	public boolean salva_datos(Simulacion _s) {
		boolean result = true;
		try {
			Statement st = _c.createStatement();
			String ssql ="";
			Integer flag=0;
			// Volcamos estructura de memoria en base de datos
			for (DatosSim ds : _s.getSimData()) {
					ssql = "INSERT  INTO datos_sim  (id_sim,tiempo,vel,fuel,dist) values(";
					ssql = ssql + _s.getId()+ "," + ds.getTiempo()+ "," +ds.getVel()+","+ds.getFuel()+","+ds.getDist();
					ssql = ssql + ")";
					st.execute(ssql);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		
		return result;
	}
	
	public boolean salva_puntuacion(Simulacion _s) {
		boolean result = true;
		
		try {
			Statement st = _c.createStatement();
			String ssql ="";
			long millis=System.currentTimeMillis(); 
			java.sql.Date _date = new java.sql.Date(millis);
					ssql = "INSERT INTO puntuacion (id_usuario,id_simulacion,tiempo,fuel,fecha) values(";
					ssql = ssql + _s.getUser().getId()+ "," + _s.getId()+","
					            + _s.getSe().getTiempo()+ "," +_s.getLander().getFuel_deposito()+",'"
					            +_date + "')";
					st.execute(ssql);
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
