/* ©2018-2019, Montaine BURGER
   HES-SO Valais-Wallis, FIG */
package bum.icehockeyfordummies.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import java.util.List;
import bum.icehockeyfordummies.database.BaseApp;
import bum.icehockeyfordummies.database.ClubEntity;
import bum.icehockeyfordummies.database.ClubRepository;


public class FavoritesViewModel extends AndroidViewModel {
    private static final String TAG = "FavoritesViewModel";
    private ClubRepository repo;


    // Set the observers on the data to react when they change
    private final MediatorLiveData<List<ClubEntity>> observableFavorites;

    public FavoritesViewModel(Application app, ClubRepository clubsRepo) {
        super(app);
        repo = clubsRepo;

        // Set to null by default
        observableFavorites = new MediatorLiveData<>();
        observableFavorites.setValue(null);

        // Observe all the changes and affect them
        LiveData<List<ClubEntity>> fav = repo.getAllFavorites();
        observableFavorites.addSource(fav, observableFavorites::setValue);
    }


    // Factory of the model
    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        private final Application app;
        private final ClubRepository repo;

        public Factory(Application app) {
            this.app = app;
            repo = ((BaseApp) app).getClubRepository();
        }


        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new FavoritesViewModel(app, repo);
        }
    }


    // Return the observable to the UI
    public LiveData<List<ClubEntity>> getAllFavorites() {
        return observableFavorites;
    }
}
