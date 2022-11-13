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

    /**
     * @return boolean
     */
    // getters
    public boolean getIsPlatinum() {
        return this.isPlatinum;
    }

    /**
     * @return List<Movie>
     */
    public List<Movie> getMovies() {
        return movies;
    }

    /**
     * @return String
     */
    public String getCinemaName() {
        return this.cinemaName;
    }

    /**
     * @return String
     */
    public String getCinemaID() {
        return this.cinemaID;
    }

    /**
     * @param isPlatinum
     */
    // setters
    public void setPlatinum(boolean isPlatinum) {
        this.isPlatinum = isPlatinum;
    }

    /**
     * @param cinemaCode
     */
    public void setCinemaID(String cinemaCode) {
        this.cinemaID = cinemaCode;
    }

    /**
     * @param cinemaName
     */
    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    /**
     * @param movies
     */
    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    /**
     * @param screeningTimes
     */
    public void setScreeningTimes(List<ScreeningTimes> screeningTimes) {
        this.screeningTimes = screeningTimes;
    }

    /**
     * @return List<ScreeningTimes>
     */
    public List<ScreeningTimes> getScreeningTimes() {
        return this.screeningTimes;
    }
}