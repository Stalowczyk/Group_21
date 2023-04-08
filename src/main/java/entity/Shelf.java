package entity;

public class Shelf {
	private Card[][] shelfLayout;
	private int maxRaw = 6;
	private int chosenColumn;
	
	public Shelf(int cardNumber, int chosenColumn) {
		this.shelfLayout = new Card[6][5];
		this.chosenColumn = chosenColumn;
	}
	public void PlaceOnShelf(int cardNumber, Card carta) {
		int startPoint = 0;
		int c = 0;
		
		while(shelfLayout[c][chosenColumn] != null) {
			startPoint++;
			c++;
		}
		
		for(int i = 0; i < cardNumber; i++) {		
			if((maxRaw - startPoint) >= cardNumber) {
				for(int j = 0; j < cardNumber; j++) {
					shelfLayout[startPoint+i][chosenColumn] = carta;
				}
			}else {
				//error condition
			}
		}
	}
}
