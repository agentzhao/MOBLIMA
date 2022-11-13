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

    /*Getters */
    /**
     * @return boolean
     * Gets the availability of the seat
     */
    public boolean isAvailable() {
        return this.available;
    }

    /**
     * @return int
     * Gets the ID of the seat
     */
    public int getSeatID() {
        return this.seatID;
    }

    /**
     * @return int
     * Gets the ticketID of the seat
     */
    public int getTicketholder() {
        return this.ticketHolder;
    }

    /**
     * @return Type
     * Gets the type of the seat (Single, Couple, Elite, Ultima)
     */
    public Type getType() {
        return this.type;
    }

    /*Setters */
    /**
     * @param seatID
     * sets the ID of the seat
     */
    public void setSeatID(int seatID) {
        this.seatID = seatID;
    }

    /**
     * @param ticketHolder
     * Sets the ticketID of the seat
     */
    public void setTicketHolder(int ticketHolder) {
        this.ticketHolder = ticketHolder;
    }

    /**
     * @param available
     * Sets the availability of the seat
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * @param type
     * Sets the type of the seat (Single, Couple, Elite, Ultima)
     */
    public void setType(Type type) {
        this.type = type;
    }
}
