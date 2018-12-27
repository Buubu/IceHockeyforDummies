package bum.icehockeyfordummies.models;

import java.util.Map;


// Model of the Club collection
public interface Club {
    String getId();
    boolean getFavorite();
    String getLogo();
    String getName();
    Map<String, Boolean> getPlayers();
    boolean getSystem();
}
