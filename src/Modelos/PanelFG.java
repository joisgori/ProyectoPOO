/*
 * Clase responsable que sean dibujados las naves y los asteroides;
 */
package Modelos;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Josué González <00034715@uca.edu.sv>;
 */
public class PanelFG extends JPanel{
    ArrayList v;
    //Constructor vacío de esta clase...
    public PanelFG(ArrayList asd) { //Siendo esta variable solo un parámetro que servirá para enviar todas las figuras que vamos a "dibujar"...
        //Puede tener objetos de distintos tipos...
        this.v = asd; 
    }
    
    public void paint(Graphics g){ //Método que nos permitirá dibujar
        Dibujable dib;
        for(int i = 0; i<v.size(); i++){ //Crea un objeto de tipo dibujable y va dibujando a medida que recorre el arreglo de objetos
            
            dib = (Dibujable)v.get(i); //Casteamos esto al tipo dibujable...
            dib.Dibujar(g);
        }
    }
    
    
}
