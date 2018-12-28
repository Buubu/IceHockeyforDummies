package bum.icehockeyfordummies.database;

import com.google.firebase.database.Exclude;
import java.util.HashMap;
import java.util.Map;
import bum.icehockeyfordummies.models.League;


// Structure of the league document
public class LeagueEntity implements League {
    private String id;
    private Map<String, Boolean> clubs;
    private String logo;
    private String name;
    private boolean system;


    // Empty constructor
    public LeagueEntity() {}

    // Constructor with methods
    public LeagueEntity(League league) {
        id = league.getId();
        clubs = league.getClubs();
        logo = league.getLogo();
        name = league.getName();
        system = league.getSystem();
    }

    // Constructor with parameters
    public LeagueEntity(String logo, String name) {
        this.logo = logo;
        this.name = name;
        system = false;
    }


    // Getters and setters
    @Exclude
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Map<String, Boolean> getClubs() { return clubs; }
    public void setClubs(Map<String, Boolean> clubs) { this.clubs = clubs; }

    public String getLogo() { return logo; }
    public void setLogo(String logo) { this.logo = logo; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public boolean getSystem() { return system; }
    public void setSystem(boolean system) { this.system = system; }


    // Map the data
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> data = new HashMap<>();

        data.put("clubs", clubs);
        data.put("logo", logo);
        data.put("name", name);
        data.put("system", system);

        return data;
    }
}
