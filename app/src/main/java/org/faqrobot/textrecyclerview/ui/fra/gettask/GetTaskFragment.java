package org.faqrobot.textrecyclerview.ui.fra.gettask;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.faqrobot.textrecyclerview.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GetTaskFragment extends Fragment {


    public GetTaskFragment() {
        // Required empty public constructor
    }

    public static GetTaskFragment getInstance(){
        return new GetTaskFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_get_task, container, false);
    }

}
