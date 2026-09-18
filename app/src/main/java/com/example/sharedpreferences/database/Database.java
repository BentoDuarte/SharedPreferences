package com.example.sharedpreferences.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database {
    private static final String PREF_NAME = "produtos";

    private final SharedPreferences preferences;

    public Database(Context context) {
        preferences = context.getSharedPreferences(
                PREF_NAME,
                Context.MODE_PRIVATE
        );
    }

    public SharedPreferences getPreferences() {
        return preferences;
    }
}
