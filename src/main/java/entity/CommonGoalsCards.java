package entity;
import main.GamePanel;


public class CommonGoalsCards {
    private Shelf shelf;


    public boolean CheckGoal1(Shelf shelf){ return shelf.checkCorners(); }       // Primo obiettivo dei 12 da fare
    public boolean checkGoal2(Shelf shelf){ return shelf.checkCardCount(); }     // Secondo obiettivo
    public boolean checkGoal3(Shelf shelf){ return shelf.checkXShape(); }       // Terzo obiettivo
    public boolean checkGoal4(Shelf shelf){ return shelf.checkColumnGoal(); }        // Quarto obiettivo
    public boolean checkGoal5(Shelf shelf){ return shelf.checkRowGoal(); }        // Quinto obiettivo
    public boolean checkGoal6(Shelf shelf){ return shelf.checkColumnSecondGoal(); }        // Sesto obiettivo

    // Sono a metà :D
}



