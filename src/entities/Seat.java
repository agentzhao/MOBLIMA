package entities;

public class Seat {
    private int seatID;
    private boolean available;
    private int ticketHolder;

    public Seat(int seatID, boolean available, int ticketholder){
        this.seatID = seatID;
        this.available = available;
        this.ticketHolder = ticketholder;
    }

    public boolean getAvailability(){
        return this.available;
    }

    public int getSeatID(){
        return this.seatID;
    }

    public void setSeatID(int seatID) {
        this.seatID = seatID;
    }

    public int getTicketholder(){
        return this.ticketHolder;
    }

    public void setAvailability(boolean newAvailability){
        this.available = newAvailability;
    }

    public void setTicketHolder(int ticketHolder){
        this.ticketHolder = ticketHolder;
    }
}
