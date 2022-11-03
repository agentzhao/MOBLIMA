package boundaries;

import java.util.*;

public class Customer extends User {
    private String mobile_number;
    private String name;
    private int age;
    private int type;

    public Customer(String email, int password, int type, String mobile_number, String name, int age) {
        super(email, password, type);
        this.mobile_number = mobile_number;
        this.name = name;
        this.age = age;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}