package com.mike_d.game.main;

import javax.swing.JFrame;

public class Main{
    public static void main(String[] args){
        JFrame window = new JFrame();
        //close the program when user clicks X
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //not resizable
        window.setResizable(false);
        window.setTitle("2D Adventure");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        //center the window and display
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}