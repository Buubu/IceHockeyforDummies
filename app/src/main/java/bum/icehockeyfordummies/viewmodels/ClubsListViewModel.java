package bum.icehockeyfordummies.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.util.Log;
import java.util.List;

import bum.icehockeyfordummies.database.BaseApp;
import bum.icehockeyfordummies.database.ClubEntity;
import bum.icehockeyfordummies.database.ClubRepository;




public class AccountListViewModel extends AndroidViewModel {

    private static final String TAG = "AccountListViewModel";

    private AccountRepository mRepository;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<List<ClientWithAccounts>> mObservableClientAccounts;
    private final MediatorLiveData<List<AccountEntity>> mObservableOwnAccounts;

    public AccountListViewModel(@NonNull Application application,
                                final String ownerId, ClientRepository clientRepository, AccountRepository accountRepository) {
        super(application);

        mRepository = accountRepository;

        mObservableClientAccounts = new MediatorLiveData<>();
        mObservableOwnAccounts = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        mObservableClientAccounts.setValue(null);
        mObservableOwnAccounts.setValue(null);

        LiveData<List<ClientWithAccounts>> clientAccounts = clientRepository.getOtherClientsWithAccounts(ownerId);
        LiveData<List<AccountEntity>> ownAccounts = mRepository.getByOwner(ownerId);

        // observe the changes of the entities from the database and forward them
        mObservableClientAccounts.addSource(clientAccounts, mObservableClientAccounts::setValue);
        mObservableOwnAccounts.addSource(ownAccounts, mObservableOwnAccounts::setValue);
    }

    /**
     * A creator is used to inject the account id into the ViewModel
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;

        private final String mOwnerId;

        private final ClientRepository mClientRepository;

        private final AccountRepository mAccountRepository;

        public Factory(@NonNull Application application, String ownerId) {
            mApplication = application;
            mOwnerId = ownerId;
            mClientRepository = ((BaseApp) application).getClientRepository();
            mAccountRepository = ((BaseApp) application).getAccountRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new AccountListViewModel(mApplication, mOwnerId, mClientRepository, mAccountRepository);
        }
    }

    /**
     * Expose the LiveData ClientWithAccounts query so the UI can observe it.
     */
    public LiveData<List<ClientWithAccounts>> getClientAccounts() {
        return mObservableClientAccounts;
    }

    /**
     * Expose the LiveData AccountEntities query so the UI can observe it.
     */
    public LiveData<List<AccountEntity>> getOwnAccounts() {
        return mObservableOwnAccounts;
    }

    public void deleteAccount(AccountEntity account) {
        ((BaseApp) getApplication()).getAccountRepository()
                .delete(account);
    }

    public void executeTransaction(final AccountEntity sender, final AccountEntity recipient) {
        //noinspection unchecked
        ((BaseApp) getApplication()).getAccountRepository().transaction(sender, recipient);
    }
}






public class ClubsListViewModel extends AndroidViewModel {

    private static final String TAG = "ClubsListViewModel";
    private ClubRepository repo;


    // Set observers on the data to react when they change
    private final MediatorLiveData<List<ClubEntity>> observableClubs;

    public ClubsListViewModel(Application app, ClubRepository repo) {
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


    // Factory of the model
    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        private final Application app;
        private final ClubRepository repo;

        public Factory(Application app) {
            this.app = app;
            repo = ((BaseApp) app).getClubRepository();
        }


        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new ClubsListViewModel(app, repo);
        }
    }
}
