package entity;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;

public class PersonalGoalsCards {

	final int numberOfSpace = 6;
	int number;
	final int numberOfRow = 6;
	final int numberOfCol = 5;
	// int[] personalGoal;
	public ArrayList<Integer> personalGoal = new ArrayList<>();

	int[][] personalGoalCardsLayout;
	public ArrayList<PlacedCard> currentGoalCardLayout = new ArrayList<>();
	private int numberOfPlayers;
	ArrayList<Card> spaceIn;
	GamePanel gp;

	// CardType[] types;
	public ArrayList<CardType> types;
	int c = 0;

	// a seconda di quanti giocatori ci sono so quante goalsCards generare
	public PersonalGoalsCards(int numberOfPlayers, GamePanel gp) {
		this.spaceIn = new ArrayList<>();
		this.numberOfPlayers = numberOfPlayers;
		this.gp = gp;

		c = 0;
		types = new ArrayList<>();
		this.addAllTiles();
		this.randomizeSixSpace();
		this.placeCardsOnSpaces();
		System.out.println(currentGoalCardLayout);

	}

	public int[][] getPersonalGoalCardsLayout() {
		return this.personalGoalCardsLayout;
	}

	// genera il numero della casella dove andrà il goal
	public void randomizeSixSpace() {
		// personalGoal contiene 6 numeri casuali da 0 a 30 i quali servono per posizionare le tessere fittizzie in quelle coordinate
		personalGoal.clear();
		Random random = new Random();
		for (int i = 0; i < numberOfSpace; i++) {
			number = random.nextInt(30);
			if (this.isSingleNumber(number, personalGoal)) { // controllo affinchè non si abbiano due numeri uguali
				personalGoal.add(number);
			} else {
				i--;
			}
		}

		personalGoalCardsLayout = new int[][] { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 } };

		for (int i = 0; i < numberOfSpace; i++) {
			personalGoalCardsLayout[personalGoal.get(i) / 5][(personalGoal.get(i) % 5)] = 1; // mette a 1 nei punti in cui va messa la placedCard fittizia
		}

	}

	// sapendo dove vanno posizionate le PlacedCard, le posiziona
	public void placeCardsOnSpaces() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				if (personalGoalCardsLayout[i][j] == 1) {
					PlacedCard card = new PlacedCard(types.get(c), i, j, gp); // types è un ArrayList contenente tutti i tipi di carte disposti casualmente
					c++;
					currentGoalCardLayout.add(card);
				}
			}
		}
	}

	// genera l'ArrayList types che contiene tutti i tipi di carte disposti in ordine casuale
	public void addAllTiles() {

		CardType[] ct = CardType.values();
		ArrayList<Integer> randomNumbers = new ArrayList<>();

		for (int i = 0; i < 6; i++) {
			Random rand = new Random();
			int number = rand.nextInt(6);

			if (this.isSingleNumber(number, randomNumbers)) { // controllo per evitare che due numeri si ripetano
				randomNumbers.add(number);
			} else {
				i--;
			}
		}

		for (int j = 0; j < 6; j++) {
			types.add(ct[randomNumbers.get(j)]);
		}
	}

	// controllo per evitare che due numeri si ripetano
	public boolean isSingleNumber(int number, ArrayList<Integer> randomNumbers) {

		for (int i = 0; i < randomNumbers.size(); i++) {
			if (number == randomNumbers.get(i)) {
				return false;
			}
		}
		return true;
	}

	// questo metodo restituisce il puntegio in base a quante carte sono uguali tra carta obiettivo e shelf
	public int score(Shelf s) {
		int cont = 0;
		int sc = 0; // punteggio

		for (int i = 0; i < 6; i++) {
			int c = this.currentGoalCardLayout.get(i).getCardCol(); // colonna da confrontare
			int r = this.currentGoalCardLayout.get(i).getCardRow(); // riga da confrontare
			if (s.getCard(r, c).getCardType() == this.currentGoalCardLayout.get(i).getCardType()) {
				cont++;
			}
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
