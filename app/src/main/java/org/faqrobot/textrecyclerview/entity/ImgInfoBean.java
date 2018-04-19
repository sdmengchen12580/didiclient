package org.faqrobot.textrecyclerview.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 孟晨 on 2018/4/18.
 */

public class ImgInfoBean implements Parcelable {

    private String imgUrl;
    private String imgContent;

    public ImgInfoBean(String imgUrl,String imgContent) {
        this.imgUrl=imgUrl;
        this.imgContent=imgContent;
    }

    public ImgInfoBean(Parcel in) {
        imgUrl = in.readString();
        imgContent = in.readString();
    }

    public static final Creator<ImgInfoBean> CREATOR = new Creator<ImgInfoBean>() {
        @Override
        public ImgInfoBean createFromParcel(Parcel in) {
            return new ImgInfoBean(in);
        }

        @Override
        public ImgInfoBean[] newArray(int size) {
            return new ImgInfoBean[size];
        }
    };

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgContent() {
        return imgContent;
    }

    public void setImgContent(String imgContent) {
        this.imgContent = imgContent;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imgUrl);
        dest.writeString(imgContent);
    }
}
