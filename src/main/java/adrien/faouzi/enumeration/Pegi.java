package adrien.faouzi.enumeration;

public enum Pegi
{
    TROIS ("3"),
    SEPT ("7"),
    DOUZE ("12"),
    SEIZE ("16"),
    DIXHUIT ("18");

    /**
     * field
     */
    private String type;

    /**
     * one argument constructor
     */
    Pegi(String type) {

        this.type =type;
    }

    /**
     * getter method
     */
    public String getPegi() {
        return type;
    }

    public int getPegiInt(){
        return Integer.parseInt(type);
    }
}
