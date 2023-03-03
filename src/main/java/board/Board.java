/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package board;

import cards.Card;
import static java.lang.Math.random;
import static java.lang.StrictMath.random;
import java.util.Random;




/**
 *
 * @author pawel
 */
public class Board {
    
    
    private int[][] boardLayout;
    
    
    
    public void setBoardLayout(int numberOfPlayers){
        switch(numberOfPlayers){
            case 2 -> this.boardLayout = new int[][]{{0,0,0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0,0,0}, {0,0,0,0,1,1,0,0,0,0,0}, {0,0,0,0,1,1,1,0,0,0,0}, {0,0,0,1,1,1,1,1,1,0,0}, {0,0,1,1,1,1,1,1,1,0,0}, {0,0,1,1,1,1,1,1,0,0,0}, {0,0,0,0,1,1,1,0,0,0,0}, {0,0,0,0,0,1,1,0,0,0,0}, {0,0,0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0,0,0}};
            case 3 -> this.boardLayout = new int[][]{{0,0,0,0,0,0,0,0,0,0,0}, {0,0,0,0,1,0,0,0,0,0,0}, {0,0,0,0,1,1,0,0,0,0,0}, {0,0,0,1,1,1,1,1,0,0,0}, {0,0,0,1,1,1,1,1,1,1,0}, {0,0,1,1,1,1,1,1,1,0,0}, {0,1,1,1,1,1,1,1,0,0,0}, {0,0,0,1,1,1,1,1,0,0,0}, {0,0,0,0,0,1,1,0,0,0,0}, {0,0,0,0,0,0,1,0,0,0,0}, {0,0,0,0,0,0,0,0,0,0,0}};
            case 4 -> this.boardLayout = new int[][]{{0,0,0,0,0,0,0,0,0,0,0}, {0,0,0,0,1,1,0,0,0,0,0}, {0,0,0,0,1,1,1,0,0,0,0}, {0,0,0,1,1,1,1,1,0,0,0}, {0,0,1,1,1,1,1,1,1,1,0}, {0,1,1,1,1,1,1,1,1,1,0}, {0,1,1,1,1,1,1,1,1,0,0}, {0,0,0,1,1,1,1,1,0,0,0}, {0,0,0,0,1,1,1,0,0,0,0}, {0,0,0,0,0,1,1,0,0,0,0}, {0,0,0,0,0,0,0,0,0,0,0}};
        }
    }
    
    public int[][] getBoardLayout(){
        return this.boardLayout;
    }
    
    public int getBoardRows(){
        return this.boardLayout.length;
    }
    
    public int getBoardColumns(){
        return this.boardLayout[0].length;           
    }
    
    public void modifyCell(int row, int col,int number){
        this.boardLayout[row][col] = number;            
    }
            
    public void randomizeBoard(){
        for (int row = 0; row < this.boardLayout.length; row++) {    
            for (int col = 0; col < this.boardLayout[row].length; col++) {
                if(this.boardLayout[row][col]==1){
                    Random rand = new Random();
                    int randomCard = rand.nextInt((6-1)+1)+1;
                    modifyCell(row, col, randomCard);
                }
            }
        }           
    }
    
    public void removeCard(int row,int col){
        this.boardLayout[row][col] = 0;
        /*
        playerBoard.getCard(Card card) ?
        */
    }
    
    
    
    
    /*
    public void removeCard(Tile tile){
        if(tile.isTileOccupied())
            modifyCell(0, 0, 0)
    }
    */
    
    /*
    public Tile getTile(final int tileCoordinate){
        return null;
    }
    */
    
}
