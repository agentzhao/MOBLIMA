package boundaries;

import java.util.*;

public class Login {
    private ArrayList<Customer> customer = new ArrayList<Customer>();
    private ArrayList<Admin> admin = new ArrayList<Admin>();
    private User returnUser = null;

    public Login() {

    }
    
    public void addCustomerList(ArrayList<Customer> customer) {
      this.customer = customer;
    }
    
    public void addAdminList(ArrayList<Admin> admin) {
      this.admin = admin;
    }

    public User loginSystem(String email, String password) {
        return searchUser(email, password);
    }

    public void createAdmin(String email, String password, int type, int id, String cineplexID) {
        Admin newAdmin = new Admin(email, password, type, id, cineplexID);
        admin.add(newAdmin);
    }

    public void createCustomer(String email, String password, int type, String mobile_number, String name, int age, int id) {
        Customer newCustomer = new Customer(email, password, type, mobile_number, name, age, id);
        customer.add(newCustomer);
    }

    public User searchUser(String email, String password) {

        for (int x = 0; x < admin.size(); x++) {
            //System.out.println(admin.get(x).getEmail() + " password: " + admin.get(x).getPassword());
            //System.out.println(email + " password: " + password);
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
