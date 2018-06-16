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

    public static void main(String[] args) {
        Ventana nuestraventana = new Ventana("Juego de naves"); //Objeto de tipo ventana
        //Creando el arreglo de nuestro panel:
        ArrayList ArregloDeObjetos = new ArrayList();
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
        
        ArregloDeObjetos.add(rectangulo);
        ArregloDeObjetos.add(circulo);
        ArregloDeObjetos.add(nave);
        PanelFG nuestroPanel = new PanelFG(ArregloDeObjetos);
        
        nuestraventana.add(nuestroPanel);
        nuestraventana.setSize(800, 600);
        nuestraventana.setVisible(true);
        
    }
    
}
