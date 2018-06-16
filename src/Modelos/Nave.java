/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author aacm12
 */
public class Nave extends Coordenada{
    
    public Coordenada cor1 = new Coordenada();//toma la coordenada de la izquierda
    public Coordenada cor2 = new Coordenada();//toma la coordenada de la derecha
    
    //;a coordenada q heredamos va a tomar la punta de la nave
    
    public Nave(){
        super();
        this.cor1.setX(0);
        this.cor1.setY(0);
        
        this.cor2.setX(0);
        this.cor2.setY(0);
        
        
    }
    
    public Nave(Coordenada a,Coordenada b,Coordenada c ){
        super(a.getX(), a.getY());
        
        this.cor1.setX(b.getX());
        this.cor1.setY(b.getY());
        
        this.cor2.setX(c.getX());
        this.cor2.setY(c.getY());
    }
    
    public Nave(Nave c){
        super(c.getX(),c.getY());
        
        this.cor1.setX(c.getX());
        this.cor1.setY(c.getY());
        
        this.cor2.setX(c.getX());
        this.cor2.setY(c.getY());
    }
    
    public void setVertice(Coordenada nva, int lado){
        if(lado==1){
            this.setX(nva.getX());
            this.setY(nva.getY());
        }
        if(lado==2){
            this.cor1.setX(nva.getX());
            this.cor1.setY(nva.getY());
        }
        if(lado==3){
            this.cor2.setX(nva.getX());
            this.cor2.setY(nva.getY());
        }
    }
    
    public void mover(Coordenada NvaCor){
        
        setVertice((this.Suma(NvaCor)), 1);
        setVertice((this.cor1.Suma(NvaCor)), 2);
        setVertice((this.cor2.Suma(NvaCor)), 3);
        
        
    }
    
}
