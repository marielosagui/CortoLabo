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
    private int idMovie;
    private String nombre;
    private String director;
    private String pais;
    private String clasificacion;
    private int año;
    private boolean en_proyeccion;

    public Movie(){
    }

    public Movie(int idMovie, String nombre, String director, String pais, String clasificacion, int año, boolean en_proyeccion) {
        this.idMovie = idMovie;
        this.nombre = nombre;
        this.director = director;
        this.pais = pais;
        this.clasificacion = clasificacion;
        this.año = año;
        this.en_proyeccion = en_proyeccion;
    }

        public Movie(String nombre, String director, String pais, String clasificacion, int año, boolean en_proyeccion) {
        this.nombre = nombre;
        this.director = director;
        this.pais = pais;
        this.clasificacion = clasificacion;
        this.año = año;
        this.en_proyeccion = en_proyeccion;
    }
    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    public String getClasificacion() {
        return clasificacion;
    }
    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }
    public int getAño() {
        return año;
    }
    public void setAño(int anio) {
        this.año = anio;
    }
    public boolean isEn_proyeccion() {
        return en_proyeccion;
    }
    public void setEn_proyeccion(boolean en_proyeccion) {
        this.en_proyeccion = en_proyeccion;
    }

}