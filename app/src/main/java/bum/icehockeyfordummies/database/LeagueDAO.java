package bum.icehockeyfordummies.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;


//TODO: check which requests are really used
@Dao
public interface LeagueDAO {

    // SELECT query
    @Query("SELECT * FROM leagues WHERE league_id = :idLeague")
    LiveData<LeagueEntity> getLeagueById(int idLeague);


    // INSERT, UPDATE and DELETE queries
    @Insert
    long insert(LeagueEntity league);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<LeagueEntity> leagues);

    @Update
    void update(LeagueEntity league);

    @Delete
    void delete(LeagueEntity league);


    // Delete "all" data: only data added by the user (not the initial data)
    @Query("DELETE FROM leagues WHERE league_system = 0")
    void deleteAll();
}
