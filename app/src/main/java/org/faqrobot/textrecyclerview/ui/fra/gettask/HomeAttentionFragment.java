package org.faqrobot.textrecyclerview.ui.fra.gettask;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import org.faqrobot.textrecyclerview.R;
import org.faqrobot.textrecyclerview.ui.fra.basefra.RxBaseFragment;
import org.faqrobot.textrecyclerview.view.LikeAnimView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeAttentionFragment extends RxBaseFragment {

    private XRecyclerView mRecyclerview;
    private LikeAnimView mLikeAnimView;

    public static HomeAttentionFragment newInstance() {
        HomeAttentionFragment fragment = new HomeAttentionFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_attention;
    }

    @Override
    public void initViews(View view) {
//        mLikeAnimView = (LikeAnimView) view.findViewById(R.id.like_view);
//        mRecyclerview = (XRecyclerView) view.findViewById(R.id.recyclerview);
//
//        mRecyclerview.setPullRefreshEnabled(true);
//        mRecyclerview.setLoadingMoreEnabled(true);
//        mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

//        mHomeAttentionAdapter = new HomeAttentionAdapter(mContext);
//        mRecyclerview.setAdapter(mHomeAttentionAdapter);
//        mRecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
//            @Override
//            public void onRefresh() {
//                if (TextUtils.isEmpty(mUserid)) {
//                    mEmptyLayout.setErrorType(EmptyLayout.NO_LOGIN);
//                } else {
//                    page = 1;
//                    userPage = 1;
//                    getDiaryData();
//                }
//            }
//
//            @Override
//            public void onLoadMore() {
//                page++;
//                getDiaryData();
//            }
//        });
//        mHomeAttentionAdapter.setOnItemClickListener(new HomeAttentionAdapter.OnItemClickListener() {
//            @Override
//            public void onLikeItemClick() {
//                // 因为有红点,先隐藏掉了
//                if (mLikeAnimView.getVisibility() == View.INVISIBLE)
//                    mLikeAnimView.setVisibility(View.VISIBLE);
//                if (mLikeAnimView.a()) {
//                    return;
//                }
//                mLikeAnimView.b();
//            }
//
//            @Override
//            public void onCareItemClick(String diaryId) {
//                Intent intent = new Intent(mContext, LikeActivity.class);
//                intent.putExtra("diaryid", diaryId);
//                mContext.startActivity(intent);
//            }
//        });
    }

}
