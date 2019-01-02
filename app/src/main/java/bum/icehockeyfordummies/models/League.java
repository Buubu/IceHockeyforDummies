/* ©2018-2019, Montaine BURGER
   HES-SO Valais-Wallis, FIG */
package bum.icehockeyfordummies.models;

import java.util.Map;


// Model of the League collection
public interface League {
    String getId();
    Map<String, Boolean> getClubs();
    String getLogo();
    String getName();
    boolean getSystem();
}
