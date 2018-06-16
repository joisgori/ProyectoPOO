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
 * @author josue
 */
public class RectanguloGrafico extends Rectangulo implements Dibujable {

    Color color;

    public RectanguloGrafico() {

    }

    public RectanguloGrafico(Color color, float x, float y, Coordenada cor) {
        super(x, y, cor);
        this.color = color;
    }
    
    

    @Override
    public void Dibujar(Graphics dw) {
        dw.setColor(color);
        dw.fillRect((int) this.getX(), (int) this.getY(), (int) this.getLado(1), (int) this.getLado(2));
    }

}

