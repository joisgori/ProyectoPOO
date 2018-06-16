/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author aacm12
 */
public class CirculoGrafico extends Circulo implements Dibujable{
    
    Color color;
    
    public CirculoGrafico(Coordenada cor, float radio, Color uncolor){
        super(cor,radio);
        this.color=uncolor;
    }
    @Override
    public void Dibujar(Graphics dw) {
        dw.setColor(color);
        dw.fillOval((int) (this.getX()-this.getRadio()), (int) (this.getY() - this.getRadio()), (int)(2*this.getRadio()),(int)(2*this.getRadio()));
    }
    
}
