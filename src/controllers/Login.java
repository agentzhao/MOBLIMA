package controllers;

import java.util.*;

import entities.Admin;
import entities.Customer;
import entities.User;

/**
 * Login is a controller that provide the system the login functionality
 */
public class Login {
    private ArrayList<Customer> customer = new ArrayList<Customer>();
    private ArrayList<Admin> admin = new ArrayList<Admin>();
    private User returnUser = null;

    public Login() {

    }

    /**
     * This function allow the system to take in a customer list
     * 
     * @param customer
     */
    public void addCustomerList(ArrayList<Customer> customer) {
        this.customer = customer;
    }

    /**
     * This function allow the system to take in an admin list
     * 
     * @param admin
     */
    public void addAdminList(ArrayList<Admin> admin) {
        this.admin = admin;
    }

    /**
     * This function is the login system
     * 
     * @param email
     * @param password
     * @return User
     */
    public User loginSystem(String email, String password) {
        return searchUser(email, password);
    }

    /**
     * This function allow the system to create new admins
     * 
     * @param email
     * @param password
     * @param type
     * @param id
     * @param cineplexID
     */
    public void createAdmin(String email, String password, int type, int id, String cineplexID) {
        Admin newAdmin = new Admin(email, password, type, id, cineplexID);
        admin.add(newAdmin);
    }

    /**
     * This function allow the system create new customers
     * 
     * @param email
     * @param password
     * @param type
     * @param mobile_number
     * @param name
     * @param age
     * @param id
     */
    public void createCustomer(String email, String password, int type, String mobile_number, String name, int age,
            int id) {
        Customer newCustomer = new Customer(email, password, type, mobile_number, name, age, id);
        customer.add(newCustomer);
    }

    /**
     * This allow the login system to search for a user that match the email and
     * password inputted
     * 
     * @param email
     * @param password
     * @return User
     */
    public User searchUser(String email, String password) {
        returnUser = null;
        for (int x = 0; x < admin.size(); x++) {

            String e = admin.get(x).getEmail();
            String p = admin.get(x).getPassword();
            if (email.equals(e) && password.equals(p)) {
                returnUser = admin.get(x);
                System.out.println("Successful admin login");
                return returnUser;
            }
        }
        for (int x = 0; x < customer.size(); x++) {
            String e = customer.get(x).getEmail();
            String p = customer.get(x).getPassword();
            if (email.equals(e) && password.equals(p)) {
                returnUser = customer.get(x);
                System.out.println("Successful customer login");
                return returnUser;
            }
        }
        System.out.println("Fail login");
        return returnUser;
    }
}
