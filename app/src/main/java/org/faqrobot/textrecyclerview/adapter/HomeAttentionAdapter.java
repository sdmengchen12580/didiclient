//package org.faqrobot.textrecyclerview.adapter;
//
//
//import android.Manifest;
//import android.app.Activity;
//import android.content.ClipboardManager;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.graphics.Bitmap;
//import android.graphics.drawable.Drawable;
//import android.support.v4.app.ActivityCompat;
//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.text.SpannableString;
//import android.text.Spanned;
//import android.text.TextPaint;
//import android.text.TextUtils;
//import android.text.method.LinkMovementMethod;
//import android.text.style.ClickableSpan;
//import android.text.style.ForegroundColorSpan;
//import android.util.TypedValue;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.ViewTreeObserver;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;
//import java.util.ArrayList;
//import butterknife.ButterKnife;
//import rx.android.schedulers.AndroidSchedulers;
//import rx.schedulers.Schedulers;
//
///**
// * Created by 88426 on 2017/3/20.
// */
//
//public class HomeAttentionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//
//    private Activity mActivity;
//    private List<RecommendDiaryEntity.DataBean> mDatas = new ArrayList<>();
//    private OnItemClickListener mOnItemClickListener;
//    private boolean isFooterVisible = false;
//    private ArrayList<InterestUserEntity.DataBean> mRecommendUserList = new ArrayList<>();
//    private int totalSize;
//    private static String[] PERMISSIONS_STORAGE = {
//            Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE
//    };
//    private SquareMoreDialog mSquareMoreDialog;
//    private ConfirmDialog mConfirmDialog;
//
//    private HomeRecommendUserAdapter mAdapter;
//    private Boolean mIsNeedGetHeight = true;
//    private boolean mIsUserId = false;
//
//    public HomeAttentionAdapter(Activity mContext) {
//        this.mActivity = mContext;
//        initRxBus();
//    }
//
//    private void initRxBus() {
//        RxBus.getInstance().toObserverable(CommonEvent.class)
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(this::refresh);
//        RxBus.getInstance().toObserverable(SavePicEvent.class)
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(this::save);
//    }
//
//    private void refresh(CommonEvent event) {
//        if (CommonEvent.UPDATE_ZAN_STATUS == event.eventCode) {
//            for (RecommendDiaryEntity.DataBean item : mDatas) {
//                if (event.para1.equals(item.diaryid)) {
//                    if (event.para3) {
//                        item.ispraise = true;
//                        item.praisenum++;
//                    } else {
//                        item.ispraise = false;
//                        item.praisenum--;
//                        if (item.praisenum < 0) {
//                            item.praisenum = 0;
//                        }
//                    }
//                }
//            }
//            notifyDataSetChanged();
//        }
//    }
//
//    private void save(SavePicEvent savePicEvent) {
//        if (savePicEvent.isSuccess) {
//            if (mSquareMoreDialog != null) {
//                CustomToask.showToast(mActivity.getString(R.string.save_success));
//                mSquareMoreDialog.dismiss();
//            }
//        }
//    }
//
//    public void setRecommendDiaryList(ArrayList<RecommendDiaryEntity.DataBean> datas) {
//        this.mDatas = datas;
//        if (mDatas.size() > 0) {
//            totalSize = mDatas.size() + 1;
//        } else {
//            totalSize = 0;
//        }
//        notifyDataSetChanged();
//    }
//
//    public void refreshRecommendDiaryList(RecommendDiaryEntity.DataBean bean) {
//        if (bean != null) {
//            ArrayList<RecommendDiaryEntity.DataBean> diaryList = new ArrayList<>();
//            diaryList.addAll(mDatas);
//            mDatas.clear();
//            mDatas.add(bean);
//            mDatas.addAll(diaryList);
//            notifyDataSetChanged();
//            totalSize = mDatas.size() + 2;
//        }
//    }
//
//    public void setFootViewVisiable(boolean visiable) {
//        isFooterVisible = visiable;
//    }
//
//    public void setRecommendUserList(ArrayList<InterestUserEntity.DataBean> recommendUserList) {
//        this.mRecommendUserList = recommendUserList;
//        if (mRecommendUserList.size() > 0) {
//            totalSize = totalSize + 1;
//        }
//        notifyDataSetChanged();
//    }
//
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = null;
//        RecyclerView.ViewHolder holder = null;
//        switch (viewType) {
//            case 0:
//                view = LayoutInflater.from(mActivity).inflate(R.layout.home_recommend_user_item, parent, false);
//                holder = new UserViewHolder(view);
//                break;
//            case 1:
//                view = LayoutInflater.from(mActivity).inflate(R.layout.home_attention_item, parent, false);
//                holder = new AttentionHolder(view);
//                break;
//            case 2:
//                view = LayoutInflater.from(mActivity).inflate(R.layout.no_more, parent, false);
//                holder = new FootViewHolder(view);
//                break;
//        }
//        return holder;
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        if (mRecommendUserList.size() > 0) {
//            if (position == 1) {
//                return 0;
//            } else if (position < mDatas.size() + 1) {
//                return 1;
//            } else {
//                return 2;
//            }
//        } else {
//            if (position < mDatas.size()) {
//                return 1;
//            } else {
//                return 2;
//            }
//        }
//    }
//
//
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        switch (getItemViewType(position)) {
//            case 0:
//                if (mAdapter == null) {
//                    UserViewHolder userViewHolder = (UserViewHolder) holder;
//                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
//                    linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//                    userViewHolder.recyclerView.setLayoutManager(linearLayoutManager);
//                    Drawable whiteDivider = mActivity.getResources().getDrawable(R.drawable.recycler_recommend_user_decoration);
//                    userViewHolder.recyclerView.addItemDecoration(new GridItemDecoration(mActivity, false, whiteDivider));
//                    mAdapter = new HomeRecommendUserAdapter(mActivity, mRecommendUserList);
//                    userViewHolder.recyclerView.setAdapter(mAdapter);
//                    userViewHolder.recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
//                        @Override
//                        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                            super.onScrollStateChanged(recyclerView, newState);
//                            if ((newState == RecyclerView.SCROLL_STATE_DRAGGING) || (newState == RecyclerView.SCROLL_STATE_SETTLING)) {
//                                recyclerView.getParent().requestDisallowInterceptTouchEvent(true);
//                            }
//                        }
//                    });
//                } else {
//                    mAdapter.notifyDataSetChanged();
//                }
//                break;
//            case 1:
//                int attentionPosition;
//                if (mRecommendUserList.size() > 0) {
//                    if (position < 1) {
//                        attentionPosition = position;
//                    } else {
//                        attentionPosition = position - 1;
//                    }
//                } else {
//                    attentionPosition = position;
//                }
//                RecommendDiaryEntity.DataBean bean = mDatas.get(attentionPosition);
//                AttentionHolder attentionHolder = (AttentionHolder) holder;
//                ImageUtils.showAvatar(mActivity, bean.userimg, attentionHolder.user_img);
//                attentionHolder.username.setText(bean.username);
//                attentionHolder.time.setText(Utils.getTime(Utils.getTimeStamp2(bean.addtime)));
//                attentionHolder.title.setText(bean.title);
//
//                if (bean.praiseList != null && bean.praiseList.size() > 0) {
//                    attentionHolder.tvLikeCount.setVisibility(View.VISIBLE);
//                } else {
//                    attentionHolder.tvLikeCount.setVisibility(View.GONE);
//                }
//                attentionHolder.tvLikeCount.setText(bean.praisenum + mActivity.getString(R.string.like1));
//                setPraiseUI(attentionHolder, bean);
//                // 头像
//
//                AttentionAvatarAdapter attentionAvatarAdapter = new AttentionAvatarAdapter(mActivity, bean.praiseList);
//                attentionHolder.rvAvatar.setLayoutManager(new WrapContentLinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));
//                attentionHolder.rvAvatar.setAdapter(attentionAvatarAdapter);
//                attentionHolder.rvAvatar.setItemAnimator(new DefaultItemAnimator());
//                attentionHolder.llLike.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//                    @Override
//                    public void onGlobalLayout() {
//                        if (position == 0 && mIsNeedGetHeight) {
//                            mIsNeedGetHeight = false;
//                            if (bean.praiseList != null && bean.praiseList.size() > 0) {
//                                if (!TextUtils.isEmpty(bean.userid) && bean.userid.equals(SPUtils.getUserId())) {
//                                    RxBus.getInstance().post(new HomeAttentionGuideEvent(true, true,
//                                            attentionHolder.root_layout.getHeight(), false));
//                                } else {
//                                    RxBus.getInstance().post(new HomeAttentionGuideEvent(true, false,
//                                            attentionHolder.root_layout.getHeight(), false));
//                                }
//                            } else {
//                                if (!TextUtils.isEmpty(bean.userid) && bean.userid.equals(SPUtils.getUserId())) {
//                                    RxBus.getInstance().post(new HomeAttentionGuideEvent(false, true, 0, false));
//                                } else {
//                                    RxBus.getInstance().post(new HomeAttentionGuideEvent(false, false, 0, false));
//                                }
//                            }
//                        }
//                    }
//                });
//
//                attentionHolder.tvLikeCount.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        mOnItemClickListener.onCareItemClick(mDatas.get(attentionPosition).diaryid);
//
//                    }
//                });
//                attentionAvatarAdapter.setOnItemClickListener(new AttentionAvatarAdapter.OnItemClickListener() {
//                    @Override
//                    public void onAvatarItemClick() {
//                        mOnItemClickListener.onCareItemClick(mDatas.get(attentionPosition).diaryid);
//                    }
//                });
//
//                // 标签
//                if (bean.tasteList != null) {
//                    attentionHolder.rvTag.setVisibility(View.VISIBLE);
//                    AttentionTagAdapter attentionTagAdapter = new AttentionTagAdapter(mActivity, bean.tasteList);
//                    attentionHolder.rvTag.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));
//                    attentionHolder.rvTag.setAdapter(attentionTagAdapter);
//                } else {
//                    attentionHolder.rvTag.setVisibility(View.GONE);
//                }
//                String diaryUrl;
//                if (bean.pagewidth == 0 || bean.pageheight == 0) { // 加载原图
//                    diaryUrl = Constants.LEKU_REFERER + "/" + bean.renderimg;
//                } else { // 截取加载
//                    ViewGroup.LayoutParams lp = attentionHolder.diary_img.getLayoutParams();
//                    float f = bean.pagewidth / (float) lp.width;
//                    diaryUrl = Constants.LEKU_REFERER + "/" + bean.renderimg
//                            + QiniuUtils.getNewCorpParams(bean.pagewidth, (int) (lp.height * f), DiaryApplication.mSmallImgParam);
//                }
//                if (!TextUtils.isEmpty(bean.userid) && bean.userid.equals(SPUtils.getUserId())) {
//                    attentionHolder.printImg.setVisibility(View.VISIBLE);
//                } else {
//                    attentionHolder.printImg.setVisibility(View.INVISIBLE);
//                }
//                Glide.with(mActivity)
//                        .load(diaryUrl)
//                        .transform(new GlideRoundTransform(mActivity, 5))
//                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                        .dontAnimate()
//                        .into(attentionHolder.diary_img);
//                initListener(attentionHolder, bean, attentionAvatarAdapter);
//                setCommentData(attentionHolder, bean);
//                break;
//            case 2:
//                FootViewHolder footViewHolder = (FootViewHolder) holder;
//                if (isFooterVisible) {
//                    footViewHolder.no_more.setVisibility(View.VISIBLE);
//                } else {
//                    footViewHolder.no_more.setVisibility(View.GONE);
//                }
//                break;
//        }
//    }
//
//    /**
//     * 收藏
//     *
//     * @author jqt
//     * create at 2017/8/2 14:03
//     */
//    private void collect(RecommendDiaryEntity.DataBean bean, AttentionHolder attentionHolder) {
//        if (Utils.isNotLogin()) {
//            CustomToask.showToast(mActivity.getString(R.string.please_login));
//            mActivity.startActivity(new Intent(mActivity, LekuLoginActivity.class));
//            return;
//        }
//        RetrofitHelper.getHomeApi().collect(bean.diaryid, bean.isfavorite ? "4" : "0").subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(serverResponseEntity -> {
//                    if ("0".equals(serverResponseEntity.reCode)) {
//                        bean.isfavorite = !bean.isfavorite;
//                        if (bean.isfavorite) {
//                            bean.favoritenum++;
//                        } else {
//                            bean.favoritenum--;
//                            if (bean.favoritenum < 0) {
//                                bean.favoritenum = 0;
//                            }
//                        }
//                        CustomToask.showToast(bean.isfavorite ? mActivity.getString(R.string.collect_success) :
//                                mActivity.getString(R.string.cancel_collect_success));
//                        RxBus.getInstance().post(new CommonEvent(CommonEvent.UPDATE_COLLECT_STATUS,
//                                "update_collect_status", bean.diaryid, bean.isfavorite));
//                        RxBus.getInstance().post(new UpdateUserCenterEvent());
//                    } else {
//                        CustomToask.showToast(bean.isfavorite ? mActivity.getString(R.string.cancel_collect_fail) :
//                                mActivity.getString(R.string.collect_fail));
//                    }
//                }, throwable -> {
//                    throwable.printStackTrace();
//                });
//    }
//
//    private void setCommentData(AttentionHolder holder, RecommendDiaryEntity.DataBean bean) {
//        if (bean.commentlist == null || bean.commentlist.size() == 0) {
//            holder.llCommentLayout.setVisibility(View.GONE);
//            return;
//        }
//        holder.llCommentLayout.setVisibility(View.VISIBLE);
//        holder.llCommentLayout.removeAllViews();
//        for (int i = 0; i < bean.commentlist.size(); i++) {
//            // 添加每条评论textview
//            holder.llCommentLayout.addView(getCommentTextView(bean.commentlist.get(i)));
//            if (i == 2) {
//                // 添加查看所有评论textview
//                holder.llCommentLayout.addView(getAllCommentTextView(bean));
//                return;
//            }
//        }
//    }
//
//    private TextView getAllCommentTextView(RecommendDiaryEntity.DataBean bean) {
//        TextView textView = (TextView) View.inflate(mActivity, R.layout.textview_attention_item_comment, null);
//        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
//        textView.setText(mActivity.getString(R.string.look_all) + Utils.getStringNum(bean.commentnum + "")
//                + mActivity.getString(R.string.num_reply));
//        textView.setTextColor(mActivity.getResources().getColor(R.color.second_page_textcolor3));
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mActivity, SquareDiaryActivity.class);
//                intent.putExtra("diaryid", bean.diaryid);
//                intent.putExtra("tag", bean.cate);
//                intent.putExtra("startById", true);
//                intent.putExtra("selectComment", true);
//                mActivity.startActivity(intent);
//            }
//        });
//        return textView;
//    }
//
//    private TextView getCommentTextView(RecommendDiaryEntity.DataBean.CommentlistBean bean) {
//        TextView textView = (TextView) View.inflate(mActivity, R.layout.textview_attention_item_comment, null);
//        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
//        textView.setLineSpacing(6f, 1.0f);
//        try {
//            String replayName = bean.username;
//            if (replayName == null) {
//                replayName = "";
//            }
//            String content = replayName + ": " + bean.content;
//            SpannableString ss = new SpannableString(content);
//            ForegroundColorSpan colorSpan = new ForegroundColorSpan(mActivity.getResources().getColor(R.color.app_theme));
//            ss.setSpan(colorSpan, 0, replayName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            ss.setSpan(new ClickableSpan() {
//                           @Override
//                           public void onClick(View widget) {
//                               Intent intent = new Intent(mActivity, UserCenterActivityNew.class);
//                               intent.putExtra("userid", bean.userid);
//                               mActivity.startActivity(intent);
//                           }
//
//                           @Override
//                           public void updateDrawState(TextPaint ds) {
//                               //                           ds.setColor(Color.RED);//文本颜色
//                               ds.setUnderlineText(false);//是否有下划线
//                               //                           ds.bgColor = Color.WHITE;//背景颜色
//                           }
//                       }, 0, replayName.length(),
//                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            textView.setText(ss);
//            textView.setMovementMethod(LinkMovementMethod.getInstance());//不设置 没有点击事件
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return textView;
//    }
//
//    private void setPraiseUI(AttentionHolder attentionHolder, RecommendDiaryEntity.DataBean bean) {
//        if (bean.ispraise) {
//            attentionHolder.ivPraise.setImageResource(R.mipmap.home_attention_liked);
//        } else {
//            attentionHolder.ivPraise.setImageResource(R.mipmap.home_attention_like);
//        }
//    }
//
//    private void initListener(AttentionHolder attentionHolder, RecommendDiaryEntity.DataBean bean,
//                              AttentionAvatarAdapter attentionAvatarAdapter) {
//        attentionHolder.username.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mActivity, UserCenterActivityNew.class);
//                intent.putExtra("userid", bean.userid);
//                mActivity.startActivity(intent);
//            }
//        });
//        attentionHolder.user_img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mActivity, UserCenterActivityNew.class);
//                intent.putExtra("userid", bean.userid);
//                mActivity.startActivity(intent);
//            }
//        });
//        attentionHolder.root_layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mActivity, SquareDiaryActivity.class);
//                intent.putExtra("diaryid", bean.diaryid);
//                intent.putExtra("tag", bean.cate);
//                intent.putExtra("startById", true);
//                mActivity.startActivity(intent);
//            }
//        });
//        // 分享按钮
//        attentionHolder.more_img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getPermission(bean);
//            }
//        });
//        // 点赞按钮
//        attentionHolder.ivPraise.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String status = bean.ispraise ? "4" : "0";
//                RetrofitHelper.getHomeApi().zan(bean.diaryid, status).subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(serverResponseEntity -> {
//                            if ("0".equals(serverResponseEntity.reCode)) {
//                                bean.ispraise = !bean.ispraise;
//                                if (bean.ispraise) {
//                                    mOnItemClickListener.onLikeItemClick();
//                                    bean.praisenum++;
//                                    attentionHolder.tvLikeCount.setText(bean.praisenum + mActivity.getString(R.string.like1));
//                                    bean.praiseList.add(0, new RecommendDiaryEntity.DataBean.PraiseListBean(false,
//                                            SPUtils.getUserId(), SPUtils.getString(Account.PREFS_USER_ICON_URL, ""),
//                                            SPUtils.getString(Account.PREFS_USER_NICKNAME, "")));
//                                    attentionAvatarAdapter.notifyItemInserted(0);
//                                } else {
//                                    bean.praisenum--;
//                                    attentionHolder.tvLikeCount.setText(bean.praisenum + mActivity.getString(R.string.like1));
//                                    if (bean.praisenum < 0) {
//                                        bean.praisenum = 0;
//                                    }
//                                    for (int i = 0; i < bean.praiseList.size(); i++) {
//                                        if (!TextUtils.isEmpty(SPUtils.getUserId()) &&
//                                                SPUtils.getUserId().equals(bean.praiseList.get(i).userid)) {
//                                            bean.praiseList.remove(bean.praiseList.get(i));
//                                            attentionAvatarAdapter.notifyItemRemoved(i);
//                                            break;
//                                        }
//                                    }
//                                }
//                                if (bean.praiseList != null && bean.praiseList.size() > 0) {
//                                    attentionHolder.tvLikeCount.setVisibility(View.VISIBLE);
//                                } else {
//                                    attentionHolder.tvLikeCount.setVisibility(View.GONE);
//                                }
//                                attentionHolder.ivPraise.setImageResource(bean.ispraise ? R.mipmap.home_attention_liked
//                                        : R.mipmap.home_attention_like);
//                            } else {
//                                CustomToask.showToast(mActivity.getString(R.string.net_useless));
//                            }
//                        }, throwable -> {
//                            throwable.printStackTrace();
//                            CustomToask.showToast(mActivity.getString(R.string.net_useless));
//                        });
//            }
//        });
//        // 打印按钮
//        attentionHolder.printImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mActivity.startActivity(new Intent(mActivity, ShopHomeActivity.class));
//            }
//        });
//
//    }
//
//    /**
//     * 华为6.0手机没有权限，需要申请权限
//     *
//     * @author jqt
//     * create at 2017/4/10 19:40
//     */
//    private void getPermission(RecommendDiaryEntity.DataBean bean) {
//        // 是否添加权限
//        int permission = ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        //如果没有权限,申请权限
//        if (permission != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(mActivity, PERMISSIONS_STORAGE, 11);
//        } else {
//            showMoreDialog(bean);
//        }
//    }
//
//    private void showMoreDialog(RecommendDiaryEntity.DataBean bean) {
//        int randomStr = Utils.getRandom(3);
//        String SHARE_TITLE = "『" + bean.title + "』";
//        UMImage umImage = new UMImage(mActivity, "http://o9vs0rb1z.qnssl.com/" + Utils.getImage(bean.renderimg)
//                + "?imageMogr2/auto-orient/crop/x1080/quality/90");
//        UMImage sinaImage = new UMImage(mActivity, "http://o9vs0rb1z.qnssl.com/" + Utils.getImage(bean.renderimg));
//        String SHARE_CONTENT = mActivity.getResources().getString(Utils.getRandomShareContent(randomStr));
//        String SHARE_URL = Constants.DIARY_SHARE_HTML + "?id=" + bean.diaryid +
//                "&os=android&pkgname=" + mActivity.getPackageName();
//
//        UMWeb web = new UMWeb(SHARE_URL);
//        web.setTitle(SHARE_TITLE);//标题
//        web.setThumb(umImage);  //缩略图
//        web.setDescription(SHARE_CONTENT);//描述
//
//        UMWeb wxweb = new UMWeb(SHARE_URL);
//        wxweb.setTitle(SHARE_TITLE + SHARE_CONTENT);//标题
//        wxweb.setThumb(umImage);  //缩略图
//        wxweb.setDescription(SHARE_CONTENT);//描述
//        if (!TextUtils.isEmpty(SPUtils.getUserId())) {
//            if (SPUtils.getUserId().equals(bean.userid)) {
//                mIsUserId = true;
//            } else {
//                mIsUserId = false;
//            }
//        } else {
//            mIsUserId = false;
//        }
//        mSquareMoreDialog = new SquareMoreDialog(mActivity, mIsUserId);
//        CommentUtils mCommentUtils = new CommentUtils(mActivity);
//        mSquareMoreDialog.setClicklistener(new SquareMoreDialog.ClickListenerInterface() {
//            @Override
//            public void shareWxCircle() {
//                new ShareAction(mActivity).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
//                        .withMedia(wxweb).setCallback(new UMShareListener() {
//                    @Override
//                    public void onStart(SHARE_MEDIA share_media) {
//                        StatisticsUtils.StatisticsFour("sharediary", bean.diaryid, 1);
//                    }
//
//                    @Override
//                    public void onResult(SHARE_MEDIA share_media) {
//                        CustomToask.showToast(mActivity.getString(R.string.share_success));
//                        mSquareMoreDialog.dismiss();
//                        ShareUtils.shareScore(bean.diaryid, "diary");
//                    }
//
//                    @Override
//                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
//                        CustomToask.showToast(mActivity.getString(R.string.share_fail));
//                    }
//
//                    @Override
//                    public void onCancel(SHARE_MEDIA share_media) {
//                        CustomToask.showToast(mActivity.getString(R.string.share_cancel));
//                    }
//                }).share();
//            }
//
//            @Override
//            public void shareQQZone() {
//                new ShareAction(mActivity).setPlatform(SHARE_MEDIA.QZONE)
//                        .withMedia(web).setCallback(new UMShareListener() {
//                    @Override
//                    public void onStart(SHARE_MEDIA share_media) {
//
//                    }
//
//                    @Override
//                    public void onResult(SHARE_MEDIA share_media) {
//                        CustomToask.showToast(mActivity.getString(R.string.share_success));
//                        mSquareMoreDialog.dismiss();
//                        ShareUtils.shareScore(bean.diaryid, "diary");
//                    }
//
//                    @Override
//                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
//                        CustomToask.showToast(mActivity.getString(R.string.share_fail));
//                    }
//
//                    @Override
//                    public void onCancel(SHARE_MEDIA share_media) {
//                        CustomToask.showToast(mActivity.getString(R.string.share_cancel));
//                    }
//                }).share();
//            }
//
//            @Override
//            public void shareWx() {
//                new ShareAction(mActivity).setPlatform(SHARE_MEDIA.WEIXIN)
//                        .withMedia(web).setCallback(new UMShareListener() {
//                    @Override
//                    public void onStart(SHARE_MEDIA share_media) {
//
//                    }
//
//                    @Override
//                    public void onResult(SHARE_MEDIA share_media) {
//                        CustomToask.showToast(mActivity.getString(R.string.share_success));
//                        mSquareMoreDialog.dismiss();
//                        ShareUtils.shareScore(bean.diaryid, "diary");
//                    }
//
//                    @Override
//                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
//                        CustomToask.showToast(mActivity.getString(R.string.share_fail));
//                    }
//
//                    @Override
//                    public void onCancel(SHARE_MEDIA share_media) {
//                        CustomToask.showToast(mActivity.getString(R.string.share_cancel));
//                    }
//                }).share();
//            }
//
//            @Override
//            public void shareQQ() {
//                new ShareAction(mActivity).setPlatform(SHARE_MEDIA.QQ)
//                        .withMedia(web).setCallback(new UMShareListener() {
//                    @Override
//                    public void onStart(SHARE_MEDIA share_media) {
//
//                    }
//
//                    @Override
//                    public void onResult(SHARE_MEDIA share_media) {
//                        CustomToask.showToast(mActivity.getString(R.string.share_success));
//                        mSquareMoreDialog.dismiss();
//                        ShareUtils.shareScore(bean.diaryid, "diary");
//                    }
//
//                    @Override
//                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
//                        CustomToask.showToast(mActivity.getString(R.string.share_fail));
//                    }
//
//                    @Override
//                    public void onCancel(SHARE_MEDIA share_media) {
//                        CustomToask.showToast(mActivity.getString(R.string.share_cancel));
//                    }
//                }).share();
//            }
//
//            @Override
//            public void shareSina() {
//                new ShareAction(mActivity).setPlatform(SHARE_MEDIA.SINA)
//                        .withText(SHARE_TITLE + SHARE_CONTENT + SHARE_URL)
//                        .withMedia(sinaImage).setCallback(new UMShareListener() {
//                    @Override
//                    public void onStart(SHARE_MEDIA share_media) {
//
//                    }
//
//                    @Override
//                    public void onResult(SHARE_MEDIA share_media) {
//                        CustomToask.showToast(mActivity.getString(R.string.share_success));
//                        mSquareMoreDialog.dismiss();
//                        ShareUtils.shareScore(bean.diaryid, "diary");
//                    }
//
//                    @Override
//                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
//                        CustomToask.showToast(mActivity.getString(R.string.share_fail));
//                    }
//
//                    @Override
//                    public void onCancel(SHARE_MEDIA share_media) {
//                        CustomToask.showToast(mActivity.getString(R.string.share_cancel));
//                    }
//                }).share();
//            }
//
//            @Override
//            public void savePic() {
//                CustomToask.showToast(mActivity.getString(R.string.saving));
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Bitmap bitmap = Utils.Bytes2Bitmap(Utils.getHttpByteRefer(DiaryApplication.referer + "/"
//                                + bean.renderimg));
//                        if (bitmap != null) {
//                            bitmap = BitmapUtils.createWatermarkBitmap(bitmap);
//                            Utils.savePicture(mActivity, bitmap);
//                        } else {
//                            CustomToask.showToast(mActivity.getString(R.string.save_fail));
//                        }
//                    }
//                }).start();
//            }
//
//            @Override
//            public void deleteDiary() {
//                mSquareMoreDialog.dismiss();
//                // 删除在线手帐
//                mConfirmDialog = new ConfirmDialog(mActivity, mActivity.getString(R.string.is_delete_diary),
//                        mActivity.getString(R.string.cancel), mActivity.getString(R.string.confirm));
//                mConfirmDialog.show();
//                mConfirmDialog.setClicklistener(new ConfirmDialog.ClickListenerInterface() {
//                    @Override
//                    public void doConfirm() {
//                        RetrofitHelper.getDiaryApi().deleteDiary(bean.diaryid).subscribeOn(Schedulers.io())
//                                .observeOn(AndroidSchedulers.mainThread())
//                                .subscribe(serverResponseEntity -> {
//                                    if ("0".equals(serverResponseEntity.reCode)) {
//                                        CustomToask.showToast(mActivity.getString(R.string.delete_success));
//                                        RxBus.getInstance().post(new CommonEvent(CommonEvent.UPDATE_DIARY, "update_diary", "", ""));
//                                        RxBus.getInstance().post(new DiaryBookListAddDiaryEvent());
//                                        RxBus.getInstance().post(new DiaryBookPageUpdateEvent());
//                                        RxBus.getInstance().post(new HomeBtnSecondEvent(0));
//                                        RxBus.getInstance().post(new RefreshHomeAttentionEvent(bean));
//                                        mConfirmDialog.dismiss();
//                                    }
//                                }, throwable -> {
//                                    CustomToask.showToast(mActivity.getString(R.string.delete_fail));
//                                    mConfirmDialog.dismiss();
//                                });
//                    }
//
//                    @Override
//                    public void doCancel() {
//                        mConfirmDialog.dismiss();
//                    }
//                });
//            }
//
//
//            @Override
//            public void copyUrl() {
//                ClipboardManager cm = (ClipboardManager) mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
//                cm.setText(SHARE_URL);
//                CustomToask.showToast(mActivity.getString(R.string.share_link_copy_success));
//            }
//
//            @Override
//            public void jubao() {
//                JubaoDialog jubaoDialog = new JubaoDialog(mActivity);
//                jubaoDialog.setClicklistener(new JubaoDialog.ClickListenerInterface() {
//                    @Override
//                    public void cancel() {
//                        jubaoDialog.dismiss();
//                    }
//
//                    @Override
//                    public void confirm(String reason) {
//                        mCommentUtils.jubao("diary", bean.diaryid, reason);
//                        jubaoDialog.dismiss();
//                    }
//                });
//                jubaoDialog.show();
//                mSquareMoreDialog.dismiss();
//            }
//        });
//        mSquareMoreDialog.show();
//    }
//
//    @Override
//    public int getItemCount() {
//        return totalSize;
//    }
//
//    public void setOnItemClickListener(OnItemClickListener listener) {
//        this.mOnItemClickListener = listener;
//    }
//
//    public interface OnItemClickListener {
//        void onLikeItemClick();
//
//        void onCareItemClick(String diaryId);
//    }
//
//
//    public class AttentionHolder extends RecyclerView.ViewHolder {
//        @Bind(R.id.root_layout)
//        LinearLayout root_layout;
//        @Bind(R.id.user_img)
//        ImageView user_img;
//        @Bind(R.id.username)
//        TextView username;
//        @Bind(R.id.time)
//        TextView time;
//        @Bind(R.id.title)
//        TextView title;
//        @Bind(R.id.diary_img)
//        ImageView diary_img;
//        @Bind(R.id.more_img)
//        ImageView more_img;
//        @Bind(R.id.print_img)
//        ImageView printImg;
//        @Bind(R.id.zan_image)
//        ImageView ivPraise;
//        @Bind(R.id.ll_comment_layout)
//        LinearLayout llCommentLayout;
//        @Bind(R.id.rv_tag)
//        RecyclerView rvTag;
//        @Bind(R.id.tv_like_count)
//        TextView tvLikeCount;
//        @Bind(R.id.rv_avatar)
//        RecyclerView rvAvatar;
//        @Bind(R.id.ll_like)
//        LinearLayout llLike;
//
//
//        public AttentionHolder(View view) {
//            super(view);
//            ButterKnife.bind(this, view);
//        }
//    }
//
//    public class FootViewHolder extends RecyclerView.ViewHolder {
//        @Bind(R.id.no_more)
//        TextView no_more;
//
//        public FootViewHolder(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//        }
//    }
//
//    public class UserViewHolder extends RecyclerView.ViewHolder {
//        @Bind(R.id.recyclerView)
//        RecyclerView recyclerView;
//
//        public UserViewHolder(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//        }
//    }
//}
