package DAORelacional;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Modelo.Puntuacion;


public class DAOPuntuaciones {
    public static Connection _c;
	public static String _MODO;

	public DAOPuntuaciones(String Modo) {
		_MODO= Modo;
		DAOMySql dms = new DAOMySql(Modo);
		if (dms.c!=null) {
			_c = dms.c;
		}
		else _c= null;
	}

    public ArrayList <Puntuacion> getPuntuaciones(){
        ArrayList <Puntuacion> all = new ArrayList<>();
        
        try{
            Statement stm = _c.createStatement();
            String ssql = "SELECT * FROM puntuacion ORDER BY fuel DESC";
            ResultSet rs = stm.executeQuery(ssql);
            while (rs.next()) {
                //Datos puntuaciones
                all.add(new Puntuacion(rs.getInt("id_usuario"),
                 rs.getInt("tiempo"), 
                 rs.getFloat("fuel"), 
                 rs.getString("fecha")));
            }
            stm.close();
            rs.close();
        }
        catch(SQLException e){
            System.out.println("Error: "+e);
        }
        return all;
    }


}
