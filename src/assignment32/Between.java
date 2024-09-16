/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment32;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

/**
 *
 * @author nakan
 */
public class Between {
    public Between(){
        initComponents();
    }
    private JFrame frame;
    private JLabel jL1;
    private int countdown = 3;
    
    public void initComponents(){
        frame = new JFrame("Countdown Window");
        jL1 = new JLabel();
        jL1.setHorizontalAlignment(JLabel.CENTER);
        jL1.setVerticalAlignment(JLabel.CENTER);
        jL1.setFont(new Font("Arial", Font.PLAIN, 30));

        frame.add(jL1);
        
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jL1.setText(Integer.toString(countdown));
                countdown--;

                if (countdown < 0) {
                    ((Timer) e.getSource()).stop();
                    frame.dispose();
                }
            }
        });
        timer.start();

        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
