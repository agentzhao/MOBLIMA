package entities;

import java.util.ArrayList;
import java.util.List;

public class Cinema{
    private boolean isPlatinum;
    private String cinemaCode;
    private String cinemaName;
    private Seat[] seats;
    private List<Movie> movies;
    public List<ScreeningTimes> screeningTimes;


    public Cinema(boolean isPlatinum, String cinemaCode, String cinemaName, Seat[] seats, List<Movie> movies, List<ScreeningTimes> screeningTimes) {
        this.isPlatinum = isPlatinum;
        this.cinemaCode = cinemaCode;
        this.cinemaName = cinemaName;
        this.seats = seats;
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

    public Seat[] getSeats() {
        return seats;
    }

    public String getCinemaCode(){
        return this.cinemaCode;
    }
    
    //setters
    public void setPlatinum(boolean isPlatinum) {
        this.isPlatinum = isPlatinum;
    }

    public void setCinemaCode(String cinemaCode) {
        this.cinemaCode = cinemaCode;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public void setSeats(Seat[] seats) {
        this.seats = seats;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public void setScreeningTimes(List<ScreeningTimes> screeningTimes) {
        this.screeningTimes = screeningTimes;
    }

    public int[] seatsAvailable(){
        int count = 0;
        int len = (this.seats).length;
        int[] availability = new int[len];
        for(int i=0; i<len; i++){
            if(seats[i].getAvailability()){
                availability[count] = seats[i].getSeatID();
                count++;
            }
        }
        return availability;
    }

    public void changeSeat(int bfSeatID, int aftSeatID){
        seats[aftSeatID].setAvailability(false);
        seats[bfSeatID].setAvailability(true);
        seats[aftSeatID].setTicketHolder(seats[bfSeatID].getTicketholder());
        seats[bfSeatID].setTicketHolder(0);

    }

    public void bookSeat(int seatID, int ticketholder){
        seats[seatID].setAvailability(false);
        seats[seatID].setTicketHolder(ticketholder);
    }

    public void unbookSeat(int seatID){
        seats[seatID].setAvailability(true);
        seats[seatID].setTicketHolder(0);
    }

    public List<ScreeningTimes> getScreeningTimes(int movieID){
        if(movieID == 0){
            return this.screeningTimes;
        }
        List<ScreeningTimes> screenTime = new ArrayList<ScreeningTimes>();
        (this.screeningTimes).forEach((screeningTimes)->{screenTime.add(screeningTimes);});
        return screenTime;
    }
}