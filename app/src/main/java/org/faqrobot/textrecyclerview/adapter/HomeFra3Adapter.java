package org.faqrobot.textrecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.faqrobot.textrecyclerview.R;
import org.faqrobot.textrecyclerview.entity.VideoBean;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class HomeFra3Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<VideoBean> mList = new ArrayList<>();
    public static final String TAG = "JieCaoVideoPlayer";
    public JCVideoPlayerStandard jcVideoPlayer;

    public HomeFra3Adapter(Context context, List<VideoBean> list) {
        this.context = context;
        mList = list;
    }

    public void refreshData(List<VideoBean> list) {
        if (mList.size() > 0 && mList != null) {
            mList.clear();
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void addBottomData(List<VideoBean> list) {
        if (mList.size() > 0 && mList != null) {
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_videoview, null);
        return new VideoHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        if (holder instanceof VideoHolder) {
            jcVideoPlayer = ((VideoHolder) holder).jcVideoPlayer;
            jcVideoPlayer.backButton.setVisibility(View.INVISIBLE);
            jcVideoPlayer.setUp(
                    ((VideoBean) this.mList.get(position)).getVideoUrl(),
                    JCVideoPlayer.SCREEN_LAYOUT_LIST, //选择类型
                    ((VideoBean) this.mList.get(position)).getVideoTitle());
            Glide.with(context.getApplicationContext())
                    .load(mList.get(position).getImgUrl())
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(jcVideoPlayer.thumbImageView);
        }
    }

    public long getItemId(int position) {
        return (long) position;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    private class VideoHolder extends RecyclerView.ViewHolder {

        JCVideoPlayerStandard jcVideoPlayer;

        public VideoHolder(View itemView) {
            super(itemView);
            jcVideoPlayer = (JCVideoPlayerStandard) itemView.findViewById(R.id.videoplayer);
        }
    }

}
