package bum.icehockeyfordummies.database;

import android.arch.lifecycle.LiveData;


public class LeagueRepository {
    private static LeagueRepository instance;
    private final AppDatabase database;

    private LeagueRepository(final AppDatabase database) { this.database = database; }

    public static LeagueRepository getInstance(final AppDatabase database) {
        if (instance == null) {
            synchronized (LeagueRepository.class) {
                if (instance == null) {
                    instance = new LeagueRepository(database);
                }
            }
        }

        return instance;
    }


    public LiveData<LeagueEntity> getLeague(final int idLeague) {
        return database.leagueDAO().getLeagueById(idLeague);
    }

    public void insert(final LeagueEntity league) {
        database.leagueDAO().insert(league);
    }

    public void update(final LeagueEntity league) {
        database.leagueDAO().update(league);
    }

    public void delete(final LeagueEntity league) {
        database.leagueDAO().delete(league);
    }
}

