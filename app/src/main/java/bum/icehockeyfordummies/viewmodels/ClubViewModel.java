package bum.icehockeyfordummies.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import bum.icehockeyfordummies.database.BaseApp;
import bum.icehockeyfordummies.database.ClubEntity;
import bum.icehockeyfordummies.database.ClubRepository;
import bum.icehockeyfordummies.database.LeagueEntity;


public class ClubViewModel extends AndroidViewModel {
    private static final String TAG = "ClubViewModel";
    private ClubRepository repo;


    // Set observers on the data to react when they change
    private final MediatorLiveData<ClubEntity> observableClub;

    public ClubViewModel(Application app, final String id, ClubRepository repository) {
        super(app);
        repo = repository;

        observableClub = new MediatorLiveData<>();
        observableClub.setValue(null);

        if (id != null) {
            LiveData<ClubEntity> club = repo.getClub(id);
            observableClub.addSource(club, observableClub::setValue);
        }
    }


    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        private final Application app;
        private final String id;
        private final ClubRepository repo;


        public Factory(Application application, String clubId) {
            app = application;
            id = clubId;
            repo = ((BaseApp) app).getClubRepository();
        }

        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new ClubViewModel(app, id, repo);
        }
    }


    public LiveData<ClubEntity> getClub() {
        return observableClub;
    }

    public void createClub(ClubEntity club, String league) {
        ((BaseApp) getApplication()).getClubRepository()
                .insert(club, league);
    }

    public void updateClub(ClubEntity club) {
        ((BaseApp) getApplication()).getClubRepository()
                .update(club);
    }

    public void deleteClub(String club, String league, ArrayList<String> players) {
        ((BaseApp) getApplication()).getClubRepository()
                .delete(club, league, players);
    }
}
