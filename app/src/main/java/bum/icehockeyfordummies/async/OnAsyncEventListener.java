package bum.icehockeyfordummies.async;


public interface OnAsyncEventListener {
    void onSuccess();
    void onFailure(Exception e);
}
