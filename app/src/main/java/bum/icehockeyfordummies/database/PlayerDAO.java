package bum.icehockeyfordummies.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;


@Dao
public interface PlayerDAO {

    // SELECT queries
    @Query("SELECT * FROM players WHERE player_id = :idPlayer")
    LiveData<PlayerEntity> getPlayerById(int idPlayer);

    @Query("SELECT * FROM players WHERE FK_club_id = :idClub ORDER BY player_number")
    LiveData<List<PlayerEntity>> getAllPlayersByClub(int idClub);


    // INSERT, UPDATE and DELETE queries
    @Insert
    int insert(PlayerEntity player);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<PlayerEntity> players);

    @Update
    void update(PlayerEntity player);

    @Delete
    void delete(PlayerEntity player);


    // Delete "all" data: only data added by the user (not the initial data)
    @Query("DELETE FROM players WHERE player_system = 0")
    void deleteAll();
}
