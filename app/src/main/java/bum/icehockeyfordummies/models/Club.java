/* ©2018-2019, Montaine BURGER
   HES-SO Valais-Wallis, FIG */
package bum.icehockeyfordummies.models;

import java.util.Map;


// Model of the Club collection
public interface Club {
    String getId();
    boolean getFavorite();
    Map<String, Boolean> getLeagues();
    String getLogo();
    String getName();
    Map<String, Boolean> getPlayers();
    boolean getSystem();
}
