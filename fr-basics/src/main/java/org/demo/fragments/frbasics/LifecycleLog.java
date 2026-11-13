package org.demo.fragments.frbasics;

import android.util.Log;

/** One tag for the Activity and its fragments: Logcat filter "tag:FragmentLifecycle". */
final class LifecycleLog {
    static final String TAG = "FragmentLifecycle";

    private LifecycleLog() {
    }

    /** Writes class name, hashCode and callback name. */
    static void d(Object owner, String callback) {
        Log.d(TAG, owner.getClass().getSimpleName()
                + "@" + Integer.toHexString(owner.hashCode()) + " " + callback);
    }
}
