/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment32;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.ArrayList;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

/**
 *
 * @author nakan
 */
public class Field {
    javax.swing.JLabel[][] labels = new javax.swing.JLabel[11][10];
    javax.swing.JLabel time = new javax.swing.JLabel();
    javax.swing.JLabel level = new javax.swing.JLabel();
    javax.swing.JLabel fruit = new javax.swing.JLabel();
    JFrame frame = new JFrame("Field");
    
    ArrayList<Integer> occupiedRocks = new ArrayList<>();
    ArrayList<Integer> occupiedSnake = new ArrayList<>();
    Map<Integer,Integer> body = new HashMap<>();
    Random random = new Random();
    
    private LevelClearListener levelClearListener;
    
    int initialDelay = 1000;
    int period = 1000;
    int currentHead = 56;
    int fruitPosi;
    
    int spentTime = 0;
    int cnt;
    int cnt2 = 3;
    int lvl;
    int criteria;
    int fruits = 0;
    
    //Snake snake = new Snake();
    SpriteSnake snake = new SpriteSnake();
    SpriteApple apple = new SpriteApple();
    
    public Field(int level, int cnt, int cri, LevelClearListener listener){
        lvl = level;
        this.cnt = cnt;
        this.levelClearListener = listener;
        criteria = cri;
        if(level < 11){
            Between between = new Between();
            timer2.setInitialDelay(initialDelay);
            timer2.setDelay(period);
            timer2.start();
            /*
            if(cnt2 == 0){
                initComponents();
            }*/
            //initComponents();
        }
        if(level >= 11){
            JOptionPane.showMessageDialog(frame, "Well done!" + "\n" + "Your scores: " + ((Snake) levelClearListener).getScore());
            RegisterScore registerScore = new RegisterScore(((Snake) levelClearListener).getScore());
            frame.dispose();
            win();
        }
    }
    
    
    public void initComponents(){
        
        timer.setInitialDelay(initialDelay);
        timer.setDelay(period);
        timer.start();
        
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
       
        //frame.addKeyListener((KeyListener) this);
        
        frame.setSize(620, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                // 太い線を描画
                g2d.setStroke(new BasicStroke(3)); // 3は線の太さ
                // 上辺
                g2d.drawLine(0, 0, getWidth(), 0);
                // 左辺
                g2d.drawLine(0, 0, 0, getHeight());
                
                int bottomY = getHeight() - 3;
                g2d.drawLine(0, bottomY, getWidth(), bottomY);
            }
        };
        
        Border border1 = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
        for (int row = 1; row < 11; row++) {
            JLabel label = new JLabel();
            label.setSize(50, 50);
            label.setLocation(row * 50, 0);
            label.setText("");
            label.setBorder(border1);
            frame.add(label);
        }
        
        Border border2 = BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK);
        for (int row = 1; row < 11; row++) {
            JLabel label = new JLabel();
            label.setSize(50, 10);
            label.setLocation(row * 50, 11 * 50);
            label.setText("");
            label.setBorder(border2);
            frame.add(label);
        }
        
        Border border3 = BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK);
        for (int col = 1; col < 11; col++) {
            JLabel label = new JLabel();
            label.setSize(50, 50);
            label.setLocation(0, col * 50);
            label.setText("");
            label.setBorder(border3);
            frame.add(label);
        }
        
        Border border4 = BorderFactory.createMatteBorder(0, 2, 0, 0, Color.BLACK);
        for (int col = 1; col < 11; col++) {
            JLabel label = new JLabel();
            label.setSize(50, 50);
            label.setLocation(11 * 50, col * 50);
            label.setText("");
            label.setBorder(border4);
            frame.add(label);
        }
        
        
        JLabel jl1 = new JLabel();
        jl1.setSize(50, 50);
        jl1.setLocation(1 * 50, 1 * 50);
        jl1.setText("");
        jl1.setHorizontalAlignment(JLabel.CENTER);
        jl1.setVerticalAlignment(JLabel.CENTER);
        jl1.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl1);
        labels[0][0] = jl1;

        JLabel jl2 = new JLabel();
        jl2.setSize(50, 50);
        jl2.setLocation(2 * 50, 1 * 50);
        jl2.setText("");
        jl2.setHorizontalAlignment(JLabel.CENTER);
        jl2.setVerticalAlignment(JLabel.CENTER);
        jl2.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl2);
        labels[1][0] = jl2;

        JLabel jl3 = new JLabel();
        jl3.setSize(50, 50);
        jl3.setLocation(3 * 50, 1 * 50);
        jl3.setText("");
        jl3.setHorizontalAlignment(JLabel.CENTER);
        jl3.setVerticalAlignment(JLabel.CENTER);
        jl3.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl3);

        JLabel jl4 = new JLabel();
        jl4.setSize(50, 50);
        jl4.setLocation(4 * 50, 1 * 50);
        jl4.setText("");
        jl4.setHorizontalAlignment(JLabel.CENTER);
        jl4.setVerticalAlignment(JLabel.CENTER);
        jl4.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl4);

        JLabel jl5 = new JLabel();
        jl5.setSize(50, 50);
        jl5.setLocation(5 * 50, 1 * 50);
        jl5.setText("");
        jl5.setHorizontalAlignment(JLabel.CENTER);
        jl5.setVerticalAlignment(JLabel.CENTER);
        jl5.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl5);

        JLabel jl6 = new JLabel();
        jl6.setSize(50, 50);
        jl6.setLocation(6 * 50, 1 * 50);
        jl6.setText("");
        jl6.setHorizontalAlignment(JLabel.CENTER);
        jl6.setVerticalAlignment(JLabel.CENTER);
        jl6.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl6);

        JLabel jl7 = new JLabel();
        jl7.setSize(50, 50);
        jl7.setLocation(7 * 50, 1 * 50);
        jl7.setText("");
        jl7.setHorizontalAlignment(JLabel.CENTER);
        jl7.setVerticalAlignment(JLabel.CENTER);
        jl7.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl7);

        JLabel jl8 = new JLabel();
        jl8.setSize(50, 50);
        jl8.setLocation(8 * 50, 1 * 50);
        jl8.setText("");
        jl8.setHorizontalAlignment(JLabel.CENTER);
        jl8.setVerticalAlignment(JLabel.CENTER);
        jl8.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl8);

        JLabel jl9 = new JLabel();
        jl9.setSize(50, 50);
        jl9.setLocation(9 * 50, 1 * 50);
        jl9.setText("");
        jl9.setHorizontalAlignment(JLabel.CENTER);
        jl9.setVerticalAlignment(JLabel.CENTER);
        jl9.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl9);

        JLabel jl10 = new JLabel();
        jl10.setSize(50, 50);
        jl10.setLocation(10 * 50, 1 * 50);
        jl10.setText("");
        jl10.setHorizontalAlignment(JLabel.CENTER);
        jl10.setVerticalAlignment(JLabel.CENTER);
        jl10.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl10);

        JLabel jl11 = new JLabel();
        jl11.setSize(50, 50);
        jl11.setLocation(1 * 50, 2 * 50);
        jl11.setText("");
        jl11.setHorizontalAlignment(JLabel.CENTER);
        jl11.setVerticalAlignment(JLabel.CENTER);
        jl11.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl11);

        JLabel jl12 = new JLabel();
        jl12.setSize(50, 50);
        jl12.setLocation(2 * 50, 2 * 50);
        jl12.setText("");
        jl12.setHorizontalAlignment(JLabel.CENTER);
        jl12.setVerticalAlignment(JLabel.CENTER);
        jl12.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl12);

        JLabel jl13 = new JLabel();
        jl13.setSize(50, 50);
        jl13.setLocation(3 * 50, 2 * 50);
        jl13.setText("");
        jl13.setHorizontalAlignment(JLabel.CENTER);
        jl13.setVerticalAlignment(JLabel.CENTER);
        jl13.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl13);

        JLabel jl14 = new JLabel();
        jl14.setSize(50, 50);
        jl14.setLocation(4 * 50, 2 * 50);
        jl14.setText("");
        jl14.setHorizontalAlignment(JLabel.CENTER);
        jl14.setVerticalAlignment(JLabel.CENTER);
        jl14.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl14);

        JLabel jl15 = new JLabel();
        jl15.setSize(50, 50);
        jl15.setLocation(5 * 50, 2 * 50);
        jl15.setText("");
        jl15.setHorizontalAlignment(JLabel.CENTER);
        jl15.setVerticalAlignment(JLabel.CENTER);
        jl15.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl15);

        JLabel jl16 = new JLabel();
        jl16.setSize(50, 50);
        jl16.setLocation(6 * 50, 2 * 50);
        jl16.setText("");
        jl16.setHorizontalAlignment(JLabel.CENTER);
        jl16.setVerticalAlignment(JLabel.CENTER);
        jl16.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl16);

        JLabel jl17 = new JLabel();
        jl17.setSize(50, 50);
        jl17.setLocation(7 * 50, 2 * 50);
        jl17.setText("");
        jl17.setHorizontalAlignment(JLabel.CENTER);
        jl17.setVerticalAlignment(JLabel.CENTER);
        jl17.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl17);

        JLabel jl18 = new JLabel();
        jl18.setSize(50, 50);
        jl18.setLocation(8 * 50, 2 * 50);
        jl18.setText("");
        jl18.setHorizontalAlignment(JLabel.CENTER);
        jl18.setVerticalAlignment(JLabel.CENTER);
        jl18.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl18);

        JLabel jl19 = new JLabel();
        jl19.setSize(50, 50);
        jl19.setLocation(9 * 50, 2 * 50);
        jl19.setText("");
        jl19.setHorizontalAlignment(JLabel.CENTER);
        jl19.setVerticalAlignment(JLabel.CENTER);
        jl19.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl19);

        JLabel jl20 = new JLabel();
        jl20.setSize(50, 50);
        jl20.setLocation(10 * 50, 2 * 50);
        jl20.setText("");
        jl20.setHorizontalAlignment(JLabel.CENTER);
        jl20.setVerticalAlignment(JLabel.CENTER);
        jl20.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl20);

        JLabel jl21 = new JLabel();
        jl21.setSize(50, 50);
        jl21.setLocation(1 * 50, 3 * 50);
        jl21.setText("");
        jl21.setHorizontalAlignment(JLabel.CENTER);
        jl21.setVerticalAlignment(JLabel.CENTER);
        jl21.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl21);

        JLabel jl22 = new JLabel();
        jl22.setSize(50, 50);
        jl22.setLocation(2 * 50, 3 * 50);
        jl22.setText("");
        jl22.setHorizontalAlignment(JLabel.CENTER);
        jl22.setVerticalAlignment(JLabel.CENTER);
        jl22.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl22);

        JLabel jl23 = new JLabel();
        jl23.setSize(50, 50);
        jl23.setLocation(3 * 50, 3 * 50);
        jl23.setText("");
        jl23.setHorizontalAlignment(JLabel.CENTER);
        jl23.setVerticalAlignment(JLabel.CENTER);
        jl23.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl23);

        JLabel jl24 = new JLabel();
        jl24.setSize(50, 50);
        jl24.setLocation(4 * 50, 3 * 50);
        jl24.setText("");
        jl24.setHorizontalAlignment(JLabel.CENTER);
        jl24.setVerticalAlignment(JLabel.CENTER);
        jl24.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl24);

        JLabel jl25 = new JLabel();
        jl25.setSize(50, 50);
        jl25.setLocation(5 * 50, 3 * 50);
        jl25.setText("");
        jl25.setHorizontalAlignment(JLabel.CENTER);
        jl25.setVerticalAlignment(JLabel.CENTER);
        jl25.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl25);

        JLabel jl26 = new JLabel();
        jl26.setSize(50, 50);
        jl26.setLocation(6 * 50, 3 * 50);
        jl26.setText("");
        jl26.setHorizontalAlignment(JLabel.CENTER);
        jl26.setVerticalAlignment(JLabel.CENTER);
        jl26.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl26);

        JLabel jl27 = new JLabel();
        jl27.setSize(50, 50);
        jl27.setLocation(7 * 50, 3 * 50);
        jl27.setText("");
        jl27.setHorizontalAlignment(JLabel.CENTER);
        jl27.setVerticalAlignment(JLabel.CENTER);
        jl27.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl27);

        JLabel jl28 = new JLabel();
        jl28.setSize(50, 50);
        jl28.setLocation(8 * 50, 3 * 50);
        jl28.setText("");
        jl28.setHorizontalAlignment(JLabel.CENTER);
        jl28.setVerticalAlignment(JLabel.CENTER);
        jl28.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl28);

        JLabel jl29 = new JLabel();
        jl29.setSize(50, 50);
        jl29.setLocation(9 * 50, 3 * 50);
        jl29.setText("");
        jl29.setHorizontalAlignment(JLabel.CENTER);
        jl29.setVerticalAlignment(JLabel.CENTER);
        jl29.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl29);

        JLabel jl30 = new JLabel();
        jl30.setSize(50, 50);
        jl30.setLocation(10 * 50, 3 * 50);
        jl30.setText("");
        jl30.setHorizontalAlignment(JLabel.CENTER);
        jl30.setVerticalAlignment(JLabel.CENTER);
        jl30.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl30);

        JLabel jl31 = new JLabel();
        jl31.setSize(50, 50);
        jl31.setLocation(1 * 50, 4 * 50);
        jl31.setText("");
        jl31.setHorizontalAlignment(JLabel.CENTER);
        jl31.setVerticalAlignment(JLabel.CENTER);
        jl31.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl31);

        JLabel jl32 = new JLabel();
        jl32.setSize(50, 50);
        jl32.setLocation(2 * 50, 4 * 50);
        jl32.setText("");
        jl32.setHorizontalAlignment(JLabel.CENTER);
        jl32.setVerticalAlignment(JLabel.CENTER);
        jl32.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl32);

        JLabel jl33 = new JLabel();
        jl33.setSize(50, 50);
        jl33.setLocation(3 * 50, 4 * 50);
        jl33.setText("");
        jl33.setHorizontalAlignment(JLabel.CENTER);
        jl33.setVerticalAlignment(JLabel.CENTER);
        jl33.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl33);

        JLabel jl34 = new JLabel();
        jl34.setSize(50, 50);
        jl34.setLocation(4 * 50, 4 * 50);
        jl34.setText("");
        jl34.setHorizontalAlignment(JLabel.CENTER);
        jl34.setVerticalAlignment(JLabel.CENTER);
        jl34.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl34);

        JLabel jl35 = new JLabel();
        jl35.setSize(50, 50);
        jl35.setLocation(5 * 50, 4 * 50);
        jl35.setText("");
        jl35.setHorizontalAlignment(JLabel.CENTER);
        jl35.setVerticalAlignment(JLabel.CENTER);
        jl35.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl35);

        JLabel jl36 = new JLabel();
        jl36.setSize(50, 50);
        jl36.setLocation(6 * 50, 4 * 50);
        jl36.setText("");
        jl36.setHorizontalAlignment(JLabel.CENTER);
        jl36.setVerticalAlignment(JLabel.CENTER);
        jl36.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl36);

        JLabel jl37 = new JLabel();
        jl37.setSize(50, 50);
        jl37.setLocation(7 * 50, 4 * 50);
        jl37.setText("");
        jl37.setHorizontalAlignment(JLabel.CENTER);
        jl37.setVerticalAlignment(JLabel.CENTER);
        jl37.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl37);

        JLabel jl38 = new JLabel();
        jl38.setSize(50, 50);
        jl38.setLocation(8 * 50, 4 * 50);
        jl38.setText("");
        jl38.setHorizontalAlignment(JLabel.CENTER);
        jl38.setVerticalAlignment(JLabel.CENTER);
        jl38.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl38);

        JLabel jl39 = new JLabel();
        jl39.setSize(50, 50);
        jl39.setLocation(9 * 50, 4 * 50);
        jl39.setText("");
        jl39.setHorizontalAlignment(JLabel.CENTER);
        jl39.setVerticalAlignment(JLabel.CENTER);
        jl39.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl39);

        JLabel jl40 = new JLabel();
        jl40.setSize(50, 50);
        jl40.setLocation(10 * 50, 4 * 50);
        jl40.setText("");
        jl40.setHorizontalAlignment(JLabel.CENTER);
        jl40.setVerticalAlignment(JLabel.CENTER);
        jl40.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl40);

        JLabel jl41 = new JLabel();
        jl41.setSize(50, 50);
        jl41.setLocation(1 * 50, 5 * 50);
        jl41.setText("");
        jl41.setHorizontalAlignment(JLabel.CENTER);
        jl41.setVerticalAlignment(JLabel.CENTER);
        jl41.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl41);

        JLabel jl42 = new JLabel();
        jl42.setSize(50, 50);
        jl42.setLocation(2 * 50, 5 * 50);
        jl42.setText("");
        jl42.setHorizontalAlignment(JLabel.CENTER);
        jl42.setVerticalAlignment(JLabel.CENTER);
        jl42.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl42);

        JLabel jl43 = new JLabel();
        jl43.setSize(50, 50);
        jl43.setLocation(3 * 50, 5 * 50);
        jl43.setText("");
        jl43.setHorizontalAlignment(JLabel.CENTER);
        jl43.setVerticalAlignment(JLabel.CENTER);
        jl43.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl43);

        JLabel jl44 = new JLabel();
        jl44.setSize(50, 50);
        jl44.setLocation(4 * 50, 5 * 50);
        jl44.setText("");
        jl44.setHorizontalAlignment(JLabel.CENTER);
        jl44.setVerticalAlignment(JLabel.CENTER);
        jl44.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl44);

        JLabel jl45 = new JLabel();
        jl45.setSize(50, 50);
        jl45.setLocation(5 * 50, 5 * 50);
        jl45.setText("");
        jl45.setHorizontalAlignment(JLabel.CENTER);
        jl45.setVerticalAlignment(JLabel.CENTER);
        jl45.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl45);

        JLabel jl46 = new JLabel();
        jl46.setSize(50, 50);
        jl46.setLocation(6 * 50, 5 * 50);
        jl46.setText("");
        jl46.setHorizontalAlignment(JLabel.CENTER);
        jl46.setVerticalAlignment(JLabel.CENTER);
        jl46.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl46);

        JLabel jl47 = new JLabel();
        jl47.setSize(50, 50);
        jl47.setLocation(7 * 50, 5 * 50);
        jl47.setText("");
        jl47.setHorizontalAlignment(JLabel.CENTER);
        jl47.setVerticalAlignment(JLabel.CENTER);
        jl47.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl47);

        JLabel jl48 = new JLabel();
        jl48.setSize(50, 50);
        jl48.setLocation(8 * 50, 5 * 50);
        jl48.setText("");
        jl48.setHorizontalAlignment(JLabel.CENTER);
        jl48.setVerticalAlignment(JLabel.CENTER);
        jl48.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl48);

        JLabel jl49 = new JLabel();
        jl49.setSize(50, 50);
        jl49.setLocation(9 * 50, 5 * 50);
        jl49.setText("");
        jl49.setHorizontalAlignment(JLabel.CENTER);
        jl49.setVerticalAlignment(JLabel.CENTER);
        jl49.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl49);

        JLabel jl50 = new JLabel();
        jl50.setSize(50, 50);
        jl50.setLocation(10 * 50, 5 * 50);
        jl50.setText("");
        jl50.setHorizontalAlignment(JLabel.CENTER);
        jl50.setVerticalAlignment(JLabel.CENTER);
        jl50.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl50);

        JLabel jl51 = new JLabel();
        jl51.setSize(50, 50);
        jl51.setLocation(1 * 50, 6 * 50);
        jl51.setText("");
        jl51.setHorizontalAlignment(JLabel.CENTER);
        jl51.setVerticalAlignment(JLabel.CENTER);
        jl51.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl51);

        JLabel jl52 = new JLabel();
        jl52.setSize(50, 50);
        jl52.setLocation(2 * 50, 6 * 50);
        jl52.setText("");
        jl52.setHorizontalAlignment(JLabel.CENTER);
        jl52.setVerticalAlignment(JLabel.CENTER);
        jl52.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl52);

        JLabel jl53 = new JLabel();
        jl53.setSize(50, 50);
        jl53.setLocation(3 * 50, 6 * 50);
        jl53.setText("");
        jl53.setHorizontalAlignment(JLabel.CENTER);
        jl53.setVerticalAlignment(JLabel.CENTER);
        jl53.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl53);

        JLabel jl54 = new JLabel();
        jl54.setSize(50, 50);
        jl54.setLocation(4 * 50, 6 * 50);
        jl54.setText("");
        jl54.setHorizontalAlignment(JLabel.CENTER);
        jl54.setVerticalAlignment(JLabel.CENTER);
        jl54.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl54);

        JLabel jl55 = new JLabel();
        jl55.setSize(50, 50);
        jl55.setLocation(5 * 50, 6 * 50);
        jl55.setText("");
        jl55.setHorizontalAlignment(JLabel.CENTER);
        jl55.setVerticalAlignment(JLabel.CENTER);
        jl55.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl55);

        JLabel jl56 = new JLabel();
        jl56.setSize(50, 50);
        jl56.setLocation(6 * 50, 6 * 50);
        jl56.setText("");
        jl56.setHorizontalAlignment(JLabel.CENTER);
        jl56.setVerticalAlignment(JLabel.CENTER);
        jl56.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl56);

        JLabel jl57 = new JLabel();
        jl57.setSize(50, 50);
        jl57.setLocation(7 * 50, 6 * 50);
        jl57.setText("");
        jl57.setHorizontalAlignment(JLabel.CENTER);
        jl57.setVerticalAlignment(JLabel.CENTER);
        jl57.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl57);

        JLabel jl58 = new JLabel();
        jl58.setSize(50, 50);
        jl58.setLocation(8 * 50, 6 * 50);
        jl58.setText("");
        jl58.setHorizontalAlignment(JLabel.CENTER);
        jl58.setVerticalAlignment(JLabel.CENTER);
        jl58.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl58);

        JLabel jl59 = new JLabel();
        jl59.setSize(50, 50);
        jl59.setLocation(9 * 50, 6 * 50);
        jl59.setText("");
        jl59.setHorizontalAlignment(JLabel.CENTER);
        jl59.setVerticalAlignment(JLabel.CENTER);
        jl59.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl59);

        JLabel jl60 = new JLabel();
        jl60.setSize(50, 50);
        jl60.setLocation(10 * 50, 6 * 50);
        jl60.setText("");
        jl60.setHorizontalAlignment(JLabel.CENTER);
        jl60.setVerticalAlignment(JLabel.CENTER);
        jl60.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl60);

        JLabel jl61 = new JLabel();
        jl61.setSize(50, 50);
        jl61.setLocation(1 * 50, 7 * 50);
        jl61.setText("");
        jl61.setHorizontalAlignment(JLabel.CENTER);
        jl61.setVerticalAlignment(JLabel.CENTER);
        jl61.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl61);

        JLabel jl62 = new JLabel();
        jl62.setSize(50, 50);
        jl62.setLocation(2 * 50, 7 * 50);
        jl62.setText("");
        jl62.setHorizontalAlignment(JLabel.CENTER);
        jl62.setVerticalAlignment(JLabel.CENTER);
        jl62.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl62);

        JLabel jl63 = new JLabel();
        jl63.setSize(50, 50);
        jl63.setLocation(3 * 50, 7 * 50);
        jl63.setText("");
        jl63.setHorizontalAlignment(JLabel.CENTER);
        jl63.setVerticalAlignment(JLabel.CENTER);
        jl63.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl63);

        JLabel jl64 = new JLabel();
        jl64.setSize(50, 50);
        jl64.setLocation(4 * 50, 7 * 50);
        jl64.setText("");
        jl64.setHorizontalAlignment(JLabel.CENTER);
        jl64.setVerticalAlignment(JLabel.CENTER);
        jl64.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl64);

        JLabel jl65 = new JLabel();
        jl65.setSize(50, 50);
        jl65.setLocation(5 * 50, 7 * 50);
        jl65.setText("");
        jl65.setHorizontalAlignment(JLabel.CENTER);
        jl65.setVerticalAlignment(JLabel.CENTER);
        jl65.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl65);

        JLabel jl66 = new JLabel();
        jl66.setSize(50, 50);
        jl66.setLocation(6 * 50, 7 * 50);
        jl66.setText("");
        jl66.setHorizontalAlignment(JLabel.CENTER);
        jl66.setVerticalAlignment(JLabel.CENTER);
        jl66.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl66);

        JLabel jl67 = new JLabel();
        jl67.setSize(50, 50);
        jl67.setLocation(7 * 50, 7 * 50);
        jl67.setText("");
        jl67.setHorizontalAlignment(JLabel.CENTER);
        jl67.setVerticalAlignment(JLabel.CENTER);
        jl67.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl67);

        JLabel jl68 = new JLabel();
        jl68.setSize(50, 50);
        jl68.setLocation(8 * 50, 7 * 50);
        jl68.setText("");
        jl68.setHorizontalAlignment(JLabel.CENTER);
        jl68.setVerticalAlignment(JLabel.CENTER);
        jl68.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl68);

        JLabel jl69 = new JLabel();
        jl69.setSize(50, 50);
        jl69.setLocation(9 * 50, 7 * 50);
        jl69.setText("");
        jl69.setHorizontalAlignment(JLabel.CENTER);
        jl69.setVerticalAlignment(JLabel.CENTER);
        jl69.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl69);

        JLabel jl70 = new JLabel();
        jl70.setSize(50, 50);
        jl70.setLocation(10 * 50, 7 * 50);
        jl70.setText("");
        jl70.setHorizontalAlignment(JLabel.CENTER);
        jl70.setVerticalAlignment(JLabel.CENTER);
        jl70.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl70);

        JLabel jl71 = new JLabel();
        jl71.setSize(50, 50);
        jl71.setLocation(1 * 50, 8 * 50);
        jl71.setText("");
        jl71.setHorizontalAlignment(JLabel.CENTER);
        jl71.setVerticalAlignment(JLabel.CENTER);
        jl71.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl71);

        JLabel jl72 = new JLabel();
        jl72.setSize(50, 50);
        jl72.setLocation(2 * 50, 8 * 50);
        jl72.setText("");
        jl72.setHorizontalAlignment(JLabel.CENTER);
        jl72.setVerticalAlignment(JLabel.CENTER);
        jl72.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl72);

        JLabel jl73 = new JLabel();
        jl73.setSize(50, 50);
        jl73.setLocation(3 * 50, 8 * 50);
        jl73.setText("");
        jl73.setHorizontalAlignment(JLabel.CENTER);
        jl73.setVerticalAlignment(JLabel.CENTER);
        jl73.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl73);

        JLabel jl74 = new JLabel();
        jl74.setSize(50, 50);
        jl74.setLocation(4 * 50, 8 * 50);
        jl74.setText("");
        jl74.setHorizontalAlignment(JLabel.CENTER);
        jl74.setVerticalAlignment(JLabel.CENTER);
        jl74.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl74);

        JLabel jl75 = new JLabel();
        jl75.setSize(50, 50);
        jl75.setLocation(5 * 50, 8 * 50);
        jl75.setText("");
        jl75.setHorizontalAlignment(JLabel.CENTER);
        jl75.setVerticalAlignment(JLabel.CENTER);
        jl75.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl75);

        JLabel jl76 = new JLabel();
        jl76.setSize(50, 50);
        jl76.setLocation(6 * 50, 8 * 50);
        jl76.setText("");
        jl76.setHorizontalAlignment(JLabel.CENTER);
        jl76.setVerticalAlignment(JLabel.CENTER);
        jl76.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl76);

        JLabel jl77 = new JLabel();
        jl77.setSize(50, 50);
        jl77.setLocation(7 * 50, 8 * 50);
        jl77.setText("");
        jl77.setHorizontalAlignment(JLabel.CENTER);
        jl77.setVerticalAlignment(JLabel.CENTER);
        jl77.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl77);

        JLabel jl78 = new JLabel();
        jl78.setSize(50, 50);
        jl78.setLocation(8 * 50, 8 * 50);
        jl78.setText("");
        jl78.setHorizontalAlignment(JLabel.CENTER);
        jl78.setVerticalAlignment(JLabel.CENTER);
        jl78.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl78);

        JLabel jl79 = new JLabel();
        jl79.setSize(50, 50);
        jl79.setLocation(9 * 50, 8 * 50);
        jl79.setText("");
        jl79.setHorizontalAlignment(JLabel.CENTER);
        jl79.setVerticalAlignment(JLabel.CENTER);
        jl79.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl79);

        JLabel jl80 = new JLabel();
        jl80.setSize(50, 50);
        jl80.setLocation(10 * 50, 8 * 50);
        jl80.setText("");
        jl80.setHorizontalAlignment(JLabel.CENTER);
        jl80.setVerticalAlignment(JLabel.CENTER);
        jl80.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl80);

        JLabel jl81 = new JLabel();
        jl81.setSize(50, 50);
        jl81.setLocation(1 * 50, 9 * 50);
        jl81.setText("");
        jl81.setHorizontalAlignment(JLabel.CENTER);
        jl81.setVerticalAlignment(JLabel.CENTER);
        jl81.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl81);

        JLabel jl82 = new JLabel();
        jl82.setSize(50, 50);
        jl82.setLocation(2 * 50, 9 * 50);
        jl82.setText("");
        jl82.setHorizontalAlignment(JLabel.CENTER);
        jl82.setVerticalAlignment(JLabel.CENTER);
        jl82.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl82);

        JLabel jl83 = new JLabel();
        jl83.setSize(50, 50);
        jl83.setLocation(3 * 50, 9 * 50);
        jl83.setText("");
        jl83.setHorizontalAlignment(JLabel.CENTER);
        jl83.setVerticalAlignment(JLabel.CENTER);
        jl83.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl83);

        JLabel jl84 = new JLabel();
        jl84.setSize(50, 50);
        jl84.setLocation(4 * 50, 9 * 50);
        jl84.setText("");
        jl84.setHorizontalAlignment(JLabel.CENTER);
        jl84.setVerticalAlignment(JLabel.CENTER);
        jl84.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl84);

        JLabel jl85 = new JLabel();
        jl85.setSize(50, 50);
        jl85.setLocation(5 * 50, 9 * 50);
        jl85.setText("");
        jl85.setHorizontalAlignment(JLabel.CENTER);
        jl85.setVerticalAlignment(JLabel.CENTER);
        jl85.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl85);

        JLabel jl86 = new JLabel();
        jl86.setSize(50, 50);
        jl86.setLocation(6 * 50, 9 * 50);
        jl86.setText("");
        jl86.setHorizontalAlignment(JLabel.CENTER);
        jl86.setVerticalAlignment(JLabel.CENTER);
        jl86.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl86);

        JLabel jl87 = new JLabel();
        jl87.setSize(50, 50);
        jl87.setLocation(7 * 50, 9 * 50);
        jl87.setText("");
        jl87.setHorizontalAlignment(JLabel.CENTER);
        jl87.setVerticalAlignment(JLabel.CENTER);
        jl87.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl87);

        JLabel jl88 = new JLabel();
        jl88.setSize(50, 50);
        jl88.setLocation(8 * 50, 9 * 50);
        jl88.setText("");
        jl88.setHorizontalAlignment(JLabel.CENTER);
        jl88.setVerticalAlignment(JLabel.CENTER);
        jl88.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl88);

        JLabel jl89 = new JLabel();
        jl89.setSize(50, 50);
        jl89.setLocation(9 * 50, 9 * 50);
        jl89.setText("");
        jl89.setHorizontalAlignment(JLabel.CENTER);
        jl89.setVerticalAlignment(JLabel.CENTER);
        jl89.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl89);

        JLabel jl90 = new JLabel();
        jl90.setSize(50, 50);
        jl90.setLocation(10 * 50, 9 * 50);
        jl90.setText("");
        jl90.setHorizontalAlignment(JLabel.CENTER);
        jl90.setVerticalAlignment(JLabel.CENTER);
        jl90.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl90);

        JLabel jl91 = new JLabel();
        jl91.setSize(50, 50);
        jl91.setLocation(1 * 50, 10 * 50);
        jl91.setText("");
        jl91.setHorizontalAlignment(JLabel.CENTER);
        jl91.setVerticalAlignment(JLabel.CENTER);
        jl91.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl91);

        JLabel jl92 = new JLabel();
        jl92.setSize(50, 50);
        jl92.setLocation(2 * 50, 10 * 50);
        jl92.setText("");
        jl92.setHorizontalAlignment(JLabel.CENTER);
        jl92.setVerticalAlignment(JLabel.CENTER);
        jl92.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl92);

        JLabel jl93 = new JLabel();
        jl93.setSize(50, 50);
        jl93.setLocation(3 * 50, 10 * 50);
        jl93.setText("");
        jl93.setHorizontalAlignment(JLabel.CENTER);
        jl93.setVerticalAlignment(JLabel.CENTER);
        jl93.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl93);

        JLabel jl94 = new JLabel();
        jl94.setSize(50, 50);
        jl94.setLocation(4 * 50, 10 * 50);
        jl94.setText("");
        jl94.setHorizontalAlignment(JLabel.CENTER);
        jl94.setVerticalAlignment(JLabel.CENTER);
        jl94.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl94);

        JLabel jl95 = new JLabel();
        jl95.setSize(50, 50);
        jl95.setLocation(5 * 50, 10 * 50);
        jl95.setText("");
        jl95.setHorizontalAlignment(JLabel.CENTER);
        jl95.setVerticalAlignment(JLabel.CENTER);
        jl95.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl95);

        JLabel jl96 = new JLabel();
        jl96.setSize(50, 50);
        jl96.setLocation(6 * 50, 10 * 50);
        jl96.setText("");
        jl96.setHorizontalAlignment(JLabel.CENTER);
        jl96.setVerticalAlignment(JLabel.CENTER);
        jl96.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl96);

        JLabel jl97 = new JLabel();
        jl97.setSize(50, 50);
        jl97.setLocation(7 * 50, 10 * 50);
        jl97.setText("");
        jl97.setHorizontalAlignment(JLabel.CENTER);
        jl97.setVerticalAlignment(JLabel.CENTER);
        jl97.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl97);

        JLabel jl98 = new JLabel();
        jl98.setSize(50, 50);
        jl98.setLocation(8 * 50, 10 * 50);
        jl98.setText("");
        jl98.setHorizontalAlignment(JLabel.CENTER);
        jl98.setVerticalAlignment(JLabel.CENTER);
        jl98.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl98);

        JLabel jl99 = new JLabel();
        jl99.setSize(50, 50);
        jl99.setLocation(9 * 50, 10 * 50);
        jl99.setText("");
        jl99.setHorizontalAlignment(JLabel.CENTER);
        jl99.setVerticalAlignment(JLabel.CENTER);
        jl99.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl99);

        JLabel jl100 = new JLabel();
        jl100.setSize(50, 50);
        jl100.setLocation(10 * 50, 10 * 50);
        jl100.setText("");
        jl100.setHorizontalAlignment(JLabel.CENTER);
        jl100.setVerticalAlignment(JLabel.CENTER);
        jl100.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jl100);

        labels[0][1] = jl1;
        labels[0][2] = jl2;
        labels[0][3] = jl3;
        labels[0][4] = jl4;
        labels[0][5] = jl5;
        labels[0][6] = jl6;
        labels[0][7] = jl7;
        labels[0][8] = jl8;
        labels[0][9] = jl9;
        labels[1][0] = jl10;
        labels[1][1] = jl11;
        labels[1][2] = jl12;
        labels[1][3] = jl13;
        labels[1][4] = jl14;
        labels[1][5] = jl15;
        labels[1][6] = jl16;
        labels[1][7] = jl17;
        labels[1][8] = jl18;
        labels[1][9] = jl19;
        labels[2][0] = jl20;
        labels[2][1] = jl21;
        labels[2][2] = jl22;
        labels[2][3] = jl23;
        labels[2][4] = jl24;
        labels[2][5] = jl25;
        labels[2][6] = jl26;
        labels[2][7] = jl27;
        labels[2][8] = jl28;
        labels[2][9] = jl29;
        labels[3][0] = jl30;
        labels[3][1] = jl31;
        labels[3][2] = jl32;
        labels[3][3] = jl33;
        labels[3][4] = jl34;
        labels[3][5] = jl35;
        labels[3][6] = jl36;
        labels[3][7] = jl37;
        labels[3][8] = jl38;
        labels[3][9] = jl39;
        labels[4][0] = jl40;
        labels[4][1] = jl41;
        labels[4][2] = jl42;
        labels[4][3] = jl43;
        labels[4][4] = jl44;
        labels[4][5] = jl45;
        labels[4][6] = jl46;
        labels[4][7] = jl47;
        labels[4][8] = jl48;
        labels[4][9] = jl49;
        labels[5][0] = jl50;
        labels[5][1] = jl51;
        labels[5][2] = jl52;
        labels[5][3] = jl53;
        labels[5][4] = jl54;
        labels[5][5] = jl55;
        labels[5][6] = jl56;
        labels[5][7] = jl57;
        labels[5][8] = jl58;
        labels[5][9] = jl59;
        labels[6][0] = jl60;
        labels[6][1] = jl61;
        labels[6][2] = jl62;
        labels[6][3] = jl63;
        labels[6][4] = jl64;
        labels[6][5] = jl65;
        labels[6][6] = jl66;
        labels[6][7] = jl67;
        labels[6][8] = jl68;
        labels[6][9] = jl69;
        labels[7][0] = jl70;
        labels[7][1] = jl71;
        labels[7][2] = jl72;
        labels[7][3] = jl73;
        labels[7][4] = jl74;
        labels[7][5] = jl75;
        labels[7][6] = jl76;
        labels[7][7] = jl77;
        labels[7][8] = jl78;
        labels[7][9] = jl79;
        labels[8][0] = jl80;
        labels[8][1] = jl81;
        labels[8][2] = jl82;
        labels[8][3] = jl83;
        labels[8][4] = jl84;
        labels[8][5] = jl85;
        labels[8][6] = jl86;
        labels[8][7] = jl87;
        labels[8][8] = jl88;
        labels[8][9] = jl89;
        labels[9][0] = jl90;
        labels[9][1] = jl91;
        labels[9][2] = jl92;
        labels[9][3] = jl93;
        labels[9][4] = jl94;
        labels[9][5] = jl95;
        labels[9][6] = jl96;
        labels[9][7] = jl97;
        labels[9][8] = jl98;
        labels[9][9] = jl99;
        labels[10][0] = jl100;

        JLabel jlTime = new JLabel();
        jlTime.setSize(200, 30);
        jlTime.setLocation(1 * 50, 11 * 50 + 10);
        jlTime.setText("Time: ");
        jlTime.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jlTime);
        time = jlTime;

        JLabel jlLevel = new JLabel();
        jlLevel.setSize(200, 20);
        jlLevel.setLocation(1 * 50, 11 * 50 + 50);
        jlLevel.setText("Level: " + lvl);
        jlLevel.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jlLevel);
        level = jlLevel;
        
        JLabel jlFruit = new JLabel();
        jlFruit.setSize(300, 20);
        jlFruit.setLocation(1 * 50, 11 * 50 + 70);
        jlFruit.setText("How many Fruits you got: 0");
        jlFruit.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jlFruit);
        fruit = jlFruit;
        
        JLabel jlDes = new JLabel();
        jlDes.setSize(500, 20);
        jlDes.setLocation(1 * 50, 11 * 50 + 110);
        jlDes.setText("% - snake head     $ - snake body    + - rock    @ - fruit");
        jlDes.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jlDes);
        
        JLabel jlDes2 = new JLabel();
        jlDes2.setSize(500, 20);
        jlDes2.setLocation(1 * 50, 11 * 50 + 130);
        jlDes2.setText("w - up     a - left    s - down    d - right");
        jlDes2.setFont(new Font("Serif", Font.BOLD, 17));
        frame.add(jlDes2);
        
        
        snake.setSize(40, 40);
        snake.setLocation(firstConverter(56)*50 + 10, secondConverter(56) * 50 + 10);
        
        
        apple.setSize(40, 40);        
        frame.getContentPane().add(snake);
        
        //labels[5][6].setText("%");
        //labels[5][6].setForeground(Color.GREEN);
        
        frame.add(panel);
        frame.setLayout(null); 
        frame.setVisible(true);
        
        makeRocks();
        createFruit();
        frame.getContentPane().add(apple);
    }   
    
    public void changeText(int num, String st, int color){
        int first = num / 10;
        int second = num % 10;  
        
        labels[first][second].setText(st);
        if(color == 1){
            labels[first][second].setForeground(Color.RED);
        }else if(color == 2){
        labels[first][second].setForeground(Color.GREEN);
        }
        
    }
    
    public void changeTime(int t){
        time.setText("Time: " + t);
    }
    
    public void changeLevel(int l){
        level.setText("Level: " + l);
    }
    
    public void updateFruit(int f){
        fruit.setText("How many Fruits you got: " + f);
    }
    
    public void resetCurrent(int num){
        currentHead = currentHead + num;
    }
    
    public void handleKeyPress(KeyEvent e){
        int key = e.getKeyCode();
        
        switch(key){
            case KeyEvent.VK_W:
                if(!crashWithBoarder(currentHead, 1)){
                    break;
                }
                crashWithBoarder(currentHead, 1);
                //changeText(currentHead - 10, "%", 2);
                //changeText(currentHead, "", 3);
                crashWithRock(currentHead - 10);
                resetCurrent(-10);
                moveSnake(currentHead);
                //System.out.println(currentHead);
                
                occupiedSnake.add(currentHead);
                getFruit(currentHead);
                snakeBody();
                //System.out.println("HashMap after moving: " + body);
                win();
                break;
            case KeyEvent.VK_A:
                if(!crashWithBoarder(currentHead, 2)){
                    break;
                }
                //changeText(currentHead - 1, "%", 2);
                //changeText(currentHead, "", 3);
                crashWithRock(currentHead - 1);
                resetCurrent(-1);
                moveSnake(currentHead);
                //System.out.println(currentHead);
                occupiedSnake.add(currentHead);
                getFruit(currentHead);
                snakeBody();
                //System.out.println("HashMap after moving: " + body);
                win();
                break;
            case KeyEvent.VK_S:
                if(!crashWithBoarder(currentHead, 3)){
                    break;
                }
                //changeText(currentHead + 10, "%", 2);
                //changeText(currentHead, "", 3);
                crashWithRock(currentHead + 10);
                resetCurrent(10);
                moveSnake(currentHead);
                //System.out.println(currentHead);
                occupiedSnake.add(currentHead);
                getFruit(currentHead);
                snakeBody();
                //System.out.println("HashMap after moving: " + body);
                win();
                break;
            case KeyEvent.VK_D:
                if(!crashWithBoarder(currentHead, 4)){
                    break;
                }
                //changeText(currentHead + 1, "%", 2);
                //changeText(currentHead, "", 3);
                crashWithRock(currentHead + 1);                
                resetCurrent(1);
                moveSnake(currentHead);
                //System.out.println(currentHead);
                                //
                occupiedSnake.add(currentHead);
                getFruit(currentHead);
                snakeBody();
                //System.out.println("HashMap after moving: " + body);
                win();
                break;
        }
    }
    
    Timer timer = new Timer(initialDelay, new ActionListener() {
        //int count = 20; // カウントダウンの初期値
        @Override
        public void actionPerformed(ActionEvent e) {
            changeTime(cnt);
            cnt--;
            spentTime++;
            if (cnt < 0) {
                gameOver("time over");
            }
        }
    }); 
    
    Timer timer2 = new Timer(initialDelay, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            cnt2--;
            if (cnt2 < 0) {
                timer2.stop();
                initComponents();
            }
        }
    }); 
    
      
    public void makeRocks(){
        occupiedRocks.add(56);

        for(int i = 0; i < lvl; i++){
            int randomValue;
            do{
                randomValue = random.nextInt(100);
            }while(occupiedRocks.contains(randomValue) || randomValue == 0);
            occupiedRocks.add(randomValue);
            SpriteRock spriteRock = new SpriteRock();
            spriteRock.setSize(40, 40);
            spriteRock.setLocation(firstConverter(randomValue)*50 + 10, secondConverter(randomValue) * 50 + 10);
            frame.getContentPane().add(spriteRock);
        }
        occupiedRocks.remove(Integer.valueOf(56));   
    }
    
    
    public void crashWithRock(int num){
        if(occupiedRocks.contains(num)){
            gameOver("crash with rock");
        }
    }
    
    public boolean crashWithBoarder(int num, int b){
        if(b == 1 && (num == 1 || num == 2 || num == 3 || num == 4 || num == 5 || num == 6 || num == 7 || num == 8 || num == 9 || num == 10)){
            gameOver("crash with boader");
            return false;
        }else if(b == 2 && (num == 1 || num == 11 || num == 21 || num == 31 || num == 41 || num == 51 || num == 61 || num == 71 || num == 81 || num == 91)){
            gameOver("crash with boader");
            return false;
        }else if(b == 3 && (num == 100 || num == 91 || num == 92 || num == 93 || num == 94 || num == 95 || num == 96 || num == 97 || num == 98 || num == 99)){
            gameOver("crash with boader");
            return false;
        }else if(b == 4 && (num == 100 || num == 10 || num == 20 || num == 30 || num == 40 || num == 50 || num == 60 || num == 70 || num == 80 || num == 90)){
            gameOver("crash with boader");
            return false;
        }
        return true;
    }
    
    public void createFruit(){
        occupiedRocks.add(56);
        int randomValue;
        do{
            randomValue = random.nextInt(100);
        }while(occupiedRocks.contains(randomValue) || occupiedSnake.contains(randomValue) || randomValue == 0);
        occupiedRocks.remove(Integer.valueOf(56));
        
        apple.setLocation(firstConverter(randomValue) * 50 + 10, secondConverter(randomValue) * 50 + 10);
        fruitPosi = randomValue;
        //System.out.println(randomValue);
    }
    
    public void getFruit(int num){
        if(num == fruitPosi){
            fruits++;
            createFruit();
            updateFruit(fruits);

            for (Map.Entry<Integer, Integer> entry : body.entrySet()) {
            //System.out.println("Before: " + entry.getKey() + ", " + entry.getValue());
            entry.setValue(entry.getValue() + 1);
            //System.out.println("After: " + entry.getKey() + ", " + entry.getValue());
            }
        }
        //System.out.println(fruits);
    }
    
    public void snakeBody(){
        for (Map.Entry<Integer, Integer> entry : body.entrySet()) {
            //System.out.println("Before: " + entry.getKey() + ", " + entry.getValue());
            entry.setValue(entry.getValue() - 1);
            //System.out.println("After: " + entry.getKey() + ", " + entry.getValue());
        }
        Iterator<Map.Entry<Integer, Integer>> iterator = body.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator.next();
            if (entry.getValue() <= -1) {
                changeText(entry.getKey(), "", 3);
                iterator.remove();
                occupiedSnake.remove(entry.getKey());
            }
        }
        body.put(currentHead, fruits);
        for (Map.Entry<Integer, Integer> entry : body.entrySet()) {
            changeText(entry.getKey(), "$", 2);
            if(entry.getValue() == fruits){
                changeText(entry.getKey(), "%", 2);
            }
        }
    }
    
    public void win(){
        if(fruits == criteria){
            timer.stop();
            if (levelClearListener instanceof Snake) {
                ((Snake) levelClearListener).updataFruit(fruits);
                ((Snake) levelClearListener).updataTime(cnt);
                System.out.println(((Snake)levelClearListener).getFruit());
                System.out.println(((Snake)levelClearListener).getTime());
            }
            System.out.println("end");
            JOptionPane.showMessageDialog(frame, "level " + lvl + " clear!");
            frame.dispose();
            
            if(levelClearListener != null){
                
                levelClearListener.onLevelClear(lvl);
            }
        }
    }
        
    public void gameOver(String st) {
        timer.stop();

        if (levelClearListener instanceof Snake) {
            ((Snake) levelClearListener).updataFruit(fruits);
            ((Snake) levelClearListener).updataTime(cnt);
            JOptionPane.showMessageDialog(frame, st + "\n" + "Your scores: " + ((Snake) levelClearListener).getScore());
            RegisterScore registerScore = new RegisterScore(((Snake) levelClearListener).getScore());
            frame.dispose();
        } else {
            JOptionPane.showMessageDialog(frame, st + "\n" + "Your scores: " + ((Snake) levelClearListener).getScore());
            RegisterScore registerScore = new RegisterScore(((Snake) levelClearListener).getScore());
            frame.dispose();
        }
    }
    
    public int firstConverter(int num){
        if(num % 10 == 0){
            return 10;
        }else{
            return num % 10;
        }
    }
    public int secondConverter(int num){
        if(num < 10){
            return 1;
        }
        if(num % 10 == 0){
            return num / 10;
        }else{
            return num / 10 + 1;
        }
    }

    public void moveSnake(int num){
        snake.setLocation(firstConverter(num)*50 + 10, secondConverter(num) * 50 + 10);
        //System.out.println(firstConverter(num) + "" + secondConverter(num));
    }
}
