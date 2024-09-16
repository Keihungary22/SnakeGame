/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment32;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author nakan
 */
public class SpriteSnake extends JLabel{
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        drawCustomGraphics(g);
    }
    private void drawCustomGraphics(Graphics g) {
        
        // Set the color for the snake
        g.setColor(Color.GREEN);

        // Draw the snake's head
        g.fillOval(10, 10, 15, 15);

        // Set the color for the eyes
        g.setColor(Color.BLACK);

        // Draw the snake's eyes
        g.fillOval(10, 10, 5, 5);
        g.fillOval(15, 10, 5, 5);
    }
}
