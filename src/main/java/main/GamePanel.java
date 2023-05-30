/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import board.Board;
import entity.Bag;
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
    int width = (int) (dim.width * 0.80);
    int height = (int) (dim.height * 0.80);   
            
    public int playerCount = getNumberOfPlayers();
    
    Thread gameThread = new Thread();
    KeyHandler keyH = new KeyHandler();
    Bag bag = new Bag();
    Board board = new Board(playerCount, this, bag);
    Player player;
    
    public GamePanel() {
        //this.setPreferredSize(new Dimension(1024, 640));
        this.setPreferredSize(new Dimension(width,height));
        this.setDoubleBuffered(true);
        this.setBackground(new Color(249,226,182));
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.allShelfs = new ArrayList<>(); 
        createAllShelfs(playerCount);
        this.player = new Player(this, keyH,board,allShelfs);
        this.startGameThread();

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
        board.update();
        player.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        board.draw(g2);
        for(int i = 0; i < allShelfs.size();i++)
            allShelfs.get(i).draw(g2);
        player.draw(g2);
        g2.dispose();
    }
    
    
    // DA RIFARE
    public int getNumberOfPlayers(){
        String s = JOptionPane.showInputDialog("Choose Number of Players");
        int playerCount = Integer.parseInt(s);
        switch (playerCount) {
            case 2 -> {
                return playerCount;
            }
            case 3 -> {
                return playerCount;
            }
            case 4 -> {
                return playerCount;
            }
            default -> getNumberOfPlayers();     
        }
        return -1;
    }
    
    public void createAllShelfs(int playerNumber){
        for(int i = 0; i < playerNumber; i++){
            Shelf shelf = new Shelf(this);
            allShelfs.add(shelf);
        }
    }
       

}   
