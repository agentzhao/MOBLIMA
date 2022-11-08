package entities;

public class ScreeningTimes {
    private int movieID;
    private String screenTime;
    private String date;
    private Seat[] seats;

    public ScreeningTimes(int movieID, String screenTime, String date, Seat[] seats){
        this.movieID = movieID;
        this.screenTime = screenTime;
        this.date = date;
        this.seats = seats;

    }
    //getters
    public int getMovieID(){
        return this.movieID;
    }
    public String getScreenTime(){
        return this.screenTime;
    }
    public Seat[] getSeats() {
        return this.seats;
    }
    public String getDate(){
        return this.date;
    }

    //setters
    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }
    public void setScreenTime(String screenTime) {
        this.screenTime = screenTime;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setSeats(Seat[] seats) {
        this.seats = seats;
    }
}
