package entity;
import main.GamePanel;


public class CommonGoalsCards {
    private Shelf shelf;


    public boolean CheckGoal1(Shelf shelf){ return shelf.checkCorners(); }       // Primo obiettivo dei 12 da fare
    public boolean checkGoal2(Shelf shelf){ return shelf.checkCardCount(); }     // Secondo obiettivo
    public boolean checkGoal3(Shelf shelf){ return shelf.checkXShape(); }       // Terzo obiettivo
}


