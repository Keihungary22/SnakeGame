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
public class SpriteRock extends JLabel{
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        drawCustomGraphics(g);
    }
    private void drawCustomGraphics(Graphics g) {
       
        g.setColor(Color.GRAY);
        int[] xPoints = {9, 0, 17};
        int[] yPoints = {0, 15, 15};
        g.fillPolygon(xPoints, yPoints, 3);   
    }  
}
