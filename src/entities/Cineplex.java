package entities;

public class Cineplex{
    private String cineplexName;
    private String cineplexID;
    private Cinema[] cinemas;

    public Cineplex(String cineplexName, String cineplexID, Cinema[] cinemas){
        this.cineplexID = cineplexID;
        this.cinemas = cinemas;
    }
    
    //Getters
    public String getCineplexID() {
        return cineplexID;
    }
    public Cinema[] getCinemas() {
        return cinemas;
    }
    public String getCineplexName() {
        return cineplexName;
    }

    //Setters
    public void setCineplexName(String cineplexName) {
        this.cineplexName = cineplexName;
    }
    public void setCineplexID(String cineplexID) {
        this.cineplexID = cineplexID;
    }
    public void setCinemas(Cinema[] cinemas) {
        this.cinemas = cinemas;
    }
}