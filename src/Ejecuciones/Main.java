/*
 * Acá estará la clase main y se ejecutará la aplicación
 */
package Ejecuciones;

import Modelos.*; //El asterístico ayuda importar todas las clases de ese paquete;
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
        PanelFG nuestroPanel = new PanelFG(ArregloDeObjetos);
        
        nuestraventana.add(nuestroPanel);
        nuestraventana.setSize(500, 400);
        nuestraventana.setVisible(true);
        
    }
    
}
