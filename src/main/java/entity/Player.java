/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import board.Board;
import java.awt.Color;
import java.awt.Graphics2D;
import main.GamePanel;
import main.KeyHandler;

/**
 *
 * @author pawel
 */
public class Player {
    public int x, y;
    public int speed;
    boolean moving= false;
    int pixelCounter = 0;
    GamePanel gp;
    KeyHandler keyH;
    Board b;
    
    /**
     *
     * @param gp
     * @param keyH
     */
    public Player(GamePanel gp, KeyHandler keyH,Board b){
        this.gp = gp;
        this.keyH = keyH;
        this.b = b;
        setDefaultValues();
    }
    
    public void setDefaultValues(){
        x=0;
        y=0;
        speed=48;
    }
    
    
    public void update(){
        if(moving==false){
            if(keyH.upPressed)
               y -= speed;
           else if(keyH.downPressed)
               y += speed;
           else if(keyH.leftPressed)
               x -= speed;
           else if(keyH.rightPressed)
               x += speed;
           else if(keyH.spacePressed){
               
               PlacedCard p = b.getCardAtCords(getPlayerX(), getPlayerY());
               b.chosenFromBoard(p.getCardRow(), p.getCardCol());
               
           }
           moving = true;   
        }
        if(moving==true){
            pixelCounter+=speed;
            
            if(pixelCounter==48){
                moving = false;
                pixelCounter = 0;
            }
        }
    }
    
    public void draw(Graphics2D g2){
        g2.setColor(Color.RED);
        g2.fillOval(x, y, gp.tileSize, gp.tileSize);
        
    }
    
    
    public int getPlayerX(){
        return this.x;
    }
    
    public int getPlayerY(){
        return this.y;
    }
    
}
