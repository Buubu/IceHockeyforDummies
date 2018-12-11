package bum.icehockeyfordummies.database;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import java.util.List;
import java.util.concurrent.Executors;


@Database(entities = {LeagueEntity.class, ClubEntity.class, PlayerEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    // Based values
    private static final String TAG = "AppDatabase";
    private static AppDatabase instance;
    public static final String DATABASE_NAME = "hockey_database";

    // Use all DAOs of the database
    public abstract LeagueDAO leagueDAO();
    public abstract ClubDAO clubDAO();
    public abstract PlayerDAO playerDAO();


    // Define all components of the database
    private final MutableLiveData<Boolean> isDatabaseCreated = new MutableLiveData<>();

    public static AppDatabase getInstance(final Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance.buildDatabase(context.getApplicationContext());
                    instance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }

        return instance;
    }


    private static AppDatabase buildDatabase(final Context appContext) {
        Log.i(TAG, "Database will be initialized.");

        return Room.databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadExecutor().execute(() -> {
                            AppDatabase database = AppDatabase.getInstance(appContext);
                            initializeDemoData(database);
                            database.setDatabaseCreated();
                        });
                    }
                }).build();
    }


    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            Log.i(TAG, "Database initialized.");
            setDatabaseCreated();
        }
    }


    private void setDatabaseCreated(){
        isDatabaseCreated.postValue(true);
    }


    public static void initializeDemoData(final AppDatabase database) {
        Executors.newSingleThreadExecutor().execute(() -> {
            database.runInTransaction(() -> {
                Log.i(TAG, "Wipe database.");
                database.playerDAO().deleteAll();
                database.clubDAO().deleteAll();
                database.leagueDAO().deleteAll();

                // Insert all the system data
                List<LeagueEntity> leagues = DataGenerator.generateLeagues();
                List<ClubEntity> clubs = DataGenerator.generateClubs();
                List<PlayerEntity> players = DataGenerator.generatePlayers();

                Log.i(TAG, "Insert demo data.");
                database.leagueDAO().insertAll(leagues);
                database.clubDAO().insertAll(clubs);
                database.playerDAO().insertAll(players);
            });
        });
    }


    public LiveData<Boolean> getDatabaseCreated() {
        return isDatabaseCreated;
    }
}
