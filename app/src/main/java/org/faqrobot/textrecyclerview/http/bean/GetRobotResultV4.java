package org.faqrobot.textrecyclerview.http.bean;

import java.util.List;

/**
 * Created by yunwen on 2017/12/6.
 */

public class GetRobotResultV4 {

    /**
     * data :
     * {"hitQuestion":"退补电量电费计算？",
     * "question":"退补电量电费",
     * <p>
     * "robotReply":[{"msgtype":"text","text":
     * {"content":"居民阶梯电价客户的故障退补电费计算应区分退补电量是本结算年用电量，还是退补以前的电量。
     * \n 您是不是还想问以下问题：
     * \n21.退补电量电费什么时候到账;
     * \n22.退补电量电费流程.
     * \n 以上答案是否解决了您的问题? 回复：A：解决 B:未解决"}}],
     * "semantic":null,
     * "serviceType":"robot"}
     * <p>
     * status : 0
     * gettaskfra_img_clock_nopressed : 请求成功
     * time : 1498187332398
     * tspan : 2
     */

    private DataBean data;
    private int status;
    private String message;
    private String time;
    private int tspan;


    //描述是发送方还是接收方
    private int direct;

    //设置不同类型的不同数据(富文本使用)
    private List<String> urlRichLink;
    private List<String> urlRichImage;
    private List<String> urlRichText;
    private String urlVoice;
    private String urlImage;

    //是否在播放
    private boolean isPlaying;

    public DataBean getData() {
        return data;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTspan() {
        return tspan;
    }

    public void setTspan(int tspan) {
        this.tspan = tspan;
    }


    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public List<String> getUrlRichLink() {
        return urlRichLink;
    }

    public void setUrlRichLink(List<String> urlRichLink) {
        this.urlRichLink = urlRichLink;
    }

    public List<String> getUrlRichImage() {
        return urlRichImage;
    }

    public void setUrlRichImage(List<String> urlRichImage) {
        this.urlRichImage = urlRichImage;
    }

    public List<String> getUrlRichText() {
        return urlRichText;
    }

    public void setUrlRichText(List<String> urlRichText) {
        this.urlRichText = urlRichText;
    }

    public String getUrlVoice() {
        return urlVoice;
    }

    public void setUrlVoice(String urlVoice) {
        this.urlVoice = urlVoice;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    @Override
    public String toString() {
        return "GetRobotResultV4{" +
                "data=" + data +
                ", status=" + status +
                ", gettaskfra_img_clock_nopressed='" + message + '\'' +
                ", time='" + time + '\'' +
                ", tspan=" + tspan +
                ", direct=" + direct +
                ", urlRichLink=" + urlRichLink +
                ", urlRichImage=" + urlRichImage +
                ", urlRichText=" + urlRichText +
                ", urlVoice='" + urlVoice + '\'' +
                ", urlImage='" + urlImage + '\'' +
                ", isPlaying=" + isPlaying +
                '}';
    }
}
