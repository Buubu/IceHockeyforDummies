package bum.icehockeyfordummies.database;

import android.arch.lifecycle.LiveData;
import android.util.Log;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class LeagueRepository {
    private static final String TAG = "LeagueRepository";
    private static LeagueRepository instance;

    // Empty constructor
    private LeagueRepository() {}


    // Retrieve the instance of the repository
    public static LeagueRepository getInstance() {
        if (instance == null) {
            synchronized (LeagueRepository.class) {
                if (instance == null) {
                    instance = new LeagueRepository();
                }
            }
        }

        return instance;
    }


//    // Select a league by its id
//    public LiveData<LeagueEntity> getLeague(final String id) {
//        DatabaseReference reference = FirebaseDatabase.getInstance()
//                .getReference("leagues")
//                .child(id);
//
//        return new LeagueLiveData(reference);
//    }


    // Insert a league
    public void insert(final LeagueEntity league) {

        // Create a reference for the new league
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("leagues");
        String key = reference.push().getKey();

        // Set the data of the new league
        FirebaseDatabase.getInstance()
                .getReference("leagues")
                .child(key)
                .setValue(league, (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        Log.d(TAG, "Insert failure!", databaseError.toException());
                    } else {
                        Log.i(TAG, "Insert successful!");
                    }
                });
    }


    // Update a league
    public void update(final LeagueEntity league) {
        FirebaseDatabase.getInstance()
                .getReference("leagues")
                .child(league.getId())
                .updateChildren(league.toMap(), (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        Log.d(TAG, "Update failure!", databaseError.toException());
                    } else {
                        Log.d(TAG, "Update successful!");
                    }
                });
    }


    // Delete a league
    public void delete(final LeagueEntity league) {
        FirebaseDatabase.getInstance()
                .getReference("leagues")
                .child(league.getId())
                .removeValue((databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        Log.d(TAG, "Delete failure!", databaseError.toException());
                    } else {
                        Log.d(TAG, "Delete successful!");
                    }
                });
    }
}

//    // Delete "all" data: only data added by the user (not the initial data)
//    @Query("DELETE FROM leagues WHERE league_system = 0")
//    void deleteAll();
