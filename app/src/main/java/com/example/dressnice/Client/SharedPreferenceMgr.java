package com.example.dressnice.Client;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferenceMgr {

    public static final String MyPREFERENCES = "WEAR-ME";

    public static SharedPreferences getSharedPrefs(Context context) {
        return context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
    }

}
