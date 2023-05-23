package entity;

public class Card {

    private final CardType cardType;

    public Card(CardType cardType) {
        this.cardType = cardType;
    }

    public CardType getCardType() {
        return this.cardType;
    }

    @Override
    public String toString() {
        return "Card Type: " + this.getCardType();
    }

    /* Tile getCard()
    public int getCardRow(){
        return this.cardRow;
    }
    public int getCardCol(){
        return this.cardCol;
    }
     */
}
