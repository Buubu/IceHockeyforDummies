/* ©2018-2019, Montaine BURGER
   HES-SO Valais-Wallis, FIG */
package bum.icehockeyfordummies.firebase;

import android.arch.lifecycle.LiveData;
import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import bum.icehockeyfordummies.database.ClubEntity;


public class ClubLiveData extends LiveData<ClubEntity> {
    private static final String TAG = "ClubLiveData";
    private final DatabaseReference reference;
    private final ClubLiveData.ClubListener listener = new ClubLiveData.ClubListener();

    // Constructor
    public ClubLiveData(DatabaseReference reference) {
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


    // Listener for the club
    private class ClubListener implements ValueEventListener {
        public void onDataChange(DataSnapshot snapshot) {
            ClubEntity club = snapshot.getValue(ClubEntity.class);

            if (club != null) {
                club.setId(snapshot.getKey());
            }

            setValue(club);
        }

        public void onCancelled(DatabaseError error) {
            Log.e(TAG, "Can't listen to the query " + reference, error.toException());
        }
    }
}
