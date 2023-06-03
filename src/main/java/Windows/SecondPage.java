package Windows;
import Classes.Hilo;
import Classes.MyRunnable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SecondPage implements ActionListener {

    JFrame frame = new JFrame("Digite los datos");
    JButton play = new JButton("PLAY");
    JButton back = new JButton("Go back");
    JPanel scrollP = new JPanel();
    JScrollPane scroll = new JScrollPane(scrollP);

    String[] algoritmos = {"FCFS","SJF","SRTF","RR"};
    JComboBox<String> algortimo = new JComboBox<>(algoritmos);
    JLabel algoLabel = new JLabel("Seleccione uno de los siguientes algoritmos: ");

    List<Hilo> hiloList = new ArrayList<>();
    List<JTextField> duracionFields = new ArrayList<>();
    List<JTextField> prioridadFields = new ArrayList<>();
    List<JTextField> quantumFields = new ArrayList<>();
    int cantProcesos;
    public SecondPage(int cantProcesos) {
        this.cantProcesos = cantProcesos;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        ImageIcon logoIcon = new ImageIcon("src/main/resources/Mini-Robot.png");
        frame.setIconImage(logoIcon.getImage());
        frame.setLayout(null);

        if (cantProcesos == 0) {
            JLabel nada = new JLabel("No hay procesos que mostrar");
            frame.setSize(380, 220);
            nada.setBounds(100, 60, 200, 20);
            back.setBounds((380-100)/2,120,100,20);
            back.addActionListener(this);
            frame.setTitle("Sin datos");
            frame.add(nada);
            frame.add(back);
        }

        else {
            frame.setSize(520, 550);

            JLabel duracionL = new JLabel("Duracion");
            JLabel prioridadL = new JLabel("Prioridad");
            JLabel quantumL = new JLabel("Quantum");

            duracionL.setBounds(110, 10, 100, 20);
            prioridadL.setBounds(220, 10, 100, 20);
            quantumL.setBounds(330, 10, 100, 20);

            scrollP.setLayout(null);
            for (int i = 0; i < cantProcesos; i++) {

                JTextField duracion = new JTextField();
                JTextField prioridad = new JTextField();
                JTextField quantum = new JTextField();
                JLabel processNumber = new JLabel("Proceso " + (i + 1));

                processNumber.setBounds(10, 40 + (i * 50), 100, 20);
                duracion.setBounds(110, 40 + (i * 50), 100, 20);
                prioridad.setBounds(220, 40 + (i * 50), 100, 20);
                quantum.setBounds(330, 40 + (i * 50), 100, 20);

                scrollP.add(processNumber);
                scrollP.add(duracion);
                scrollP.add(prioridad);
                scrollP.add(quantum);

                duracionFields.add(duracion);
                prioridadFields.add(prioridad);
                quantumFields.add(quantum);
            }
            scrollP.add(prioridadL);
            scrollP.add(duracionL);
            scrollP.add(quantumL);

            scrollP.setPreferredSize(new Dimension(400, cantProcesos * 53));

            scroll.setBounds(10, 10, 480, 400);

            algoLabel.setBounds(10, 420, 280, 20);
            algortimo.setBounds(280, 420, 100, 20);
            frame.add(algoLabel);
            frame.add(algortimo);

            play.setBounds((520 - 80) / 2, 460, 80, 25);
            play.addActionListener(this);
            frame.add(play);
            frame.add(scroll);
        }
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==back){
            frame.dispose();
            new InicialPage();
        }
        if(e.getSource()==play){

            for (int i = 0; i < cantProcesos; i++) {
                String duracionValue = duracionFields.get(i).getText();
                String prioridadValue = prioridadFields.get(i).getText();
                String quantumValue = quantumFields.get(i).getText();

                if (duracionValue.isEmpty() || prioridadValue.isEmpty() || quantumValue.isEmpty()) {
                    duracionValue = "0";
                    prioridadValue = "0";
                    quantumValue = "0";
                } else {
                    try {
                        Integer.parseInt(duracionValue);
                        Integer.parseInt(prioridadValue);
                        Integer.parseInt(quantumValue);


                    } catch (NumberFormatException ex) {
                        duracionValue = "0";
                        prioridadValue = "0";
                        quantumValue = "0";
                    }
                }

                Hilo hilo = new Hilo(Integer.parseInt(duracionValue),Integer.parseInt(prioridadValue),Integer.parseInt(quantumValue));
                hiloList.add(hilo);
            }
            duracionFields.clear();
            prioridadFields.clear();
            quantumFields.clear();

            frame.dispose();
            new AnimationPage(this.hiloList, algortimo.getSelectedItem().toString());
        }
    }
}
