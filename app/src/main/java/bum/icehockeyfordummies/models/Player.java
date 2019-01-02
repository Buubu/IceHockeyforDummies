/* ©2018-2019, Montaine BURGER
   HES-SO Valais-Wallis, FIG */
package bum.icehockeyfordummies.models;

import java.util.Map;


// Model of the Player collection
public interface Player {
    String getId();
    int getBirthdate();
    Map<String, Boolean> getClubs();
    String getFirstname();
    String getLastname();
    String getLicense();
    int getNumber();
    String getPicture();
    int getPoints();
    String getPosition();
    boolean getSystem();
}
