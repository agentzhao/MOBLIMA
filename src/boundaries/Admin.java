package boundaries;

import java.util.*;

public class Admin extends User {
    private String cineplexID;

    public Admin(String email, String password, int type, int id, String cineplexID) {
        super(email, password, type, id);
        this.cineplexID = cineplexID;
    }

    public String getcineplexID() {
        return cineplexID;
    }

    public void setCinemaId(String cineplexID) {
        this.cineplexID = cineplexID;
    }

}