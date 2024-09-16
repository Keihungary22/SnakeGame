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
public class SpriteApple extends JLabel{
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        drawCustomGraphics(g);
    }
    private void drawCustomGraphics(Graphics g) {
        // Set the color for the apple
        g.setColor(Color.RED);
        // Draw the apple body
        g.fillOval(0, 5, 25, 25);
        // Draw the apple stem
        g.setColor(Color.BLACK);
        g.fillRect(10, 0, 5, 5);
    }
}
