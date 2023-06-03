package Classes;

public class MyRunnable implements Runnable{

    private Hilo hilo;

    public MyRunnable(Hilo hilo) {
        this.hilo = hilo;
    }

    @Override
    public void run() {
        System.out.println(hilo.getDuracion()+" "+hilo.getPrioridad()+" "+hilo.getQuantum());
    }
}
