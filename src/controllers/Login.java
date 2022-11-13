package controllers;

import java.util.*;

import entities.Admin;
import entities.Customer;
import entities.User;

public class Login {
    private ArrayList<Customer> customer = new ArrayList<Customer>();
    private ArrayList<Admin> admin = new ArrayList<Admin>();
    private User returnUser = null;

    public Login() {

    }

    /**
     * @param customer
     */
    public void addCustomerList(ArrayList<Customer> customer) {
        this.customer = customer;
    }

    /**
     * @param admin
     */
    public void addAdminList(ArrayList<Admin> admin) {
        this.admin = admin;
    }

    /**
     * @param email
     * @param password
     * @return User
     */
    public User loginSystem(String email, String password) {
        return searchUser(email, password);
    }

    /**
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
