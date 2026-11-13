package org.demo.fragments.frcommunication;

import android.content.Context;

/** Keeps the error modes in SharedPreferences: they survive rotation and a restart of the process. */
public final class BugSettings {
    public static final String OBSERVE_THIS = "observe_this";

    private static final String FILE = "bugs";

    private BugSettings() {
    }

    /** Default is false: the code is correct. */
    public static boolean isOn(Context context, String key) {
        return context.getSharedPreferences(FILE, Context.MODE_PRIVATE).getBoolean(key, false);
    }

    public static void set(Context context, String key, boolean on) {
        context.getSharedPreferences(FILE, Context.MODE_PRIVATE).edit().putBoolean(key, on).apply();
    }
}
