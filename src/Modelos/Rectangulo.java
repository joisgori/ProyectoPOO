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
public class Rectangulo extends Coordenada {
    
    private float lado1, lado2;

    public Rectangulo() {
        super();
        this.lado1 = 0;
        this.lado2 = 0;
    }

    public Rectangulo(float x, float y, Coordenada cor) {
        super(cor);
        this.lado1 = x;
        this.lado2 = y;
    }

    public Rectangulo(Coordenada nvo) {
        super(nvo.getX(), nvo.getY());
        //this.lado1 = nvo.lado1;
        //this.lado2 = nvo.lado2;
    }
    
    public float getLado(int lado){
        if(lado == 1){
            return this.lado1;
        }
        if(lado == 2){
            return this.lado2;
        }
        if(lado != 1 && lado!=2){
            return 0;
        }
        return 0;
    }
    
    
    
}
