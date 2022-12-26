package adrien.faouzi.enumération;

public enum MultiPlayer
{
    SOLO ("solo"),
    MULTI ("multi"),
    SOLOMULTI ("solo multi");

    /**
     * field
     */
    private String type;

    /**
     * one argument constructor
     */
    MultiPlayer(String type) {

        this.type =type;
    }

    /**
     * getter method
     */
    public String getMultiPlayer() {
        return type;
    }
}
