package entity;

        
        
public class Card {
    
    protected int cardRow;
    protected int cardCol;
    protected final CardType cardColor;
    protected int[] cardPosition;

        public Card(final int row, final int col,CardType cardColor) {
        this.cardRow = row;
        this.cardCol = col;
        this.cardColor = cardColor;
        this.cardPosition = new int[2];
    }
    
    public void setCardPosition(final int row,final int col){
        this.cardCol = col;
        this.cardRow = row;        
    }    
        
        
    public CardType getCardType(){
        return this.cardColor;
    }
    
    public int[] getCardPosition(){
        return this.cardPosition;
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
