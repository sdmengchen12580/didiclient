package org.faqrobot.textrecyclerview.ui.fra.prividertask;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.faqrobot.textrecyclerview.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProviderTaskFragment extends Fragment {


    public ProviderTaskFragment() {
        // Required empty public constructor
    }


    public static ProviderTaskFragment getInstance(){
        return new ProviderTaskFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_provider_task, container, false);
    }

}
