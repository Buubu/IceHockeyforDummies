package bum.icehockeyfordummies.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import bum.icehockeyfordummies.models.Player;


// Declare player entity (linked to one club)
@Entity(tableName = "players",
        foreignKeys = @ForeignKey(entity = ClubEntity.class,
                parentColumns = "club_id",
                childColumns = "FK_club_id",
                onUpdate = ForeignKey.CASCADE))
public class PlayerEntity implements Player {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "player_id")
    private int idPlayer;

    @NonNull
    @ColumnInfo(name = "player_number")
    private int numberPlayer;

    @NonNull
    @ColumnInfo(name = "player_firstname")
    private String firstNamePlayer;

    @NonNull
    @ColumnInfo(name = "player_lastname")
    private String lastNamePlayer;

    @NonNull
    @ColumnInfo(name = "player_birthdate")
    private int birthdatePlayer;

    @ColumnInfo(name = "player_picture")
    private String picturePlayer;

    @NonNull
    @ColumnInfo(name = "player_position")
    private String positionPlayer;

    @NonNull
    @ColumnInfo(name = "player_license")
    private String licensePlayer;

    @ColumnInfo(name = "player_points")
    private int pointsPlayer;

    @NonNull
    @ColumnInfo(name = "FK_club_id")
    private int FK_idClub;

    @NonNull
    @ColumnInfo(name = "player_system")
    private boolean systemPlayer;


    // Empty constructor
    public PlayerEntity() {}

    // Constructor using methods
    public PlayerEntity(Player player) {
        idPlayer = player.getIdPlayer();
        numberPlayer = player.getNumberPlayer();
        firstNamePlayer = player.getFirstNamePlayer();
        lastNamePlayer = player.getLastNamePlayer();
        birthdatePlayer = player.getBirthdatePlayer();
        picturePlayer = player.getPicturePlayer();
        positionPlayer = player.getPositionPlayer();
        licensePlayer = player.getLicensePlayer();
        pointsPlayer = player.getPointsPlayer();
        FK_idClub = player.getFK_idClub();
        systemPlayer = player.getSystemPlayer();
    }

    // Constructor using parameters
    public PlayerEntity(@NonNull int numberPlayer, @NonNull String firstNamePlayer, @NonNull String lastNamePlayer,
                        @NonNull int birthdatePlayer, String picturePlayer, @NonNull String positionPlayer, @NonNull String licensePlayer,
                        int pointsPlayer, @NonNull int idClub) {
        this.numberPlayer = numberPlayer;
        this.firstNamePlayer = firstNamePlayer;
        this.lastNamePlayer = lastNamePlayer;
        this.birthdatePlayer = birthdatePlayer;
        this.picturePlayer = picturePlayer;
        this.positionPlayer = positionPlayer;
        this.licensePlayer = licensePlayer;
        this.pointsPlayer = pointsPlayer;
        FK_idClub = idClub;
        systemPlayer = false;
    }


    // Getters and setters
    public int getIdPlayer() { return idPlayer; }
    public int getNumberPlayer() { return numberPlayer; }
    @NonNull public String getFirstNamePlayer() { return firstNamePlayer; }
    @NonNull public String getLastNamePlayer() { return lastNamePlayer; }
    public int getBirthdatePlayer() { return birthdatePlayer; }
    public String getPicturePlayer() { return picturePlayer; }
    @NonNull public String getPositionPlayer() { return positionPlayer; }
    @NonNull public String getLicensePlayer() { return licensePlayer; }
    public int getPointsPlayer() { return pointsPlayer; }
    public int getFK_idClub() { return FK_idClub; }
    public boolean getSystemPlayer() { return systemPlayer; }

    public void setIdPlayer(int idPlayer) { this.idPlayer = idPlayer; }
    public void setNumberPlayer(int numberPlayer) { this.numberPlayer = numberPlayer; }
    public void setFirstNamePlayer(@NonNull String firstNamePlayer) { this.firstNamePlayer = firstNamePlayer; }
    public void setLastNamePlayer(@NonNull String lastNamePlayer) { this.lastNamePlayer = lastNamePlayer; }
    public void setBirthdatePlayer(int birthdatePlayer) { this.birthdatePlayer = birthdatePlayer; }
    public void setPicturePlayer(String picturePlayer) { this.picturePlayer = picturePlayer; }
    public void setPositionPlayer(@NonNull String positionPlayer) { this.positionPlayer = positionPlayer; }
    public void setLicensePlayer(@NonNull String licensePlayer) { this.licensePlayer = licensePlayer; }
    public void setPointsPlayer(int pointsPlayer) { this.pointsPlayer = pointsPlayer; }
    public void setFK_idClub(int idClub) { FK_idClub = idClub; }
    public void setSystemPlayer(boolean systemPlayer) { this.systemPlayer = systemPlayer; }
}
