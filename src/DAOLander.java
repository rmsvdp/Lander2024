import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOLander {

	
	public static Connection _c;
	public static String _MODO;

	public DAOLander(String Modo) {
		_MODO= Modo;
		DAOMySql dms = new DAOMySql(Modo);
		if (dms.c!=null) {
			_c = dms.c;
		}
		else _c= null;
	}
	
	/**
	 * Trae la lista de Landers registrados en la base de datos
	 * @return Colección de objetos Lander registrados
	 */
	public ArrayList<Lander>getLanders() {
		
		ArrayList<Lander> all = new ArrayList<Lander>();
		Integer id_lander;
		Integer perfil_pot;
		try {
			Statement stm = _c.createStatement();
			String ssql = "SELECT * FROM lander ORDER BY id_lander";
			ResultSet rs = stm.executeQuery(ssql);
			while (rs.next()) {
				//-- Datos principales lander
				id_lander = rs.getInt("id_lander");
				perfil_pot = rs.getInt("perfil_pot");
				Lander nlan = new Lander(rs.getString("nombre"),rs.getInt("fuel"),rs.getDouble("t_a"));
				//-- Datos del perfil de potencia
				DAOPerfilPot dpp = new DAOPerfilPot(_MODO);
				PerfilPot p =dpp.findPerfilById(perfil_pot);
				dpp._c.close();  //-- Si no se cierran, van quedando hasta que termine la aplicación
								 //-- o las elimina el motor si no se usan
				nlan.setPerfPot(p);
				all.add(nlan);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return all;
	}
	
	
}
