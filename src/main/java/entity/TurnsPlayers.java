
package entity;

import java.util.ArrayList;

//non è finito visto che non ho inserito alcuni dati per poi essere collegato al programma
//per adesso è cosi perchè vorrei un po di aiuto
public class TurnsPlayers {
	
	ArrayList<PlacedCard> chosenCards;
	public ArrayList<PlacedCard> chosenCardInOrder;
	
	private int nowPlayer;
	private int numbOfPlayer;
	
	public TurnsPlayers(int numbOfPlayer) {
		
		this.numbOfPlayer = numbOfPlayer;
		this.nowPlayer = 0;
		this.insertedCards = 0;
		chosenCards = new ArrayList<PlacedCard>();
		chosenCardInOrder = new ArrayList<PlacedCard>();
	}
	
	public int getNowPlayer() {
		return nowPlayer;
	}
	
	//quante carte il giocatore prende, visto che deve prendere per forsa almeno da 1 a 3 carte
	public void pickCards(ArrayList<PlacedCard> numbCards) {
		chosenCards = numbCards;
	}
	
	//passa automaticamente al prossio giocatore il turno
	public void insertCards(ArrayList<PlacedCard> numbCards) {
		chosenCardInOrder = numbCards;
		nextPlayer();
	}
	
	//quando il giocatore inserisce le carte nella sua griglia passa al prossimo
	public void nextPlayer() {
		nowPlayer = (nowPlayer + 1) % numbOfPlayer;
		chosenCards.isEmpty();
		chosenCardInOrder.isEmpty();
	}
	
	//controlla se il turno è finito quando prende le carte e le inserisce nel sua griglia
	public boolean turnFinish() {
		return chosenCards.size() > 0 && chosenCardInOrder.size() > 0 && chosenCards.size() <= 3;
	}
}

