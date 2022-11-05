package boundaries;

import java.util.*;

public class Login {
    private ArrayList<Customer> customer = new ArrayList<Customer>();
    private ArrayList<Admin> admin = new ArrayList<Admin>();
    private User returnUser;

    public Login() {

    }

    public User loginSystem(String email, String password) {
        return searchUser(email, password);
    }

    public void createAdmin(String email, String password, int type, int id, String cineplexID) {
        Admin newAdmin = new Admin(email, password, type, id, cineplexID);
        admin.add(newAdmin);
    }

    public void createCustomer(String email, String password, int type, String mobile_number, String name, int age) {
        Customer newCustomer = new Customer(email, password, type, mobile_number, name, age);
        customer.add(newCustomer);
    }

    public User searchUser(String email, String password) {

        for (int x = 0; x < admin.size(); x++) {
            if (admin.get(x).getEmail() == email && admin.get(x).getPassword() == password) {
                returnUser = admin.get(x);
                return returnUser;
            }
        }
        for (int x = 0; x < customer.size(); x++) {
            if (customer.get(x).getEmail() == email && customer.get(x).getPassword() == password) {
                returnUser = customer.get(x);
                return returnUser;
            }
        }
        return returnUser;
    }
}
