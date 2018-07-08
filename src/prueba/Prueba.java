/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;
import GUI.*;
import java.io.IOException;
import java.util.*;
import javax.swing.JFrame;
/**
 *
 * @author aacm12
 */
public class Prueba {

    /**
     * @param args the command line arguments
     VENTANA
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Space Invaders");
        ventana.setContentPane(new ventana());
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.pack();
        ventana.setVisible(true);
    }
    TIENDA
    public static void main(String[] args) {
        JFrame tienda = new JFrame("Space Invaders");
        tienda.setContentPane(new tienda());
        tienda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tienda.setResizable(false);
        tienda.pack();
        tienda.setVisible(true);
    }
    //LOGIN
    public static void main(String[] args) {
        JFrame Login = new JFrame("Space Invaders");
        Login.setContentPane(new Login());
        Login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Login.setResizable(false);
        Login.pack();
        Login.setVisible(true);
    }*/
    //ENTRADA
    public static void main(String[] args) throws IOException {
        EntradaUsuario x = new EntradaUsuario();
        //tienda t = new tienda();
        //ventana v = new ventana();
        //v.main();
        //t.main();
        x.main();
    }
    
}
