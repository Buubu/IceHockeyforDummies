package bum.icehockeyfordummies.database;

import android.arch.lifecycle.LiveData;
import java.util.List;


public class ClubRepository {
    private static ClubRepository instance;
    private final AppDatabase database;

    private ClubRepository(final AppDatabase database) { this.database = database; }


    public static ClubRepository getInstance(final AppDatabase database) {
        if (instance == null) {
            synchronized (ClubRepository.class) {
                if (instance == null) {
                    instance = new ClubRepository(database);
                }
            }
        }

        return instance;
    }



    public LiveData<ClubEntity> getClub(final int idClub) {
        return database.clubDAO().getClubById(idClub);
    }

    public LiveData<List<ClubEntity>> getClubs(final int idLeague) {
        return database.clubDAO().getAllClubsByLeague(idLeague);
    }

    public LiveData<List<ClubEntity>> getAllClubs() {
        return database.clubDAO().getAllClubs();
    }

    public LiveData<List<ClubEntity>> getFavorites(final int idLeague) {
        return database.clubDAO().getAllClubsFavorite(idLeague);
    }


    public void insert(final ClubEntity club) {
        database.clubDAO().insert(club);
    }

    public void update(final ClubEntity club) {
        database.clubDAO().update(club);
    }

    public void delete(final ClubEntity club) {
        database.clubDAO().delete(club);
    }
}
