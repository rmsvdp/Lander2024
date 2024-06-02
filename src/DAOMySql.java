import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Base64;

public class DAOMySql {

	public static Connection c;
	
	public DAOMySql(String modo) {
		
		String server_ip    =   "192.168.1.73"; // localhost	192.168.1.99
        String server_port  =   "3306";
        String database     =   "lander2024";
        String _usr         =   "root";
        String _pwd         =   "root";
        String dbcs;        

        try{  
        	switch (modo) {
        	case "remote":
        		dbcs = "jdbc:mysql://" + server_ip + ":" + server_port + "/" + database;
        		c=DriverManager.getConnection (dbcs,_usr,_pwd);    
        	break;
        	case "local":
        		dbcs = "jdbc:mysql://" + server_ip + ":" + server_port + "/" + database;
                c=DriverManager.getConnection (dbcs,_usr,_pwd);  
        	break;
        	default:
        		System.out.println("Modo de conexión no válido");
        		c=null;
        	}

        }catch(Exception e){ 
            System.out.println(e);
            System.out.println("Error al conectar con la plataforma");
            c = null;
            }
	}
	
    private static String dec(String s) throws UnsupportedEncodingException{
        byte[] decode = Base64.getDecoder().decode(s.getBytes());
        return new String(decode, "utf-8");
    }
	
} // DAOMySql
