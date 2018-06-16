/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author josue
 */
public class Coordenada {
    
    private float x, y;

    public Coordenada() {
        this.x = 0;
        this.y = 0;
    }

    public Coordenada(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public Coordenada(Coordenada nva){
        this.x = nva.x;
        this.y = nva.y;
    }
    
    //Métodos getter y setter:

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
    
    public Coordenada Suma(Coordenada S){
        
        float Sumx=this.x + S.getX();
        float Sumy=this.y + S.getY();
        
        Coordenada Cor = new Coordenada(Sumx , Sumy);
        
        
        return Cor;
    }
    
}
