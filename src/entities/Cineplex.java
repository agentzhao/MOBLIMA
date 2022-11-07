package entities;

public class Cineplex{
    private String cineplexID;
    private Cinema[] cinemas;

    public Cineplex(String cineplexID, Cinema[] cinemas){
        this.cineplexID = cineplexID;
        this.cinemas = cinemas;
    }

    public String getCineplexID() {
        return cineplexID;
    }

    public Cinema[] getCinemas() {
        return cinemas;
    }
}