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
    boolean moving = false;
    int pixelCounter = 0;
    GamePanel gp;
    KeyHandler keyH;
    Board b;
    ArrayList<PlacedCard> chosenCards;
    int chosenCol;
    private ArrayList<Shelf> allShelfs;
    Shelf shelf;
    private boolean turnDone;
    
    
    int currentTurn;


    /**
     *
     * @param gp
     * @param keyH
     */
    public Player(GamePanel gp, KeyHandler keyH, Board b, ArrayList allShelfs) {
        this.gp = gp;
        this.keyH = keyH;
        this.b = b;
        setDefaultValues();
        this.currentTurn = 1;
        this.allShelfs = allShelfs;
        chosenCards = new ArrayList<PlacedCard>();
        this.chosenCol = -1;
        this.shelf = setStartingTurn();
        this.turnDone = false;
        printAllShelfState();
    }

    public void setDefaultValues() {
        x = 0;
        y = 0;
        speed = 48;
    }
    
    public void setCurrentTurn(){
        for(int i = 0; i < this.allShelfs.size();i++){
            this.shelf = this.allShelfs.get(i);
            shelf.setPlayerTurn(true);
        }
    }
    
    public Shelf setStartingTurn(){
        shelf = allShelfs.get(0);
        this.shelf.setPlayerTurn(true);
        return shelf;
    }
    
    /*
    public void nextTurn(){
        for(int i = 0; i < this.allShelfs.size();i++){ 
            Shelf s = this.allShelfs.get(i);
            if(s.getPlayerTurn()==true){
                s.setPlayerTurn(false);
                if(i==this.allShelfs.size()-1){
                    s = this.allShelfs.get(0);
                    i=0;
            }
                else
                    s = this.allShelfs.get(i+1);
                s.setPlayerTurn(true);
                this.shelf = s;
            }
        }
    }
*/
    
    public void nextTurn(){
        for(int i = 0; i < allShelfs.size();i++){
            System.out.println(i);///  4          
            Shelf s = this.allShelfs.get(i);   //0 
            if(s.getPlayerTurn()==true){
                s.setPlayerTurn(false);
        }
        s = allShelfs.get(currentTurn);
        s.setPlayerTurn(true);
        this.shelf = s;
    }
    }
    
    public void printAllShelfState(){
        for(int i = 0; i < allShelfs.size();i++){
            Shelf s = this.allShelfs.get(i); 
            System.out.println("SHELF : "+s+" SHELFS STATE "+s.getPlayerTurn());
        }
    }

    
    
    
    
    
    
    
    
    
    
    public void update() {
        if (moving == false) {
            if (keyH.upPressed) {
                y -= speed;
            } else if (keyH.downPressed) {
                y += speed;
            } else if (keyH.leftPressed) {
                x -= speed;
            } else if (keyH.rightPressed) {
                x += speed;
            } else if (keyH.spacePressed) {
                if(this.turnDone==false){
                b.chosenFromBoard(this.getPlayerX() / gp.tileSize, this.getPlayerY() / gp.tileSize);
                
                //System.out.println("CARD CORDS "+ b.getCardAtCords(getPlayerX(), getPlayerY()));
                //System.out.println("CHOSEN CARDS : "+b.chosenCards);
            }} //r canccella tutte le scelte nell'arraylist chosecards
            else if (keyH.rPressed) {
                b.deleteChosenCards();                                             
            } else if (keyH.enterPressed) {
                if(this.turnDone==false){
                chosenCards = b.sendChosenCards();                
                if(chosenCards != null && chosenCol == -1) {		
                	resetPlayerChoice();
                	this.chosenCol = getInputFromUser();
                }               
                if (b.chosenCards != null) {
                	if(shelf.isColumnAvailable(chosenCards, this.chosenCol)) {
                		shelf.placeOnShelf(chosenCards, chosenCol);
                        resetPlayerChoice();
                        b.removeChosenCardsFromBoard();
                        this.turnDone = true;
                        /*
                        nextTurn();
                        if(this.currentTurn==allShelfs.size()-1){
                            this.currentTurn = 0;
                        }
                        else
                            this.currentTurn++;
*/
                        
                        printAllShelfState();
                    } else {
                    	//System.out.println("non c'è abbastanza spazio nella colonna");
                    	resetPlayerChoice();
                    }
                }else {
                	//System.out.println("non hai scelto delle carte");
                	resetPlayerChoice();
                }
                //System.out.println(chosenCards);
                b.deleteChosenCards(); 		//cancella in automatico l'array               
                }
            }
            else if(keyH.pPressed){
                if(this.turnDone==true){
                nextTurn();
                        if(this.currentTurn==allShelfs.size()-1){
                            this.currentTurn = 0;
                        }
                        else
                            this.currentTurn++;
                        this.turnDone=false;
                }
            }
            moving = true;
        }
        if (moving == true) {
            pixelCounter += speed;

            if (pixelCounter == 48) {
                moving = false;
                pixelCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.fillOval(x, y, gp.tileSize, gp.tileSize);
    }

    public int getPlayerX() {
        return this.x;
    }

    public int getPlayerY() {
        return this.y;
    }

    public int getInputFromUser() {
        String userInput = JOptionPane.showInputDialog("Choose shelf column");
        this.chosenCol = Integer.parseInt(userInput);
        //System.out.println("Entrato getinput");
        return this.chosenCol;
    }

    public void resetPlayerChoice() {
        this.chosenCol = -1;
    }

}
