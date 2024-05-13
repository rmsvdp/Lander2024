import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOLander {

	
	public static Connection _c;
	

	public DAOLander(String Modo) {
		DAOMySql dms = new DAOMySql("local");
		if (dms.c!=null) {
			_c = dms.c;
		}
		else _c= null;
	}
	
	public ArrayList<Lander>getLanders() {
		
		ArrayList<Lander> all = new ArrayList<Lander>();
		try {
			Statement stm = _c.createStatement();
			String ssql = "SELECT * FROM Lander";
			ResultSet rs = stm.executeQuery(ssql);
			while (rs.next()) {
				all.add(new Lander(rs.getString(1),rs.getDouble(2),rs.getInt(3)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
