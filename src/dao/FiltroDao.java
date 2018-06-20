/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author LN710Q
 */
import com.mysql.jdbc.PreparedStatement;
import conexion.conexion;
import interfaces.metodos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import modelo.Movie;
import java.util.logging.Logger;

/**
 *
 * @author LN710Q
 */
public class FiltroDao implements metodos <Movie> {
    
    private static final String SQL_INSERT ="INSERT INTO movie(nombre,año,director,pais,clasificacion,proyeccion) VALUES (?,?,?,?,?,?)";
    private static final String SQL_UPDATE ="UPDATE movie SET nombre =?,año=?, director=? pais=?,clasificacion=?,proyeccion=? WHERE codMovie=?";
    private static final String SQL_DELETE = "DELETE FROM movie WHERE idMovie=?";
    private static final String SQL_READ ="SELECT * FROM movie WHERE idMovie=?";
    private static final String SQL_READALL ="SELECT * FROM movie";

    private static final conexion con =conexion.conectar();
    @Override
    public boolean create(Movie g) {
        PreparedStatement ps;
        try{
            ps = (PreparedStatement) con.getCnx().prepareStatement(SQL_INSERT);
            ps.setString(1, g.getNombre());
            ps.setString(2,g.getDirector());
            ps.setString(3,g.getPais());
            ps.setString(4, g.getClasificacion());
            ps.setInt(5,g.getAño());
            ps.setBoolean(6,true);
            if(ps.executeUpdate()> 0){
                return true;
            }           
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean delete(Object key) {
    
        PreparedStatement ps;
        try{
            ps= (PreparedStatement) con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1,key.toString());
            
            if(ps.executeUpdate()> 0){
                return true;
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean update(Movie c) {
       PreparedStatement ps;
       try{
           System.out.println(c.getNombre());
           ps=(PreparedStatement) con.getCnx().prepareStatement(SQL_UPDATE);
           ps.setString(1,c.getNombre());
           ps.setString(2,c.getDirector());
           ps.setString(3,c.getPais());
           ps.setInt(4,c.getAño());
           ps.setString(5,c.getClasificacion());
           ps.setBoolean(6,c.getEnProyeccion());
           
           if (ps.executeUpdate()>0){
               return true;
           }
       }catch(SQLException ex){
           System.out.println(ex.getMessage());
           Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE,null,ex);
       }finally{
           con.cerrarConexion();
       }
       return false;
    }

    @Override
    public Movie read(Object key) {
       Movie f=null;
       PreparedStatement ps;
       ResultSet rs;
       try{
           ps=(PreparedStatement) con.getCnx().prepareStatement(SQL_READ);
           ps.setString(1,key.toString());
           
           rs=ps.executeQuery();
           while(rs.next()){
               f = new Movie(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4), rs.getInt(5), rs.getBoolean(6));
           }
           rs.close();
       }
       catch(SQLException ex){
           System.out.println(ex.getMessage());
           Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE,null,ex);
       }finally{
           con.cerrarConexion();
    }
       return f;
    }

    @Override
    public ArrayList<Movie> readAll() {
        Movie f= null;
        ArrayList<Movie> all = new ArrayList();
        Statement s;
        ResultSet rs;
        try{
            s= con.getCnx().prepareStatement(SQL_READALL);
            rs= s.executeQuery(SQL_READALL);
            while(rs.next()){
                f= new Movie(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4), rs.getInt(5), rs.getBoolean(6));
            } 
            rs.close();
        }
        catch(SQLException ex){
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null,ex);
        }
       return all;
    } 
}