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
     * Get the ID of the movie the showtime
     */
    public int getMovieID() {
        return this.movieID;
    }

    /**
     * @return String
     * Get the name of the cinema the screeningtime is at
     */
    public String getCinemaName() {
        return cinemaName;
    }

    /**
     * @return String
     * Get the time the screeningtime
     */
    public String getScreenTime() {
        return this.screenTime;
    }

    /**
     * @return String
     * Gets the ID of the cinema the screeningtime is at
     */
    public String getCinemaID() {
        return cinemaID;
    }

    /**
     * @return Seat[]
     * Gets the array of seats in the screeningtime
     */
    public Seat[] getSeats() {
        return this.seats;
    }

    /**
     * @return String
     * Gets the date of the screeningtime
     */
    public String getDate() {
        return this.date;
    }

    /*Setters */
    /**
     * @param movieID
     * Set the ID of the movie the showtime
     */
    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    /**
     * @param screenTime
     * Set the time the screeningtime
     */
    public void setScreenTime(String screenTime) {
        this.screenTime = screenTime;
    }

    /**
     * @param date
     * Sets the date of the screeningtime
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @param seats
     * Sets the array of seats in the screeningtime
     */
    public void setSeats(Seat[] seats) {
        this.seats = seats;
    }

    /**
     * @param cinemaID
     * Sets the ID of the cinema the screeningtime is at
     */
    public void setCinemaID(String cinemaID) {
        this.cinemaID = cinemaID;
    }

    /**
     * @param cinemaName
     * Set the name of the cinema the screeningtime is at
     */
    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }
}
