package entities;

import java.util.List;

public class Cinema {
    private boolean isPlatinum;
    private String cinemaID;
    private String cinemaName;
    private List<Movie> movies;
    public List<ScreeningTimes> screeningTimes;

    public Cinema(boolean isPlatinum, String cinemaID, String cinemaName, List<Movie> movies,
            List<ScreeningTimes> screeningTimes) {
        this.isPlatinum = isPlatinum;
        this.cinemaID = cinemaID;
        this.cinemaName = cinemaName;
        this.movies = movies;
        this.screeningTimes = screeningTimes;
    }

    /*Getters */
    /**
     * @return boolean
     * Gets the cinema platinum boolean value
     * if true, the cinema is platinum
     * else the cinema is not platinum
     */
    public boolean getIsPlatinum() {
        return this.isPlatinum;
    }

    /**
     * @return List<Movie>
     * Gets the list of movies the cinema is showing
     */
    public List<Movie> getMovies() {
        return movies;
    }

    /**
     * @return String
     * Gets the name of the cinema
     */
    public String getCinemaName() {
        return this.cinemaName;
    }

    /**
     * @return String
     * Gets the ID of the cinema
     */
    public String getCinemaID() {
        return this.cinemaID;
    }

     /**
     * @return List<ScreeningTimes>
     * Gets the screeningtimes of the cinema
     */
    public List<ScreeningTimes> getScreeningTimes() {
        return this.screeningTimes;
    }

    /*Setter */
    /**
     * @param isPlatinum
     * Sets the cinema to be either platinum or not
     */
    public void setPlatinum(boolean isPlatinum) {
        this.isPlatinum = isPlatinum;
    }

    /**
     * @param cinemaCode
     * Sets the ID of the cinema
     */
    public void setCinemaID(String cinemaCode) {
        this.cinemaID = cinemaCode;
    }

    /**
     * @param cinemaName
     * Sets the name of the Cinema
     */
    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    /**
     * @param movies
     * Sets the array of movies the cinema has
     */
    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    /**
     * @param screeningTimes
     * Sets the screeningtimes the movie can access
     */
    public void setScreeningTimes(List<ScreeningTimes> screeningTimes) {
        this.screeningTimes = screeningTimes;
    }

}