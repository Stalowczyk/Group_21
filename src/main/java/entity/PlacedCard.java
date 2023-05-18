/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import main.GamePanel;

/**
 *
 * @author pawel
 */
public class PlacedCard extends Card{
    
    private int row;
    private int col;
    private final GamePanel gp;

    public PlacedCard(CardType cardType,int row,int col,GamePanel gp) {
        super(cardType);
        this.row = row;
        this.col = col;
        this.gp = gp;
    }
    
    
    public int getCardRow(){
        return this.row;
    }
    
    public int getCardCol(){
        return this.col;
    }
    
    public int getCardX(){
        return this.row*gp.tileSize;
    }
    public int getCardY(){
        return this.col*gp.tileSize;
    }
    
    //NON SO PERCHE MA PER X VA USATO Y E PER Y X
    public void draw(Graphics2D g2){
        switch(this.getCardType()){
                case WHITE -> {
                    g2.setColor(Color.WHITE);
                    g2.fillRect(this.getCardY(),this.getCardX(), gp.tileSize, gp.tileSize);
                    
                }
                case PINK -> {
                    g2.setColor(Color.PINK);
                    g2.fillRect(this.getCardY(),this.getCardX(), gp.tileSize, gp.tileSize);
                }
                case BLUE -> {
                    g2.setColor(Color.BLUE);
                    g2.fillRect(this.getCardY(),this.getCardX(), gp.tileSize, gp.tileSize);
                }
                case CYAN -> {
                    g2.setColor(Color.CYAN);
                    g2.fillRect(this.getCardY(),this.getCardX(), gp.tileSize, gp.tileSize);
                }
                case GREEN -> {
                    g2.setColor(Color.GREEN);
                    g2.fillRect(this.getCardY(),this.getCardX(), gp.tileSize, gp.tileSize);
                }
                case YELLOW -> {
                    g2.setColor(Color.YELLOW);
                    g2.fillRect(this.getCardY(),this.getCardX(), gp.tileSize, gp.tileSize);
                }


            }
    }
    
}
