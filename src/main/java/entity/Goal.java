package entity;

public class Goal {
    private String name;
    private String description;

    private int index;

    public Goal(int index,String name,String description){
        this.index = index;
        this.name = name;
        this.description = description;

    }
    public int getIndex() {return this.index;}
    public String getName() {return name;}
    public String getDescription() {return description;}
}
