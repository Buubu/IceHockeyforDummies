package bum.icehockeyfordummies.firebase;

import android.arch.lifecycle.LiveData;
import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import bum.icehockeyfordummies.database.ClubEntity;


public class UClubsLiveData extends LiveData<List<ClubEntity>> {
    private static final String TAG = "UClubsLiveData";
    private final DatabaseReference reference;
    private final UClubsListener listener = new UClubsListener();

    // Constructor
    public UClubsLiveData(DatabaseReference reference) {
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


    // Listener for the list of users' clubs
    private class UClubsListener implements ValueEventListener {
        public void onDataChange(DataSnapshot snapshot) { setValue(toUClubs(snapshot)); }

        public void onCancelled(DatabaseError error) {
            Log.e(TAG, "Can't listen to the query " + reference, error.toException());
        }
    }


    // Returns the list of users' clubs
    private List<ClubEntity> toUClubs(DataSnapshot snapshot) {
        List<ClubEntity> uClubs = new ArrayList<>();

        for (DataSnapshot child: snapshot.getChildren()) {
            ClubEntity club = child.getValue(ClubEntity.class);
            club.setId(child.getKey());

            if (!club.getSystem()) {
                uClubs.add(club);
            }
        }

        return uClubs;
    }
}
