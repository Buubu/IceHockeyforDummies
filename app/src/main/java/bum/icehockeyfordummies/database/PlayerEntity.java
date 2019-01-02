/* ©2018-2019, Montaine BURGER
   HES-SO Valais-Wallis, FIG */
package bum.icehockeyfordummies.database;

import com.google.firebase.database.Exclude;
import java.util.HashMap;
import java.util.Map;
import bum.icehockeyfordummies.models.Player;


// Structur of the player document
public class PlayerEntity implements Player {
    private String id;
    private int birthdate;
    private Map<String, Boolean> clubs = new HashMap<>();
    private String firstname;
    private String lastname;
    private String license;
    private int number;
    private String picture;
    private int points;
    private String position;
    private boolean system;


    // Empty constructor
    public PlayerEntity() {}

    // Constructor with methods
    public PlayerEntity(Player player) {
        id = player.getId();
        birthdate = player.getBirthdate();
        clubs = player.getClubs();
        firstname = player.getFirstname();
        lastname = player.getLastname();
        license = player.getLicense();
        number = player.getNumber();
        picture = player.getPicture();
        points = player.getPoints();
        position = player.getPosition();
        system = player.getSystem();
    }

    // Constructor with parameters
    public PlayerEntity(int birthdate, String firstname, String lastname, String license, int number,
                        String picture, String position, String club) {
        this.birthdate = birthdate;
        this.firstname = firstname;
        this.lastname = lastname;
        this.license = license;
        this.number = number;
        this.picture = picture;
        points = 0;
        this.position = position;
        system = false;

        String id = club;
        clubs.put(id, true);
    }


    // Getters and setters
    @Exclude
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public int getBirthdate() { return birthdate; }
    public void setBirthdate(int birthdate) { this.birthdate = birthdate; }

    public Map<String, Boolean> getClubs() { return clubs; }
    public void setClubs(Map<String, Boolean> clubs) { this.clubs = clubs; }

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getLicense() { return license; }
    public void setLicense(String license) { this.license = license; }

    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }

    public String getPicture() { return picture; }
    public void setPicture(String picture) { this.picture = picture; }

    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public boolean getSystem() { return system; }
    public void setSystem(boolean system) { this.system = system; }


    // Player to String
    public String toString() {
        return firstname + " " + lastname;
    }


    // Map the data
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> data = new HashMap<>();

        data.put("birthdate", birthdate);
        data.put("clubs", clubs);
        data.put("firstname", firstname);
        data.put("lastname", lastname);
        data.put("license", license);
        data.put("number", number);
        data.put("picture", picture);
        data.put("points", points);
        data.put("position", position);
        data.put("system", system);

        return data;
    }
}
