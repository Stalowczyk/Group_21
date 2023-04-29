package entity;
import main.GamePanel;


public class CommonGoalsCards {
    private Shelf shelf;

    CommonGoalsCards(Shelf shelf,GamePanel gp){ this.shelf = shelf; }

    public boolean checkObjective1(Shelf shelf){ return shelf.checkCorners(); }       // Primo obiettivo dei 12 da fare

}


