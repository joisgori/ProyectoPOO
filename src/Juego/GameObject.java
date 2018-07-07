/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import java.awt.Color;
import java.awt.Rectangle;

/**
 *
 * @author Roxana
 */
public abstract class GameObject implements Drawable {
    int xPos;
    int yPos;
    Color color;
    boolean isColliding;
    
    public GameObject(){};
    
    // Constructor para cualquier objeto Gamer
    public GameObject(int xPosition, int yPosition, Color color) {
        this.xPos = xPosition;
        this.yPos = yPosition;
        this.color = color;
    }

    public abstract Rectangle getBounds();

    // Obtiene la posicion X de cualquier objeto
    public int getXPosition() {
        return xPos;
    }

    // Obtiene la posicion Y de cualquier objeto
    public int getYPosition() {
        return yPos;
    }

    // Obtiene el color de cualquier objeto
    public Color getColor() {
        return color;
    }

    // Coloca la posicion X de cualquier objeto
    public void setXPosition(int xPosition) {
        this.xPos = xPosition;
    }

    // Coloca la posicion X de cualquier objeto
    public void setYPosition(int yPosition) {
        this.yPos = yPosition;
    }

    // Coloca el color de cualquier objeto
    public void setColor(Color color) {
        this.color = color;
    }

    // Comprueba si el espacio de golpe de dos objetos se intersectan
    public boolean isColliding(GameObject other) {
        isColliding = other.getBounds().intersects(this.getBounds());
        return isColliding;
    }
}
