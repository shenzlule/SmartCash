package org.loan.smartcash.session;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String PREF_NAME = "smartcash_session";
    private static final String KEY_EMAIL = "user_email";
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void login(String email) {
        editor.putString(KEY_EMAIL, email);
        editor.apply();
    }

    public void logout() {
        editor.clear();
        editor.apply();
    }

    public boolean isLoggedIn() {
        return prefs.contains(KEY_EMAIL);
    }

    public String getUserEmail() {
        return prefs.getString(KEY_EMAIL, null);
    }
}
