package entities;

/**
 * Customer is a entity that store customer information
 */
public class Customer extends User {
    private String mobile_number;
    private String name;
    private int age;

    /**
     * This is the constructor for Customer
     * 
     * @param email
     * @param password
     * @param type
     * @param mobile_number
     * @param name
     * @param age
     * @param id
     */
    public Customer(String email, String password, int type, String mobile_number, String name, int age, int id) {
        super(email, password, type, id);
        this.mobile_number = mobile_number;
        this.name = name;
        this.age = age;
    }

    /**
     * This function get the mobile number of the customer
     * 
     * @return String
     */
    public String getMobile_number() {
        return mobile_number;
    }

    /**
     * This function set the mobiel number of the customer
     * 
     * @param mobile_number
     */
    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    /**
     * This function get the name of the customer
     * 
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * This function set the name of the customer
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This function get the age of the customer
     * 
     * @return int
     */
    public int getAge() {
        return age;
    }

    /**
     * This function set the age of the customer
     * 
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

}