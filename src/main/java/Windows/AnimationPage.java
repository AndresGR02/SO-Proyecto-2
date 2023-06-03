package Windows;
import Classes.Hilo;
import Classes.MyRunnable;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AnimationPage implements ActionListener {

    List<Thread> threadList = new ArrayList<>();
    JButton back = new JButton("Go back");
    JFrame frame = new JFrame();
    JLabel algoritmoName = new JLabel();
    List<Hilo> hiloList;
    List<JLabel> labels = new ArrayList<>();
    boolean escape = false;
    public AnimationPage(List<Hilo> hiloList, String algoritmo) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        ImageIcon logoIcon = new ImageIcon("src/main/resources/Mini-Robot.png");
        frame.setIconImage(logoIcon.getImage());
        frame.setLayout(null);

        this.hiloList = hiloList;

        hiloList.sort(Comparator.comparingInt(Hilo::getPrioridad));

        for (Hilo i: hiloList) {
            if (i.getQuantum() == 0 || i.getPrioridad() == 0 || i.getDuracion() == 0) {
                JLabel nada = new JLabel("Alguno de los procesos tienen un 0 en su valor");
                frame.setSize(380, 220);
                nada.setBounds(50, 60, 300, 20);
                back.setBounds((380-100)/2,120,100,20);
                back.addActionListener(this);
                frame.setTitle("Sin datos");
                frame.add(nada);
                frame.add(back);
                escape = true;
                break;
            }/*
            Thread thread = new Thread(new MyRunnable(i));
            threadList.add(thread);
            System.out.println(i.getPrioridad());*/
        }

        if(!escape) {
            frame.setSize(1020, 550);

            cargaInicial(hiloList);

            if (algoritmo.equals("FCFS")) {
                algoritmoName.setText("FCFS");
                algoritmoName.setBounds((1020 - 50) / 2, 15, 50, 20);
                frame.add(algoritmoName);
                //this.FCFS(threadList);
                this.FCFS(labels);
            }
            if (algoritmo.equals("SJF")) {
                algoritmoName.setText("SJF");
                algoritmoName.setBounds((1020 - 50) / 2, 15, 50, 20);
                frame.add(algoritmoName);
                //this.SJF(threadList);
            }
            if (algoritmo.equals("SRTF")) {
                algoritmoName.setText("SRTF");
                algoritmoName.setBounds((1020 - 50) / 2, 15, 50, 20);
                frame.add(algoritmoName);
                //this.SRTF(threadList);
            }
            if (algoritmo.equals("RR")) {
                algoritmoName.setText("RR");
                algoritmoName.setBounds((1020 - 50) / 2, 15, 50, 20);
                frame.add(algoritmoName);
                //this.RR(threadList);
            }
        }
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void cargaInicial(List<Hilo> hiloList){
        for (int i = 0; i < hiloList.size(); i++) {

            JLabel proceso = new JLabel("Proceso "+(i+1));
            proceso.setBackground(new Color(49, 147, 243));
            proceso.setOpaque(true);
            proceso.setBounds(880,50+(i*90),80,80);
            proceso.setHorizontalAlignment(SwingConstants.CENTER);
            labels.add(proceso);
            frame.add(proceso);
        }
    }

    public void FCFS(List<JLabel> labels){
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
            for (int i = 0; i < hiloList.size(); i++) {
                labels.get(i).setBounds(100+(i*90),(550-80)/2,80,80);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==back){
            frame.dispose();
            new SecondPage(this.hiloList.size());
        }
    }
}
