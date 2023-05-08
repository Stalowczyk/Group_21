package entity;
import main.GamePanel;


public class CommonGoalsCards {
    private Shelf shelf;


    public static boolean checkGoal1(Shelf shelf){ return shelf.checkCorners(); }                 // Primo obiettivo dei 12 da fare
    public static boolean checkGoal2(Shelf shelf){ return shelf.checkCardCount(); }               // Secondo obiettivo
    public static boolean checkGoal3(Shelf shelf){ return shelf.checkXShape(); }                  // Terzo obiettivo
    public static boolean checkGoal4(Shelf shelf){ return shelf.checkColumnGoal(); }              // Quarto obiettivo
    public static boolean checkGoal5(Shelf shelf){ return shelf.checkRowGoal(); }                 // Quinto obiettivo
    public static boolean checkGoal6(Shelf shelf){ return shelf.checkColumnSecondGoal(); }        // Sesto obiettivo
    public static boolean checkGoal7(Shelf shelf){ return shelf.checkRowSecondGoal(); }           // Settimo obiettivo
    public static boolean checkGoal8(Shelf shelf){ return shelf.checkDiagonal(); }                // Ottavo obiettivo
    public static boolean checkGoal9(Shelf shelf){ return shelf.checkTower(); }                   // Nono obiettivo
    public static boolean checkGoal10(Shelf shelf){ return shelf.checkCube(); }                   // Decimo obiettivo
    public static boolean checkGoal11(Shelf shelf){ return shelf.checkCoppie(); }                 // Undicesimo obiettivo
    public static boolean checkGoal12(Shelf shelf){ return shelf.checkTetris(); }                 // Dodicesimo obiettivo

    public boolean isGoalAchieved(Goal goal, Shelf shelf) {
        return switch (goal.getIndex()) {
            case 1 -> CommonGoalsCards.checkGoal1(shelf);
            case 2 -> CommonGoalsCards.checkGoal2(shelf);
            case 3 -> CommonGoalsCards.checkGoal3(shelf);
            case 4 -> CommonGoalsCards.checkGoal4(shelf);
            case 5 -> CommonGoalsCards.checkGoal5(shelf);
            case 6 -> CommonGoalsCards.checkGoal6(shelf);
            case 7 -> CommonGoalsCards.checkGoal7(shelf);
            case 8 -> CommonGoalsCards.checkGoal8(shelf);
            case 9 -> CommonGoalsCards.checkGoal9(shelf);
            case 10 -> CommonGoalsCards.checkGoal10(shelf);
            case 11 -> CommonGoalsCards.checkGoal11(shelf);
            case 12 -> CommonGoalsCards.checkGoal12(shelf);
            default -> throw new IllegalArgumentException("Goal Index invalido");
        };
    }
}



