import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOSimulacion {

	
	public static Connection _c;
	

	public DAOSimulacion(String Modo) {
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
		
		if (salva_objeto(s)) {// Salva Simulación
			if (salva_datos(s)) {// Salva Datos simulacion
				salva_puntuacion(s);// Salva Puntuación
			}
		}
		return true;
	}
	
	public boolean salva_objeto(Simulacion s) {
		boolean result = false;
		
		
		return result;
	}
	
	public boolean salva_datos(Simulacion s) {
		boolean result = false;
		return result;
	}
	
	public boolean salva_puntuacion(Simulacion s) {
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
