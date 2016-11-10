package io.github.eventhandler.sessionmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


public class SessionManager {

    private static final String PREF_NAME = "io.github.eventhandler.sessionmanager.SessionManager.PREF_NAME";
    private static final String KEY_LOGGED_IN = "io.github.eventhandler.sessionmanager.SessionManager.KEY_LOGGED_IN";
    private static final String KEY_TOKEN = "io.github.eventhandler.sessionmanager.SessionManager.KEY_TOKEN";
    private static final String KEY_PASS = "io.github.eventhandler.sessionmanager.SessionManager.KEY_PASS";

    private static final String TAG = "SessionManager";

    private SharedPreferences mPref;

    public SessionManager(Context context) {
        mPref = context.getSharedPreferences(PREF_NAME, 0);
    }

    public void setLoginState(boolean loginState) {
        SharedPreferences.Editor editor = mPref.edit();
        editor.putBoolean(KEY_LOGGED_IN, loginState);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return mPref.getBoolean(KEY_LOGGED_IN, false);
    }

    public void setToken(String token) {
        SharedPreferences.Editor editor = mPref.edit();
        editor.putString(KEY_TOKEN, token);
        editor.apply();
    }

    public String getToken() {
        return mPref.getString(KEY_TOKEN, null);
    }

    public void setPassword(String password) {
        try {
            password = StringHelper.converToSHA512(password);
            SharedPreferences.Editor editor = mPref.edit();
            editor.putString(KEY_PASS, password);
            editor.apply();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            Log.e(TAG, "Cannot hash the given string");
            Log.e(TAG, e.getMessage());
        }
    }

    public String getPassword() {
        return mPref.getString(KEY_PASS, null);
    }
}
