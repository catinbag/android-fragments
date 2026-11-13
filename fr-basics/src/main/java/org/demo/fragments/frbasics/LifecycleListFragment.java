package org.demo.fragments.frbasics;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LifecycleListFragment extends LoggingFragment {

    public LifecycleListFragment() {
        super(R.layout.fragment_lifecycle_list);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((TextView) view.findViewById(R.id.instance)).setText(
                getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()));
        view.findViewById(R.id.open_details).setOnClickListener(v ->
                getParentFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.container, new LifecycleDetailsFragment())
                        .addToBackStack(null)
                        .commit());
    }
}
