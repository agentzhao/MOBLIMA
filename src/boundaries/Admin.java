package boundaries;

import java.util.*;

public class Admin extends User {
    private int id;
    private int cinemaId;

    public Admin(String email, int password, int type, int id, int cinemaId) {
        super(email, password, type);
        this.id = id;
        this.cinemaId = cinemaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

}