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
	int cont;
	// CardType[] types;
	public ArrayList<CardType> types;
	int c;
	int sc;

	// a seconda di quanti giocatori ci sono so quante goalsCards generare
	public PersonalGoalsCards(int numberOfPlayers, GamePanel gp) {
		this.spaceIn = new ArrayList<>();
		this.numberOfPlayers = numberOfPlayers;
		this.gp = gp;
		this.sc = 0;
		this.c = 0;
		types = new ArrayList<>();
		this.addAllTiles();
		this.randomizeSixSpace();
		this.placeCardsOnSpaces();
		this.cont = 0;
		System.out.println(currentGoalCardLayout);

	}

	public int[][] getPersonalGoalCardsLayout() {
		return this.personalGoalCardsLayout;
	}

	// genera il numero della casella dove andrÃ il goal
	public void randomizeSixSpace() {
		// personalGoal contiene 6 numeri casuali da 0 a 30 i quali servono per
		// posizionare le tessere fittizzie in quelle coordinate
		personalGoal.clear();
		Random random = new Random();
		for (int i = 0; i < numberOfSpace; i++) {
			number = random.nextInt(30);
			if (this.isSingleNumber(number, personalGoal)) { // controllo affinchÃ¨ non si abbiano due numeri uguali
				personalGoal.add(number);
			} else {
				i--;
			}
		}

		personalGoalCardsLayout = new int[][] { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 } };

		for (int i = 0; i < numberOfSpace; i++) {
			personalGoalCardsLayout[5 - (personalGoal.get(i) / 5)][(personalGoal.get(i) % 5)] = 1;
			; // mette a 1 nei punti in cui va messa la placedCard fittizia
		}

	}

	// sapendo dove vanno posizionate le PlacedCard, le posiziona
	public void placeCardsOnSpaces() {
		for (int col = 0; col < this.personalGoalCardsLayout.length; col++) {
			for (int row = 0; row < this.personalGoalCardsLayout[col].length; row++) {
				if (personalGoalCardsLayout[col][row] == 1) {
					PlacedCard card = new PlacedCard(types.get(c), row, col, gp); // types Ã¨ un ArrayList contenente
																					// tutti i tipi di carte disposti
																					// casualmente
					c++;
					currentGoalCardLayout.add(card);
				}
			}
		}
	}

	// genera l'ArrayList types che contiene tutti i tipi di carte disposti in
	// ordine casuale
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

	// questo metodo restituisce il puntegio in base a quante carte sono uguali tra
	// carta obiettivo e shelf
	public int score(Shelf s) { // punteggio

		for (int i = 0; i < 6; i++) {

			PlacedCard personalCard = this.currentGoalCardLayout.get(i);
			// System.out.println("BEFORE PERSONALGOALCARD CORDS "+
			// personalCard.getCardRow()+" "+personalCard.getCardCol());//0 3
			PlacedCard shelfCard = s.getCard(personalCard.getCardRow(), personalCard.getCardCol());
			// 0
			if (shelfCard != null) {

				if (personalCard.getCardType() == shelfCard.getCardType()) {
					// System.out.println("FOUND MATCH AT PERSONALGOALCARD CORDS "+
					// personalCard.getCardRow()+" "+personalCard.getCardCol());
					// System.out.println("FOUND MATCH AT SHELF CORDS "+ shelfCard.getCardRow()+"
					// "+shelfCard.getCardCol());
					// System.out.println("CONTATORE BEFORE "+this.cont);
					this.cont++;

				}
			}

		}

		switch (cont) {
		case 0 -> this.sc = 0;
		case 1 -> this.sc = 1;
		case 2 -> this.sc = 2;
		case 3 -> this.sc = 4;
		case 4 -> this.sc = 6;
		case 5 -> this.sc = 9;
		case 6 -> this.sc = 12;
		}
		return this.sc;

	}

	public void draw(Graphics2D g2) {
		for (int i = 0; i < currentGoalCardLayout.size(); i++) {
			PlacedCard p = currentGoalCardLayout.get(i);
			switch (p.getCardType()) {
			case WHITE -> {
				g2.setColor(Color.white);
				g2.fillRect(18 * 48 + p.getCardX(), 48 + p.getCardY(), 48, 48);
			}
			case PINK -> {
				g2.setColor(Color.PINK);
				g2.fillRect(18 * 48 + p.getCardX(), 48 + p.getCardY(), 48, 48);
			}
			case BLUE -> {
				g2.setColor(Color.BLUE);
				g2.fillRect(18 * 48 + p.getCardX(), 48 + p.getCardY(), 48, 48);
			}
			case CYAN -> {
				g2.setColor(Color.CYAN);
				g2.fillRect(18 * 48 + p.getCardX(), 48 + p.getCardY(), 48, 48);
			}
			case GREEN -> {
				g2.setColor(Color.GREEN);
				g2.fillRect(18 * 48 + p.getCardX(), 48 + p.getCardY(), 48, 48);
			}
			case YELLOW -> {
				g2.setColor(Color.YELLOW);
				g2.fillRect(18 * 48 + p.getCardX(), 48 + p.getCardY(), 48, 48);
			}
			}
		}

		for (int col = 0; col < this.personalGoalCardsLayout.length; col++) {
			for (int row = 0; row < this.personalGoalCardsLayout[col].length; row++) {
				g2.setColor(Color.BLACK);
				g2.drawRect(864 + row * 48, 48 + col * 48, 48, 48);
			}
		}
	}

	public void printOutAll() {
		for (int i = 0; i < currentGoalCardLayout.size(); i++)
			System.out.println("PERSONALGOALCARD  LAYOUT CARD NUMBER " + currentGoalCardLayout.get(i).getCardRow() + " "
					+ currentGoalCardLayout.get(i).getCardCol());

	}

}
