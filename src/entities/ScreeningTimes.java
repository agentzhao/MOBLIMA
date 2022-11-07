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

    //for seats for screening time
    public Seat[] seatsAvailable(){
        int count = 0;
        int len = (this.seats).length;
        Seat[] availability = new Seat[len];
        for(int i=0; i<len; i++){
            if(seats[i].isAvailable()){
                availability[count] = seats[i];
                count++;
            }
        }
        return availability;
    }
    public void changeSeat(int bfSeatID, int aftSeatID){
        seats[aftSeatID].setAvailable(false);
        seats[bfSeatID].setAvailable(true);
        seats[aftSeatID].setTicketHolder(seats[bfSeatID].getTicketholder());
        seats[bfSeatID].setTicketHolder(0);

    }
    public void bookSeat(int seatID, int ticketholder){
        seats[seatID].setAvailable(false);
        seats[seatID].setTicketHolder(ticketholder);
    }
    public void unbookSeat(int seatID){
        seats[seatID].setAvailable(true);
        seats[seatID].setTicketHolder(0);
    }
    public void makeAllAvailable(){
        for(int i=0; i<seats.length; i++){
            seats[i].setAvailable(true);
        }
    }
}
