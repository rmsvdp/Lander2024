import java.sql.Connection;
import java.sql.DriverManager;

public class DAOMySql {

	public static Connection c;
	
	public DAOMySql(String modo) {
		
		String server_ip    =   "localhost";
        String server_port  =   "3306";
        String database     =   "lander2024";
        String _usr         =   "root";
        String _pwd         =   "root";
        String dbcs;        

        try{  
        	switch (modo) {
        	case "remote":
        		dbcs = "jdbc:mysql://" + server_ip + ":" + server_port + "/" + database;
        	break;
        	case "local":
        		dbcs = "jdbc:mysql://" + server_ip + ":" + server_port + "/" + database;
        	break;
        	default:
        		dbcs = "jdbc:mysql://" + server_ip + ":" + server_port + "/" + database;
        	}
            c=DriverManager.getConnection (dbcs,_usr,_pwd);  
        }catch(Exception e){ 
            System.out.println(e);
            System.out.println("Error al conectar con la plataforma");
            c = null;
            }
	}
	
}
