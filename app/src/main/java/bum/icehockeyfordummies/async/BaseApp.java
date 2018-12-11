package bum.icehockeyfordummies.async;

import android.app.Application;
import bum.icehockeyfordummies.database.AppDatabase;
import bum.icehockeyfordummies.database.ClubRepository;
import bum.icehockeyfordummies.database.LeagueRepository;
import bum.icehockeyfordummies.database.PlayerRepository;


public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public AppDatabase getDatabase() {
        return AppDatabase.getInstance(this);
    }



    public PlayerRepository getPlayerRepository() {
        return PlayerRepository.getInstance(getDatabase());
    }

    public ClubRepository getClubRepository() {
        return ClubRepository.getInstance(getDatabase());
    }

    public LeagueRepository getLeagueRepository() {
        return LeagueRepository.getInstance(getDatabase());
    }
}
