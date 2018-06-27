/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author LN710Q
 */
public class Movie {
    private String Nombre;
    private String Director;
    private String Pais;
    private String Clasificacion;
    private int año;
    private boolean EnProyeccion;
    
    public Movie(){
    }
    
    public Movie(String Nombre, String Director, String Pais, String Clasificacion, int año,boolean EnProyeccion){
        this.Nombre= Nombre;
        this.Director= Director;
        this.Pais= Pais;
        this.Clasificacion= Clasificacion;
        this.año=año;
        this.EnProyeccion=EnProyeccion;
    }
    
    public Movie(String Nombre, String Director, int año,boolean EnProyeccion){
        this.Nombre=Nombre;
        this.Director=Director;
        this.año=año;
        this.EnProyeccion=EnProyeccion;
    }
    
    public Movie(String Nombre, String Clasificacion, boolean EnProyeccion){
        this.Nombre=Nombre;
        this.Clasificacion=Clasificacion;
        this.EnProyeccion=EnProyeccion;
    }

    public Movie(String string, String string0, String string1, int aInt, boolean aBoolean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String Director) {
        this.Director = Director;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String Pais) {
        this.Pais = Pais;
    }

    public String getClasificacion() {
        return Clasificacion;
    }

    public void setClasificacion(String Clasificacion) {
        this.Clasificacion = Clasificacion;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public boolean getEnProyeccion() {
        return EnProyeccion;
    }

    public void setEnProyeccion(boolean EnProyeccion) {
        this.EnProyeccion = EnProyeccion;
    }
    
    
}
