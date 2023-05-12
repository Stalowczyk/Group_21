package entity;

//non è finito visto che non ho inserito alcuni dati per poi essere collegato al programma
//per adesso è cosi perchè vorrei un po di aiuto
public class TurnsPlayers {
	
	private int nowPlayer;
	private int numbOfPlayer;
	private int takenCards;
	private int insertedCards;
	
	public TurnsPlayers(int numbOfPlayer) {
		
		this.numbOfPlayer = numbOfPlayer;
		this.nowPlayer = 0;
		this.takenCards = 0;
		this.insertedCards = 0;
	}
	
	public int getNowPlayer() {
		return nowPlayer;
	}
	
	//quante carte il giocatore prende, visto che deve prendere per forsa almeno da 1 a 3 carte
	public void pickCards(int numbCards) {
		takenCards = numbCards;
	}
	
	//passa automaticamente al prossio giocatore il turno
	public void insertCards(int numbCards) {
		insertedCards = numbCards;
		nextPlayer();
	}
	
	//quando il giocatore inserisce le carte nella sua griglia passa al prossimo
	public void nextPlayer() {
		nowPlayer = (nowPlayer + 1) % numbOfPlayer;
		takenCards = 0;
		insertedCards = 0;
	}
	
	//controlla se il turno è finito quando prende le carte e le inserisce nel sua griglia
	public boolean turnFinish() {
		return takenCards > 0 && insertedCards > 0 && takenCards <= 3;
	}
}
