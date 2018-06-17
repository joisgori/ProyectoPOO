/*
 * Acá estará la clase main y se ejecutará la aplicación
 */
package Ejecuciones;

import Modelos.*; //El asterístico ayuda importar todas las clases de ese paquete;
import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author Josué González <00034715@uca.edu.sv>;
 */
public class Main {
    //Para la creación de la ubicación de asteroides
    public static int Aleatorio(int Max, int Min){
        return (int)(Math.random()*(Max-Min));
    }

    public static void main(String[] args) {
        Ventana nuestraventana = new Ventana("Juego de naves"); //Objeto de tipo ventana
        //Creando el arreglo de nuestro panel:
        ArrayList ArregloDeObjetos = new ArrayList();
        //Mejor manipulación de asteroides
        
        //Crearemos el planel...
        Coordenada cor1 = new Coordenada(250,250);
        Coordenada cor2 = new Coordenada(500,290);
        //nave
        Coordenada cor3 = new Coordenada(475,500);//punta
        Coordenada cor4 = new Coordenada(425,575);//izquierda
        Coordenada cor5 = new Coordenada(525,575);//derecha
        
        NaveGrafico nave = new NaveGrafico(cor3,cor4,cor5,Color.CYAN);
        
        
        
        RectanguloGrafico rectangulo = new RectanguloGrafico(Color.pink, 80 , 80,cor1 );
        CirculoGrafico circulo = new CirculoGrafico(cor2,50,Color.BLACK);
        
        //ArregloDeObjetos.add(rectangulo);
        //ArregloDeObjetos.add(circulo);
        
        // Creación de asteroides
        int rango = Aleatorio(800, 500);
        //Coordenada de la salida de nuestro asteroide
        Coordenada Salida = new Coordenada(rango,0);
        RectanguloGrafico Asteroide = new RectanguloGrafico(Color.red, 25, 25, Salida);
        
        int rango1 = Aleatorio(800, 500);
        Coordenada Salida1 = new Coordenada(rango,0);
        RectanguloGrafico Asteroide1 = new RectanguloGrafico(Color.red, 25, 25, Salida1);
        
        int rango2 = Aleatorio(800, 500);
        Coordenada Salida2 = new Coordenada(rango,0);
        RectanguloGrafico Asteroide2 = new RectanguloGrafico(Color.red, 25, 25, Salida2);
        
        int rango3 = Aleatorio(800, 500);
        Coordenada Salida3 = new Coordenada(rango,0);
        RectanguloGrafico Asteroide3 = new RectanguloGrafico(Color.red, 25, 25, Salida3);
        
        int rango4 = Aleatorio(800, 500);
        Coordenada Salida4 = new Coordenada(rango,0);
        RectanguloGrafico Asteroide4 = new RectanguloGrafico(Color.red, 25, 25, Salida4);
        
        ArregloDeObjetos.add(Asteroide);
        ArregloDeObjetos.add(Asteroide1);
        ArregloDeObjetos.add(Asteroide2);
        ArregloDeObjetos.add(Asteroide3);
        ArregloDeObjetos.add(Asteroide4);
                
        ArregloDeObjetos.add(nave);
        PanelFG nuestroPanel = new PanelFG(ArregloDeObjetos);
        
        nuestroPanel.refNave(nave); //No se utilizara la nave de la misma forma en como la pintamos.
        nuestroPanel.refAst(Asteroide, Asteroide1, Asteroide2, Asteroide3, Asteroide4);//Se podran modificar su posición
        
        nuestraventana.add(nuestroPanel);
        nuestraventana.setSize(800, 600);
        nuestraventana.setVisible(true);
        nuestroPanel.iniciar();
        
    }
    
}
