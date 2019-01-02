/* ©2018-2019, Montaine BURGER
   HES-SO Valais-Wallis, FIG */
package bum.icehockeyfordummies.database;

import android.app.Application;


public class BaseApp extends Application {

    // Retrieve all the repositories
    public PlayerRepository getPlayerRepository() {
        return PlayerRepository.getInstance();
    }

    public ClubRepository getClubRepository() {
        return ClubRepository.getInstance();
    }


    // Not used following Mr Schumacher's instructions
    public LeagueRepository getLeagueRepository() {
        return LeagueRepository.getInstance();
    }
}
