package bum.icehockeyfordummies.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import bum.icehockeyfordummies.models.Club;


// Declare club entity (linked to one league)
@Entity(tableName = "clubs", indices = {@Index(value = "club_name", unique = true)},
        foreignKeys = @ForeignKey(entity = LeagueEntity.class,
                parentColumns = "league_id",
                childColumns = "FK_league_id",
                onUpdate = ForeignKey.CASCADE)
)
public class ClubEntity implements Club {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "club_id")
    private int idClub;

    @NonNull
    @ColumnInfo(name = "club_name")
    private String nameClub;

    @ColumnInfo(name = "club_logo")
    private String logoClub;

    @NonNull
    @ColumnInfo(name = "FK_league_id")
    private int FK_idLeague;

    @NonNull
    @ColumnInfo(name = "club_system")
    private boolean systemClub;

    @NonNull
    @ColumnInfo(name = "club_favorite")
    private boolean favoriteClub;


    // Empty constructor
    public ClubEntity() {}

    // Constructor using methods
    public ClubEntity(Club club) {
        idClub = club.getIdClub();
        nameClub = club.getNameClub();
        logoClub = club.getLogoClub();
        FK_idLeague = club.getFK_idLeague();
        systemClub = club.getSystemClub();
        favoriteClub = club.getFavoriteClub();
    }

    // Constructor using parameters
    public ClubEntity(@NonNull String nameClub, String logoClub, int idLeague) {
        this.nameClub = nameClub;
        this.logoClub = logoClub;
        FK_idLeague = idLeague;
        systemClub = false;
        favoriteClub = false;
    }


    // Getters and setters
    public int getIdClub() { return idClub; }
    @NonNull public String getNameClub() { return nameClub; }
    public String getLogoClub() { return logoClub; }
    public int getFK_idLeague() { return FK_idLeague; }
    public boolean getSystemClub() { return systemClub; }
    public boolean getFavoriteClub() { return favoriteClub; }

    public void setIdClub(int idClub) { this.idClub = idClub; }
    public void setNameClub(@NonNull String nameClub) { this.nameClub = nameClub; }
    public void setLogoClub(String logoClub) { this.logoClub = logoClub; }
    public void setFK_idLeague(int idLeague) {FK_idLeague = idLeague; }
    public void setSystemClub(boolean systemClub) { this.systemClub = systemClub; }
    public void setFavoriteClub(boolean favoriteClub) {this.favoriteClub = favoriteClub; }
}
