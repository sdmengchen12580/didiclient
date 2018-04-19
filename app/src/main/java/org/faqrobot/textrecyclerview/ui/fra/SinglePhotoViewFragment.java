package org.faqrobot.textrecyclerview.ui.fra;


import android.view.View;
import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import org.faqrobot.textrecyclerview.R;
import org.faqrobot.textrecyclerview.ui.basefra.RxBaseFragment;

public class SinglePhotoViewFragment extends RxBaseFragment {


    public PhotoView photoView;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_single_photo_view;
    }

    @Override
    public void initViews(View view) {
        photoView = (PhotoView) view.findViewById(R.id.photoview1);
    }


}
