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
	public ArrayList<Card> currentGoalCardLayout = new ArrayList<>();

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
			number = random.nextInt(31);
			personalGoal[i] = number;
		}

		personalGoalCardsLayout = new int[][] { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 } };

		for (int i = 0; i < numberOfSpace; i++) {
			personalGoalCardsLayout[personalGoal[i] / 5][(personalGoal[i] % 5) - 1] = 1;
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
}
