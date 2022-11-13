package entities;

/**
 * Admin is a entity that store the information of an admin
 */
public class Admin extends User {
    private String cineplexID;

    /**
     * This is the constructor for Admin
     * 
     * @param email
     * @param password
     * @param type
     * @param id
     * @param cineplexID
     */
    public Admin(String email, String password, int type, int id, String cineplexID) {
        super(email, password, type, id);
        this.cineplexID = cineplexID;
    }

    /**
     * This function return the cineplex ID of the cineplex this admin is
     * responsible for
     * 
     * @return String
     */
    public String getcineplexID() {
        return cineplexID;
    }

    /**
     * This function set the cineplex ID of the admin
     * 
     * @param cineplexID
     */
    public void setCinemaId(String cineplexID) {
        this.cineplexID = cineplexID;
    }

}