package dominio.tarea;

import dominio.tarea.exceptions.TareaException;

import java.util.ArrayList;

public class GestorDeTareas {
    private static GestorDeTareas instancia;
    private Integer contador;
    private ArrayList<Tarea> tareas;
    private GestorDeTareas() {
        this.tareas = new ArrayList<>();
        this.contador = 1;
    }

    public static GestorDeTareas getInstance() {
        if (instancia == null) {
            instancia = new GestorDeTareas();
        }
        return instancia;
    }

    public void agregarTarea(Tarea tarea) {
        tarea.setCompletada(false);
        tarea.setNumero(contador);
        this.contador = this.contador + 1;
        this.tareas.add(tarea);
    }

    public void completarTarea(Integer numero) {
        System.out.println(existeTarea(numero));
        if(existeTarea(numero)){
            Tarea tarea = obtenerTarea(numero);
            tarea.setCompletada(true);
        }else{
            throw new TareaException("la tarea a completar no existe!");
        }

    }

    public void eliminarTarea(Integer numero){
        System.out.println(existeTarea(numero));
        if(existeTarea(numero)) {
            Tarea tarea = obtenerTarea(numero);
            this.tareas.remove(tarea);
        }else {
            throw new TareaException("la tarea a eliminar no existe!");
        }
    }

    private Tarea obtenerTarea(Integer numero){
        return this.tareas.stream().filter(tarea -> tarea.getNumero() == numero).findFirst().get();
    }
    private boolean existeTarea(Integer numero){
        return this.tareas.stream().anyMatch(tarea -> tarea.getNumero() == numero);
    }

    public ArrayList<Tarea> getTareas() {
        return this.tareas;
    }
}
