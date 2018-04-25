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
public class SquareFragmentNew extends Fragment {


    public static SquareFragmentNew newInstance() {
        SquareFragmentNew fragment = new SquareFragmentNew();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_square_fragment_new, container, false);
    }

}
