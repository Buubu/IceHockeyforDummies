package bum.icehockeyfordummies.database;

import android.arch.lifecycle.LiveData;
import android.util.Log;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.List;
import bum.icehockeyfordummies.firebase.ClubLiveData;
import bum.icehockeyfordummies.firebase.ClubsLiveData;


public class ClubRepository {
    private static final String TAG = "ClubRepository";
    private static ClubRepository instance;

    // Empty constructor
    private ClubRepository() {}


    // Retrieve the instance of the repository
    public static ClubRepository getInstance() {
        if (instance == null) {
            synchronized (ClubRepository.class) {
                if (instance == null) {
                    instance = new ClubRepository();
                }
            }
        }

        return instance;
    }


    // Select a club by its id
    public LiveData<ClubEntity> getClub(final String id) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("clubs")
                .child(id);

        return new ClubLiveData(reference);
    }

    // Select all clubs
    public LiveData<List<ClubEntity>> getAllClubs() {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("clubs");

        return new ClubsLiveData(reference, false);
    }

    // Select all clubs from a league
    //TODO: if I have the time to do it...

    // Select all favorite clubs
    public LiveData<List<ClubEntity>> getAllFavorites() {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("clubs");

        return new ClubsLiveData(reference, true);
    }


    // Insert a club (includes create the club and add it to a league)
    public void insert(final ClubEntity club, final String league) {

        // Create a reference for the new club
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("clubs");
        String key = reference.push().getKey();

        // Set the data of the new club
        FirebaseDatabase.getInstance()
                .getReference("clubs")
                .child(key)
                .setValue(club, (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        Log.d(TAG, "Insert failure!", databaseError.toException());
                    } else {
                        Log.i(TAG, "Insert successful!");
                    }
                });

        // Add this new club into the league
        FirebaseDatabase.getInstance()
                .getReference("leagues")
                .child(league)
                .child("clubs")
                .child(key)
                .setValue(true, (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        Log.d(TAG, "Insert failure!", databaseError.toException());
                    } else {
                        Log.i(TAG, "Insert successful!");
                    }
                });
    }


    // Update a club
    public void update(final ClubEntity club) {
        FirebaseDatabase.getInstance()
                .getReference("clubs")
                .child(club.getId())
                .updateChildren(club.toMap(), (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        Log.d(TAG, "Update failure!", databaseError.toException());
                    } else {
                        Log.d(TAG, "Update successful!");
                    }
                });
    }


    // Delete a club
    //TODO: when a club is deleted, all the players related too?
    public void delete(final ClubEntity club) {
        FirebaseDatabase.getInstance()
                .getReference("clubs")
                .child(club.getId())
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
//    @Query("DELETE FROM clubs WHERE club_system = 0")
//    void deleteAll();
