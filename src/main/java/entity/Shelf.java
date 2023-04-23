package entity;

import java.util.ArrayList;

public class Shelf {
	private Card[][] shelfLayout;
	private int maxRaw = 6;
	private int chosenColumn;
	
	public Shelf(int cardNumber, int chosenColumn) {
		this.shelfLayout = new Card[6][5];
		this.chosenColumn = chosenColumn;
	}
	
	
	
	public void placeOnShelf(ArrayList<Card> chosenCards) {		
		int startPoint = 0;
		int c = 0;
		int cardNumber = chosenCards.size();
		
		while(shelfLayout[c][chosenColumn] != null) {
			startPoint++;
			c++;
		}
			
			if((maxRaw - startPoint) >= cardNumber) {	
				for(int i = 0; i < cardNumber; i++) {
					shelfLayout[startPoint+i][chosenColumn] = chosenCards.get(i);
				}
				for(int i = 0; i < chosenCards.size(); i++) {
					chosenCards.remove(i);
				}
			}
			else {
				//error condition
			}
		}
	}


