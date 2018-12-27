package bum.icehockeyfordummies.firebase;

import android.arch.lifecycle.LiveData;
import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import bum.icehockeyfordummies.database.PlayerEntity;


public class PlayersLiveData extends LiveData<List<PlayerEntity>> {
    private static final String TAG = "PlayersLiveData";
    private final DatabaseReference reference;
    private final PlayersListener listener = new PlayersListener();

    // Constructor
    public PlayersLiveData(DatabaseReference reference) {
        this.reference = reference;
    }


    // Overrided methods
    protected void onActive() {
        Log.d(TAG, "onActive");
        reference.addValueEventListener(listener);
    }

    protected void onInactive() {
        Log.d(TAG, "onInactive");
    }


    // Listener for the list of players
    private class PlayersListener implements ValueEventListener {
        public void onDataChange(DataSnapshot snapshot) {
            setValue(toPlayers(snapshot));
        }

        public void onCancelled(DatabaseError error) {
            Log.e(TAG, "Can't listen to the query " + reference, error.toException());
        }
    }


    // Returns the list of players
    private List<PlayerEntity> toPlayers(DataSnapshot snapshot) {
        List<PlayerEntity> players = new ArrayList<>();

        for (DataSnapshot child : snapshot.getChildren()) {
            PlayerEntity player = child.getValue(PlayerEntity.class);
            player.setId(child.getKey());
            players.add(player);
        }

        return players;
    }
}
