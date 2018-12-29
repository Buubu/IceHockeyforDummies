package bum.icehockeyfordummies.firebase;

import android.arch.lifecycle.LiveData;
import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bum.icehockeyfordummies.database.PlayerEntity;


public class PlayersLiveData extends LiveData<List<PlayerEntity>> {
    private static final String TAG = "PlayersLiveData";
    private final DatabaseReference reference;
    private final PlayersListener listener = new PlayersListener();
    private final String idClub;

    // Constructor
    public PlayersLiveData(DatabaseReference reference, String club) {
        this.reference = reference;
        idClub = club;
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
        public void onDataChange(DataSnapshot snapshot) { setValue(toPlayers(snapshot)); }

        public void onCancelled(DatabaseError error) {
            Log.e(TAG, "Can't listen to the query " + reference, error.toException());
        }
    }


    // Returns the list of players
    private List<PlayerEntity> toPlayers(DataSnapshot snapshot) {
        List<PlayerEntity> ref = new ArrayList<>();
        List<PlayerEntity> players = new ArrayList<>();

        for (DataSnapshot child : snapshot.getChildren()) {
            PlayerEntity player = child.getValue(PlayerEntity.class);
            player.setId(child.getKey());
            ref.add(player);
        }

        for (int item=0; item<ref.size(); item++) {
            Map<String, Boolean> clubs = ref.get(item).getClubs();



            for (Object club : clubs.keySet()) {
                if (club.equals(idClub)) {
                    players.add(ref.get(item));
                }
            }
        }

        return players;
    }
}
