package vistas;

import vistas.tarea.PanelBotones;
import vistas.tarea.TablaTareas;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame{
    private JPanel panelBotones;
    private TablaTareas panelTabla;
    public VentanaPrincipal() {
        super("SISTEMA DE GESTION DE TAREAS");
        this.setSize(1280, 720);
        this.setLocation(0, 0);;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        agregarComponentes();
    }

    private void agregarComponentes(){
        panelTabla = new TablaTareas();
        panelBotones = new PanelBotones(this, this.panelTabla);
        Container contenedorPrincipal = getContentPane();
        contenedorPrincipal.setLayout(new BorderLayout());
        contenedorPrincipal.add(panelBotones, BorderLayout.NORTH);
        contenedorPrincipal.add(panelTabla, BorderLayout.CENTER);
    }
    public void actualizarTabla(){
        this.panelTabla.iniciarTabla();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }
}
