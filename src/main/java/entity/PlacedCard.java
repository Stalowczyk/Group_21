/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.Graphics2D;

/**
 *
 * @author pawel
 */
public class PlacedCard extends Card{
    
    public int row;
    public int col;

    public PlacedCard(CardType cardType,int row,int col) {
        super(cardType);
        this.row = row;
        this.col = col;
    }
    
    
    
    public int getCardRow(){
        return row;
    }
    public int getCardCol(){
        return col;
    }
    
    public void draw(Graphics2D g2){
        
    }
    
}
