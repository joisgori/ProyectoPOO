/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

/**
 *
 * @author aacm12
 */
public class NaveGrafico extends Nave implements Dibujable{

    Color color;
    
    public NaveGrafico(Coordenada a,Coordenada b,Coordenada c, Color uncolor){
        super(a,b,c);
        this.color=uncolor;
    }
    
    @Override
    public void Dibujar(Graphics dw) {
        dw.setColor(color);
        int x[]={(int)this.getX(),(int) this.cor1.getX(),(int)this.cor2.getX()};
        int y[]={(int)this.getY(),(int) this.cor1.getY(),(int)this.cor2.getY()};
        
        Polygon p = new Polygon(x,y,3);
        dw.fillPolygon(p);
    }
    
    public void Pintar(Graphics dw, Color uncolor) {
        dw.setColor(uncolor);
        int x[]={(int)this.getX(),(int) this.cor1.getX(),(int)this.cor2.getX()};
        int y[]={(int)this.getY(),(int) this.cor1.getY(),(int)this.cor2.getY()};
        
        Polygon p = new Polygon(x,y,3);
        dw.fillPolygon(p);
    }
    
}
