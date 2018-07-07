/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import Controladores.KeyboardController;
import java.awt.Color;

/**
 *
 * @author Roxana
 */
public abstract class ControlledGameObject extends GameObject implements Moveable {
    KeyboardController control;
    
    // Constructor para cualquier objeto controlable.
    public ControlledGameObject(int xPosition, int yPosition, Color color, KeyboardController control)
    {
        super(xPosition, yPosition, color);
        this.control = control;
    }
}
