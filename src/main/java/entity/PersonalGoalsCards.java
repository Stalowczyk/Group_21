package entity;

import java.util.ArrayList;
import java.util.Random;

import main.GamePanel;

public class PersonalGoalsCards {
	
	final int numberOfSpace = 6;
	int number;
	final int NumberOfRow = 6;
	final int numberOfCol = 5;
	int[] personalGoal;
	int[][] personalGoalCardsLayout;
	public ArrayList<PlacedCard> currentGoalCardLayout = new ArrayList<>();
	private int numberOfPlayers;
	ArrayList<Card> spaceIn;
	GamePanel gp;

	public PersonalGoalsCards(int numberOfPlayers, GamePanel gp) { // a seconda di quanti giocatori ci sono so quante
																	// goalsCards generare
		this.spaceIn = new ArrayList<>();
		this.numberOfPlayers = numberOfPlayers;
		this.gp = gp;
	}

	public int[][] getPersonalGoalCardsLayout() {
		return this.personalGoalCardsLayout;
	}

	public void randomizeSixSpace() { // genera il numero della casella dove andrà il goal
		personalGoal = new int[6];
		Random random = new Random();
		for (int i = 0; i <= numberOfSpace; i++) {
			number = random.nextInt(30);
			personalGoal[i] = number;
		}

		personalGoalCardsLayout = new int[][] { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 } };

		for (int i = 0; i < numberOfSpace; i++) {
			personalGoalCardsLayout[personalGoal[i] / 5][(personalGoal[i] % 5)] = 1;	
		}
	}

	public void placeCardsOnSpaces() {
		for (int i = 0; i <= 6; i++) {
			for (int j = 0; j <= 5; j++) {
				if (personalGoalCardsLayout[i][j] == 1) {
					PlacedCard card = new PlacedCard(spaceIn.get(i).getCardType(), i, j, gp);
					currentGoalCardLayout.add(card);
				}
			}
		}
	}

	public void AddAllTiles() {
		for (int i = 0; i <= 6; i++) {
			for (CardType s : CardType.values()) {
				Card c = new Card(s);
				spaceIn.add(c);
			}
		}
	}
	
	//questo metodo restituisce il puntegio in base a quante carte sono uguali tra carta obiettivo e shelf
	public int score(Shelf s) {
		int cont = 0;
		int sc = 0;	//punteggio
		
		for (int i = 0; i <= 6; i++) {
			int c = this.currentGoalCardLayout.get(i).getCardCol(); // colonna da confrontare
			int r = this.currentGoalCardLayout.get(i).getCardRow(); // riga da confrontare
			if (s.getCard(r, c).getCardType() == this.currentGoalCardLayout.get(i).getCardType()) cont++;
		}
		switch (cont) {
		case 0 -> sc = 0;
		case 1 -> sc = 1;
		case 3 -> sc = 4;
		case 4 -> sc = 6;
		case 5 -> sc = 9;
		case 6 -> sc = 12;
		}
		return sc;
	}
	

}
