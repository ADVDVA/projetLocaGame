package adrien.faouzi.enumeration;

public enum StatusCopy
{
    DISPONIBLE ("disponible"),
    LOUER ("louer"),
    CASSER ("casser");

    /**
     * field
     */
    private String type;

    /**
     * one argument constructor
     */
    StatusCopy(String type) {

        this.type =type;
    }

    /**
     * getter method
     */
    public String getStatusCopy() {
        return type;
    }
}
