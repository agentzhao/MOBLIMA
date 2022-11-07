package boundaries;

import java.util.*;

public class User {
    private String email;
    private String password;
    private int type;
    private int id;

    
    public User(String email, String password, int type, int id) {
        this.email = email;
        this.password = password;
        this.type = type;
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}