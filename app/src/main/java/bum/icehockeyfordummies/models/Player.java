package bum.icehockeyfordummies.models;


// Model of the Player entity
public interface Player {
    int getIdPlayer();
    int getNumberPlayer();
    String getFirstNamePlayer();
    String getLastNamePlayer();
    int getBirthdatePlayer();
    String getPicturePlayer();
    String getPositionPlayer();
    String getLicensePlayer();
    int getPointsPlayer();
    int getFK_idClub();
    boolean getSystemPlayer();
}
