package bum.icehockeyfordummies.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import bum.icehockeyfordummies.database.BaseApp;
import bum.icehockeyfordummies.database.ClubEntity;
import bum.icehockeyfordummies.database.PlayerEntity;
import bum.icehockeyfordummies.database.PlayerRepository;


public class PlayerViewModel extends AndroidViewModel {
    private static final String TAG = "PlayerViewModel";
    private PlayerRepository repo;


    // Set observers on the data to react when they change
    private final MediatorLiveData<PlayerEntity> observablePlayer;

    public PlayerViewModel(Application app, final String id, PlayerRepository repository) {
        super(app);
        repo = repository;

        observablePlayer = new MediatorLiveData<>();
        observablePlayer.setValue(null);

        if (id != null) {
            LiveData<PlayerEntity> player = repo.getPlayer(id);
            observablePlayer.addSource(player, observablePlayer::setValue);
        }
    }


    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        private final Application app;
        private final String id;
        private final PlayerRepository repo;


        public Factory(Application application, String playerId) {
            app = application;
            id = playerId;
            repo = ((BaseApp) app).getPlayerRepository();
        }

        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new PlayerViewModel(app, id, repo);
        }
    }


    public LiveData<PlayerEntity> getPlayer() {
        return observablePlayer;
    }
}
