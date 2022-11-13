package entities;

import java.util.*;

public class Customer extends User {
    private String mobile_number;
    private String name;
    private int age;

    public Customer(String email, String password, int type, String mobile_number, String name, int age, int id) {
        super(email, password, type, id);
        this.mobile_number = mobile_number;
        this.name = name;
        this.age = age;
    }

    /**
     * @return String
     */
    public String getMobile_number() {
        return mobile_number;
    }

    /**
     * @param mobile_number
     */
    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    /**
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return int
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

}