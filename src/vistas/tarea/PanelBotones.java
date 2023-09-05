package vistas.tarea;

import dominio.tarea.GestorDeTareas;
import utils.ComponenteFactory;
import vistas.VentanaPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelBotones extends JPanel implements ActionListener {
    private JButton botonAgregar;
    private JButton botonCompletar;
    private JButton botonEliminar;
    private TablaTareas tablaTareas;
    private RegistrarTarea registrarTarea;
    private VentanaPrincipal ventanaPrincipal;
    public PanelBotones(VentanaPrincipal ventanaPrincipal, TablaTareas tablaTareas){
        this.ventanaPrincipal = ventanaPrincipal;
        this.tablaTareas = tablaTareas;
        iniciarComponentes();
    }
    private void iniciarComponentes(){
        Color colorFondo = Color.white;
        botonAgregar = ComponenteFactory.getButtonConFormatoAzul("Agregar Tarea");
        botonCompletar = ComponenteFactory.getButtonConFormatoAzul("Completar Tarea");
        botonEliminar = ComponenteFactory.getButtonConFormatoAzul("Eliminar Tarea");
        botonAgregar.addActionListener(this);
        botonCompletar.addActionListener(this);
        botonEliminar.addActionListener(this);

        this.setBackground(colorFondo);
        this.setBorder(BorderFactory.createLineBorder(colorFondo, 20, false));
        this.setLayout(new GridLayout(1, 4, 2, 2));
        this.add(botonAgregar);
        this.add(botonCompletar);
        this.add(botonEliminar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == botonAgregar) {
            this.registrarTarea = new RegistrarTarea( ventanaPrincipal, true);
            registrarTarea.setVisible(true);
        }

        if(e.getSource() == botonEliminar) {
            if(tablaTareas.puedoTrabajarConLaTabla()) {

                int result = JOptionPane.showConfirmDialog(this,
                        "Esta seguro de que desea eliminar la tarea "+tablaTareas.getNumeroDeTarea()+"?" , "Eliminar Tarea",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if(result == 0) {
                    try{
                        GestorDeTareas.getInstance().eliminarTarea(tablaTareas.getNumeroDeTarea());
                        ventanaPrincipal.actualizarTabla();
                    }catch(RuntimeException exception){
                        JOptionPane.showMessageDialog(this, exception.getMessage() );
                    }

                }

            }
        }

        if(e.getSource() == botonCompletar) {
            if(tablaTareas.puedoTrabajarConLaTabla()) {
                try{
                    GestorDeTareas.getInstance().completarTarea(tablaTareas.getNumeroDeTarea());
                    ventanaPrincipal.actualizarTabla();
                }catch(RuntimeException exception){
                    JOptionPane.showMessageDialog(this, exception.getMessage() );
                }
            }
        }
    }
}
