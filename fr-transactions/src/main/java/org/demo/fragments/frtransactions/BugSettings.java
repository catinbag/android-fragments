package org.demo.fragments.frtransactions;

import android.content.Context;
import android.content.SharedPreferences;

/** Keeps the modes in SharedPreferences: they survive rotation and a restart of the process. */
public final class BugSettings {
    private static final String FILE = "bugs";

    private BugSettings() {
    }

    private static SharedPreferences prefs(Context context) {
        return context.getSharedPreferences(FILE, Context.MODE_PRIVATE);
    }

    /** Default is false: the code is correct. */
    public static boolean isOn(Context context, Bug bug) {
        return prefs(context).getBoolean(bug.name(), false);
    }

    public static void set(Context context, Bug bug, boolean on) {
        prefs(context).edit().putBoolean(bug.name(), on).apply();
    }
}
