package bum.icehockeyfordummies.async;

import android.app.Application;
import android.os.AsyncTask;
import bum.icehockeyfordummies.database.LeagueEntity;


public class DeleteLeague extends AsyncTask<LeagueEntity, Void, Void> {
    private static final String TAG = "DeleteLeague";
    private Application app;
    private OnAsyncEventListener callback;
    private Exception exception;

    public DeleteLeague(Application app, OnAsyncEventListener callback) {
        this.app = app;
        this.callback = callback;
    }


    @Override
    protected Void doInBackground(LeagueEntity... params) {
        try {
            for (LeagueEntity league : params)
                ((BaseApp) app).getLeagueRepository().delete(league);
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
