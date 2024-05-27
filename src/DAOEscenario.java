import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOEscenario {

	public static Connection _c;
	
	public DAOEscenario(String Modo) {
		DAOMySql dms = new DAOMySql(Modo);
		if (dms.c!=null) {
			_c = dms.c;
		}
		else _c= null;
	}

	public Integer getIdbyEscenario(Escenario es) {
		
		Integer result = -1;
		Statement stm;
		try {
			stm = _c.createStatement();
			String ssql = "SELECT id_escenario FROM lander WHERE nombre = '"+es.getNombre()+"'";
			ResultSet rs = stm.executeQuery(ssql);
			
			if (rs.next()) { result = rs.getInt("id_escenario");}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Trae la lista de Landers registrados en la base de datos
	 * @return Colección de objetos Lander registrados
	 */
	public ArrayList<Escenario>getEscenarios() {
		
		ArrayList<Escenario> ale = new ArrayList<Escenario>();
		Integer id_escenario;
		try {
			Statement stm = _c.createStatement();
			String ssql = "SELECT * FROM escenario ORDER BY id_escenario";
			ResultSet rs = stm.executeQuery(ssql);
			while (rs.next()) {
				id_escenario = rs.getInt("id_escenario");
				Escenario nesc = new Escenario(rs.getString("nombre"),rs.getDouble("gravedad"),rs.getDouble("ve"),rs.getDouble("he"));
				ale.add(nesc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ale;
	}
}
