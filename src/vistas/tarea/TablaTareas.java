package vistas.tarea;

import dominio.tarea.GestorDeTareas;
import dominio.tarea.Tarea;
import utils.ComponenteFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TablaTareas extends JPanel{
    private JTable tabla;
    public TablaTareas(){
        Color colorFondo = Color.white;
        this.setBorder(BorderFactory.createLineBorder(colorFondo, 20, false));
        this.setLayout(new GridLayout(1,1));
        this.iniciarTabla();

    }
    public void iniciarTabla() {
        ArrayList<Tarea> misTareas = GestorDeTareas.getInstance().getTareas();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setRowCount(misTareas.size());
        modelo.setColumnCount(6);

        int i = 0;
        for (Tarea tarea: misTareas) {
            modelo.setValueAt(tarea.getNumero(), i, 0);
            modelo.setValueAt(tarea.getDescripcion(), i, 1);
            modelo.setValueAt(tarea.isCompletada() ? "SI": "NO", i, 2);
            i = i + 1;
        }

        String fila[] = {"NUMERO", "DESCRIPCION", "COMPLETADA"};
        modelo.setColumnIdentifiers(fila);

        tabla = ComponenteFactory.getTablaConFormato(modelo);
        this.removeAll();
        this.add(new JScrollPane(tabla));
        this.updateUI();
    }

    public int getNumeroDeTarea() {
        if(tabla == null) {
            return -1;
        }else{
            if(tabla.getSelectedRow() < 0 )
                return -1;
            return Integer.parseInt(String.valueOf(tabla.getValueAt(tabla.getSelectedRow(), 0)));
        }
    }

    public boolean puedoTrabajarConLaTabla() {
        if(GestorDeTareas.getInstance().getTareas().size() > 0) {

            if(getNumeroDeTarea() < 0) {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione una fila!" );
            }else {
                return true;
            }
        }else {
            JOptionPane.showMessageDialog(this, "No hay Tareas!" );
        }
        return false;
    }
}
