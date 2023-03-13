/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package board;

import entity.Bag;
import entity.Card;
import entity.CardType;
import entity.PlacedCard;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import main.GamePanel;




/**
 *
 * @author pawel
 */
public final class Board {
    
    private final int numberOfPlayers;
    private int[][] boardLayout;
    private int boardSize;
    public ArrayList<PlacedCard> currentBoardLayout;
    GamePanel gp;
    Bag b;
    
    
    
    public Board(int numberOfPlayers,GamePanel gp,Bag b){
        this.numberOfPlayers = numberOfPlayers;
        this.gp = gp;
        this.b = b;
        this.currentBoardLayout = new ArrayList<>();
        setDefaultBoardLayout();
        setBoardSize();
        placeCardsOnBoard(b);
        b.printCardsInSize();
        b.printCardsOutSize();
        
        
    }
    
    public void setDefaultBoardLayout(){
        switch(this.numberOfPlayers){
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
    // MAYBE PUT THIS IN setDefaultBoardLayout method
    public void setBoardSize(){
        switch(this.numberOfPlayers){
            case 2:
                this.boardSize=29;
            case 3:
                this.boardSize=36;
            case 4:
                this.boardSize=44;
        }
    }
    
    public void placeCardsOnBoard(Bag b){
        for(int row = 0; row < this.boardLayout.length; row++) {
            for(int col = 0; col < this.boardLayout[row].length; col++){
                if(this.boardLayout[row][col]==1){
                    PlacedCard p = new PlacedCard(b.pullRandom().getCardType(),row,col,gp);
                    currentBoardLayout.add(p);               
                }
                
            }
            
        }
                
    }
    
    public void printAllCardsOnBoard(){
        
        currentBoardLayout.forEach(x -> System.out.println(x));
    }
    
    public void update(){
 
    }
    
    
    
    
    public void draw(Graphics2D g2){                     
        for (int row = 0; row < this.boardLayout.length; row++) {             
            for (int col = 0; col < this.boardLayout[row].length; col++) {                                     
                switch(this.boardLayout[row][col]){                     
                   case 0 -> {
                        g2.setColor(Color.BLACK);
                        g2.fillRect(col*gp.tileSize, row*gp.tileSize, gp.tileSize, gp.tileSize);
                    }
                    case 1 -> {
                        g2.setColor(Color.GRAY);
                        g2.fillRect(col*gp.tileSize, row*gp.tileSize, gp.tileSize, gp.tileSize);
                    }
                }
            }
        }
        
        //MOVE TO PlacedCard class 
        for(int i = 0; i < currentBoardLayout.size() ;i++){
            currentBoardLayout.get(i).draw(g2);
        }
    }
    
    
    public void removePlacedCard(int[] cords){
        for(int i = 0;i<currentBoardLayout.size();i++){
            if(cords[0]==(currentBoardLayout.get(i).getCardRow()*gp.tileSize)&&cords[1]==(currentBoardLayout.get(i).getCardCol()*gp.tileSize)){
                currentBoardLayout.remove(i);
            }
        }
    }
    
   
  
    
        
           
        
}
        
        
        
      
    
    
    
    
    
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*
    public void removeCard(int row,int col){
        this.boardLayout[row][col] = 0;
        /*
        playerBoard.getCard(Card card) ?
        */
    
    
    
    
    
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
    

