package vistas.tarea;

import dominio.tarea.GestorDeTareas;
import dominio.tarea.Tarea;
import utils.ComponenteFactory;
import vistas.VentanaPrincipal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class RegistrarTarea extends JDialog implements ActionListener{
    private JPanel panelHeader;
    private JPanel panelDescripcion;
    private JPanel panelBotones;
    private JLabel labelDescripcion;
    private JTextArea textAreaDescripcion;
    private JButton botonRegistrar;
    private JButton botonCancelar;
    private VentanaPrincipal ventanaPrincipal;

    public RegistrarTarea(VentanaPrincipal ventanaPrincipal , boolean modal){
        super(ventanaPrincipal, modal);
        this.setSize(820, 500);
        this.setLocationRelativeTo(null);
        this.ventanaPrincipal = ventanaPrincipal;
        this.setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        agregarComponentes();

    }

    private void agregarComponentes(){
        Container contenedorPrincipal = getContentPane();
        Color colorFondo = Color.white;
        panelHeader = new JPanel();
        panelDescripcion = new JPanel();
        panelBotones = new JPanel();
        labelDescripcion = ComponenteFactory.getLabelConFormato3("Descripcion");
        textAreaDescripcion = ComponenteFactory.getTextAreaConFormato();
        botonRegistrar = ComponenteFactory.getButtonConFormatoAzul("  REGISTRAR  ");
        botonCancelar = ComponenteFactory.getButtonConFormatoAzul("   CANCELAR  ");

        panelDescripcion.setBackground(colorFondo);
        panelDescripcion.setLayout(new BorderLayout());
        panelDescripcion.setBorder(BorderFactory.createLineBorder(colorFondo, 10, true));
        panelDescripcion.add(labelDescripcion, BorderLayout.NORTH);
        panelDescripcion.add(new JScrollPane(textAreaDescripcion), BorderLayout.CENTER);

        panelBotones.setBorder(BorderFactory.createLineBorder(colorFondo, 10, false));
        panelBotones.setLayout(new FlowLayout());
        panelBotones.setBackground(colorFondo);
        botonRegistrar.addActionListener(this);
        panelBotones.add(botonRegistrar);
        botonCancelar.addActionListener(this);
        panelBotones.add(botonCancelar);

        panelHeader.setBackground(colorFondo);
        panelHeader.setLayout(new FlowLayout());
        panelHeader.setBorder(BorderFactory.createLineBorder(colorFondo, 10));
        panelHeader.add(ComponenteFactory.getLabelConFormato4("Registrar Tarea"));

        contenedorPrincipal.setBackground(Color.black);
        contenedorPrincipal.setLayout(new BorderLayout());
        contenedorPrincipal.add(panelHeader, BorderLayout.NORTH);
        contenedorPrincipal.add(panelDescripcion, BorderLayout.CENTER);
        contenedorPrincipal.add(panelBotones, BorderLayout.SOUTH);
    }

    public void blanquear(){
        textAreaDescripcion.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        if(source == botonRegistrar){
            GestorDeTareas gestorDeTareas = GestorDeTareas.getInstance();
            try {
                Tarea nuevaTarea = Tarea.instancia(null,  textAreaDescripcion.getText(), false);
                gestorDeTareas.agregarTarea(nuevaTarea);
                blanquear();
                ventanaPrincipal.actualizarTabla();
                this.dispose();
            } catch (RuntimeException exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage(),"Cuidado",JOptionPane.INFORMATION_MESSAGE);
            }
        }

        if(source == botonCancelar) {
            blanquear();
            this.dispose();;
        }
    }


}