package entity;

import java.util.*;

public class Shelf {
	private Card[][] shelfLayout;
	private int maxRaw = 6;
	private int chosenColumn;
<<<<<<< HEAD

=======
	private int points;
	public static int boardsFilled = 0;
	
>>>>>>> branch 'dev' of https://github.com/Stalowczyk/Group_21.git
	public Shelf(int cardNumber, int chosenColumn) {
		this.shelfLayout = new Card[6][5];
		this.chosenColumn = chosenColumn;
		this.points = 0;
	}
<<<<<<< HEAD

	public void placeOnShelf(ArrayList<Card> chosenCards) {
=======
	public int getPoints() {
		return points;
	}
	public void addPoints(int amount) {
		points += amount;
	}
	
	public void placeOnShelf(ArrayList<Card> chosenCards) {		
>>>>>>> branch 'dev' of https://github.com/Stalowczyk/Group_21.git
		int startPoint = 0;
		int c = 0;
		int cardNumber = chosenCards.size();

		while (shelfLayout[c][chosenColumn] != null) {
			startPoint++;
			c++;
		}

		if ((maxRaw - startPoint) >= cardNumber) {
			for (int i = 0; i < cardNumber; i++) {
				shelfLayout[startPoint + i][chosenColumn] = chosenCards.get(i);
			}
			for (int i = 0; i < chosenCards.size(); i++) {
				chosenCards.remove(i);
			}
		} else {
			// error condition
		}
	}

	public boolean checkCorners() { 			// Sistema di controllo angoli della shelf
		CardType type = null; 					// Controlla se tutti gli angoli hanno lo stesso cardType, serve per obbiettivo
												// collettivo
		if (this.shelfLayout[0][0] != null) { 	// Filo
			type = this.shelfLayout[0][0].getCardType();
		}
		if (type != null && type == this.shelfLayout[0][4].getCardType() && type == this.shelfLayout[5][0].getCardType()
				&& type == this.shelfLayout[5][4].getCardType()) {
			return true;
		}
<<<<<<< HEAD
=======
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
>>>>>>> branch 'dev' of https://github.com/Stalowczyk/Group_21.git
		return false;
	}
<<<<<<< HEAD
}
=======

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
	public boolean checkColumnGoal() {
		int Linee = 0;
		for (int i = 0; i < this.shelfLayout.length; i++) {
			for (int j = 0; j < this.shelfLayout[i].length; j++) {
				if (i == 5) {
					Card a = this.shelfLayout[i][j];
					if (a != null) {
						CardType type = a.getCardType();
						Card b = this.shelfLayout[i-1][j];
						Card c = this.shelfLayout[i-2][j];
						Card d = this.shelfLayout[i-3][j];
						Card e = this.shelfLayout[i-4][j];
						Card f = this.shelfLayout[i-5][j];
						if (b != null && c != null && d != null && e != null && f != null) {
							if (b.getCardType() != type && b.getCardType() != c.getCardType() && b.getCardType() != d.getCardType() && b.getCardType() != e.getCardType() &&
									c.getCardType() != type && c.getCardType() != d.getCardType() && c.getCardType() != e.getCardType() && b.getCardType() != f.getCardType() &&
									f.getCardType() != type && d.getCardType() != type && d.getCardType() != e.getCardType() && d.getCardType() != f.getCardType() &&
									e.getCardType() != type && e.getCardType() != f.getCardType() && c.getCardType() != f.getCardType()) {
								Linee++;
							}
						}
					}
				}
			}
		}
		return Linee >= 2;
	}
	public boolean checkRowGoal() {
		int Linee = 0;
		for (int i = 0; i < this.shelfLayout.length; i++) {
			for (int j = 0; j < this.shelfLayout[i].length; j++) {
				if (j == 4) {
					Card a = this.shelfLayout[i][j];
					if (a != null) {
						CardType type = a.getCardType();
						Card b = this.shelfLayout[i][j-1];
						Card c = this.shelfLayout[i][j-2];
						Card d = this.shelfLayout[i][j-3];
						Card e = this.shelfLayout[i][j-4];
						if (b != null && c != null && d != null && e != null) {
							if (b.getCardType() != type && b.getCardType() != c.getCardType() && b.getCardType() != d.getCardType() && b.getCardType() != e.getCardType() &&
									c.getCardType() != type && c.getCardType() != d.getCardType() && c.getCardType() != e.getCardType() && d.getCardType() != type &&
									d.getCardType() != e.getCardType() && e.getCardType() != type) {
								Linee++;
							}
						}
					}
				}
			}
		}
		return Linee >= 2;
	}
	public boolean checkColumnSecondGoal() {
		int Linee = 0;
		for (int i = 0; i < this.shelfLayout.length; i++) {
			for (int j = 0; j < this.shelfLayout[i].length; j++) {
				if (i == 5) {
					Card a = this.shelfLayout[i][j];
					if (a != null) {
						Card b = this.shelfLayout[i-1][j];
						Card c = this.shelfLayout[i-2][j];
						Card d = this.shelfLayout[i-3][j];
						Card e = this.shelfLayout[i-4][j];
						Card f = this.shelfLayout[i-5][j];
						if (b != null && c != null && d != null && e != null && f != null) {
							int numTypes = 0;
							boolean[] typeFound = new boolean[6];
							Card[] cards = {a,b,c,d,e,f};
							for (int k = 0; k < 6; k++) {
								CardType currentType = cards[k].getCardType();
								if (!typeFound[currentType.ordinal()]) {
									typeFound[currentType.ordinal()] = true;
									numTypes++;
								}
							}
							if(numTypes <= 3) {
								Linee++;}
						}
					}
				}
			}
		}
		return Linee >= 3;
	}
	public boolean checkRowSecondGoal() {
		int Linee = 0;
		for (int i = 0; i < this.shelfLayout.length; i++) {
			for (int j = 0; j < this.shelfLayout[i].length; j++) {
				if (j == 4) {
					Card a = this.shelfLayout[i][j];
					if (a != null) {
						Card b = this.shelfLayout[i][j-1];
						Card c = this.shelfLayout[i][j-2];
						Card d = this.shelfLayout[i][j-3];
						Card e = this.shelfLayout[i][j-4];
						if (b != null && c != null && d != null && e != null) {
							int numTypes = 0;
							boolean[] typeFound = new boolean[6];
							Card[] cards = {a,b,c,d,e};
							for (int k = 0; k < 5; k++) {
								CardType currentType = cards[k].getCardType();
								if (!typeFound[currentType.ordinal()]) {
									typeFound[currentType.ordinal()] = true;
									numTypes++;
								}
							}
							if(numTypes <= 3) {
								Linee++;}
						}
					}
				}
			}
		}
		return Linee >= 4;
	}


	public boolean checkDiagonal() {
		boolean Diagonal1 = false;
		boolean Diagonal2 = false;
		for(int i = 0; i < this.shelfLayout.length; i++) {
			for(int j = 0; j < this.shelfLayout[i].length; j++) {
				if (j == 0 && (i == 0 || i == 1)) {
					Card a = this.shelfLayout[i][j];
					if(a != null) {
						CardType type = a.getCardType();
						Card b = this.shelfLayout[i+1][j+1];
						Card c = this.shelfLayout[i+2][j+2];
						Card d = this.shelfLayout[i+3][j+3];
						Card e = this.shelfLayout[i+4][j+4];
						if (b != null && c != null && d != null && e != null) {
							if (b.getCardType() == type && c.getCardType() == type && d.getCardType() == type && e.getCardType() == type) {
								Diagonal1 = true;
							}
						}
					}
				}
				if (j == 4 && (i == 0 || i == 1)) {
					Card a = this.shelfLayout[i][j];
					if(a != null) {
						CardType type = a.getCardType();
						Card b = this.shelfLayout[i+1][j-1];
						Card c = this.shelfLayout[i+2][j-2];
						Card d = this.shelfLayout[i+3][j-3];
						Card e = this.shelfLayout[i+4][j-4];
						if (b != null && c != null && d != null && e != null) {
							if (b.getCardType() == type && c.getCardType() == type && d.getCardType() == type && e.getCardType() == type) {
								Diagonal2 = true;
							}

						}
					}
				}
			}
		}
		return Diagonal1 || Diagonal2;
	}


	public boolean checkTower() {
		boolean Tower1 = false;
		boolean Tower2 = false;
		Card i = this.shelfLayout[5][4];
		Card j = this.shelfLayout[5][0];
		if(i != null) {
			Card a = this.shelfLayout[5][3];Card b = this.shelfLayout[4][3];Card c = this.shelfLayout[5][2];
			Card d = this.shelfLayout[4][2];Card e = this.shelfLayout[3][2];Card f = this.shelfLayout[5][1];
			Card g = this.shelfLayout[4][1];Card h = this.shelfLayout[3][1];Card k = this.shelfLayout[2][1];
			Card l = this.shelfLayout[5][0];Card m = this.shelfLayout[4][0];Card n = this.shelfLayout[3][0];
			Card o = this.shelfLayout[2][0];Card p = this.shelfLayout[1][0];
			if(a != null && b != null && c != null && d != null && e != null && f != null && g != null &&
					h != null && k != null && l != null && m != null && n != null && o != null && p != null) {
				Tower1 = true;
			}
		}
		if(j != null) {
			Card a = this.shelfLayout[5][1];Card b = this.shelfLayout[4][1];Card c = this.shelfLayout[5][2];
			Card d = this.shelfLayout[4][2];Card e = this.shelfLayout[3][2];Card f = this.shelfLayout[5][3];
			Card g = this.shelfLayout[4][3];Card h = this.shelfLayout[3][3];Card k = this.shelfLayout[2][3];
			Card l = this.shelfLayout[5][4];Card m = this.shelfLayout[4][4];Card n = this.shelfLayout[3][4];
			Card o = this.shelfLayout[2][4];Card p = this.shelfLayout[1][4];
			if(a != null && b != null && c != null && d != null && e != null && f != null && g != null &&
					h != null && k != null && l != null && m != null && n != null && o != null && p != null) {
				Tower2 = true;
			}
		}
		return Tower1 || Tower2;
	}



	public boolean checkCube() {
		List<Card> blacklistedCards = new ArrayList<Card>();
		int Cubi = 0;
		for (int i = 0; i < this.shelfLayout.length; i++) {
			for (int j = 0; j < this.shelfLayout[i].length; j++) {
				if (i > 0 && j < 4) {
					Card centro = this.shelfLayout[i][j];
					if (centro != null) {
						CardType type = centro.getCardType();
						Card Angolo1 = this.shelfLayout[i][j+1];
						Card Angolo2 = this.shelfLayout[i-1][j];
						Card Angolo3 = this.shelfLayout[i-1][j+1];
						if (Angolo1 != null && Angolo2 != null && Angolo3 != null) {
							if (Angolo1.getCardType() == type && Angolo2.getCardType() == type && Angolo3.getCardType() == type) {
								if (!blacklistedCards.contains(centro) && !blacklistedCards.contains(Angolo1) &&
										!blacklistedCards.contains(Angolo2) && !blacklistedCards.contains(Angolo3)) {
									blacklistedCards.add(centro);
									blacklistedCards.add(Angolo1);
									blacklistedCards.add(Angolo2);
									blacklistedCards.add(Angolo3);
									Cubi++;
								}
							}
						}

					}
				}
			}
		}
		return Cubi >= 2;
	}

	public boolean checkCoppie() {
		List<Card> blacklistedCards = new ArrayList<Card>();
		int Coppie = 0;
		for (int i = 0; i < this.shelfLayout.length; i++) {
			for (int j = 0; j < this.shelfLayout[i].length; j++) {
				if (i < 5) {
					Card centro = this.shelfLayout[i][j];
					if (centro != null) {
						CardType type = centro.getCardType();
						Card Angolo1 = this.shelfLayout[i+1][j];
						if (Angolo1 != null) {
							if (Angolo1.getCardType() == type) {
								if (!blacklistedCards.contains(centro) && !blacklistedCards.contains(Angolo1)) {
									blacklistedCards.add(centro);
									blacklistedCards.add(Angolo1);
									Coppie++;
								}
							}
						}

					}
				}
				if (j < 4) {
					Card centro = this.shelfLayout[i][j];
					if (centro != null) {
						CardType type = centro.getCardType();
						Card Angolo1 = this.shelfLayout[i][j+1];
						if (Angolo1 != null) {
							if (Angolo1.getCardType() == type) {
								if (!blacklistedCards.contains(centro) && !blacklistedCards.contains(Angolo1)) {
									blacklistedCards.add(centro);
									blacklistedCards.add(Angolo1);
									Coppie++;
								}
							}
						}

					}
				}
			}
		}
		return Coppie >= 6;
	}

	//prova
	
	public boolean checkTetris() {
		List<Card> blacklistedCards = new ArrayList<Card>();
		int Tetris = 0;
		for (int i = 0; i < this.shelfLayout.length; i++) {
			for (int j = 0; j < this.shelfLayout[i].length; j++) {
				// 1/14
				if (j < 2) {
					Card a = this.shelfLayout[i][j];
					if (a != null) {
						CardType type = a.getCardType();
						Card b = this.shelfLayout[i][j+1];
						Card c = this.shelfLayout[i][j+2];
						Card d = this.shelfLayout[i][j+3];
						if (b != null && c != null && d != null) {
							if (b.getCardType() == type && c.getCardType() == type && d.getCardType() == type) {
								if (!blacklistedCards.contains(a) && !blacklistedCards.contains(b) &&
										!blacklistedCards.contains(c) && !blacklistedCards.contains(d)) {
									blacklistedCards.add(a);
									blacklistedCards.add(b);
									blacklistedCards.add(c);
									blacklistedCards.add(d);
									Tetris++;}}}}}
				// 2/14
				if (i < 3) {
					Card a = this.shelfLayout[i][j];
					if (a != null) {
						CardType type = a.getCardType();
						Card b = this.shelfLayout[i+1][j];
						Card c = this.shelfLayout[i+2][j];
						Card d = this.shelfLayout[i+3][j];
						if (b != null && c != null && d != null) {
							if (b.getCardType() == type && c.getCardType() == type && d.getCardType() == type) {
								if (!blacklistedCards.contains(a) && !blacklistedCards.contains(b) &&
										!blacklistedCards.contains(c) && !blacklistedCards.contains(d)) {
									blacklistedCards.add(a);
									blacklistedCards.add(b);
									blacklistedCards.add(c);
									blacklistedCards.add(d);
									Tetris++;}}}}}
				// 3/14
				if (i > 0 && j > 1) {
					Card a = this.shelfLayout[i][j];
					if (a != null) {
						CardType type = a.getCardType();
						Card b = this.shelfLayout[i][j-1];
						Card c = this.shelfLayout[i][j-2];
						Card d = this.shelfLayout[i-1][j-2];
						if (b != null && c != null && d != null) {
							if (b.getCardType() == type && c.getCardType() == type && d.getCardType() == type) {
								if (!blacklistedCards.contains(a) && !blacklistedCards.contains(b) &&
										!blacklistedCards.contains(c) && !blacklistedCards.contains(d)) {
									blacklistedCards.add(a);
									blacklistedCards.add(b);
									blacklistedCards.add(c);
									blacklistedCards.add(d);
									Tetris++;}}}}}
				// 4/14
				if (i > 1 && j < 4) {
					Card a = this.shelfLayout[i][j];
					if (a != null) {
						CardType type = a.getCardType();
						Card b = this.shelfLayout[i-1][j];
						Card c = this.shelfLayout[i-2][j];
						Card d = this.shelfLayout[i-2][j+1];
						if (b != null && c != null && d != null) {
							if (b.getCardType() == type && c.getCardType() == type && d.getCardType() == type) {
								if (!blacklistedCards.contains(a) && !blacklistedCards.contains(b) &&
										!blacklistedCards.contains(c) && !blacklistedCards.contains(d)) {
									blacklistedCards.add(a);
									blacklistedCards.add(b);
									blacklistedCards.add(c);
									blacklistedCards.add(d);
									Tetris++;}}}}}
				// 5/14
				if (i < 5 && j < 3) {
					Card a = this.shelfLayout[i][j];
					if (a != null) {
						CardType type = a.getCardType();
						Card b = this.shelfLayout[i][j+1];
						Card c = this.shelfLayout[i][j+2];
						Card d = this.shelfLayout[i+1][j+2];
						if (b != null && c != null && d != null) {
							if (b.getCardType() == type && c.getCardType() == type && d.getCardType() == type) {
								if (!blacklistedCards.contains(a) && !blacklistedCards.contains(b) &&
										!blacklistedCards.contains(c) && !blacklistedCards.contains(d)) {
									blacklistedCards.add(a);
									blacklistedCards.add(b);
									blacklistedCards.add(c);
									blacklistedCards.add(d);
									Tetris++;}}}}}
				// 6/14
				if (i < 4 && j > 0) {
					Card a = this.shelfLayout[i][j];
					if (a != null) {
						CardType type = a.getCardType();
						Card b = this.shelfLayout[i+1][j];
						Card c = this.shelfLayout[i+2][j];
						Card d = this.shelfLayout[i+2][j-1];
						if (b != null && c != null && d != null) {
							if (b.getCardType() == type && c.getCardType() == type && d.getCardType() == type) {
								if (!blacklistedCards.contains(a) && !blacklistedCards.contains(b) &&
										!blacklistedCards.contains(c) && !blacklistedCards.contains(d)) {
									blacklistedCards.add(a);
									blacklistedCards.add(b);
									blacklistedCards.add(c);
									blacklistedCards.add(d);
									Tetris++;}}}}}
				// 7/14
				if (i > 1 && j > 0) {
					Card a = this.shelfLayout[i][j];
					if (a != null) {
						CardType type = a.getCardType();
						Card b = this.shelfLayout[i-1][j];
						Card c = this.shelfLayout[i-2][j];
						Card d = this.shelfLayout[i-2][j-1];
						if (b != null && c != null && d != null) {
							if (b.getCardType() == type && c.getCardType() == type && d.getCardType() == type) {
								if (!blacklistedCards.contains(a) && !blacklistedCards.contains(b) &&
										!blacklistedCards.contains(c) && !blacklistedCards.contains(d)) {
									blacklistedCards.add(a);
									blacklistedCards.add(b);
									blacklistedCards.add(c);
									blacklistedCards.add(d);
									Tetris++;}}}}}
				// 8/14
				if (i > 0 && j < 3) {
					Card a = this.shelfLayout[i][j];
					if (a != null) {
						CardType type = a.getCardType();
						Card b = this.shelfLayout[i][j+1];
						Card c = this.shelfLayout[i][j+2];
						Card d = this.shelfLayout[i-1][j+2];
						if (b != null && c != null && d != null) {
							if (b.getCardType() == type && c.getCardType() == type && d.getCardType() == type) {
								if (!blacklistedCards.contains(a) && !blacklistedCards.contains(b) &&
										!blacklistedCards.contains(c) && !blacklistedCards.contains(d)) {
									blacklistedCards.add(a);
									blacklistedCards.add(b);
									blacklistedCards.add(c);
									blacklistedCards.add(d);
									Tetris++;}}}}}
				// 9/14
				if (i < 4 && j < 4) {
					Card a = this.shelfLayout[i][j];
					if (a != null) {
						CardType type = a.getCardType();
						Card b = this.shelfLayout[i+1][j];
						Card c = this.shelfLayout[i+2][j];
						Card d = this.shelfLayout[i+2][j+1];
						if (b != null && c != null && d != null) {
							if (b.getCardType() == type && c.getCardType() == type && d.getCardType() == type) {
								if (!blacklistedCards.contains(a) && !blacklistedCards.contains(b) &&
										!blacklistedCards.contains(c) && !blacklistedCards.contains(d)) {
									blacklistedCards.add(a);
									blacklistedCards.add(b);
									blacklistedCards.add(c);
									blacklistedCards.add(d);
									Tetris++;}}}}}
				// 10/14
				if (i < 5 && j > 1) {
					Card a = this.shelfLayout[i][j];
					if (a != null) {
						CardType type = a.getCardType();
						Card b = this.shelfLayout[i][j-1];
						Card c = this.shelfLayout[i][j-2];
						Card d = this.shelfLayout[i+1][j-2];
						if (b != null && c != null && d != null) {
							if (b.getCardType() == type && c.getCardType() == type && d.getCardType() == type) {
								if (!blacklistedCards.contains(a) && !blacklistedCards.contains(b) &&
										!blacklistedCards.contains(c) && !blacklistedCards.contains(d)) {
									blacklistedCards.add(a);
									blacklistedCards.add(b);
									blacklistedCards.add(c);
									blacklistedCards.add(d);
									Tetris++;}}}}}
				// 11/14
				if (i < 4 && j > 0) {
					Card a = this.shelfLayout[i][j];
					if (a != null) {
						CardType type = a.getCardType();
						Card b = this.shelfLayout[i+1][j];
						Card c = this.shelfLayout[i+2][j];
						Card d = this.shelfLayout[i+1][j-1];
						if (b != null && c != null && d != null) {
							if (b.getCardType() == type && c.getCardType() == type && d.getCardType() == type) {
								if (!blacklistedCards.contains(a) && !blacklistedCards.contains(b) &&
										!blacklistedCards.contains(c) && !blacklistedCards.contains(d)) {
									blacklistedCards.add(a);
									blacklistedCards.add(b);
									blacklistedCards.add(c);
									blacklistedCards.add(d);
									Tetris++;}}}}}
				// 12/14
				if (i > 1 && j < 4) {
					Card a = this.shelfLayout[i][j];
					if (a != null) {
						CardType type = a.getCardType();
						Card b = this.shelfLayout[i-1][j];
						Card c = this.shelfLayout[i-2][j];
						Card d = this.shelfLayout[i-1][j+1];
						if (b != null && c != null && d != null) {
							if (b.getCardType() == type && c.getCardType() == type && d.getCardType() == type) {
								if (!blacklistedCards.contains(a) && !blacklistedCards.contains(b) &&
										!blacklistedCards.contains(c) && !blacklistedCards.contains(d)) {
									blacklistedCards.add(a);
									blacklistedCards.add(b);
									blacklistedCards.add(c);
									blacklistedCards.add(d);
									Tetris++;}}}}}
				// 13/14
				if (i > 0 && j > 1) {
					Card a = this.shelfLayout[i][j];
					if (a != null) {
						CardType type = a.getCardType();
						Card b = this.shelfLayout[i][j-1];
						Card c = this.shelfLayout[i][j-2];
						Card d = this.shelfLayout[i-1][j-1];
						if (b != null && c != null && d != null) {
							if (b.getCardType() == type && c.getCardType() == type && d.getCardType() == type) {
								if (!blacklistedCards.contains(a) && !blacklistedCards.contains(b) &&
										!blacklistedCards.contains(c) && !blacklistedCards.contains(d)) {
									blacklistedCards.add(a);
									blacklistedCards.add(b);
									blacklistedCards.add(c);
									blacklistedCards.add(d);
									Tetris++;}}}}}
				// 14/14
				if (i < 5 && j < 3) {
					Card a = this.shelfLayout[i][j];
					if (a != null) {
						CardType type = a.getCardType();
						Card b = this.shelfLayout[i][j+1];
						Card c = this.shelfLayout[i][j+2];
						Card d = this.shelfLayout[i+1][j+1];
						if (b != null && c != null && d != null) {
							if (b.getCardType() == type && c.getCardType() == type && d.getCardType() == type) {
								if (!blacklistedCards.contains(a) && !blacklistedCards.contains(b) &&
										!blacklistedCards.contains(c) && !blacklistedCards.contains(d)) {
									blacklistedCards.add(a);
									blacklistedCards.add(b);
									blacklistedCards.add(c);
									blacklistedCards.add(d);
									Tetris++;}}}}}}}
		return Tetris >= 4;
		// Lo so che è orribile ma è l'unico modo che mi è venuto in mente, se abbiamo tempo a fine progetto vediamo se abbiamo idee
	}
	public void findCardGroups() {
		boolean[][] visited = new boolean[6][5];
		List<Integer> groupSizes = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				if (!visited[i][j]) {
					Card card = this.shelfLayout[i][j];
					if (card != null) {
						int groupSize = bfs(visited, i, j, card);
						if (groupSize > 2) {
							groupSizes.add(groupSize);
						}
					}
				}
			}
		}
		for (int i = 0; i < groupSizes.size(); i++) {
			switch (groupSizes.get(i)) {
				case 3 -> this.addPoints(2);
				case 4 -> this.addPoints(3);
				case 5 -> this.addPoints(5);
				case 6 -> this.addPoints(8);
				default -> this.addPoints(8);
			}
		}
	}

	private int bfs(boolean[][] visited, int row, int col, Card card) {
		int groupSize = 0;
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[]{row, col});
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int r = curr[0];
			int c = curr[1];
			if (r < 0 || r >= 6 || c < 0 || c >= 5 || visited[r][c]) {
				continue;
			}
			Card currCard = shelfLayout[r][c];
			if (currCard == null || currCard.getCardType() != card.getCardType()) {
				continue;
			}
			visited[r][c] = true;
			groupSize++;
			queue.offer(new int[]{r - 1, c});
			queue.offer(new int[]{r + 1, c});
			queue.offer(new int[]{r, c - 1});
			queue.offer(new int[]{r, c + 1});
		}
		return groupSize;
	}

	public boolean isBoardFilled() {
		boolean board = true;
		for (int i = 0; i < this.shelfLayout.length; i++) {
			for (int j = 0; j < this.shelfLayout[i].length; j++) {
				if (this.shelfLayout[i][j] == null) {
					board = false;
				}
			}
		}
		if (boardsFilled == 0 && board) {
			this.addPoints(1);
			boardsFilled++;
			return true;
		} else {
			return false;
		}
	}
}

>>>>>>> branch 'dev' of https://github.com/Stalowczyk/Group_21.git

