package entities;

public class ScreeningTimes {
    private String cinemaID;
    private String cinemaName;
    private int movieID;
    private String screenTime;
    private String date;
    private Seat[] seats;

    public ScreeningTimes(String cinemaID, String cinemaName, int movieID, String screenTime, String date,
            Seat[] seats) {
        this.cinemaID = cinemaID;
        this.cinemaName = cinemaName;
        this.movieID = movieID;
        this.screenTime = screenTime;
        this.date = date;
        this.seats = seats;

    }

    /*Getters */
    /**
     * @return int
     */
    public int getMovieID() {
        return this.movieID;
    }

    /**
     * @return String
     */
    public String getCinemaName() {
        return cinemaName;
    }

    /**
     * @return String
     */
    public String getScreenTime() {
        return this.screenTime;
    }

    /**
     * @return String
     */
    public String getCinemaID() {
        return cinemaID;
    }

    /**
     * @return Seat[]
     */
    public Seat[] getSeats() {
        return this.seats;
    }

    /**
     * @return String
     */
    public String getDate() {
        return this.date;
    }

    /*Setters */
    /**
     * @param movieID
     */
    // setters
    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    /**
     * @param screenTime
     */
    public void setScreenTime(String screenTime) {
        this.screenTime = screenTime;
    }

    /**
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @param seats
     */
    public void setSeats(Seat[] seats) {
        this.seats = seats;
    }

    /**
     * @param cinemaID
     */
    public void setCinemaID(String cinemaID) {
        this.cinemaID = cinemaID;
    }

    /**
     * @param cinemaName
     */
    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }
}
