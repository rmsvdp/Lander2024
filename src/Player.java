import java.sql.Date;

public class Player {

	
	private String nombre;
	private String pwd;
	private String grupo;
	private Date fechaLogin;
	
	
	/** 
	 * Recupera un jugador de la base de datos
	 * @param Id código único de usuario
	 */
	public Player(Integer Id) {
		
		
	}
	public Player(String nombre, String pwd, String grupo) {
		super();
		this.nombre = nombre;
		this.pwd = pwd;
		this.grupo = grupo;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	
	
	
}
