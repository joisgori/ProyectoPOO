/*
 * Clase responsable que sean dibujados las naves y los asteroides;
 */
package Modelos;
import static Ejecuciones.Main.Aleatorio;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener; //importación de la interfaz.
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Josué González <00034715@uca.edu.sv>;
 */
public class PanelFG extends JPanel implements KeyListener{ //La librería modificará los objetos en nuestro Panel 
    ArrayList v;
    NaveGrafico nave;//Creación de la nave gráfica.
    //Definicendo coordenadas que utilizaremos.
    Coordenada movimientoIzq = new Coordenada(-25,0); //Eje x.
    Coordenada movimientoDer = new Coordenada(25,0); //Eje x
    Coordenada movimientoNulo = new Coordenada(0,0); //Movimienot nulo lo hará cuando soltemos la tecla.
    //Creación de los asteroides.
    RectanguloGrafico Asteroide;
    RectanguloGrafico Asteroide1;
    RectanguloGrafico Asteroide2;
    RectanguloGrafico Asteroide3;
    RectanguloGrafico Asteroide4;
    
    
    //Constructor vacío de esta clase...
    public PanelFG(ArrayList vectordeO) { //Siendo esta variable solo un parámetro que servirá para enviar todas las figuras que vamos a "dibujar"... El panel recibirá un vector de objetos
        //Puede tener objetos de distintos tipos...
        this.v = vectordeO; //El panel asignó al ArrayList que tiene como atributo.
        this.addKeyListener(this); // El objeto podrá ser escuchado
        setFocusable(true);
    }
    
    public void paint(Graphics g){ //Método que nos permitirá dibujar
        Dimension d = getSize(); //Objeto dimensión que contendrá la dimensión del objeto
        Image Imagen = createImage(d.width,d.height); // objeto imagen donde tomará las diensiones ancho y alto.
        Graphics buff = Imagen.getGraphics(); //Grafico auxiliar que se le asigna a los graficos de la imagen que creamos.
        
        Dibujable dib;
        for(int i = 0; i<v.size(); i++){ //Crea un objeto de tipo dibujable y va dibujando a medida que recorre el arreglo de objetos
            
            dib = (Dibujable)v.get(i); //Casteamos esto al tipo dibujable...
            dib.Dibujar(buff); //No se quiere que se repinte constantemente.
        }
        g.drawImage(Imagen, 0, 0, this); // Se pega la imagen frente al panel
    }
    //Redefiniendo para no hacer un repaint.
    @Override
    public void update(Graphics g){
        paint(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) { //Nos escuchará cuando presionemos una tecla.
        int tecla = e.getKeyCode(); //Representará la tecla que presionamos.
        
        if(tecla == KeyEvent.VK_LEFT){ //Declaración de movimiento izquierda.
            //this.nave.Pintar(this.getGraphics(), Color.WHITE); //Pintando la nave de blanco 
            this.nave.mover(movimientoIzq); //Moviendo la nave para la izquierda
            //this.nave.Pintar(this.getGraphics(), Color.BLACK); //Pintando la nave de negro.
        }
        if(tecla == KeyEvent.VK_RIGHT){ //Declaración de movimiento derecha.
            //this.nave.Pintar(this.getGraphics(), Color.WHITE); //Pintando la nave de blanco //LA nave se movera sola con ayuda del buff
            this.nave.mover(movimientoDer); //Moviendo la nave para la derecha.
            //this.nave.Pintar(this.getGraphics(), Color.BLACK); //Pintando la nave de negro.
        }
        //Cuando se presione la tecla, se agregara una bala al arreglo de objetos
        if(tecla == KeyEvent.VK_Q){
            CirculoGrafico bala = nave.Bala();
            nave.balas.add(bala);//Al arreglo de objetos se le esta agregando la bala.
            v.add(bala);//Pinta la bala
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { // Cuando soltemos la tecla puede ejecutar otra cosa.
        int tecla = e.getKeyCode(); //Representará la tecla que presionamos.
        
        if(tecla == KeyEvent.VK_LEFT){ //Declaración de movimiento izquierda.
            this.nave.mover(movimientoNulo); //Moviendo la nave para la nulo.
        }
        if(tecla == KeyEvent.VK_RIGHT){ //Declaración de movimiento derecha.
            this.nave.mover(movimientoNulo); //Moviendo la nave para la nulo.
        }
        if(tecla == KeyEvent.VK_Q){}
    }
    public void refNave(NaveGrafico n){ // Referencia de la nave.
        this.nave = n;
    }
    public void refAst(RectanguloGrafico a,RectanguloGrafico b,RectanguloGrafico c,RectanguloGrafico d,RectanguloGrafico e){
        Asteroide = a;
        Asteroide1 = b;
        Asteroide2 = c;
        Asteroide3 = d;
        Asteroide4 = e;
    }
    //Se´ra nuestro star del juego
    public void iniciar(){
        while(true){
            try{//si el arreglo de naves no esta vacío entonces 
                if(!nave.balas.isEmpty()){
                    nave.Ciclo();
                }
                //Aignación de moviento para los asteroides
                Asteroide.Ciclo();
                Asteroide1.Ciclo();
                Asteroide2.Ciclo();
                Asteroide3.Ciclo();
                Asteroide4.Ciclo();
                //Posicionando los asteroides en en 0 
                if(Asteroide.getY()>525){
                   int rango = Aleatorio(800,50); //Nueva posición a ocupar.
                   Asteroide.setY(0); //El asteroide comenzará desde su partida inicial
                   Asteroide.setX(rango);
                }
                if(Asteroide1.getY()>525){
                   int rango = Aleatorio(800,50); //Nueva posición a ocupar.
                   Asteroide1.setY(0); //El asteroide comenzará desde su partida inicial
                   Asteroide1.setX(rango);
                }
                if(Asteroide2.getY()>525){
                   int rango = Aleatorio(800,50); //Nueva posición a ocupar.
                   Asteroide2.setY(0); //El asteroide comenzará desde su partida inicial
                   Asteroide2.setX(rango);
                }
                if(Asteroide3.getY()>525){
                   int rango = Aleatorio(800,50); //Nueva posición a ocupar.
                   Asteroide3.setY(0); //El asteroide comenzará desde su partida inicial
                   Asteroide3.setX(rango);
                }
                if(Asteroide4.getY()>525){
                   int rango = Aleatorio(800,50); //Nueva posición a ocupar.
                   Asteroide4.setY(0); //El asteroide comenzará desde su partida inicial
                   Asteroide4.setX(rango);
                }
                
                //Asteroide4.setY(200); //Enviados por referencias, si se le quiere cambiar ahora se podrá
                Thread.sleep(50); //Hilo de ejecución cada 50 milisegundos
            }
            // Cuando no pueda hacerlo entonces
            catch(InterruptedException err){
                System.out.println("Error");
            }
            repaint();
        }
    }
}
