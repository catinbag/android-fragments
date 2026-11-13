package org.demo.fragments.frbasics;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LifecycleDetailsFragment extends LoggingFragment {

    public LifecycleDetailsFragment() {
        super(R.layout.fragment_lifecycle_details);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((TextView) view.findViewById(R.id.instance)).setText(
                getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()));
    }
}
