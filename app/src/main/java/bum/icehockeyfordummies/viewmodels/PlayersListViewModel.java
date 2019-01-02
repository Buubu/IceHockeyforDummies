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
import bum.icehockeyfordummies.database.PlayerEntity;
import bum.icehockeyfordummies.database.PlayerRepository;


public class PlayersListViewModel extends AndroidViewModel {
    private static final String TAG = "PlayersListViewModel";
    private PlayerRepository repo;
    private String club;


    // Set observers on the data to react when they change
    private final MediatorLiveData<List<PlayerEntity>> observablePlayers;

    public PlayersListViewModel(Application app, PlayerRepository playersRepo, String club) {
        super(app);
        repo = playersRepo;
        this.club = club;

        // Set to null by default
        observablePlayers = new MediatorLiveData<>();
        observablePlayers.setValue(null);

        // Observe all the changes and affect them
        LiveData<List<PlayerEntity>> players = repo.getAllPlayers(club);
        observablePlayers.addSource(players, observablePlayers::setValue);
    }


    // Factory of the model
    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        private final Application app;
        private final PlayerRepository repo;
        private final String club;

        public Factory(Application app, String club) {
            this.app = app;
            repo = ((BaseApp) app).getPlayerRepository();
            this.club = club;
        }


        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new PlayersListViewModel(app, repo, club);
        }
    }


    // Return the observable to the UI
    public LiveData<List<PlayerEntity>> getAllPlayers() { return observablePlayers; }
}
