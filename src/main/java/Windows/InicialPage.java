package Windows;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class InicialPage implements ActionListener{

    JFrame frame = new JFrame("Digite los datos");
    JTextField cantProcesos = new JTextField();
    JLabel cantLabel = new JLabel("Digite la cantidad de procesos que desea: ");
    JButton buttonCantProcesos = new JButton("Next");

    public InicialPage(){
        ImageIcon logoIcon = new ImageIcon("src/main/resources/Mini-Robot.png");
        frame.setIconImage(logoIcon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(380,220);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        cantProcesos.setBounds(255,60,100,20);
        cantLabel.setBounds(10,60,500,20);
        buttonCantProcesos.setBounds((380-70)/2,100,70,25);
        buttonCantProcesos.addActionListener(this);
        frame.add(cantLabel);
        frame.add(cantProcesos);
        frame.add(buttonCantProcesos);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String cp = cantProcesos.getText();
        if (cp.isEmpty()) {
            cp = "0";
        } else {
            try {
                Integer.parseInt(cp);
            } catch (NumberFormatException ex) {
                cp = "0";
            }
        }
        frame.dispose();
        new SecondPage(Integer.parseInt(cp));
    }
}
