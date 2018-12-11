package bum.icehockeyfordummies.async;

import android.app.Application;
import android.os.AsyncTask;
import bum.icehockeyfordummies.database.PlayerEntity;


public class UpdatePlayer extends AsyncTask<PlayerEntity, Void, Void> {
    private static final String TAG = "UpdatePlayer";
    private Application app;
    private OnAsyncEventListener callback;
    private Exception exception;

    public UpdatePlayer(Application app, OnAsyncEventListener callback) {
        this.app = app;
        this.callback = callback;
    }


    @Override
    protected Void doInBackground(PlayerEntity... params) {
        try {
            for (PlayerEntity player : params)
                ((BaseApp) app).getPlayerRepository().update(player);
        } catch (Exception e) {
            exception = e;
        }

        return null;
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        if (callback != null) {
            if (exception == null) {
                callback.onSuccess();
            } else {
                callback.onFailure(exception);
            }
        }
    }
}
