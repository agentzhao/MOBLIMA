package entities;

public class Seat {
    public enum Type {Normal, Couple, Elite, Ultima};

    private Type type;
    private int seatID;
    private boolean available;
    private int ticketHolder;

    public Seat(Type type, int seatID, boolean available, int ticketholder){
        this.type = type;
        this.seatID = seatID;
        this.available = available;
        this.ticketHolder = ticketholder;
    }

    //getters
    public boolean isAvailable() {
        return this.available;
    }
    public int getSeatID(){
        return this.seatID;
    }
    public int getTicketholder(){
        return this.ticketHolder;
    }
    public Type getType() {
        return this.type;
    }

    //setters
    public void setSeatID(int seatID) {
        this.seatID = seatID;
    }
    public void setAvailability(boolean newAvailability){
        this.available = newAvailability;
    }
    public void setTicketHolder(int ticketHolder){
        this.ticketHolder = ticketHolder;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
    public void setType(Type type) {
        this.type = type;
    }
}
