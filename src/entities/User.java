package entities;

/**
 * User is the superclass of admin and customer class
 */
public class User {
    private String email;
    private String password;
    private int type;
    private int id;

    /**
     * This is the constructor of User
     * 
     * @param email
     * @param password
     * @param type
     * @param id
     */
    public User(String email, String password, int type, int id) {
        this.email = email;
        this.password = password;
        this.type = type;
        this.id = id;
    }

    /**
     * This function return the type of the user account
     * 1: Admin
     * 2: Customer
     * 
     * @return int
     */
    public int getType() {
        return type;
    }

    /**
     * This function set the user type
     * 
     * @param type
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * This function retrieve the email of the user
     * 
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * This function set the email of the user
     * 
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This function get the password of the user
     * 
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * This function set the password of the user
     * 
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This function get the ID of the user
     * 
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * This function set the ID of the user
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

}