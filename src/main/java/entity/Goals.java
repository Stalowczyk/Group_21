package entity;

public enum Goals {

    GOAL1(1, "Goal #1", "Four tiles of the same type in the four corners of the bookshelf."),
    GOAL2(2, "Goal #2", "Eight tiles of the same type. There’s no restriction about the position of these tiles."),
    GOAL3(3, "Goal #3", "Five tiles of the same type forming an X."),
    GOAL4(4, "Goal #4", "Two columns each formed by 6 different types of tiles."),
    GOAL5(5, "Goal #5", "Two lines each formed by 5 different types of tiles. One line can show the same or a different combination of the other line."),
    GOAL6(6, "Goal #6", "Three columns each formed by 6 tiles of maximum three different types. One column can show the same or a different combination of another column."),
    GOAL7(7, "Goal #7", "Four lines each formed by 5 tiles of maximum three different types. One line can show the same or a different combination of another line."),
    GOAL8(8, "Goal #8", "Five tiles of the same type forming a diagonal."),
    GOAL9(9, "Goal #9", "Five columns of increasing or decreasing height. Starting from the first column on the left or on the right, each next column must be made of exactly one more tile.Tiles can be of any type."),
    GOAL10(10, "Goal #10", "Two groups each containing 4 tiles of the same type in a 2x2 square. The tiles of one square can be different from those of the other square."),
    GOAL11(11, "Goal #11", "Six groups each containing at least 2 tiles of the same type (not necessarily in the depicted shape).The tiles of one group can be different from those of another group."),
    GOAL12(12, "Goal #12", "Four groups each containing at least 4 tiles of the same type (not necessarily in the depicted shape).The tiles of one group can be different from those of another group.");

    private final int index;
    private final String name;
    private final String description;

    private Goals(int index, String name, String description) {
        this.index = index;
        this.name = name;
        this.description = description;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
