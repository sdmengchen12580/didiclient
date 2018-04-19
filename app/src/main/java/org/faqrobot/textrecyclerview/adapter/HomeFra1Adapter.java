package org.faqrobot.textrecyclerview.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.faqrobot.textrecyclerview.R;
import org.faqrobot.textrecyclerview.entity.ImgInfoBean;
import org.faqrobot.textrecyclerview.ui.act.PhotoViewActivity;
import org.faqrobot.textrecyclerview.view.RatioImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by 孟晨 on 2018/4/16.
 */

public class HomeFra1Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String textContents[] = {"我美不！", "难道我不美吗", "你真的不觉得我长的很漂亮吗？", "我好美！", "我好害羞呀！"};
    private List<ImgInfoBean> mList;
    private Context context;

    public HomeFra1Adapter(Context context, List<ImgInfoBean> list) {
        this.context = context;
        this.mList = list;
    }

    public void clearData() {
        if (mList.size() > 0 && mList != null) {
            mList.clear();
            notifyDataSetChanged();
        }
    }

    public void refreshData(List<ImgInfoBean> list) {
        if (list.size() > 0 && mList != null) {
            mList.clear();
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void addBottomData(List<ImgInfoBean> list) {
        if (list.size() > 0 && list != null) {
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.homefra1_singleitem, null);
        return new ImgsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        if (holder instanceof ImgsViewHolder) {
            TextView textView = ((ImgsViewHolder) holder).textView;
            RatioImageView imageView = ((ImgsViewHolder) holder).imageView;
            View view = ((ImgsViewHolder) holder).item;
            textView.setText(textContents[new Random().nextInt(textContents.length)]);
            Glide.with(context)
                    .load(mList.get(position).getImgUrl())
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(imageView)
                    .getSize((width, height) -> {
                        if (!view.isShown()) {
                            view.setVisibility(View.VISIBLE);
                        }
                    });
            imageView.setTag(R.string.app_name, mList.get(position));
            imageView.setOnClickListener(v -> {
                Intent intent=new Intent();
                intent.setClass(context, PhotoViewActivity.class);
                intent.putExtra("position", position);
                intent.putParcelableArrayListExtra("persons", (ArrayList<? extends Parcelable>) mList);
                context.startActivity(intent);
            });
            ViewCompat.setTransitionName(((ImgsViewHolder) holder).imageView, mList.get(position).getImgUrl());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    private class ImgsViewHolder extends RecyclerView.ViewHolder {
        public View item;
        public TextView textView;
        public RatioImageView imageView;

        public ImgsViewHolder(View itemView) {
            super(itemView);
            item = itemView;
            textView = (TextView) itemView.findViewById(R.id.item_title);
            imageView = (RatioImageView) itemView.findViewById(R.id.item_img);
            imageView.setOriginalSize(50, 50);
        }
    }
}
