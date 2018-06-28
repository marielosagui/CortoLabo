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
    public JLabel lblNombre, lblDirector, lblPais, lblClasificacion, lblAnio, lblEnProyeccion;
    public JTextField nombre, director, pais, anio;
    public JComboBox clasificacion;
    ButtonGroup existencia = new ButtonGroup();
    public JRadioButton si;
    public JRadioButton no;
    public JTable resultados;
    public JPanel table;
    public JButton insertar, eliminar, actualizar, buscar;
    private static final int ANCHOC = 150, ALTOC = 30;
    DefaultTableModel tm;
   
    
    public consulta(){
        super("--CINEPOLIX--");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container = getContentPane();
        container.add(lblNombre);
        container.add(clasificacion);
        container.add(lblDirector);
        container.add(lblPais);
        container.add(lblClasificacion);
        container.add(lblAnio);
        container.add(nombre);
        container.add(director);
        container.add(pais);
        container.add(anio);
        container.add(si);
        container.add(no);
        container.add(table);
        container.add(insertar);
        container.add(eliminar);
        container.add(actualizar);
        container.add(buscar);
        container.add(lblEnProyeccion);
        setSize(1000,500);
        eventos();
    }
        public final void agregarLabels(){
        lblNombre = new JLabel("Nombre:");
        lblDirector = new JLabel("Director:");
        lblPais = new JLabel ("Pais:");
        lblClasificacion = new JLabel ("Clasificacion:");
        lblAnio = new JLabel ("Año:");
        lblEnProyeccion = new JLabel("En proyección:");
        lblNombre.setBounds(200, 50, 50, 30);
        lblDirector.setBounds(200, 90, 60, 30);
        lblPais.setBounds(220, 130, 50, 30);
        lblClasificacion.setBounds(540, 50, 80, 30);
        lblAnio.setBounds(590, 90, 50, 30);
        lblEnProyeccion.setBounds(532, 130, 90, 30);
    }

    
    public final void formulario(){
        nombre = new JTextField();
        director = new JTextField();
        pais = new JTextField();
        anio = new JTextField();
        si = new JRadioButton("Si",true);
        no = new JRadioButton("No");
        clasificacion = new JComboBox();
        resultados = new JTable();
        buscar = new JButton("Buscar");
        insertar = new JButton("Insertar");
        eliminar = new JButton("Eliminar");
        actualizar = new JButton("Actualizar");

        clasificacion.addItem("G");
        clasificacion.addItem("PG-13");
        clasificacion.addItem("14A");
        clasificacion.addItem("18A");
        clasificacion.addItem("R");
        clasificacion.addItem("A");
        
        table = new JPanel();
        existencia = new ButtonGroup();
        existencia.add(si);
        existencia.add(no);
        nombre.setBounds(270, 50, ANCHOC, ALTOC);
        director.setBounds(270, 90, ANCHOC, ALTOC);
        pais.setBounds(270, 130, ANCHOC, ALTOC);
        anio.setBounds(640, 90, ANCHOC, ALTOC);
        si.setBounds(640, 130, 50, ALTOC);
        no.setBounds(690, 130, 50, ALTOC);
        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;


        buscar.setBounds(740, 210, ANCHOC,ALTOC);
        insertar.setBounds(90, 210, ANCHOC,ALTOC);
        actualizar.setBounds(300, 210, ANCHOC,ALTOC);
        eliminar.setBounds(530, 210, ANCHOC,ALTOC);
        clasificacion.setBounds(640, 50, ANCHOC,ALTOC);
        resultados=new JTable();
        resultados.setBounds(0,250,1000,200);
        table.setBounds(resultados.getBounds());
        table.add(new JScrollPane(resultados));
    }

        public void llenarTabla(){
        tm = new DefaultTableModel(){
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
                        return Integer.class;

                    default:
                        return Boolean.class;
                }
            }
        };

        

        tm.addColumn("Nombre");
        tm.addColumn("Director");
        tm.addColumn("Pais");
        tm.addColumn("Clasificacion");
        tm.addColumn("Año");
        tm.addColumn("En proyeccion");
        
        MovieDao fd = new MovieDao();
        ArrayList<Movie> filtros = fd.readAll();
        
        for (Movie fi : filtros){
            tm.addRow(new Object[]{fi.getNombre(),fi.getDirector(), fi.getPais(), fi.getClasificacion(), fi.getAño(), fi.isEn_proyeccion()});
        }
        resultados.setModel(tm);
    }
        public void eventos(){
        insertar.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            MovieDao fd = new MovieDao();
            Movie f = new Movie (nombre.getText(),director.getText(),pais.getText(),clasificacion.getSelectedItem().toString(),
            Integer.parseInt(anio.getText()),true);
           
            if (no.isSelected()){
                f.setEn_proyeccion(false);
            }
            if(fd.create(f)){
                JOptionPane.showMessageDialog(null,"Filtro Registrado!");
                limpiarCampos();
                llenarTabla();
            }
            else{
                JOptionPane.showMessageDialog(null, "Ocurrio un error!");
            }
        }
    });
        
        eliminar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                MovieDao fd = new MovieDao();
                if(fd.delete(nombre.getText())){
                   JOptionPane.showMessageDialog(null,"Filtro eliminado");
                    limpiarCampos();
                    llenarTabla(); 
                }else{
                    JOptionPane.showMessageDialog(null,"Ocurrio un problema al momento de eliminar el filtro");
                }
       }
        });

        actualizar.addActionListener(new ActionListener(){
            @Override

            public void actionPerformed(ActionEvent e){

                MovieDao fd = new MovieDao();
                Movie f = new Movie (nombre.getText(),director.getText(),pais.getText(),clasificacion.getSelectedItem().toString(),
                Integer.parseInt(anio.getText()),true);

                if(no.isSelected()){
                    f.setEn_proyeccion(false);
                }
                if(fd.update(f)){
                    JOptionPane.showMessageDialog(null,"Filtro Modificado con exito");
                    limpiarCampos();
                    llenarTabla();
                }else{
                    JOptionPane.showMessageDialog(null,"Ocurrio un problema al momento de modificar el filtro");
                }
            }
        });

        buscar.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            MovieDao fd = new MovieDao();
            Movie f = fd.read(nombre.getText());
            if(f == null){
                JOptionPane.showMessageDialog(null,"El filtro buscado no se ha encontrado");
            }else{
                director.setText(f.getDirector());
                clasificacion.setSelectedItem(f.getClasificacion());
                anio.setText(Integer.toString(f.getAño()));
                pais.setText(f.getPais());

                if(f.isEn_proyeccion()){
                    si.setSelected(true);
                }else{
                    no.setSelected(true);
                }
            }
        }
    });
    }
    public void limpiarCampos(){
        nombre.setText("");
        director.setText("");
        pais.setText("");
        clasificacion.setSelectedItem("G");
        anio.setText("");
    }
}