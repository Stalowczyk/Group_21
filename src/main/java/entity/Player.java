/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

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
    
    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
    }
    
    public void setDefaultValues(){
        x=0;
        y=0;
        speed=16;
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
        g2.setColor(Color.BLACK);
        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
    }
    
    
}
