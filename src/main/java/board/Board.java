/*


 * 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package board;

import entity.Bag;
import entity.Card;
import entity.CardType;
import entity.PlacedCard;
import entity.Shelf;

import java.awt.Color;
import java.awt.Font;
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

    private int firstRow;
    private int firstCol;
    private int oldRaw;
    private int oldCol;
    private boolean first;
    Shelf s;
    public ArrayList<PlacedCard> chosenCards;
    public ArrayList<PlacedCard> chosenCardsInOrder;		//******************

    public Board(int numberOfPlayers, GamePanel gp, Bag b, Shelf s) {
        this.numberOfPlayers = numberOfPlayers;
        this.gp = gp;
        this.b = b;
        this.currentBoardLayout = new ArrayList<>();
        setDefaultBoardLayout();
        setBoardSize();
        placeCardsOnBoard(b);
        chosenCards = new ArrayList<PlacedCard>();
        chosenCardsInOrder = new ArrayList<PlacedCard>();		//******************
    }

    public void setDefaultBoardLayout() {
        switch (this.numberOfPlayers) {
            case 2 ->
                this.boardLayout = new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0}, {0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0}, {0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
            case 3 ->
                this.boardLayout = new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
            case 4 ->
                this.boardLayout = new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0}, {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        }
    }

    public int[][] getBoardLayout() {
        return this.boardLayout;
    }

    public int getBoardRows() {
        return this.boardLayout.length;
    }

    public int getBoardColumns() {
        return this.boardLayout[0].length;
    }

    public void modifyCell(int row, int col, int number) {
        this.boardLayout[row][col] = number;
    }

    public void randomizeBoard() {
        for (int col = 0; col < this.boardLayout.length; col++) {
            for (int row = 0; row < this.boardLayout[col].length; row++) {
                if (this.boardLayout[row][col] == 1) {
                    Random rand = new Random();
                    int randomCard = rand.nextInt((6 - 1) + 1) + 1;
                    modifyCell(row, col, randomCard);
                }
            }
        }
    }

    // MAYBE PUT THIS IN setDefaultBoardLayout method
    public void setBoardSize() {
        switch (this.numberOfPlayers) {
            case 2:
                this.boardSize = 29;
            case 3:
                this.boardSize = 36;
            case 4:
                this.boardSize = 44;
        }
    }

    public void placeCardsOnBoard(Bag b) {
        for (int col = 0; col < this.boardLayout.length; col++) {
            for (int row = 0; row < this.boardLayout[col].length; row++) {
                if (this.boardLayout[row][col] == 1) {
                    PlacedCard p = new PlacedCard(b.pullRandom().getCardType(), row, col, gp);
                    currentBoardLayout.add(p);
                }

            }

        }

    }

    //distribuisce le carte sulla board anche se alcune sono già piazzate
    //per adesso lascio così poi si vedrà
    public void repeatPlaceCardsOnBoard(Bag b) {
        for (int col = 0; col < this.boardLayout.length; col++) {
            for (int row = 0; row < this.boardLayout[col].length; row++) {
                if (this.boardLayout[row][col] == 1 && this.getCardAtCords(row * gp.tileSize, col * gp.tileSize) == null) {
                    PlacedCard p = new PlacedCard(b.pullRandom().getCardType(), row, col, gp);
                    currentBoardLayout.add(p);
                }

            }

        }

    }

    public void printAllCardsOnBoard() {

        currentBoardLayout.forEach(x -> System.out.println(x));
    }

    public void update() {
        if (this.refreshBoard()) {
            //System.out.println("è entrato nell'update");
            this.repeatPlaceCardsOnBoard(b);

        }
        //System.out.println("refresh");
    }

    public void draw(Graphics2D g2) {
        for (int col = 0; col < this.boardLayout.length; col++) {
            for (int row = 0; row < this.boardLayout[col].length; row++) {
                switch (this.boardLayout[row][col]) {
                    /*MAGENTA = DIMENSIONS OF WHOLE ARRAY 
                    case 0 -> {
                        g2.setColor(Color.LIGHT_GRAY);
                        g2.fillRect(row * gp.tileSize, col * gp.tileSize, gp.tileSize, gp.tileSize);
                    }
                    */

                    case 1 -> {
                        g2.setColor(Color.GRAY);
                        g2.drawRect(row * gp.tileSize, col * gp.tileSize, gp.tileSize, gp.tileSize);
                    }
                }
                g2.setColor(Color.BLACK);
                g2.drawRect(1,1, 528, 528);
            }
        }

        //MOVED DRAWING TO PlacedCard class 
        for (int i = 0; i < currentBoardLayout.size(); i++) {
            currentBoardLayout.get(i).draw(g2);
        }
        Font currentFont = g2.getFont();
        currentFont = currentFont.deriveFont(currentFont.getSize()*1.4F);
        g2.setFont(currentFont);
        for(int i = 0; i < chosenCards.size();i++){
            g2.drawString(String.valueOf(i), chosenCards.get(i).getCardX(), chosenCards.get(i).getCardY()+48);
        }
    }

    public void removePlacedCard(int x, int y) {
        for (int i = 0; i < currentBoardLayout.size(); i++) {
            if (x == (currentBoardLayout.get(i).getCardX()) && y == (currentBoardLayout.get(i).getCardY())) {
                currentBoardLayout.remove(i);

            }
        }

    }

    public boolean isCardPlaced(int x, int y) {
        for (int i = 0; i < currentBoardLayout.size(); i++) {
            if (x == (currentBoardLayout.get(i).getCardX()) && y == (currentBoardLayout.get(i).getCardY())) {

                return true;
            }
        }
        return false;
    }

    public void determinePossibility(int x, int y) {
        if (isCardPlaced(x, y)) {

        }
    }

    public boolean hasAFreeBorder(int x, int y) {
        PlacedCard card = getCardAtCords(x, y);
        if (card != null) {
            if (isCardPlaced(card.getCardX() + 48, y)) {
                if (isCardPlaced(card.getCardX() - 48, y)) {
                    if (isCardPlaced(x, card.getCardY() + 48)) {
                        if (isCardPlaced(x, card.getCardY() - 48)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public PlacedCard getCardAtCords(int x, int y) {
        for (int i = 0; i < currentBoardLayout.size(); i++) {
            if (x == (currentBoardLayout.get(i).getCardX()) && y == (currentBoardLayout.get(i).getCardY())) {
                return currentBoardLayout.get(i);
            }
        }
        return null;
    }

    public void chosenFromBoard(int chosenRow, int chosenCol) {
        first = chosenCards.isEmpty();
        PlacedCard t = this.getCardAtCords(chosenRow * gp.tileSize, chosenCol * gp.tileSize);
        if (t == null) {
            System.out.println("non c'è la carta");
        } else {
            if (chosenCards.size() < 3) {

                if (this.hasAFreeBorder(chosenRow * gp.tileSize, chosenCol * gp.tileSize)) {
                    if (first) {
                        chosenCards.add(this.getCardAtCords(chosenRow * gp.tileSize, chosenCol * gp.tileSize));
                        firstRow = chosenRow;
                        firstCol = chosenCol;
                        oldRaw = chosenRow;
                        oldCol = chosenCol;
                    } else {
                        if (chosenRow == firstRow && chosenRow == oldRaw) {
                            if (chosenCol == oldCol + 1 || chosenCol == oldCol - 1) {
                                chosenCards.add(this.getCardAtCords(chosenRow * gp.tileSize, chosenCol * gp.tileSize));
                                oldRaw = chosenRow;
                                oldCol = chosenCol;
                            }
                        }
                        if (chosenCol == firstCol && chosenCol == oldCol) {
                            if (chosenRow == oldRaw + 1 || chosenRow == oldRaw - 1) {
                                chosenCards.add(this.getCardAtCords(chosenRow * gp.tileSize, chosenCol * gp.tileSize));
                                oldRaw = chosenRow;
                                oldCol = chosenCol;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(chosenCards);
    }
    

    

    public void deleteChosenCards() {
        if (!this.chosenCards.isEmpty()) {
            this.chosenCards.clear();
        }
    }

    //questo metodo restituisce true se la card ha tutti e 4 i lati liberi
    public Boolean hasAllFreeBorders(int x, int y) {
        PlacedCard card = getCardAtCords(x, y);
        if (card != null) {
            if (!isCardPlaced(card.getCardX() + 48, y) && !isCardPlaced(card.getCardX() - 48, y) && !isCardPlaced(x, card.getCardY() + 48) && !isCardPlaced(x, card.getCardY() - 48)) {
                //System.out.println("sono entrato nell'if");
                return true;
            }
        }
        //System.out.println("non entrato");
        return false;
    }

    //questo metodo restituisce true se la board va refreshata
    public Boolean refreshBoard() {
        for (int i = 0; i < this.currentBoardLayout.size(); i++) {
            int x = this.currentBoardLayout.get(i).getCardX();
            int y = this.currentBoardLayout.get(i).getCardY();
            if (!this.hasAllFreeBorders(x, y)) {
                return false;
            }
        }
        return true;
    }

    //questo metodo scorre l'arraylist e rimuove le carte selezionate dall board
    public void removeChosenCardsFromBoard() {
        for (int i = 0; i < this.chosenCards.size(); i++) {
            this.removePlacedCard(this.chosenCards.get(i).getCardX(), this.chosenCards.get(i).getCardY());
        }
        chosenCards.clear();
    }

    //questo metodo crea l'arrayList chosenCardsInOrder
    public ArrayList<PlacedCard> changeOrder(ArrayList<Integer> order) {		//passo l'array contenente l'ordine (1, 3, 2)
    	//chosenCardsInOrder.clear();
 		for(int i = 0; i < order.size(); i++) {
 			int c = order.get(i);
 			PlacedCard pc = this.chosenCards.get(c);
 			chosenCardsInOrder.add(pc);
 		}
 		return chosenCardsInOrder;
 		
 		//questo andrebbe messo dopo che metti le carte nella shelf
 			//chosenCardsInOrder.clear();
 		//*******************
 	}
 	

 	
 	/*
 	public void sendCardsToShelf() {
 		//Scanner sc = new Scanner(System.in);	
 		//int chosenCol = sc.nextInt();
 		//s.placeOnShelf(chosenCardsInOrder, chosenCol);
 		//sc.close();
 	}*/
    
    public ArrayList<PlacedCard> sendChosenCards() {
        if (!this.chosenCards.isEmpty()) {
            return this.chosenCards;
        }
        return null;
    }

    public void sendChosenCol() {

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
