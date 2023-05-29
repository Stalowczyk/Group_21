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
    Shelf s;
    int chosenCol;
    int currentPlayer;
    
    
    public ArrayList<Integer> order = new ArrayList<>(); 
    public ArrayList<PlacedCard> chosenCardsInOrder = new ArrayList<>(); 

    /**
     *
     * @param gp
     * @param keyH
     */
    public Player(GamePanel gp, KeyHandler keyH, Board b, Shelf s) {
        this.gp = gp;
        this.keyH = keyH;
        this.b = b;
        setDefaultValues();
        chosenCards = new ArrayList<PlacedCard>();
        this.s = s;
        this.chosenCol = -1;
        this.currentPlayer = 0;
    }

    public void setDefaultValues() {
        x = 0;
        y = 0;
        speed = 48;
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
                b.chosenFromBoard(this.getPlayerX() / gp.tileSize, this.getPlayerY() / gp.tileSize);
                System.out.println(b.hasAFreeBorder(getPlayerX(), getPlayerY()));
            } //r canccella tutte le scelte nell'arraylist chosecards
            else if (keyH.rPressed) {
                b.deleteChosenCards();
                
                
                
            } else if (keyH.enterPressed) {
                chosenCards = b.sendChosenCards();
                
                if(chosenCards != null && chosenCol == -1) {		
                	resetPlayerChoice();
                	this.chosenCol = getInputFromUser();
                }
                
                if (chosenCards != null) {
                	if(s.isColumnAvailable(chosenCards, this.chosenCol)) {
                		
                		this.setOrder();

                		//qui ci va b.changeOrder()
                		chosenCardsInOrder = b.changeOrder(order);
                		order.clear();
                		
                		s.placeOnShelf(chosenCardsInOrder, chosenCol); //chosenCards
                        resetPlayerChoice();
                        b.removeChosenCardsFromBoard();
                        currentPlayer++;
                        
                    } else {
                    	//System.out.println("non c'è abbastanza spazio nella colonna");
                    	resetPlayerChoice();
                    }
                }else {
                	//System.out.println("non hai scelto delle carte");
                	resetPlayerChoice();
                }
                System.out.println(chosenCards);
                b.deleteChosenCards(); 		//cancella in automatico l'array
                
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
        String s = JOptionPane.showInputDialog("Choose shelf column");
        this.chosenCol = Integer.parseInt(s);
        System.out.println("Entrato getinput");
        return this.chosenCol;
    }

    public void resetPlayerChoice() {
        this.chosenCol = -1;
    }
    
    public void setOrder(){
		//pop up dove si crea l'arrayList con l'ordine (1, 3, 2)
		if(chosenCards.size() > 1) {
			for(int i = 0; i < chosenCards.size(); i++) {
    			String sid = JOptionPane.showInputDialog("set the "+(i+1)+"st "+"card to put in shelf");
    	        int number = Integer.parseInt(sid);
    	        
    	        if(number >= 0 && number < (chosenCards.size())) {
    	        	if(!this.sameNumbers(number)) {
    	        		order.add(number);
    	        	}else {
    	        		System.out.println("hai inserito un numero uguale ad uno precedentemente inserito");
    	        		i--;
    	        	}
    	        }else {
    	        	System.out.println("inserisci un numero coerente");
    	        	i--;
    	        }
    	        
    		}
		}else order.add(1);
    }
    
    public boolean sameNumbers(int number) {
    	for(int i = 0; i < order.size(); i++) {
    		if(order.get(i) == number) {
    			return true;
    		}
    	}
    	return false;
    }

}
