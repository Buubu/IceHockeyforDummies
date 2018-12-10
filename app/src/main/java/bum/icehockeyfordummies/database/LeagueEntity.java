package bum.icehockeyfordummies.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import bum.icehockeyfordummies.models.League;


// Declare league entity
@Entity(tableName = "leagues")
public class LeagueEntity implements League {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "league_id")
    private int idLeague;

    @NonNull
    @ColumnInfo(name = "league_name")
    private String nameLeague;

    @ColumnInfo(name = "league_logo")
    private String logoLeague;

    @NonNull
    @ColumnInfo(name = "league_system")
    private boolean systemLeague;


    // Empty constructor
    public LeagueEntity() {}

    // Constructor using parameters
    public LeagueEntity(@NonNull String nameLeague, String logoLeague) {
        this.nameLeague = nameLeague;
        this.logoLeague = logoLeague;
        systemLeague = false;
    }

    // Constructor using methods
    public LeagueEntity(League league) {
        idLeague = league.getIdLeague();
        nameLeague = league.getNameLeague();
        logoLeague = league.getLogoLeague();
        systemLeague = league.getSystemLeague();
    }


    // Getters and setters
    public int getIdLeague() { return idLeague; }
    @NonNull public String getNameLeague() { return nameLeague; }
    public String getLogoLeague() { return logoLeague; }
    public boolean getSystemLeague() { return systemLeague; }

    public void setIdLeague(int idLeague) { this.idLeague = idLeague; } //TODO: used?
    public void setNameLeague(@NonNull String nameLeague) { this.nameLeague = nameLeague; }
    public void setLogoLeague(String logoLeague) { this.logoLeague = logoLeague; }
    public void setSystemLeague(boolean systemLeague) { this.systemLeague = systemLeague; }
}
