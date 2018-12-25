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

    public LeagueRepository getLeagueRepository() {
        return LeagueRepository.getInstance();
    }
}
