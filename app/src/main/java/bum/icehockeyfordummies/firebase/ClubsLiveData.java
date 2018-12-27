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


public class ClubsLiveData extends LiveData<List<ClubEntity>> {
    private static final String TAG = "ClubsLiveData";
    private final DatabaseReference reference;
    private final boolean isFavoriteList;
    private final ValueEventListener listener;

    // Constructor
    public ClubsLiveData(DatabaseReference reference, boolean isFavoriteList) {
        this.reference = reference;
        this.isFavoriteList = isFavoriteList;

        // Create the right listener
        if (!this.isFavoriteList) {
            listener = new ClubsListener();
        } else {
            listener = new FavoritesListener();
        }
    }


    // Overrided methods
    protected void onActive() {
        Log.d(TAG, "onActive");
        reference.addValueEventListener(listener);
    }

    protected void onInactive() {
        Log.d(TAG, "onInactive");
    }


    // Listener for the list of clubs
    private class ClubsListener implements ValueEventListener {
        public void onDataChange(DataSnapshot snapshot) {
            setValue(toClubs(snapshot));
        }

        public void onCancelled(DatabaseError error) {
            Log.e(TAG, "Can't listen to the query " + reference, error.toException());
        }
    }

    // Listener for the favorites
    private class FavoritesListener implements ValueEventListener {
        public void onDataChange(DataSnapshot snapshot) {
            setValue(toFavorites(snapshot));
        }

        public void onCancelled(DatabaseError error) {
            Log.e(TAG, "Cant't listen to the query " + reference, error.toException());
        }
    }


    // Returns the list of clubs
    private List<ClubEntity> toClubs(DataSnapshot snapshot) {
        List<ClubEntity> clubs = new ArrayList<>();

        for (DataSnapshot child : snapshot.getChildren()) {
            ClubEntity club = child.getValue(ClubEntity.class);
            club.setId(child.getKey());
            clubs.add(club);
        }

        return clubs;
    }

    // Returns the list of favorites
    private List<ClubEntity> toFavorites(DataSnapshot snapshot) {
        List<ClubEntity> favorites = new ArrayList<>();

        for (DataSnapshot child : snapshot.getChildren()) {
            ClubEntity club = child.getValue(ClubEntity.class);
            club.setId(child.getKey());

            if (club.getFavorite()) {
                favorites.add(club);
            }
        }

        return favorites;
    }
}
