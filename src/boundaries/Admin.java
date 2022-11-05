package boundaries;

import java.util.*;

public class Admin extends User {
    private int id;
    private String cineplexID;

    public Admin(String email, String password, int type, int id, String cineplexID) {
        super(email, password, type);
        this.id = id;
        this.cineplexID = cineplexID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getcineplexID() {
        return cineplexID;
    }

    public void setCinemaId(String cineplexID) {
        this.cineplexID = cineplexID;
    }

}