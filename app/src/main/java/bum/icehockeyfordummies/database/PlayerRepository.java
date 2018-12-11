package bum.icehockeyfordummies.database;

import android.arch.lifecycle.LiveData;
import java.util.List;


public class PlayerRepository {
    private static PlayerRepository instance;
    private final AppDatabase database;

    private PlayerRepository(final AppDatabase database) { this.database = database; }

    public static PlayerRepository getInstance(final AppDatabase database) {
        if (instance == null) {
            synchronized (PlayerRepository.class) {
                if (instance == null) {
                    instance = new PlayerRepository(database);
                }
            }
        }

        return instance;
    }


    public LiveData<PlayerEntity> getPlayer(final int idPlayer) {
        return database.playerDAO().getPlayerById(idPlayer);
    }

    public LiveData<List<PlayerEntity>> getPlayers(final int idClub) {
        return database.playerDAO().getAllPlayersByClub(idClub);
    }


    public void insert(final PlayerEntity player) {
        database.playerDAO().insert(player);
    }

    public void update(final PlayerEntity player) {
        database.playerDAO().update(player);
    }

    public void delete(final PlayerEntity player) {
        database.playerDAO().delete(player);
    }
}
