package Classes;

public class Hilo {
    private int duracion;
    private int prioridad;
    private int quantum;

    public Hilo(int duracion, int prioridad, int quantum) {
        this.duracion = duracion;
        this.prioridad = prioridad;
        this.quantum = quantum;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }
}
