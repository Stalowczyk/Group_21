package entity;

import java.util.Random;

public class CommonGoals {

    private static int completedTimes0 = 0;
    private static int completedTimes1 = 0;
    private Shelf shelf;
    private static final Random random = new Random();
    Goals[] choosenGoals;

    CommonGoals() {
        Goals[] allGoals = Goals.values();
        choosenGoals = new Goals[2];
        int index1 = random.nextInt(allGoals.length);
        int index2 = random.nextInt(allGoals.length - 1);
        if (index2 >= index1) {
            index2++;
        }
        choosenGoals[0] = allGoals[index1];
        choosenGoals[1] = allGoals[index2];
    }

    public String getFirstName() {
        return choosenGoals[0].getName();
    }

    public String getSecondName() {
        return choosenGoals[1].getName();
    }

    public String getFirstDescription() {
        return choosenGoals[0].getDescription();
    }

    public String getSecondDescription() {
        return choosenGoals[1].getDescription();
    }

    public int getFirstIndex() {
        return choosenGoals[0].getIndex();
    }

    public int getSecondIndex() {
        return choosenGoals[1].getIndex();
    }

    public boolean checkGoal1(Shelf shelf) {
        return shelf.checkCorners();
    }                 // Primo obiettivo dei 12 da fare

    public boolean checkGoal2(Shelf shelf) {
        return shelf.checkCardCount();
    }               // Secondo obiettivo

    public boolean checkGoal3(Shelf shelf) {
        return shelf.checkXShape();
    }                  // Terzo obiettivo

    public boolean checkGoal4(Shelf shelf) {
        return shelf.checkColumnGoal();
    }              // Quarto obiettivo

    public boolean checkGoal5(Shelf shelf) {
        return shelf.checkRowGoal();
    }                 // Quinto obiettivo

    public boolean checkGoal6(Shelf shelf) {
        return shelf.checkColumnSecondGoal();
    }        // Sesto obiettivo

    public boolean checkGoal7(Shelf shelf) {
        return shelf.checkRowSecondGoal();
    }           // Settimo obiettivo

    public boolean checkGoal8(Shelf shelf) {
        return shelf.checkDiagonal();
    }                // Ottavo obiettivo

    public boolean checkGoal9(Shelf shelf) {
        return shelf.checkTower();
    }                   // Nono obiettivo

    public boolean checkGoal10(Shelf shelf) {
        return shelf.checkCube();
    }                   // Decimo obiettivo

    public boolean checkGoal11(Shelf shelf) {
        return shelf.checkCoppie();
    }                 // Undicesimo obiettivo

    public boolean checkGoal12(Shelf shelf) {
        return shelf.checkTetris();
    }                 // Dodicesimo obiettivo

    public boolean isFirstGoalAchieved(Shelf shelf) {
        boolean Result = switch (getFirstIndex()) {
            case 1 ->
                this.checkGoal1(shelf);
            case 2 ->
                this.checkGoal2(shelf);
            case 3 ->
                this.checkGoal3(shelf);
            case 4 ->
                this.checkGoal4(shelf);
            case 5 ->
                this.checkGoal5(shelf);
            case 6 ->
                this.checkGoal6(shelf);
            case 7 ->
                this.checkGoal7(shelf);
            case 8 ->
                this.checkGoal8(shelf);
            case 9 ->
                this.checkGoal9(shelf);
            case 10 ->
                this.checkGoal10(shelf);
            case 11 ->
                this.checkGoal11(shelf);
            case 12 ->
                this.checkGoal12(shelf);
            default ->
                throw new IllegalArgumentException("Goal Index invalido");
        };
        if (Result) {
            switch (completedTimes0) {
                case 0 ->
                    shelf.addPoints(8);
                case 1 ->
                    shelf.addPoints(6);
                case 2 ->
                    shelf.addPoints(4);
                case 3 ->
                    shelf.addPoints(2);
                default ->
                    throw new IllegalArgumentException("Index invalido");
            }
            completedTimes0++;
        }
        return Result;
    }

    public boolean isSecondGoalAchieved(Shelf shelf) {
        boolean Result = switch (getSecondIndex()) {
            case 1 ->
                this.checkGoal1(shelf);
            case 2 ->
                this.checkGoal2(shelf);
            case 3 ->
                this.checkGoal3(shelf);
            case 4 ->
                this.checkGoal4(shelf);
            case 5 ->
                this.checkGoal5(shelf);
            case 6 ->
                this.checkGoal6(shelf);
            case 7 ->
                this.checkGoal7(shelf);
            case 8 ->
                this.checkGoal8(shelf);
            case 9 ->
                this.checkGoal9(shelf);
            case 10 ->
                this.checkGoal10(shelf);
            case 11 ->
                this.checkGoal11(shelf);
            case 12 ->
                this.checkGoal12(shelf);
            default ->
                throw new IllegalArgumentException("Goal Index invalido");
        };
        if (Result) {
            switch (completedTimes1) {
                case 0 ->
                    shelf.addPoints(8);
                case 1 ->
                    shelf.addPoints(6);
                case 2 ->
                    shelf.addPoints(4);
                case 3 ->
                    shelf.addPoints(2);
                default ->
                    throw new IllegalArgumentException("Index invalido");
            }
            completedTimes1++;
        }
        return Result;
    }
}
