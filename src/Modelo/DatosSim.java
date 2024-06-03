package Modelo;
import java.time.LocalDateTime;

public class DatosSim {

	
	
	private double dist=0;                     // Distancia a la superficie m
	private double acel=0;                     // aceleración m·s-2
	private double vel=0;                      // velocidad en m· s-1
	private double impulso=0;                  // Impulso retrocohetes en m·s-2
	private double fuel=0;					   // Fuel restante
	private int tiempo = 0;                    // Tiempo de simulación
	
	
	
	public DatosSim(double dist, double acel, double vel, double impulso, double fuel, int tiempo) {
		super();
		this.dist = dist;
		this.acel = acel;
		this.vel = vel;
		this.impulso = impulso;
		this.fuel = fuel;
		this.tiempo = tiempo;
	}
	public double getDist() {		return dist;	}
	public void setDist(double dist) {		this.dist = dist;	}
	public double getAcel() {		return acel;	}
	public void setAcel(double acel) {		this.acel = acel;	}
	public double getVel() {		return vel;	}
	public void setVel(double vel) {		this.vel = vel;	}
	public double getImpulso() {		return impulso;	}
	public void setImpulso(double impulso) {	this.impulso = impulso;	}
	public double getFuel() {		return fuel;	}
	public void setFuel(double fuel) {		this.fuel = fuel;	}
	public int getTiempo() {		return tiempo;	}
	public void setTiempo(int tiempo) {		this.tiempo = tiempo;	}
    
	public String toString() {
		
		return "";
	}
	
}
