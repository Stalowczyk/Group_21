/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import board.Board;
import entity.Bag;
import entity.CommonGoals;
import entity.PersonalGoalsCards;
import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import entity.Shelf;
import java.awt.Dialog;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author pawel
 */
public class GamePanel extends JPanel implements Runnable {

    final int orgiginalTileSize = 16;
    final int scale = 3;

    public final int tileSize = orgiginalTileSize * scale;
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 20;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    int FPS = 10;

    private ArrayList<Shelf> allShelfs;

    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int) (dim.width * 0.90);
    int height = (int) (dim.height * 0.90);
    public Integer playerCount;
    Thread gameThread = new Thread();
    KeyHandler keyH = new KeyHandler();
    Bag bag = new Bag();
    Board board;
    Player player;
    
    CommonGoals commonGoals;
    
    public GamePanel() {
        this.playerCount = getPlayerCount();
        if (playerCount == null) {
            System.out.println("main.GamePanel.<init>()");
            System.exit(1);
        }
        //this.setPreferredSize(new Dimension(1024, 640));
        this.setPreferredSize(new Dimension(width, height));
        this.setDoubleBuffered(true);
        this.setBackground(new Color(249, 226, 182));
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.allShelfs = new ArrayList<>();
        createAllShelfs(this.playerCount);
        this.board = new Board(this.playerCount, this, bag);
        this.player = new Player(this, keyH, board, allShelfs);
        this.startGameThread();
        
        this.commonGoals = new CommonGoals();


    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS;
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
                //System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.update();
        board.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        board.draw(g2);
        for (int i = 0; i < allShelfs.size(); i++) {
            allShelfs.get(i).draw(g2);
        }
        player.draw(g2);
        
        commonGoals.draw(g2);
        
        g2.dispose();
        

        
    }

    // DA RIFARE
    private Integer getPlayerCount() {
        this.playerCount = 0;
        boolean isValidInput = false;

        while (!isValidInput) {
            String input = JOptionPane.showInputDialog(null, "How many players will be playing the game?");
            if (input == null) {
                return null;
            }
            try {
                playerCount = Integer.parseInt(input);
                if (playerCount >= 2 && playerCount <= 4) {
                    isValidInput = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid input! Please enter a number between 2 and 4.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input! Please enter a whole number.");
            }
        }

        return this.playerCount;
    }

    private static String[] getPlayerUsernames(int playerCount) {
        String[] usernames = new String[playerCount];
        for (int i = 0; i < playerCount; i++) {
            String input = JOptionPane.showInputDialog(null, "Enter username for Player " + (i + 1));
            if (input == null || input.isEmpty()) {
            	
                return new String[0]; // User pressed cancel or closed the dialog               
            }
            usernames[i] = input;
        }
        return usernames;
    }

    public void createAllShelfs(int playerNumber) {
        for (int i = 0; i < playerNumber; i++) {
            String s = JOptionPane.showInputDialog("Choose Player " + i + " Name");
            Shelf shelf = new Shelf(this, s);
            allShelfs.add(shelf);
        }
    }

}
