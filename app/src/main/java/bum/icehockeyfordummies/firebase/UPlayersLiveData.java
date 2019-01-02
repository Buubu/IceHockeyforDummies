/* ©2018-2019, Montaine BURGER
   HES-SO Valais-Wallis, FIG */
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


public class UPlayersLiveData extends LiveData<List<PlayerEntity>> {
    private static final String TAG = "UPlayersLiveData";
    private final DatabaseReference reference;
    private final UPlayersListener listener = new UPlayersListener();

    // Constructor
    public UPlayersLiveData(DatabaseReference reference) {
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


    // Listener for the list of users' players
    private class UPlayersListener implements ValueEventListener {
        public void onDataChange(DataSnapshot snapshot) { setValue(toUPlayers(snapshot)); }

        public void onCancelled(DatabaseError error) {
            Log.e(TAG, "Can't listen to the query " + reference, error.toException());
        }
    }


    // Returns the list of users' players
    private List<PlayerEntity> toUPlayers(DataSnapshot snapshot) {
        List<PlayerEntity> uPlayers = new ArrayList<>();

        for (DataSnapshot child: snapshot.getChildren()) {
            PlayerEntity player = child.getValue(PlayerEntity.class);
            player.setId(child.getKey());

            if (!player.getSystem()) {
                uPlayers.add(player);
            }
        }

        return uPlayers;
    }
}
