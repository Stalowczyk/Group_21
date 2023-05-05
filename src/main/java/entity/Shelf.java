package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	public boolean checkCorners() {                            //Sistema di controllo angoli della shelf
		CardType type = null;                                  //Controlla se tutti gli angoli hanno lo stesso cardType, serve per obbiettivo collettivo
		if (this.shelfLayout[0][0] != null) {                  // Filo
			type = this.shelfLayout[0][0].getCardType();
		}
		if (type != null && type == this.shelfLayout[0][4].getCardType()
				&& type == this.shelfLayout[5][0].getCardType()
				&& type == this.shelfLayout[5][4].getCardType()) {
			return true;
		}
		return false;}

	public boolean checkCardCount() {                                         // Controlla tutti gli elementi della shelf
		Map<CardType, Integer> cardCounts = new HashMap<>();
		for (int i = 0; i < this.shelfLayout.length; i++) {
			for (int j = 0; j < this.shelfLayout[i].length; j++) {
				Card card = this.shelfLayout[i][j];                          // Se trova una card, prende il suo cardtype e lo memorizza nella hashmap
				if (card != null) {
					CardType type = card.getCardType();
					int count = cardCounts.getOrDefault(type, 0);
					cardCounts.put(type, count + 1);
				}
			}
		}
		for (int count : cardCounts.values()) {                              // Controlla se nella hashmap ci sono almeno 8 cardtype uguali
			if (count >= 8) {                                                // Serve per secondo obbiettivo
				return true;
			}
		}
		return false;
	}

	public boolean checkXShape() {                                              // Controlla se nella shelf si forma una X di carte dello stesso cardtype
		for (int i = 0; i < this.shelfLayout.length; i++) {
			for (int j = 0; j < this.shelfLayout[i].length; j++) {
				if (i > 0 && i < 5 && j > 0 && j < 4) {                            // Questo if salta i bordi dove non si possono creare le x
					Card centro = this.shelfLayout[i][j];                          // Assegno una card Centro che sarà il centro della x
					if (centro != null) {
						CardType type = centro.getCardType();
						Card Angolo1 = this.shelfLayout[i+1][j+1];                   // assegno un nome ad ogni angolo e dopo controllo se tutti gli angoli hanno una card
						Card Angolo2 = this.shelfLayout[i+1][j-1];                   // e se la card ha lo stesso cardtype del centro
						Card Angolo3 = this.shelfLayout[i-1][j+1];
						Card Angolo4 = this.shelfLayout[i-1][j-1];
						if (Angolo1 != null && Angolo2 != null && Angolo3 != null && Angolo4 != null) {
							if (Angolo1.getCardType() == type && Angolo2.getCardType() == type && Angolo3.getCardType() == type && Angolo4.getCardType() == type) {
								return true;
							}
						}
					}
				}
			}
		} return false;
	}

	}


