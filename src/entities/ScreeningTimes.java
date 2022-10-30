package entities;

public class ScreeningTimes {
    private int movieID;
    private String screenTime;
    private String date;

    public ScreeningTimes(int movieID, String screenTime, String date){
        this.movieID = movieID;
        this.screenTime = screenTime;
        this.date = date;
    }

    public int getMovieID(){
        return this.movieID;
    }

    public String getScreenTime(){
        return this.screenTime;
    }

    public String getDate(){
        return this.date;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public void setScreenTime(String screenTime) {
        this.screenTime = screenTime;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
