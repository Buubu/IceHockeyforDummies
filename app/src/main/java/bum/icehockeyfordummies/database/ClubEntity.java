package bum.icehockeyfordummies.database;

import com.google.firebase.database.Exclude;
import java.util.HashMap;
import java.util.Map;
import bum.icehockeyfordummies.models.Club;


// Structure of the club document
public class ClubEntity implements Club {
    private String id;
    private boolean favorite;
    private String logo;
    private String name;
    private boolean system;


    // Empty constructor
    public ClubEntity() {}

    // Constructor with methods
    public ClubEntity(Club club) {
        favorite = club.getFavorite();
        logo = club.getLogo();
        name = club.getName();
        system = club.getSystem();
    }

    // Constructor with parameters
    public ClubEntity(String logo, String name) {
        favorite = false;
        this.logo = logo;
        this.name = name;
        system = false;
    }


    // Getters and setters
    @Exclude
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public boolean getFavorite() { return favorite; }
    public void setFavorite(boolean favorite) { this.favorite = favorite; }

    public String getLogo() { return logo; }
    public void setLogo(String logo) { this.logo = logo; }

    public String getName() { return logo; }
    public void setName(String name) { this.name = name; }

    public boolean getSystem() { return system; }
    public void setSystem(boolean system) { this.system = system; }


    // Map the data
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> data = new HashMap<>();

        data.put("favorite", favorite);
        data.put("logo", logo);
        data.put("name", name);
        data.put("system", system);

        return data;
    }
}
