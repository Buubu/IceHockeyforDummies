package bum.icehockeyfordummies.database;

import android.arch.lifecycle.LiveData;
import android.util.Log;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.List;
import bum.icehockeyfordummies.firebase.PlayerLiveData;
import bum.icehockeyfordummies.firebase.PlayersLiveData;


public class PlayerRepository {
    private static final String TAG = "PlayerRepository";
    private static PlayerRepository instance;

    // Empty constructor
    private PlayerRepository() {}


    // Retrieve the instance of the repository
    public static PlayerRepository getInstance() {
        if (instance == null) {
            synchronized (PlayerRepository.class) {
                if (instance == null) {
                    instance = new PlayerRepository();
                }
            }
        }

        return instance;
    }


    // Select a player by his id
    public LiveData<PlayerEntity> getPlayer(final String id) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("players")
                .child(id);

        return new PlayerLiveData(reference);
    }

    // Select all players from a club
    public LiveData<List<PlayerEntity>> getAllPlayers(final String club) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("players");

        return new PlayersLiveData(reference, club);
    }


    // Insert a player (includes create the player and add him to a club)
    public void insert(final PlayerEntity player, final String club) {

        // Create a reference for the new player
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("players");
        String key = reference.push().getKey();

        // Set the data of the new player
        FirebaseDatabase.getInstance()
                .getReference("players")
                .child(key)
                .setValue(player, (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        Log.d(TAG, "Insert failure!", databaseError.toException());
                    } else {
                        Log.i(TAG, "Insert successful!");
                    }
                });

        // Add this new player into the club
        FirebaseDatabase.getInstance()
                .getReference("clubs")
                .child(club)
                .child("players")
                .child(key)
                .setValue(true, (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        Log.d(TAG, "Insert failure!", databaseError.toException());
                    } else {
                        Log.i(TAG, "Insert successful!");
                    }
                });
    }


    // Update a player
    public void update(final PlayerEntity player) {
        FirebaseDatabase.getInstance()
                .getReference("players")
                .child(player.getId())
                .updateChildren(player.toMap(), (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        Log.d(TAG, "Update failure!", databaseError.toException());
                    } else {
                        Log.d(TAG, "Update successful!");
                    }
                });
    }


    // Delete a player
    public void delete(final String player, final String club) {

        // Delete the reference of the player inside the club
        FirebaseDatabase.getInstance()
                .getReference("clubs")
                .child(club)
                .child("players")
                .child(player).removeValue((databaseError, databaseReference) -> {
            if (databaseError != null) {
                Log.d(TAG, "Delete failure!", databaseError.toException());
            } else {
                Log.d(TAG, "Delete successful!");
            }
        });

        // Delete the player
        FirebaseDatabase.getInstance()
                .getReference("players")
                .child(player)
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
//    @Query("DELETE FROM players WHERE player_system = 0")
//    void deleteAll();
