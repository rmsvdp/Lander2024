import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOPlayer {

	public static Connection _c;
	public static String _MODO;

	public DAOPlayer(String Modo) {
		_MODO= Modo;
		DAOMySql dms = new DAOMySql(Modo);
		if (dms.c!=null) {
			_c = dms.c;
		}
		else _c= null;
	}
	
	public Integer getIdbyPlayer(Player p) {
		
		Integer result = -1;
		Statement stm;
		try {
			stm = _c.createStatement();
			String ssql = "SELECT id_usuario FROM usuario WHERE nick = '"+p.getNombre()+"'" +
						  " AND pwd ='"+ p.getPwd()+"'";
			ResultSet rs = stm.executeQuery(ssql);
			
			if (rs.next()) { result = rs.getInt("id_usuario");}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
