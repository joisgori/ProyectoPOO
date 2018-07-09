/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import Controladores.KeyboardController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import usuario.Dato;
import usuario.usuario;

/**
 *
 * @author aacm12
 */
public class GamePanel extends JPanel {

    // componentes
    private Timer gameTimer;
    private KeyboardController controller;
    // Controls size of game window and framerate
    private final int gameWidth = 800;
    private final int gameHeight = 800;
    private final int framesPerSecond = 120;

    // contadores
    Random r = new Random();
    private int score = 0;
    private int level = 1;
    private int numberOfLives = 3;
    private int highScore;
    private int markerX, markerY;
    private static int bossHealth = 30;
    File f = new File("Highscore.txt");

    // objetos
    private Ship playerShip;
    private Ship singleLife;
    private Ship bonusEnemy;
    private Enemy enemy;
    private Shield shield;
    private Bullet bullet;
    private Beam beam, beam2, beam3;

    //boolean
    private boolean newBulletCanFire = true;
    private boolean newBeamCanFire = true;
    private boolean newBonusEnemy = true;
    private boolean hitMarker = false;

    // Array Lists
    private ArrayList<Ship> lifeList = new ArrayList();
    private ArrayList<Ship> bonusEnemyList = new ArrayList();
    private ArrayList<Enemy> enemyList = new ArrayList();
    private ArrayList<Shield> shieldList = new ArrayList();
    private ArrayList<Beam> beamList = new ArrayList();
    private ImageIcon background = new ImageIcon("images/backgroundSkin.jpg");

    // Audio files and streams
    private File beamSound = new File("sounds/alienBeam.wav");
    private File bulletSound = new File("sounds/bulletSound.wav");
    private File levelUpSound = new File("sounds/levelUpSound.wav");
    private File deathSound = new File("sounds/deathSound.wav");
    private File hitmarkerSound = new File("sounds/hitmarkerSound.wav");
    private File shieldSound = new File("sounds/shieldSound.wav");
    private File bossSound = new File("sounds/bossSound.wav");
    private File bonusSound = new File("sounds/bonusSound.wav");
     private File damageSound = new File("sounds/damageSound.wav");
    private AudioStream beamSoundAudio;
    private InputStream beamSoundInput;
    private AudioStream bulletSoundAudio;
    private InputStream bulletSoundInput;
    private AudioStream levelUpSoundAudio;
    private InputStream levelUpSoundInput;
    private AudioStream deathSoundAudio;
    private InputStream deathSoundInput;
    private AudioStream hitSoundAudio;
    private InputStream hitSoundInput;
    private AudioStream shieldSoundAudio;
    private InputStream shieldSoundInput;
    private AudioStream bossSoundAudio;
    private InputStream bossSoundInput;
    private AudioStream bonusSoundAudio;
    private InputStream bonusSoundInput;
    private AudioStream damageSoundAudio;
    private InputStream damageSoundInput;


    public static int getBossHealth() {
        return bossHealth;
    }

    public final void setupGame() throws FileNotFoundException, IOException {
        
        // Sets enemies for normal levels
        if (level != 3 && level != 6 && level != 9 && level != 12) {
            // Six rows
            for (int row = 0; row < 6; row++) {
                // 5 columns
                for (int column = 0; column < 5; column++) {
                    enemy = new Enemy((20 + (row * 100)), (20 + (column * 60)), level, 0, column, null, 40, 40); // Enemy speed will increase each level
                    enemyList.add(enemy);
                }
            }
        }
        // Setlos niveles en los q aparece el jefe
        if (level == 3 || level == 6 || level == 9 || level == 12) {
            AudioPlayer.player.start(bossSoundAudio); // sonido del boss
            enemy = new Enemy(20, 20, 3, 0, 100, null, 150, 150);
            enemyList.add(enemy);
        }
        // Gives directions on level 1
        if (level == 1) {
            JOptionPane.showMessageDialog(null, "Bienvenidos!\n\nInstrucciones:\n\n- Use las flechas izquierda/derecha para moverse\n- ocupe space para disparar\n- los enemigos se vuelven mas rapidos cada nivel"
                    + "\n- hay un JEFE cada 3 niveles\n- una nave que da puntos extra saldrá esporádicamente\n- disparele para ganar los puntos!\n- Presione R para reiniciar el puntaje\n- ACTIVAR EL SONIDO\n\n¡Desde el equipo JAR de POO, que lo disfrute!");
        }
        // Resetea todos los movimientos del controlador
        controller.resetController();

        // Set los valores iniciales para la nave del usuario 
        playerShip = new Ship(375, 730, null, controller);

        // pone el contados del las vidas
        for (int column = 0; column < numberOfLives; column++) {
            singleLife = new Ship(48 + (column * 20), 10, Color.WHITE, null);
            lifeList.add(singleLife);
        }

        // Set los valores para los escudos
        for (int row = 0;
                row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                shield = new Shield(100 + (column * 250), 650 - (row * 10), 70, 10, Color.RED);
                shieldList.add(shield);
            }
        }
    }
    

    @Override
    public void paint(Graphics g) {


        background.paintIcon(null, g, 0, -150);


        if (bullet != null) {
            if (hitMarker) {
                g.setColor(Color.WHITE);
                if (level != 3 && level != 6 && level != 9 && level != 12) {
                    g.drawString("+ 100", markerX + 20, markerY -= 1);
                } else {
                    g.drawString("- 1", markerX + 75, markerY += 1);
                }
            }
        }
        // dibuja la nave del usuario
        playerShip.draw(g);

        // dibuja los 3 escudos
        for (int index = 0; index < shieldList.size(); index++) {
            shieldList.get(index).draw(g);
        }

        //dibuja los 3 diferentes tipos de aliens
        try {
            for (int index = 0; index < enemyList.size(); index++) {
                enemyList.get(index).draw(g);
            }

        } catch (IndexOutOfBoundsException e) {
        }

        // dibuja el disparo al presionar espacio
        if (controller.getKeyStatus(32)) {
            if (newBulletCanFire) {
                bullet = new Bullet(playerShip.getXPosition() + 22, playerShip.getYPosition() - 20, 0, Color.RED);
                AudioPlayer.player.start(bulletSoundAudio); // Plays bullet sound
                newBulletCanFire = false;
            }
        }

        if (bullet != null) {
            bullet.draw(g);
        }

        // genera los ataques enemigos
        if (level != 3 && level != 6 && level != 9 && level != 12) {
            if (newBeamCanFire) {
                for (int index = 0; index < enemyList.size(); index++) {
                    if (r.nextInt(30) == index) {
                        beam = new Beam(enemyList.get(index).getXPosition(), enemyList.get(index).getYPosition(), 0, Color.YELLOW);
                        beamList.add(beam);
                        AudioPlayer.player.start(beamSoundAudio);
                    }
                    newBeamCanFire = false;
                }
            }
        }
        // Genera los ataques del jefe, estos son mas rapidos de lo normal
        if (level == 3 || level == 6 || level == 9 || level == 12) {
            if (newBeamCanFire) {
                for (int index = 0; index < enemyList.size(); index++) {
                    if (r.nextInt(5) == index) {
                        beam = new Beam(enemyList.get(index).getXPosition() + 75, enemyList.get(index).getYPosition() + 140, 0, Color.YELLOW);
                        beam2 = new Beam(enemyList.get(index).getXPosition(), enemyList.get(index).getYPosition() + 110, 0, Color.YELLOW);
                        beam3 = new Beam(enemyList.get(index).getXPosition() + 150, enemyList.get(index).getYPosition() + 110, 0, Color.YELLOW);
                        beamList.add(beam);
                        beamList.add(beam2);
                        beamList.add(beam3);
                        AudioPlayer.player.start(beamSoundAudio); 
                    }
                    newBeamCanFire = false;
                }
            }
        }
        // Dibuja los disparos
        for (int index = 0; index < beamList.size(); index++) {
            beamList.get(index).draw(g);
        }
        // genera el enemigo bonus
        if (newBonusEnemy) {
            if (r.nextInt(3000) == 1500) {
                bonusEnemy = new Ship(-50, 30, Color.RED, null);
                bonusEnemyList.add(bonusEnemy);
                newBonusEnemy = false;
            }
        }
        // dibuja el bonus
        for (int index = 0; index < bonusEnemyList.size(); index++) {
            bonusEnemyList.get(index).bonusDraw(g);
        }

        // Set los puntos
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 260, 20);

        // set el contador de vidas
        g.setColor(Color.WHITE);
        g.drawString("Lives:", 11, 20);
        for (int index = 0; index < lifeList.size(); index++) {
            lifeList.get(index).lifeDraw(g);
        }
        // Set el nivel en 
        g.setColor(Color.WHITE);
        g.drawString("Level " + level, 750, 20);

        // Set el puntaje mas alto
        g.setColor(Color.WHITE);
        g.drawString("Highscore: " + highScore, 440, 20);

        // dibuja la vida del boss
        if (level == 3 || level == 6 || level == 9 || level == 12) {
            g.setColor(Color.WHITE);
            g.drawString("Boss Health: " + bossHealth, 352, 600);
        }
    }
    
    
    public void updateGameState(int frameNumber) throws IOException {

        // para q el usuario se pueda mover de izquierda a derecha
        playerShip.move();

        // actualiza el puntaje mas alto 
        try {
            
            Scanner fileScan = new Scanner(f);
            while (fileScan.hasNextInt()) {
                String nextLine = fileScan.nextLine();
                Scanner lineScan = new Scanner(nextLine);
                highScore = lineScan.nextInt();
            }
            
            
            
            
        } catch (FileNotFoundException e) {
        }
        // para resetear el puntaje mas alto
        if (controller.getKeyStatus(82)) {
            int answer = JOptionPane.showConfirmDialog(null, "resetear puntaje?", ":)", 0);
            controller.resetController();
            if (answer == 0) {
                try {
                    String scoreString = Integer.toString(0);
                    PrintWriter pw = new PrintWriter(new FileOutputStream(f, false));
                    pw.write(scoreString);
                    pw.close();
                } catch (FileNotFoundException e) {
                }
            }
        }
        // actualiza el puntaje mas alto en tiempo real, a medida q lo supera
        try {
            if (score > highScore) {
                String scoreString = Integer.toString(score);
                PrintWriter pw = new PrintWriter(new FileOutputStream(f, true));
                pw.write(scoreString);
                pw.close();
            }
        } catch (FileNotFoundException e) {
        }

        // para q los enemigos se muevan
        if ((enemyList.get(enemyList.size() - 1).getXPosition() + enemyList.get(enemyList.size() - 1).getXVelocity()) > 760 || (enemyList.get(0).getXPosition() + enemyList.get(0).getXVelocity()) < 0) {
            for (int index = 0; index < enemyList.size(); index++) {
                enemyList.get(index).setXVelocity(enemyList.get(index).getXVelocity() * -1);
                enemyList.get(index).setYPosition(enemyList.get(index).getYPosition() + 10);
            }
        } else {
            for (int index = 0; index < enemyList.size(); index++) {
                enemyList.get(index).move();
            }
        }

        // mueve el laser
        if (bullet != null) {
            bullet.setYPosition(bullet.getYPosition() - 15);
            if (bullet.getYPosition() < 0) {
                newBulletCanFire = true;
            }

            // check para ver si le pego a algo
            for (int index = 0; index < enemyList.size(); index++) {
                if (bullet.isColliding(enemyList.get(index))) {
                    AudioPlayer.player.start(hitSoundAudio);
                    bullet = new Bullet(0, 0, 0, null);
                    newBulletCanFire = true;
                    // atualiza el puntaje en nivels normales
                    if (level != 3 && level != 6 && level != 9 && level != 12) {
                        score += 100;
                        hitMarker = true;
                        markerX = enemyList.get(index).getXPosition(); 
                        markerY = enemyList.get(index).getYPosition();
                        enemyList.remove(index);

                    }
                    //atualiza el puntaje en nivels de JEFE
                    if (level == 3 || level == 6 || level == 9 || level == 12) {
                        hitMarker = true;
                        markerX = enemyList.get(index).getXPosition(); 
                        markerY = enemyList.get(index).getYPosition() + 165;
                        bossHealth -= 1;
                        if (bossHealth == 0) {
                            enemyList.remove(index);
                            score += 9000;//premio por ganarle al jefe
                        }
                    }
                }
            }

            // check para ver si le pego al escudo o a lasers enemigos
            for (int index = 0; index < shieldList.size(); index++) {
                if (bullet.isColliding(shieldList.get(index))) {
                    // Calidad del escudo
                    // Fuerte
                    if (shieldList.get(index).getColor() == Color.RED) {
                        shieldList.get(index).setColor(Color.ORANGE);
                        AudioPlayer.player.start(shieldSoundAudio); // Plays sound if shield takes damage
                        bullet = new Bullet(0, 0, 0, null);
                        newBulletCanFire = true;
                    // Bueno
                    } else if (shieldList.get(index).getColor() == Color.ORANGE) {
                        shieldList.get(index).setColor(Color.YELLOW);
                        AudioPlayer.player.start(shieldSoundAudio);
                        bullet = new Bullet(0, 0, 0, null);
                        newBulletCanFire = true;
                    // acceptable
                    } else if (shieldList.get(index).getColor() == Color.YELLOW) {
                        shieldList.get(index).setColor(Color.WHITE);
                        AudioPlayer.player.start(shieldSoundAudio);
                        bullet = new Bullet(0, 0, 0, null);
                        newBulletCanFire = true;
                    // Debil
                    } else if (shieldList.get(index).getColor() == Color.WHITE) {
                        shieldList.remove(index);
                        AudioPlayer.player.start(shieldSoundAudio);
                        bullet = new Bullet(0, 0, 0, null);
                        newBulletCanFire = true;
                    }
                }
            }
        }
        // movimiento del bonus
        if (!bonusEnemyList.isEmpty()) {
            for (int index = 0; index < bonusEnemyList.size(); index++) {
                bonusEnemyList.get(index).setXPosition(bonusEnemyList.get(index).getXPosition() + (2));
                if (bonusEnemyList.get(index).getXPosition() > 800) {
                    bonusEnemyList.remove(index);
                    newBonusEnemy = true;
                }
            }
            // cunado le pega al bonus
            for (int index = 0; index < bonusEnemyList.size(); index++) {
                if (bullet != null) {
                    if (bonusEnemyList.get(index).isColliding(bullet)) {
                        bonusEnemyList.remove(index);
                        bullet = new Bullet(0, 0, 0, null);
                        newBulletCanFire = true;
                        newBonusEnemy = true;
                        AudioPlayer.player.start(bonusSoundAudio); 
                        score += 5000; // premio por pegarle al bonus
                    }
                }
            }
        }

        // mueve laser para aliens
        if (level != 3 && level != 6 && level != 9 && level != 12) {
            if (beam != null) {
                for (int index = 0; index < beamList.size(); index++) {
                    beamList.get(index).setYPosition(beamList.get(index).getYPosition() + (4));
                    if (beamList.get(index).getYPosition() > 800) {
                        beamList.remove(index);
                    }
                }
            }
        }
        //mueve laser para jefe
        if (level == 3 || level == 6 || level == 9 || level == 12) {
            if (beam != null) {
                for (int index = 0; index < beamList.size(); index++) {
                    beamList.get(index).setYPosition(beamList.get(index).getYPosition() + (2 * level)); //la velocidad en la q dispara el jefe incrementara cada vez q aparezca
                    if (beamList.get(index).getYPosition() > 800) {
                        beamList.remove(index);
                    }
                }
            }
        }

        // check para cuando los disparos de los enemigos le pegan o al laser del usuario o a los escudos
        try {
            for (int j = 0; j < shieldList.size(); j++) {
                for (int index = 0; index < beamList.size(); index++) {
                    if (beamList.get(index).isColliding(shieldList.get(j))) {
                        // Fuerte
                        if (shieldList.get(j).getColor() == Color.RED) {
                            shieldList.get(j).setColor(Color.ORANGE);
                            AudioPlayer.player.start(shieldSoundAudio);
                            beamList.remove(index);
                        // Bueno
                        } else if (shieldList.get(j).getColor() == Color.ORANGE) {
                            shieldList.get(j).setColor(Color.YELLOW);
                            AudioPlayer.player.start(shieldSoundAudio);
                            beamList.remove(index);
                        // acceptable
                        } else if (shieldList.get(j).getColor() == Color.YELLOW) {
                            shieldList.get(j).setColor(Color.WHITE);
                            AudioPlayer.player.start(shieldSoundAudio);
                            beamList.remove(index);
                        // Debil
                        } else if (shieldList.get(j).getColor() == Color.WHITE) {
                            shieldList.remove(j);
                            AudioPlayer.player.start(shieldSoundAudio);
                            beamList.remove(index);
                        }
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
        }

        // Check cuando le pegan al usuario
        for (int index = 0; index < beamList.size(); index++) {
            if (beamList.get(index).isColliding(playerShip)) {
                beamList.remove(index);
                AudioPlayer.player.start(damageSoundAudio);
                lifeList.remove(lifeList.size() - 1); // quita una vida
            }
        }

        // solo crea nuevos lasers cuando el anterio ya a desaparacido
        if (beamList.isEmpty()) {
            newBeamCanFire = true;
        }

        //Destruye el escudo si los aliens lo tocan
        for (int input = 0; input < enemyList.size(); input++) {
            for (int j = 0; j < shieldList.size(); j++) {
                if (enemyList.get(input).isColliding(shieldList.get(j))) {
                    shieldList.remove(j);
                }
            }
            // si los aliens llegan a esta posicion x entonces se resetea el nivel y pierde una vida
            if (enemyList.get(input).getYPosition() + 50 >= 750) {
                enemyList.clear();
                shieldList.clear();
                lifeList.clear();
                beamList.clear();
                bossHealth = 30;
                numberOfLives -= 1;
                AudioPlayer.player.start(deathSoundAudio); // Plays death sound when enemies reach bottom
                setupGame();
            }
        }

        //actualiza la imagen del contador de vidas
        if (playerShip.isColliding) {
            int index = lifeList.size() - 1;
            lifeList.remove(index);
        } 
        // rmina el juego si el usuario se queda sin vidas
        else if (lifeList.isEmpty()) {
            AudioPlayer.player.start(deathSoundAudio); 
            // a la opcion de salir o jugar otra vez
            int answer = JOptionPane.showConfirmDialog(null, "Quiere Jugar otra vez?", "Perdio con " + score + " puntos", 0);
            // si decide jugar otra vez esto reinicia todos los valores
            if (answer == 0) {
                lifeList.clear();
                enemyList.clear();
                shieldList.clear();
                beamList.clear();
                bonusEnemyList.clear();
                score = 0;
                level = 1;
                bossHealth = 30;
                numberOfLives = 3;
                newBulletCanFire = true;
                newBeamCanFire = true;
                newBonusEnemy = true;
                setupGame();
            }
            // si decide acabar el juego
            if (answer == 1) {
                System.exit(0);
            }
        }

        // mueve al siguiente nivel
        if (enemyList.isEmpty()) {
            beamList.clear();
            shieldList.clear();
            bonusEnemyList.clear();
            lifeList.clear();
            level += 1;
            bossHealth = 30;
            setupGame();
            AudioPlayer.player.start(levelUpSoundAudio);
        }
        
        // Sonidos
        try {
            beamSoundInput = new FileInputStream(beamSound);
            beamSoundAudio = new AudioStream(beamSoundInput);
            bulletSoundInput = new FileInputStream(bulletSound);
            bulletSoundAudio = new AudioStream(bulletSoundInput);
            levelUpSoundInput = new FileInputStream(levelUpSound);
            levelUpSoundAudio = new AudioStream(levelUpSoundInput);
            deathSoundInput = new FileInputStream(deathSound);
            deathSoundAudio = new AudioStream(deathSoundInput);
            hitSoundInput = new FileInputStream(hitmarkerSound);
            hitSoundAudio = new AudioStream(hitSoundInput);
            shieldSoundInput = new FileInputStream(shieldSound);
            shieldSoundAudio = new AudioStream(shieldSoundInput);
            bossSoundInput = new FileInputStream(bossSound);
            bossSoundAudio = new AudioStream(bossSoundInput);
            bonusSoundInput = new FileInputStream(bonusSound);
            bonusSoundAudio = new AudioStream(bonusSoundInput);
            damageSoundInput = new FileInputStream(damageSound);
            damageSoundAudio = new AudioStream(damageSoundInput);
        } catch (IOException e) {
        }
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// GAME PANEL    
    
    public GamePanel() throws IOException {
        // Set el tamaño del panel
        this.setSize(gameWidth, gameHeight);
        this.setPreferredSize(new Dimension(gameWidth, gameHeight));
        this.setBackground(Color.BLACK);

        // Register KeyboardController as KeyListener
        controller = new KeyboardController();
        this.addKeyListener(controller);

        // Llama setupGame ra initializar todos los campos
        this.setupGame();
        this.setFocusable(true);
        this.requestFocusInWindow();
    }


    public void start() {
        // Set un nuevo timer que se repita cada 20 millisegundos (50 FPS)
        gameTimer = new Timer(1000 / framesPerSecond, new ActionListener() {

            // contancia de los numeros de frames producidos
            private int frameNumber = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // actualiza el estado del juego y pinta la pantalla
                    updateGameState(frameNumber++);
                    repaint();
                } catch (IOException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        Timer gameTimerHitMarker = new Timer(1000, new ActionListener() {

            // contancia de los numeros de frames producidos
            @Override
            public void actionPerformed(ActionEvent e) {
                // actualiza el estado del juego y pinta la pantalla
                hitMarker = false;
            }
        });

        gameTimer.setRepeats(true);
        gameTimer.start();
        gameTimerHitMarker.setRepeats(true);
        gameTimerHitMarker.start();
    }

}
