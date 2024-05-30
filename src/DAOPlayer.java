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
	
	
	public Player getPlayerbyId(Integer _id) {
		
		Statement stm;
		Player p;
		try {
			stm = _c.createStatement();
			String ssql = "SELECT id_usuario,nick,pwd,grupo FROM usuario WHERE id_usuario = "+ _id;
			ResultSet rs = stm.executeQuery(ssql);
			if (rs.next()) { 
				p = new Player(rs.getString(2),rs.getString(3),rs.getString(4));
				p.setId(_id);
				long millis=System.currentTimeMillis(); 
				java.sql.Date _now = new java.sql.Date(millis);
				p.setFechaLogin(_now);
				return p;
			}
			else return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

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
