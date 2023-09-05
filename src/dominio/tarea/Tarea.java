package dominio.tarea;

import dominio.tarea.exceptions.TareaException;

public class Tarea {
    private Integer numero;
    private String descripcion;
    private boolean completada;

    private Tarea(Integer numero, String descripcion, boolean completada) {
        this.numero = numero;
        this.descripcion = descripcion;
        this.completada = completada;
    }

    public static Tarea instancia(Integer numero, String descripcion, boolean completada){
        if(descripcion == null || descripcion.isBlank())
            throw new TareaException("La descripcion no puede estar vacia!");

        return new Tarea(numero, descripcion, completada);
    }

    public Integer getNumero() {
        return numero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }
}
