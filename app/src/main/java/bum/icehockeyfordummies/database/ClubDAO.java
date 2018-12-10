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
public interface ClubDAO {

    // SELECT queries
    @Query("SELECT * FROM clubs WHERE club_id = :idClub")
    LiveData<ClubEntity> getClubById(int idClub);

    @Query("SELECT * FROM clubs")
    LiveData<List<ClubEntity>> getAllClubs();

    @Query("SELECT * FROM clubs WHERE FK_league_id = :idLeague")
    LiveData<List<ClubEntity>> getAllClubsByLeague(int idLeague);

    @Query("SELECT * FROM clubs WHERE FK_league_id = :idLeague AND club_favorite = 1")
    LiveData<List<ClubEntity>> getAllClubsFavorite(int idLeague);
    // TODO: check the boolean type, is it right like this ?


    // INSERT, UPDATE and DELETE queries
    @Insert
    int insert(ClubEntity club);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ClubEntity> clubs);

    @Update
    void update(ClubEntity club);

    @Delete
    void delete(ClubEntity club);


    // Delete "all" data: only data added by the user (not the initial data)
    @Query("DELETE FROM clubs WHERE club_system = 0")
    void deleteAll();
}
