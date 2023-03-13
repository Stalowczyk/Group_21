/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import board.Board;
import entity.Bag;
import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author pawel
 */
public class GamePanel extends JPanel implements Runnable {
    
    
    final int orgiginalTileSize = 16;
    final int scale = 3;
  
    final public int tileSize = orgiginalTileSize * scale;
    final int maxScreenCol = 11;
    final int maxScreenRow = 11;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    
    int FPS = 10;
    
    Thread gameThread = new Thread();
    KeyHandler keyH = new KeyHandler();
    Bag b = new Bag();
    Board board = new Board(4, this,b);
    Player player = new Player(this,keyH);
    
    
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setDoubleBuffered(true);
        this.setBackground(Color.BLACK);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.startGameThread();
    
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    

    @Override
    public void run() {
        
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while(gameThread!=null){
            
            currentTime = System.nanoTime();            
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            
            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >=1000000000){
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        } 
        
    }
    
    public void update(){
       player.update();
    }
      
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        board.draw(g2);
        player.draw(g2);
        g2.dispose();
    }
    
    
    
    
}
                
                  
                        
                        
                
                
            
               
                    
            
    
    

