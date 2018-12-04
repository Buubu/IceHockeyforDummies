package bum.icehockeyfordummies.models;


// Model of the Club entity
public interface Club {
    int getIdClub();
    String getNameClub();
    String getLogoClub();
    int getFK_idLeague();
    boolean getSystemClub();
    boolean getFavoriteClub();
}
