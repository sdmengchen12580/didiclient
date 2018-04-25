package org.faqrobot.textrecyclerview.ui.fra.my;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.faqrobot.textrecyclerview.R;
import org.faqrobot.textrecyclerview.view.DynamicWave;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {

    private DynamicWave dynamicWave;
    public MyFragment() {
    }

    public static MyFragment getInstance(){
        return new MyFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        return view;
    }

}
