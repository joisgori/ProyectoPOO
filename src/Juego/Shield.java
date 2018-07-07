/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import Controladores.KeyboardController;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author Roxana
 */
public class Shield extends GameObject{
    int width;
    int height;

    //Constructor del escudo
    public Shield(int xPosition, int yPosition, int width, int height, Color color) {
        super(xPosition, yPosition, color);
        this.width = width;
        this.height = height;

    }

    // Getter y setters para cada parte del contructor del escudo
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    // Utilizado para dibujar los objetos escudo
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(this.getXPosition(), this.getYPosition(), 90, 10);
    }

    // Utilizado para obtener el espacio del golpe de un objeto escudo.
    @Override
    public Rectangle getBounds() {
        Rectangle shieldHitbox = new Rectangle(this.getXPosition(), this.getYPosition(), 90, 10);
        return shieldHitbox;
    }

}
