package com.mike_d.game.main;

import com.mike_d.game.entity.Player;
import com.mike_d.game.object.SuperObject;
import com.mike_d.game.tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; //48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12; // ratio is 4:3
    public final int screenWidth = tileSize * maxScreenCol; //768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // WORLD SETTINGS
    public final int maxWorldCol =50;
    public final int maxWorldRow = 50;

    // FPS
    final int FPS = 60;

    TileManager tileM = new TileManager(this);

    //SYSTEM
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Sound music = new Sound();
    Sound soundFX = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);

    //ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];



    public GamePanel(){
        //tells layout panel "prefers" 768x576
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        //double buffering - drawings rendered off-screen first,
        //then swapped in, reducing flicker
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){

        aSetter.setObject();
        playMusic(0);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
//    public void run() {
//        double drawInterval = 1000000000 / FPS; //  0.016666 seconds
//        double nextDrawTime = System.nanoTime() + drawInterval;
//        long nanoTime = System.nanoTime();
//
//
//        while (gameThread != null) {
//            // 1 update: update character information such as char position
//            update();
//            // 2 draw: draw the screen
//            repaint();
//
//            try {
//                double remainingTime = nextDrawTime - nanoTime;
//                remainingTime = remainingTime / 1000000000;
//
//                if (remainingTime < 0) {
//                    remainingTime = 0;
//                }
//                Thread.sleep((long) remainingTime);
//
//                nextDrawTime += drawInterval;
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
    public void run () {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }
    public void update() {
        player.update();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        //draw tiles first
        tileM.draw(g2);

        //draw object
        //scan object array one by one
        //beware null pointer error
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }

        }

        //draw player
        player.draw(g2);

        // UI
        ui.draw(g2);

        //
        g2.dispose();
    }
    public void playMusic(int i) {
        music.setFile(i);
        music.playSound();
        music.loopAudio();
    }
    public void stopMusic() {
        music.stopAudio();
    }
    public void playSfx(int i) {
        soundFX.setFile(i);
        soundFX.playSound();
    }
}

