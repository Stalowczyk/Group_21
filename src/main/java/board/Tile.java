package board;

import entity.Card;
import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import java.util.Map;



public abstract class Tile {
    
    protected final int tileCoordinate;
    
    private static final Map<Integer, EmptyTile>EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();
    
    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
        
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
        for(int i = 0; i < 42; i++){
            emptyTileMap.put(i, new EmptyTile(i));
        }
        return ImmutableMap.copyOf(emptyTileMap);
    }

    public Tile() {
        this.tileCoordinate = 0;
    }
    
    public static Tile createTile(final int tileCoordinate, final Card card){
        return card != null ? new OccupiedTile(tileCoordinate, card): EMPTY_TILES_CACHE.get(tileCoordinate);
    }
    
    private Tile(int tileCoordinate){
        this.tileCoordinate = tileCoordinate;
    }
    
    public abstract boolean isTileOccupied();
    
    public abstract Card getCard();
    
    public static final class EmptyTile extends Tile{
        
        private EmptyTile(final int coordinate){
            super(coordinate);
        }
        
        @Override
        public boolean isTileOccupied(){
            return false;
        }
        
        @Override
        public Card getCard(){
            return null;
        }
    }
    
    
    public static final class OccupiedTile extends Tile {
        private final Card cardOnTile;

        private OccupiedTile(int tileCoordinate, Card cardOnTile) {
            super(tileCoordinate);
            this.cardOnTile = cardOnTile;
        }
        
        @Override
        public boolean isTileOccupied(){
            return true;
        }
        @Override
        public Card getCard(){
            return this.cardOnTile;
        }
        
        
    }
    
}
