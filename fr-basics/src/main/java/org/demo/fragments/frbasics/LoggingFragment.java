package org.demo.fragments.frbasics;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/** A fragment that writes every lifecycle callback to Logcat. The log line comes before super. */
public abstract class LoggingFragment extends Fragment {

    protected LoggingFragment(@LayoutRes int layoutId) {
        super(layoutId);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        LifecycleLog.d(this, "onAttach");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        LifecycleLog.d(this, "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        LifecycleLog.d(this, "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        LifecycleLog.d(this, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        LifecycleLog.d(this, "onViewStateRestored");
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onStart() {
        LifecycleLog.d(this, "onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        LifecycleLog.d(this, "onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        LifecycleLog.d(this, "onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        LifecycleLog.d(this, "onStop");
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        LifecycleLog.d(this, "onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView() {
        LifecycleLog.d(this, "onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        LifecycleLog.d(this, "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        LifecycleLog.d(this, "onDetach");
        super.onDetach();
    }
}
