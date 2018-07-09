
package Juego;

import java.awt.Color;

/**
 *
 * @author Josué González <00034715@uca.edu.sv>
 */
public abstract class MovingGameObject extends GameObject implements Moveable{
    
    int xVel;
    int yVel;
    
    public MovingGameObject(int xPosition, int yPosition, int xVelocity, int yVelocity, Color color)
    {
        super(xPosition, yPosition, color);
        this.xVel = xVelocity;
        this.yVel = yVelocity;
    
    }
    
    public int getXVelocity()
    {
        return xVel;
    }
    public int getYVelocity()
    {
        return yVel;
    }
    public void setXVelocity(int xVelocity)
    {
        this.xVel = xVelocity;
    }
    public void setYVelocity(int yVelocity)
    {
        this.yVel = yVelocity;
    }
    
    @Override
    
    public void move()
    {
        this.xPos += xVel;
        this.yPos += yVel;
    }
    
}
