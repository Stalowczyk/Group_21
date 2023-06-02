/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import board.Board;
import java.awt.Color;
import java.awt.Font;
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
    CommonGoals c;
    private static int completedTimes0 = 0;
    private static int completedTimes1 = 0;
    ArrayList<PlacedCard> chosenCards;
    Integer chosenCol;
    private ArrayList<Shelf> allShelfs;
    Shelf shelf;
    private boolean turnDone;

    public ArrayList<Integer> order = new ArrayList<>(); 
    public ArrayList<PlacedCard> chosenCardsInOrder = new ArrayList<>(); 
    
    int currentTurn;
    
    private boolean gameDone;
    private boolean test;
    private boolean firstShelfFilled;


    /**
     *
     * @param gp
     * @param keyH
     */
    public Player(GamePanel gp, KeyHandler keyH, Board b, ArrayList allShelfs, CommonGoals c) {
        this.gp = gp;
        this.keyH = keyH;
        this.b = b;
        this.c = c;
        this.firstShelfFilled=false;
        setDefaultValues();
        this.test = false;
        this.gameDone = false;
        this.currentTurn = 1;
        this.allShelfs = allShelfs;
        this.chosenCards = b.chosenCards;
        this.chosenCol = null;
        this.shelf = setStartingTurn();
        this.turnDone = false;
    }

    public void setDefaultValues() {
        x = 0;
        y = 0;
        speed = 48;
    }

    public void setCurrentTurn() {
        for (int i = 0; i < this.allShelfs.size(); i++) {
            this.shelf = this.allShelfs.get(i);
            shelf.setPlayerTurn(true);
        }
    }

    public Shelf setStartingTurn() {
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
    public void nextTurn() {
        for (int i = 0; i < allShelfs.size(); i++) {
            ///  4          
            Shelf s = this.allShelfs.get(i);   //0 
            if (s.getPlayerTurn() == true) {
                s.setPlayerTurn(false);
            }
            s = allShelfs.get(currentTurn);
            s.setPlayerTurn(true);
            this.shelf = s;
        }
    }

    public void printAllShelfState() {
        for (int i = 0; i < allShelfs.size(); i++) {
            Shelf s = this.allShelfs.get(i);
            System.out.println("SHELF : " + s + " SHELFS STATE " + s.getPlayerTurn());
        }
    }

    public void update() {
        //MOVEMENT
        this.test = checkAllShelfs();
        if(this.test==false) {
            if (moving == false) {
                if (keyH.upPressed) {
                    y -= speed;
                } else if (keyH.downPressed) {
                    y += speed;
                } else if (keyH.leftPressed) {
                    x -= speed;
                } else if (keyH.rightPressed) {
                    x += speed;
                    //INTERACTION
                } else if (keyH.spacePressed) {
                    if (this.turnDone == false) {
                        b.chosenFromBoard(this.getPlayerX() / gp.tileSize, this.getPlayerY() / gp.tileSize);
                    }
                } //r canccella tutte le scelte nell'arraylist chosecards
                else if (keyH.rPressed) {
                    b.deleteChosenCards();

                } else if (keyH.enterPressed) {
                    if (this.turnDone == false) {
                        if (!b.chosenCards.isEmpty()) {
                            //resetPlayerChoice();
                            this.chosenCol = getInputFromUser();
                        }
                        if (!b.chosenCards.isEmpty() && chosenCol != null) {
                            if (shelf.isColumnAvailable(b.chosenCards, this.chosenCol)) {
                            	
                            	this.setOrder();
                        		chosenCardsInOrder = b.changeOrder(order);

                            	
                                shelf.placeOnShelf(b.chosenCardsInOrder, this.chosenCol);
                                b.chosenCardsInOrder.clear();
                                
                                resetPlayerChoice();

                                b.removeChosenCardsFromBoard();

                                
                                if(order.size() > 0) {
                                this.turnDone = true;
                                }
                                order.clear();//
                                

                                if (this.shelf.isShelfFilled()) {
                                    this.shelf.isFirstShelfFilled();
                                    this.firstShelfFilled=true;
                                    this.shelf.setFinalTurn(true);
                                    //for shelf in allShelfs
                                    //if shelf.getFirstShelfFilled == false
                                    //this.shelf.setFirstShelfFilled

                                }
                                if(this.firstShelfFilled==true){
                                    this.shelf.setFinalTurn(true);
                                }

                            } else {
                               // System.out.println("non c'è abbastanza spazio nella colonna");
                            	JOptionPane.showMessageDialog(null, "there isn't enough space in the chosen column");
                                resetPlayerChoice();
                            }

                        } else {
                           // System.out.println("non hai scelto delle carte");
                            resetPlayerChoice();
                        }
                        //System.out.println(chosenCards);
                        b.deleteChosenCards();         //cancella in automatico l'array
                    }
                } else if (keyH.pPressed) {
                    if (this.turnDone == true) {
                        if(this.c.isFirstGoalAchieved(this.shelf) && !this.shelf.firstime1){
                            switch (completedTimes0) {
                                case 0 ->
                                        this.shelf.addPoints(8);
                                case 1 ->
                                        this.shelf.addPoints(6);
                                case 2 ->
                                        this.shelf.addPoints(4);
                                case 3 ->
                                        this.shelf.addPoints(2);
                                default ->
                                        throw new IllegalArgumentException("Index invalido");
                            }
                            completedTimes0++;
                            this.shelf.firstime1 = true;
                        }
                        if(this.c.isSecondGoalAchieved(this.shelf) && !this.shelf.firstime2){
                            switch (completedTimes1) {
                                case 0 ->
                                        this.shelf.addPoints(8);
                                case 1 ->
                                        this.shelf.addPoints(6);
                                case 2 ->
                                        this.shelf.addPoints(4);
                                case 3 ->
                                        this.shelf.addPoints(2);
                                default ->
                                        throw new IllegalArgumentException("Index invalido");
                            }
                            completedTimes1++;
                            this.shelf.firstime2 = true;
                        }
                        nextTurn();
                        if (this.currentTurn == allShelfs.size() - 1) {
                            this.currentTurn = 0;
                        } else {
                            this.currentTurn++;
                        }
                        this.turnDone = false;
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
            

        
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.fillOval(x, y, gp.tileSize, gp.tileSize);
        Font currentFont = new Font("Times New Roman", Font.BOLD, 18);
        g2.setColor(Color.BLACK);
        g2.setFont(currentFont);
        String s1 = String.valueOf(turnDone);
        if(this.test==true && this.gameDone==false){
            System.out.println("CALCOLO PUNTEGGIO");
            calculateAllShelfPoints();
            this.gameDone=true;
        }
        if(this.test==true){
        for(int i = 0 ; i < this.allShelfs.size();i++ ){
            System.out.println("STARTED DRAWING");
            Shelf s = this.allShelfs.get(i);
                g2.drawString(s.getPlayerName(), 875, 36*i+384);
                g2.drawString(String.valueOf(s.getPoints()), 1100 , 36*i+384);
               
                
            }
        }
        
    }
    
    public void calculateAllShelfPoints(){
        for(int i = 0 ; i < this.allShelfs.size();i++ ){
            Shelf s = this.allShelfs.get(i);
            s.findCardGroups();
            s.addPoints(s.getPersonalGoalCardPoints());
        }
    }
    
    
    public int getPlayerX() {
        return this.x;
    }

    public int getPlayerY() {
        return this.y;
    }

    public Integer getInputFromUser() {
        boolean isValidInput = false;

        while (!isValidInput) {
            String input = JOptionPane.showInputDialog(null, "Choose a column between 0 and 4");
            if (input == null) {
                return null;
            }

            try {
                this.chosenCol = Integer.parseInt(input);
                if (this.chosenCol >= 0 && chosenCol <= 4) {
                    isValidInput = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid input! Please choose a column between 0 and 4.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input! Please enter a whole number.");
            }
        }

        return this.chosenCol;
    }

    public void resetPlayerChoice() {
        this.chosenCol = null;
    }
    

    public boolean checkAllShelfs(){
        for(int i = 0;i<this.allShelfs.size();i++){
            Shelf s = this.allShelfs.get(i);
            System.out.println(s.hashCode()+" "+s.getFinalTurn());
            if(s.getFinalTurn() == false){
                return false;
            }
        }
        return true;
    }
    
   
    
    public boolean sameNumbers(int number) {
    	for(int i = 0; i < order.size(); i++) {
    		if(order.get(i) == number) {
    			return true;
    		}
    	}
    	return false;
    }

    public void setOrder(){
    	Integer number = null;
			if (chosenCards.size() >= 1) {
				for (int i = 0; i < chosenCards.size(); i++) {
					if(chosenCards.size() > 1) {
						number = this.getNumberForOrder(i);
					}else if(chosenCards.size() == 1) {
						number = 0;
					}
					if (number == null) {
						i = chosenCards.size() - 1;
						 b.deleteChosenCards();         //cancella in automatico l'array
						 order.clear();
					} else {
						if (!this.sameNumbers(number)) {
							order.add(number);
						} else {
							JOptionPane.showMessageDialog(null, "Invalid input! you put the same number twice");
							i--;
						}
					}
				}
			}    	

    }

    

    public Integer getNumberForOrder(int i) {
    	Integer number = null;
    	 boolean isValidInput = false;
    	while (!isValidInput) {
            String sid = JOptionPane.showInputDialog(null, "set the "+(i+1)+"st card to put in shelf");
            if (sid == null) {
                return null;
            }

            try {
                number = Integer.parseInt(sid);
                if (number >= 0 && number < chosenCards.size()) {
                    isValidInput = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid input! Please enter a number between 0 and "+(chosenCards.size()-1));
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input! Please enter a whole number.");
            }
        }
    	return number;
        
    }
    	

   
}






