/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import board.Board;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

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
    private int chosenCol;
    
    /**
     *
     * @param gp
     * @param keyH
     */
    public Player(GamePanel gp, KeyHandler keyH,Board b){
        this.chosenCol = -1;
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
        	   b.chosenFromBoard(this.getPlayerX()/gp.tileSize, this.getPlayerY()/gp.tileSize);
        	   System.out.println(b.hasAFreeBorder(getPlayerX(), getPlayerY()));
           }
            
          //r canccella tutte le scelte nell'arraylist chosecards
           else if(keyH.rPressed) {
        	   b.deleteChosenCards();
           }
            //if chosencards != null display 
           else if(keyH.enterPressed) {	
        	  
        	  b.removeChosenCardsFromBoard();
        	  //b.setOrder();
         	  b.deleteChosenCards(); 
                  
                  
         	 
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
    
     public int getInputFromUser(){
         String s = JOptionPane.showInputDialog("Choose shelf column");
         this.chosenCol = Integer.parseInt(s);
         System.out.println("Entrato getinput");
         
         return this.chosenCol;
     }
     
     public void resetPlayerChoice(){
         this.chosenCol = -1;
     }
    
}
