/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment32;

import javax.swing.JFrame;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author nakan
 */
public class Snake implements LevelClearListener{
    
    private int level = 1;
    private int time = 0;
    private int fruit = 0; 
    
    public Snake(){
        
        initComponents();
        //Field.labels[0][0].setText("kei");
        //System.out.println(fruit);
    }
    
    private void initComponents(){
        Field field = new Field(1, 60, 5, this);
    }
    
    @Override
    public void onLevelClear(int level){
        if(level == 1){
            Field field2 = new Field(2, 55, 5, this);
        }else if(level == 2){
            Field field3 = new Field(3, 50, 5, this);
        }else if(level == 3){
            Field field4 = new Field(4, 45, 5, this);
        }else if(level == 4){
            Field field5 = new Field(5, 40, 5, this);
        }else if(level == 5){
            Field field6 = new Field(6, 35, 5, this);
        }else if(level == 6){
            Field field7 = new Field(7, 30, 5, this);
        }else if(level == 7){
            Field field8 = new Field(8, 25, 5, this);
        }else if(level == 8){
            Field field9 = new Field(9, 20, 5, this);
        }else if(level == 9){
            Field field10 = new Field(10, 20, 5, this);
        }else if(level == 10){
            Field field11 = new Field(11, 40, 3, this);
        }
    }
    
    public void updataFruit(int fruits){
        fruit += fruits;
    }
    public int getFruit(){
        return fruit;
    }
    
    public void updataTime(int rTime){
        time += rTime;
    }
    public int getTime(){
        return time;
    }
    
    public int getScore(){
        return fruit * time / 10;
    }
    
}
