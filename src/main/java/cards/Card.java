package cards;

        
        
public class Card {
    
    protected final int cardRow;
    protected final int cardCol;
    protected final CardType cardColor;

        public Card(final int row, final int col,CardType cardColor) {
        this.cardRow = row;
        this.cardCol = col;
        this.cardColor = cardColor;
    }
    
    public CardType getCardType(){
        return this.cardColor;
    }
    public int getCardRow(){
        return this.cardRow;
    }
    public int getCardCol(){
        return this.cardCol;
    }
    
    
    
    
}
