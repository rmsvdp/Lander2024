import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOPerfilPot {

	
	
	public static Connection _c;
	public static String _MODO;

	public DAOPerfilPot(String Modo) {
		_MODO= Modo;
		DAOMySql dms = new DAOMySql(Modo);
		if (dms.c!=null) {
			_c = dms.c;
		}
		else _c= null;
	}
	
	public PerfilPot findPerfilById(Integer id) {
		
		float[] p = new float[10];
		PerfilPot pp;
		try {
			Statement stm = _c.createStatement();
			String ssql = "SELECT nivel, valor FROM perfil_pot WHERE id_perfil="+id+ " ORDER BY nivel";
			ResultSet rs =stm.executeQuery(ssql);
			while (rs.next()) {
				p[rs.getInt("nivel")] =rs.getFloat("valor");
			}
			pp= new PerfilPot();
			pp.setId(id);
			pp.setPotencia(p);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pp= null;
		}
		
		return pp;
	}
	
}
