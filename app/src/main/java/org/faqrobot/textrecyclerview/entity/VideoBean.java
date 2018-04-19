package org.faqrobot.textrecyclerview.entity;

public class VideoBean {
    private String imgUrl;
    private String videoTitle;
    private String videoUrl;

    public VideoBean(String videoUrl, String videoTitle, String imgUrl) {
        this.videoUrl = videoUrl;
        this.videoTitle = videoTitle;
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public VideoBean setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public String getVideoUrl() {
        return this.videoUrl;
    }

    public VideoBean setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public String getVideoTitle() {
        return this.videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }
}
