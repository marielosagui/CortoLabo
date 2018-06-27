/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import dao.MovieDao;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Movie;

/**
 *
 * @author LN710Q
 */
public class consulta extends JFrame{
    
    public JLabel lblNombre, lblDirector, lblPais, lblClasificacion, lblAño;
    public JTextField Nombre, Director, Pais, Año;
    public JComboBox Clasificacion;
    public JCheckBox EnProyeccion;
    public JRadioButton si;
    public JTable resultados;
    
    public JPanel table;
    public JButton insertar,eliminar,actualizar,buscar;
    private static final int ANCHOC=180, ALTOC =50;
    
    DefaultTableModel tm;
    
    public consulta(){
        super("CINEPOLIX");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container = getContentPane();
        container.add(lblNombre);
        container.add(lblDirector);
        container.add(lblPais);
        container.add(lblClasificacion);
        container.add(lblAño);
        container.add(EnProyeccion);
        container.add(Nombre);
        container.add(Director);
        container.add(Pais);
        container.add(Año);
        setSize(750,750);
       // eventos();       
    }
    public final void agregarLabels(){
        lblNombre =new JLabel("Nombre");
        lblDirector =new JLabel ("Director");
        lblPais=new JLabel("Pais");
        lblClasificacion = new JLabel("Clasificacion");
        lblAño = new JLabel("Año");
        lblNombre.setBounds(10, 10, ANCHOC, ALTOC);
        lblDirector.setBounds(10,60,ANCHOC,ALTOC);
        lblPais.setBounds(1, 100,ANCHOC, ALTOC);
        lblClasificacion.setBounds(10, 140, ANCHOC,ALTOC);
        lblAño.setBounds(10, 140, ANCHOC,ALTOC);
    }
    
    public final void formulario(){
        Nombre =new JTextField();
        Director= new JTextField();
        Pais= new JTextField();
        Año= new JTextField();
        Clasificacion =new JComboBox();
        si= new JRadioButton("si", true);
        resultados =new JTable();
        buscar= new JButton("Buscar");
        insertar=new JButton("Insertar");
        eliminar= new JButton("Eliminar");
        actualizar= new JButton("Actualizar");
        
        table =new JPanel();
        //agregar elementos al combox clasificacion
        
        Clasificacion.addItem("G");
        Clasificacion.addItem("PG");
        Clasificacion.addItem("14A");
        Clasificacion.addItem("18A");
        Clasificacion.addItem("R");
        Clasificacion.addItem("A");
        
        // agrear elementos al combox marca
        
        
    Nombre.setBounds(140, 10, ANCHOC, ALTOC);
    insertar.setBounds(140, 60, ANCHOC, ALTOC);
    Director.setBounds(140, 100, ANCHOC, ALTOC);
    Pais.setBounds(140, 140, 50, ALTOC);
    Año.setBounds(140, 10, ANCHOC, ALTOC);
    buscar.setBounds(300, 10, ANCHOC, ALTOC);
    insertar.setBounds(10, 210, ANCHOC, ALTOC);
    actualizar.setBounds(150, 210, ANCHOC, ALTOC);
    eliminar.setBounds(140, 10, ANCHOC, ALTOC);
    resultados = new JTable();
    table.setBounds(10, 250, 500, 200);
    table.add(new JScrollPane(resultados));
    }
    
    public void llenarTabla(){
        tm=new DefaultTableModel(){
            public Class<?> getColumnClass(int column){
                switch(column){
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2: 
                        return String.class;
                    case 3: 
                        return String.class;
                    case 4: 
                        return String.class;
                    case 5: 
                        return int.class;
                    default :
                        return Boolean.class;
                }
            }
        };
        // columnas que mostraran los respectivos nombres
        tm.addColumn("Nombre");
        tm.addColumn("Director");
        tm.addColumn("Pais");
        tm.addColumn("Clasificacion");
        tm.addColumn("Año");
        tm.addColumn("En Proyeccion");
        
        // consulta a la base de datos por metodo readAll
        MovieDao fd = new MovieDao();
        ArrayList <Movie> movies = fd.readAll();
        
        movies.forEach((fi) -> {
            tm.addRow(new Object[]{fi.getNombre(), fi.getDirector(), fi.getPais(), fi.getClasificacion(), fi.getAño(),fi.getEnProyeccion()});
        });
        
        resultados.setModel(tm);
    }
    
    public void eventos(){
        
        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              MovieDao md =new MovieDao();
              Movie m = new Movie(Nombre.getText(), Director.getText(),Pais.getText(), (String) Clasificacion.getSelectedItem(),
                      Integer.parseInt(Año.getText()),true);
            
                if (md.create(m)){
                    JOptionPane.showMessageDialog(null,"Pelicula registrado con exito");
                    limpiarCampos();
                    llenarTabla();
                }else{
                    JOptionPane.showMessageDialog(null,"Ocurrio un problema al momento de modificar el filtro");
                }
            }
        });
        actualizar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
            MovieDao md = new MovieDao();
            Movie m = new Movie(Nombre.getText(), Director.getText(),Pais.getText(), (String) Clasificacion.getSelectedItem(),
                      Integer.parseInt(Año.getText()),true);
             
            if(si.isSelected()){
                m.setEnProyeccion(true);
            }
            
            if (md.update(m)){
               JOptionPane.showMessageDialog(null,"Filtro Modificado con exito");
               limpiarCampos();
               llenarTabla();
            } else{
                JOptionPane.showMessageDialog(null,"Ocurrio un problema al momento de modificar el filtro");
            }
            }    
        });
        
        eliminar.addActionListener((ActionEvent e) -> {
            MovieDao md = new MovieDao();
            if(md.delete(Nombre.getText())){
                JOptionPane.showMessageDialog(null,"Pelicula Eliminada con exito");
                limpiarCampos();
                llenarTabla();
            }else{
                JOptionPane.showMessageDialog(null, "El filtro buscado no se a encontrado");
            }
        });
        
        buscar.addActionListener((ActionEvent e) -> {
            MovieDao md = new MovieDao();
            Movie m = md.read(Nombre.getText());
            if(m==null){
                JOptionPane.showMessageDialog(null, "La pelicula no se a encontrado");
            }else{
                Director.setText(m.getDirector());
                Clasificacion.setSelectedItem(m.getClasificacion());
                Pais.setText(m.getPais());
                
            }
        });
        
        }
    public void limpiarCampos(){
        Nombre.setText("");
        Clasificacion.setSelectedItem("R");
        Director.setText("");
    }
    
     public static void main(String[] args) {
        // TODO code application logic here
        java.awt.EventQueue.invokeLater(new Runnable(){
        @Override
         public void run(){
             new consulta().setVisible(true);
         } 
    });
    }
}
    
