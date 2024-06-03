package DAORelacional;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Modelo.Simulacion;
import Modelo.DatosSim;
import Modelo.Player;

import java.sql.Date;
import java.sql.PreparedStatement;

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
				//System.out.println("Guardando Simulacion...");
				if (salva_datos(s)) {// Salva Datos simulacion
					//System.out.println("Registrando datos ...");
					result =salva_puntuacion(s);// Salva Puntuación
					//System.out.println("Registrando puntuación..");
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
			/** Fecha sin h-m-s
			long millis=System.currentTimeMillis(); 
			java.sql.Date _now = new java.sql.Date(millis);
			**/
			/** Fecha con h-m-s
			 * 
			 */
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
			LocalDateTime _now = LocalDateTime.now();
			String dateTimeString = _now.format(formatter);
			
			String ssql = "INSERT INTO simulacion (id_usuario,id_lander,id_escenario,fecha)  values(";

			ssql = ssql + id_player+ "," + id_lander+ "," +id_escenario;
			ssql = ssql + ",'" + _now + "')";
			st.execute(ssql);
				
				// Recuperar el Id generado por el motor de la base de datos
				ssql = "SELECT max(id_sim) FROM simulacion  WHERE id_usuario="+id_player + " AND " +
				       "id_lander = " + id_lander + " AND id_escenario= "+ id_escenario ;
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

			// Descartar el último registro
			_s.getSimData().remove(_s.getSimData().size()-1);
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
			String ssql ="INSERT INTO puntuacion (id_usuario,id_simulacion,tiempo,fuel,fecha) values(?,?,?,?,?)";
			PreparedStatement pstmt = _c.prepareStatement(ssql);
			Timestamp t =  new java.sql.Timestamp(new java.util.Date().getTime());
			
			pstmt.setInt(1, _s.getUser().getId());
			pstmt.setInt(2, _s.getId());
			pstmt.setInt(3, _s.getSe().getTiempo());
			pstmt.setDouble(4, _s.getLander().getFuel_deposito());
			pstmt.setTimestamp(5, t);

			pstmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}	
		return result;
	}
	
	/** 
	 * Trae de la base de datos, todas las 10 últimas simulaciones
	 * create view v_detalle_sim as 
		select s.id_sim, s.fecha as FECHA, s.id_usuario, l.nombre as MODULO ,e.nombre as ESCENARIO
		create view v_punt_full as 
	   select u.nick as JUGADOR, e.nombre as ESCENARIO, l.nombre, p.tiempo as TIEMPO, p.fuel as FUEL
	 * @return
	 */
	public ArrayList<String> getSimulaciones(Player _p){
		ArrayList<String> als = new ArrayList<String>();
		try {
			Statement stm = _c.createStatement();
			String ssql = "SELECT * FROM v_detalle_sim WHERE id_usuario ="+
			_p.getId() + " ORDER BY fecha DESC LIMIT 5";
			ResultSet rs = stm.executeQuery(ssql);
			while (rs.next()) {
				//-- Datos principales simulacion
				als.add(rs.getInt(1)+"#"+rs.getString("FECHA")+ " " + rs.getString("ESCENARIO")+" " + rs.getString("MODULO"));
			}
			return als;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	/**
	 * Recupera los datos de una simulación
	 * @return
	 */
	public ArrayList<DatosSim> getDatosSimbyId(Integer id_simulacion){
		
		ArrayList<DatosSim> ds = new ArrayList<DatosSim>();
		
		try {
			Statement stm = _c.createStatement();
			String ssql = "SELECT tiempo,vel,fuel,dist FROM datos_sim WHERE id_sim = " +
			               id_simulacion +" ORDER BY tiempo ASC";
			
			ResultSet rs = stm.executeQuery(ssql);
			while (rs.next()) {
				//-- Datos de la simulacion
				//(double dist, double acel, double vel, double impulso, double fuel, int tiempo)
				ds.add(new DatosSim(rs.getDouble("dist"),0.0, rs.getDouble("vel"),
						            0.0,rs.getDouble("fuel"), rs.getInt("tiempo")));
			}
			return ds;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}//getDatosSimbyId
	
} // DAOSimulacion
