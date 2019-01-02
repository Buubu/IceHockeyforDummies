/* ©2018-2019, Montaine BURGER
   HES-SO Valais-Wallis, FIG */
package bum.icehockeyfordummies.firebase;

import android.arch.lifecycle.LiveData;
import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import bum.icehockeyfordummies.database.PlayerEntity;


public class PlayerLiveData extends LiveData<PlayerEntity> {
    private static final String TAG = "PlayerLiveData";
    private final DatabaseReference reference;
    private final PlayerLiveData.PlayerListener listener = new PlayerLiveData.PlayerListener();

    // Constructor
    public PlayerLiveData(DatabaseReference reference) {
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


    // Listener for the player
    private class PlayerListener implements ValueEventListener {
        public void onDataChange(DataSnapshot snapshot) {
            PlayerEntity player = snapshot.getValue(PlayerEntity.class);

            if (player != null) {
                player.setId(snapshot.getKey());
            }

            setValue(player);
        }

        public void onCancelled(DatabaseError error) {
            Log.e(TAG, "Can't listen to the query " + reference, error.toException());
        }
    }
}
