package bum.icehockeyfordummies.database;

import android.arch.lifecycle.LiveData;
import android.util.Log;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import bum.icehockeyfordummies.firebase.ClubLiveData;
import bum.icehockeyfordummies.firebase.ClubsLiveData;
import bum.icehockeyfordummies.firebase.UClubsLiveData;


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

    // Select all favorite clubs
    public LiveData<List<ClubEntity>> getAllFavorites() {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("clubs");

        return new ClubsLiveData(reference, true);
    }

    // Select all the users' clubs
    public LiveData<List<ClubEntity>> getUClubs() {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("clubs");

        return new UClubsLiveData(reference);
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


    // Mark a club as favorite
    public void favorite(final String idClub, final Boolean favorite) {
        Boolean todo;

        if (favorite) {
            todo = false;
        } else {
            todo = true;
        }

        FirebaseDatabase.getInstance()
                .getReference("clubs")
                .child(idClub)
                .child("favorite")
                .setValue(todo);
    }


    // Update a club
    public void update(final ClubEntity club, String oldLeague, String newLeague) {

        // Change the reference of the club inside the leagues
        Map.Entry<String, Boolean> entry = club.getLeagues().entrySet().iterator().next();
        if (entry.getKey() != oldLeague) {
            FirebaseDatabase.getInstance()
                    .getReference("leagues")
                    .child(oldLeague)
                    .child("clubs")
                    .child(club.getId()).removeValue();

            FirebaseDatabase.getInstance()
                    .getReference("leagues")
                    .child(newLeague)
                    .child("clubs")
                    .child(club.getId())
                    .setValue(true);
        }

        // Update the club
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
    public void delete(final String club, final String league, ArrayList<String> players) {

        // Delete the reference of the club inside the league
        FirebaseDatabase.getInstance()
                .getReference("leagues")
                .child(league)
                .child("clubs")
                .child(club).removeValue((databaseError, databaseReference) -> {
            if (databaseError != null) {
                Log.d(TAG, "Delete failure!", databaseError.toException());
            } else {
                Log.d(TAG, "Delete successful!");
            }
        });

        // Delete the reference of the club inside the players
        if (players != null) {
            for (String player: players) {
                FirebaseDatabase.getInstance().getReference("players")
                        .child(player)
                        .child("clubs")
                        .child(club).removeValue();
            }
        }

        // Delete the club
        FirebaseDatabase.getInstance()
                .getReference("clubs")
                .child(club)
                .removeValue((databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        Log.d(TAG, "Delete failure!", databaseError.toException());
                    } else {
                        Log.d(TAG, "Delete successful!");
                    }
                });
    }

    // Delete all the clubs
    public void deleteAll(List<ClubEntity> clubs) {

        // For each club...
        for (int club=0; club<clubs.size(); club++) {
            // Get the league
            Map.Entry<String, Boolean> entry = clubs.get(club).getLeagues().entrySet().iterator().next();
            String key = entry.getKey();

            // Get the players
            Map<String, Boolean> ref = clubs.get(club).getPlayers();
            ArrayList<String> players = new ArrayList<>();
            for (Map.Entry<String, Boolean> player: ref.entrySet()) {
                String id = player.getKey();
                players.add(id);
            }

            // Delete the club
            delete(clubs.get(club).getId(), key, players);
        }
    }
}
