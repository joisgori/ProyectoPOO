
package Juego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javafx.scene.shape.Ellipse;
import javax.swing.ImageIcon;

/**
 *
 * @author Josué González <00034715@uca.edu.sv>
 */
public class Beam extends MovingGameObject {


    public Beam(int xPosition, int yPosition, int diameter, Color color) {
        super(xPosition, yPosition, 0, 0, color);
    }
    

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(this.getXPosition(), this.getYPosition(), 7, 15);
    }
    

    @Override
    public Rectangle getBounds() {
        Rectangle beamHitbox = new Rectangle(xPos, yPos, 7, 15);
        return beamHitbox;
    }
}
