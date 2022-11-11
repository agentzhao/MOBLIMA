package entities;

import java.util.List;

public class Cinema{
    private boolean isPlatinum;
    private String cinemaID;
    private String cinemaName;
    private List<Movie> movies;
    public List<ScreeningTimes> screeningTimes;


    public Cinema(boolean isPlatinum, String cinemaID, String cinemaName, List<Movie> movies, List<ScreeningTimes> screeningTimes) {
        this.isPlatinum = isPlatinum;
        this.cinemaID = cinemaID;
        this.cinemaName = cinemaName;
        this.movies = movies;
        this.screeningTimes = screeningTimes;
    }

    //getters
    public boolean getIsPlatinum(){
        return this.isPlatinum;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public String getCinemaName() {
        return this.cinemaName;
    }

    public String getCinemaID(){
        return this.cinemaID;
    }
    
    //setters
    public void setPlatinum(boolean isPlatinum) {
        this.isPlatinum = isPlatinum;
    }

    public void setCinemaID(String cinemaCode) {
        this.cinemaID = cinemaCode;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public void setScreeningTimes(List<ScreeningTimes> screeningTimes) {
        this.screeningTimes = screeningTimes;
    }

    public List<ScreeningTimes> getScreeningTimes(){
        return this.screeningTimes;
    }
}