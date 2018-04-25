package org.faqrobot.textrecyclerview.config;

/**
 * Created by 孟晨 on 2018/4/12.
 */

public class MData {

    public static final int HANDLER_MESSAGE_WHAT = 1;
    public static final String IMGURL = "http://ovuwadagy.bkt.clouddn.com/homeact_img";


    public static final int STATE_SPECK=0;
    public static final int STATE_LISTENERING=1;
    public static final int STATE_IS_WEBVIEW_OPEN = 2;
    public static final int STATE_IS_CLICK_SINGLE_IMAGE = 3;
    public static final int STATE_IS_DEAD = 4;
    public static final int STATE_IS_VIDEO_PLAY = 6;
    public static final int STATE_IS_VOICE_PLAY = 7;
    public static final int STATE_IS_BYEBYE = 9;
    public static final int STATE_IS_VIDEO_NEED_REPLAY = 10;
    public static final int STATE_IS_NEWSIMAGE_IS_CLICK = 11;
    public static final int STATE_IS_CHANGE_TO_PEOPLE = 13;
    public static final int STATE_IS_ARITIFICIAL_AND_IDEL = 14;
    public static final int STATE_IS_CHANGE_TO_PEOPLE_AND_STATE_IS_RECODER = 15;
    public static final int STATE_IS_ARITIFICIAL_AND_SPECK = 16;
    public static final int STATE_IS_ARITIFICIAL_AND_SPECK_NO_IDEL = 17;
    public static final int STATE_IS_ARITIFICIAL_AND_JUDGE_RESTART_ARITIFICIAL = 18;
    public static final int STATE_IS_CHANGE_TO_PEOPLE_AND_STATE_IS_VIDEO = 19;

    public static final String HELLOWORD = "您好，云问网络科技有限公司欢迎你的到来。";
    //云知声
    public static final String yunzhisheng_appKey = "lgz2vvmkt67zddphtryfrqv6m2xzganhcntx6my3";
    public static final String yunzhisheng_secret = "0fb999dc0f48be0a599de765c482ae84";

    //主机地址（可变更）
    public static final String HOSTNAME = "http://v4.faqrobot.net/";
    //设置客户clientId
    public static final String CLIENDID = "5255525611";
    //设置客户sourceId
    public static final int SOURCEID = 9;
    //设置机器人问题类型
    public static final String QUESTION = "aq";
    //设置机器人appid
    public static final String APPID = "yYpPVnoDpvibxwQYHM";
    //设置机器人sercet
    public static final String SECRET = "wrbpCSrvgl390F56AC93";
    //站点信息
    public static final String ZHANGDIAN = "1504151942355529";
    //设置机器人常在线类型
    public static final String ROBAT_ONLIONING = "kl";
    //设置机器人常用信息
    public static final String ROBAT_INFORMATION = "p";
    //设置机器人获取流程答案接口
    public static final String ROBAT_GWTFLOW = "getflw";
    //设置转人工
    public static final String ROBAT_TOPERSION = "needperson";
    //设置机器人离线类型
    public static final String ROBAT_NOONLION = "offline";


    public static class Type {
        /**纯文本*/
        public static final String TXT = "text";
        /**图文*/
        public static final String IMAGEANDTITLE = "news";
        /**图片*/
        public static final String IMAGE = "image";
        /**视频*/
        public static final String VIDEO = "video";
        /**音频*/
        public static final String VOICE = "voice";
        /**富文本*/
        public static final String RICHTEXT = "richText";
        /**有相似问题*/
        public static final String LIKEQUESSTION = "guide";
        /**指令问题*/
        public static final String COMMAND = "command";
        /**转人工接口-左边显示webContent*/
        public static final String CHANGE_TO_PEOPLE_LEFT_TEXT = "peopleleft";
        /**转人工接口-右边显示用户说的话*/
        public static final String CHANGE_TO_PEOPLE_RIGHT_TEXT = "peopleright";


        //--------------------------------------相关指令-------------------------------------
        /**常见问题指令*/
        public static final String OFTENQUSETION = "oftenquestion";
        /**上传信息指令*/
        public static final String UPPERSONINFORMATION = "uppersoninformation";
        /**再见指令*/
        public static final String SEEYOU = "seeyou";
        /**音量调高*/
        public static final String UPVOLUME = "upvolume";
        /**音量调低*/
        public static final String DOWNVOLUME = "downvolume";
        /**播放视频*/
        public static final String PLAYVIDEO = "playvideo";
    }

}
