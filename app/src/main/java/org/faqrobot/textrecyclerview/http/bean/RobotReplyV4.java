package org.faqrobot.textrecyclerview.http.bean;

import java.util.List;


public class RobotReplyV4 {

    private String msgtype;

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    private TextBean text;  //文本
    private ImageBean image; //单图片
    private VoiceBean voice;  //音频
    private VideoBean video;  //视频
    private NewsBean news;     //图文
    private RichTextBean richText;  //富文本
    private GuideBean guide;    //反问
    private CommandBean command;

    public CommandBean getCommand() {
        return command;
    }


    public String getMsgtype() {
        return msgtype;
    }

    public TextBean getText() {
        return text;
    }


    public ImageBean getImage() {
        return image;
    }


    public VoiceBean getVoice() {
        return voice;
    }


    public VideoBean getVideo() {
        return video;
    }


    public NewsBean getNews() {
        return news;
    }


    public RichTextBean getRichText() {
        return richText;
    }


    public GuideBean getGuide() {
        return guide;
    }


    public static class TextBean {
        @Override
        public String toString() {
            return "TextBean{" +
                    "content='" + content + '\'' +
                    '}';
        }

        /**
         * content : 居民阶梯电价客户的故障退补电费计算应区分退补电量是本结算年用电量，还是退补以前的电量。
         * 您是不是还想问以下问题：
         * 21.退补电量电费什么时候到账;
         * 22.退补电量电费流程.
         * 以上答案是否解决了您的问题? 回复：A：解决 B:未解决
         */

        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static class ImageBean {
        @Override
        public String toString() {
            return "ImageBean{" +
                    "name='" + name + '\'' +
                    ", format='" + format + '\'' +
                    ", url='" + url + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }

        /**
         * name : 电费收费方式.jpg
         * format : jpg
         * url : http://v4.faqrobot.net/upload/web/1476067342641247/20170222/2017080220202.jpg
         * content : 您是不是还想问以下问题：
         * 21.退补电量电费什么时候到账;
         * 22.退补电量电费流程.
         * 以上答案是否解决了您的问题? 回复：A：解决 B:未解决
         */

        private String name;
        private String format;
        private String url;
        private String content;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static class VoiceBean {
        @Override
        public String toString() {
            return "VoiceBean{" +
                    "name='" + name + '\'' +
                    ", format='" + format + '\'' +
                    ", url='" + url + '\'' +
                    ", recognition='" + recognition + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }

        /**
         * name : 电费收费方式.mp3
         * format : mp3
         * url : http://v4.faqrobot.net/upload/web/1476067342641247/20170222/2017080220203.mp3
         * recognition : (语音识别后文字)居民阶梯电价客户的故障退补电费计算应区分退补电量是本结算年用电量。
         * <p>
         * content : 您是不是还想问以下问题：
         * 21.退补电量电费什么时候到账;
         * 22.退补电量电费流程.
         * 以上答案是否解决了您的问题? 回复：A：解决 B:未解决
         */

        private String name;
        private String format;
        private String url;
        private String recognition;
        private String content;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getRecognition() {
            return recognition;
        }

        public void setRecognition(String recognition) {
            this.recognition = recognition;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static class VideoBean {
        @Override
        public String toString() {
            return "VideoBean{" +
                    "name='" + name + '\'' +
                    ", format='" + format + '\'' +
                    ", url='" + url + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }

        /**
         * name : 电费收费方式.mp4
         * format : mp4
         * url : http://v4.faqrobot.net/upload/web/1476067342641247/20170222/2017080220204.mp4
         * content : 您是不是还想问以下问题：
         * 21.退补电量电费什么时候到账;
         * 22.退补电量电费流程.
         * 以上答案是否解决了您的问题? 回复：A：解决 B:未解决
         */

        private String name;
        private String format;
        private String url;
        private String content;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static class NewsBean {

        @Override
        public String toString() {
            return "NewsBean{" +
                    "articles=" + articles +
                    '}';
        }

        private List<ArticlesBean> articles;

        public List<ArticlesBean> getArticles() {
            return articles;
        }

        public void setArticles(List<ArticlesBean> articles) {
            this.articles = articles;
        }

        public static class ArticlesBean {

            @Override
            public String toString() {
                return "ArticlesBean{" +
                        "description='" + description + '\'' +
                        ", picurl='" + picurl + '\'' +
                        ", title='" + title + '\'' +
                        ", url='" + url + '\'' +
                        '}';
            }

            /**
             * description : 珍惜的人，走过的路。我们跟着时间越走越远，身边的人身边的事。能留下来的才算是你的，一些路一些分别。时……
             * picurl : http://v4.faqrobot.net/upload/web/1476067342641247/20170222/83001487727860389.png
             * title : 单图文222
             * url : http://v4.faqrobot.net/servlet/WBShow?action=swxmsg&wbId=-100&sysNum=1476067342641247&FromUserName=API_123&wxpicid=91
             */

            private String description;
            private String picurl;
            private String title;
            private String url;

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getPicurl() {
                return picurl;
            }

            public void setPicurl(String picurl) {
                this.picurl = picurl;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }

    public static class RichTextBean {

        @Override
        public String toString() {
            return "RichTextBean{" +
                    "description='" + description + '\'' +
                    ", picurl='" + picurl + '\'' +
                    ", title='" + title + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }

        /**
         * description : 珍惜的人，走过的路。我们跟着时间越走越远，身边的人身边的事。能留下来的才算是你的，一些路一些分别。时……
         * picurl : http://v4.faqrobot.net/upload/web/1476067342641247/20170222/83001487727860389.png
         * title : 标准问题
         * url : http://v4.faqrobot.net/servlet/WBShow?action=swxmsg&wbId=-100&sysNum=1476067342641247&FromUserName=API_123&wxpicid=91
         */

        private String description;
        private String picurl;
        private String title;
        private String url;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class GuideBean {
        @Override
        public String toString() {
            return "GuideBean{" +
                    "content='" + content + '\'' +
                    '}';
        }

        /**
         * content : 您关心的是不是以下问题？
         * 1.冰箱结冰
         * 2.冰箱跳闸
         * 3.冰箱不制冷
         * 4.冰箱流水
         * 直接回复文字或序号。
         */

        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static class CommandBean {
        @Override
        public String toString() {
            return "CommandBean{" +
                    "content='" + content + '\'' +
                    '}';
        }


        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }


}
