package bum.icehockeyfordummies.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;
import android.util.Log;
import java.util.List;
import bum.icehockeyfordummies.async.DeleteClub;
import bum.icehockeyfordummies.async.OnAsyncEventListener;
import bum.icehockeyfordummies.database.ClubEntity;
import bum.icehockeyfordummies.database.ClubRepository;


public class ClubsListViewModel extends AndroidViewModel {

    private static final String TAG = "ClubsListViewModel";
    private ClubRepository repo;


    // Set observers on the data to react when they change
    private final MediatorLiveData<List<ClubEntity>> observableClubs;

    public ClubsListViewModel(@NonNull Application app, ClubRepository repo) {
        super(app);
        this.repo = repo;

        // Set to null by default
        observableClubs = new MediatorLiveData<>();
        observableClubs.setValue(null);

        LiveData<List<ClubEntity>> clubs = repo.getAllClubs();


        // Observe all the changes and affect them
        observableClubs.addSource(clubs, observableClubs::setValue);
    }


    // Return the observable to the UI
    public LiveData<List<ClubEntity>> getAllClubs() {
        return observableClubs;
    }


    // Behaviour of the delete query
    public void deleteClub(ClubEntity club) {
        new DeleteClub(getApplication(), new OnAsyncEventListener() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "Delete the club: success");
            }

            @Override
            public void onFailure(Exception e) {
                Log.d(TAG, "Delete the club: failure", e);
            }
        }).execute(club);
    }
}
