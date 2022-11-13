package entities;

public class Seat {
    public enum Type {
        Normal, Couple, Elite, Ultima
    };

    private Type type;
    private int seatID;
    private boolean available;
    private int ticketHolder;

    public Seat(Type type, int seatID, boolean available, int ticketholder) {
        this.type = type;
        this.seatID = seatID;
        this.available = available;
        this.ticketHolder = ticketholder;
    }

    /**
     * @return boolean
     */
    // getters
    public boolean isAvailable() {
        return this.available;
    }

    /**
     * @return int
     */
    public int getSeatID() {
        return this.seatID;
    }

    /**
     * @return int
     */
    public int getTicketholder() {
        return this.ticketHolder;
    }

    /**
     * @return Type
     */
    public Type getType() {
        return this.type;
    }

    /**
     * @param seatID
     */
    // setters
    public void setSeatID(int seatID) {
        this.seatID = seatID;
    }

    /**
     * @param ticketHolder
     */
    public void setTicketHolder(int ticketHolder) {
        this.ticketHolder = ticketHolder;
    }

    /**
     * @param available
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * @param type
     */
    public void setType(Type type) {
        this.type = type;
    }
}
