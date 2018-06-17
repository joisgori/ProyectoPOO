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
    ArrayList ast = new ArrayList();
    NaveGrafico nave;//Creación de la nave gráfica.
    //Definicendo coordenadas que utilizaremos.
    Coordenada movimientoIzq = new Coordenada(-25,0); //Eje x.
    Coordenada movimientoDer = new Coordenada(25,0); //Eje x
    Coordenada movimientoNulo = new Coordenada(0,0); //Movimienot nulo lo hará cuando soltemos la tecla.
    //Creación de los asteroides.
    //RectanguloGrafico Asteroide;
    //RectanguloGrafico Asteroide1;
    //RectanguloGrafico Asteroide2;
    //RectanguloGrafico Asteroide3;
    //RectanguloGrafico Asteroide4;
    
    //Contador de Asteroides el cúal servirá para la implementación de niveles
    int ContadorAsteroides = 5;
    
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
        ast.add(a);
        ast.add(b);
        ast.add(c);
        ast.add(d);
        ast.add(e);
        
        //Asteroide = a;
        //Asteroide1 = b;
        //Asteroide2 = c;
        //Asteroide3 = d;
        //Asteroide4 = e;
    }
    public void Colision(){
        //Cuando las coordenadas de los dos objetos sean iguales entonces chocan
        for(int i=0; i<nave.balas.size(); i++){
            CirculoGrafico bala =(CirculoGrafico) nave.balas.get(i);
            for(int j=0; j<ast.size();j++){ //Cada bala se comparará con los asteroides en un instante
                RectanguloGrafico aste = (RectanguloGrafico) ast.get(j); 
                
                //coordenadas
                Coordenada Corbala = new Coordenada(bala.getX(),bala.getY()); //Coordenada por el centro
                
                Coordenada Derecha = new Coordenada(aste.getX()+30,aste.getY());
                Coordenada Izquierda = new Coordenada(aste.getX()-15,aste.getY());
                Coordenada medio= new Coordenada(aste.getX(),aste.getY());
                
                //Comparación de asteroides con las balas si esto se cumple entonces se produce una colisión
                if(Corbala.getX()>Izquierda.getX() && Corbala.getX()<Derecha.getX() && Corbala.getY()<medio.getY()){
                    // una vez que choquen se pinten y no se vean
                    aste.pintar(Color.WHITE);
                    bala.pintar(Color.WHITE);
                    //settear un lugar en la pantalla donde no se vea lo blanco, por que cuando un objeto pase sobre él, esté se observará
                    bala.setY(-100);
                    aste.setY(-100);
                    //Borrando
                    nave.balas.remove(bala);
                    ast.remove(aste);
                    ContadorAsteroides = ContadorAsteroides-1;
                    
                    
                }
                
            }
        }
    }
    
    //Se´ra nuestro star del juego
    public void iniciar(){
        while(true){
            try{//si el arreglo de naves no esta vacío entonces 
                if(!nave.balas.isEmpty()){
                    nave.Ciclo();
                }
                //Aignación de moviento para los asteroides
                //Asteroide.Ciclo();
                //Asteroide1.Ciclo();
                //Asteroide2.Ciclo();
                //Asteroide3.Ciclo();
                //Asteroide4.Ciclo();
                
                //Recorrer la cantidad de asteroides que tiene el arreglo
                for(int i=0; i<ast.size();i++){
                    RectanguloGrafico rect = (RectanguloGrafico) ast.get(i);
                    rect.Ciclo();
                    
                    if(rect.getY()>525){
                        int rango = Aleatorio(800,50); //Nueva posición a ocupar.
                        rect.setY(0); //El asteroide comenzará desde su partida inicial
                        rect.setX(rango);
                    }
                }
                //Cuando el contador de asteroides sea menos a lo indicado entonces seguir creando asteroides
                if(ContadorAsteroides < 5){ // se ha producido una colision
                    int rango = Aleatorio(800,50);
                    Coordenada Inicio = new Coordenada(rango,0);// se ubicará al arriba al principio del eje x
                    RectanguloGrafico nuevo = new RectanguloGrafico(Color.RED,25,25,Inicio);
                    //agregando al arreglo de asteroides
                    ast.add(nuevo);
                    //agregando al arreglo de objetos del paint
                    v.add(nuevo);
                    //dandole un ciclo
                    nuevo.Ciclo();
                    ContadorAsteroides = ContadorAsteroides +1;
                    
                }
                
                Colision();
                /*
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
                }*/
                
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
