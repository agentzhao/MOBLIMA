package entities;

public class Cineplex {
    private String cineplexName;
    private String cineplexID;
    private Cinema[] cinemas;

    public Cineplex(String cineplexName, String cineplexID, Cinema[] cinemas) {
        this.cineplexName = cineplexName;
        this.cineplexID = cineplexID;
        this.cinemas = cinemas;
    }

    /**
     * @return String
     */
    // Getters
    public String getCineplexID() {
        return cineplexID;
    }

    /**
     * @return Cinema[]
     */
    public Cinema[] getCinemas() {
        return cinemas;
    }

    /**
     * @return String
     */
    public String getCineplexName() {
        return cineplexName;
    }

    /**
     * @param cineplexName
     */
    // Setters
    public void setCineplexName(String cineplexName) {
        this.cineplexName = cineplexName;
    }

    /**
     * @param cineplexID
     */
    public void setCineplexID(String cineplexID) {
        this.cineplexID = cineplexID;
    }

    /**
     * @param cinemas
     */
    public void setCinemas(Cinema[] cinemas) {
        this.cinemas = cinemas;
    }
}