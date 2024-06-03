package Modelo;
public class Puntuacion {
    private int id_Usuario;
    private int tiempo;
    private float fuel;
    private String fecha;

    public Puntuacion(int id_Usuario, int tiempo, float fuel, String fecha) {
        this.id_Usuario = id_Usuario;
        this.tiempo = tiempo;
        this.fuel = fuel;
        this.fecha = fecha;
    }

    public int getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public float getFuel() {
        return fuel;
    }

    public void setFuel(float fuel) {
        this.fuel = fuel;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    

    

}
