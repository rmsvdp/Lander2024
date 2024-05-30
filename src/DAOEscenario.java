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
		
//		Integer result = -1;
//		Statement stm;
//		try {
//			stm = _c.createStatement();
//			String ssql = "SELECT id_escenario FROM escenario WHERE nombre = '"+es.getNombre()+"'";
//			ResultSet rs = stm.executeQuery(ssql);
//			
//			if (rs.next()) { result = rs.getInt("id_escenario");}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		return es.getId();
	}
	
public Escenario getEscenariobyId(Integer _id) {
		
		Integer result = -1;
		Statement stm;
		Escenario es= null;
		try {
			stm = _c.createStatement();
			String ssql = "SELECT nombre,gravedad,ve,he FROM escenario WHERE id_ecenario = " + _id;
			ResultSet rs = stm.executeQuery(ssql);
			
			if (rs.next()) {
				 es = new Escenario( rs.getString(1),rs.getDouble(2),rs.getDouble(3), Double.valueOf(rs.getInt(4)));
				 es.setId(_id);
				 return es;
			}
			else return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

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
				nesc.setId(id_escenario);
				ale.add(nesc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ale;
	}
}
