package entities;
/**
 * Creates an entity cineplex to store the information of a cineplex
 */
public class Cineplex {
    private String cineplexName;
    private String cineplexID;
    private Cinema[] cinemas;

    /**
     * @param cineplexName
     * @param cineplexID
     * @param cinemas
     */
    public Cineplex(String cineplexName, String cineplexID, Cinema[] cinemas) {
        this.cineplexName = cineplexName;
        this.cineplexID = cineplexID;
        this.cinemas = cinemas;
    }

    /*Getters */
    /**
     * @return String
     * Gets the ID of the cineplex
     */
    public String getCineplexID() {
        return cineplexID;
    }

    /**
     * @return Cinema[]
     * Gets the array of cinemas the cineplex has
     */
    public Cinema[] getCinemas() {
        return cinemas;
    }

    /**
     * @return String
     * Gets the name of the cineplex
     */
    public String getCineplexName() {
        return cineplexName;
    }

    /*Setters */
    /**
     * @param cineplexName
     * Sets the name of the cineplex
     */
    public void setCineplexName(String cineplexName) {
        this.cineplexName = cineplexName;
    }

    /**
     * @param cineplexID
     * Sets the ID of the cineplex
     */
    public void setCineplexID(String cineplexID) {
        this.cineplexID = cineplexID;
    }

    /**
     * @param cinemas
     * Set the cinemas the cineplex has
     */
    public void setCinemas(Cinema[] cinemas) {
        this.cinemas = cinemas;
    }
}