package org.faqrobot.textrecyclerview.http.bean;

import java.util.List;

/**
 * Created by yunwen on 2017/12/1.
 */

public class DataBean {

    @Override
    public String toString() {
        return "DataBean{" +
                "hitQuestion='" + hitQuestion + '\'' +
                ", question='" + question + '\'' +
                ", robotReply=" + robotReply +
                ", semantic=" + semantic +
                ", serviceType='" + serviceType + '\'' +
                '}';
    }

    /**
     * hitQuestion : 退补电量电费计算？
     * question : 退补电量电费
     * robotReply : null
     * semantic : {"intent":{"type":"recommendProduct","details":{"subject":"冰箱","properties":{"priceRegion":{"start":4000,"end":6000},"brand":"haier","color":"red"}}}}
     * serviceType : semantic
     */

    private String hitQuestion;
    private String question;
    private List<RobotReplyV4> robotReply;
    private SemanticBean semantic;
    private String serviceType;

    public String getHitQuestion() {
        return hitQuestion;
    }

    public void setHitQuestion(String hitQuestion) {
        this.hitQuestion = hitQuestion;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<RobotReplyV4> getRobotReply() {
        return robotReply;
    }

    public void setRobotReply(List<RobotReplyV4> robotReply) {
        this.robotReply = robotReply;
    }

    public SemanticBean getSemantic() {
        return semantic;
    }


    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }


    public static class SemanticBean {


        @Override
        public String toString() {
            return "SemanticBean{" +
                    "intent=" + intent +
                    '}';
        }

        /**
         * intent : {"type":"recommendProduct","details":{"subject":"冰箱","properties":{"priceRegion":{"start":4000,"end":6000},"brand":"haier","color":"red"}}}
         */

        private IntentBean intent;

        public IntentBean getIntent() {
            return intent;
        }


        public static class IntentBean {


            @Override
            public String toString() {
                return "IntentBean{" +
                        "type='" + type + '\'' +
                        ", details=" + details +
                        '}';
            }

            /**
             * type : recommendProduct
             * details : {"subject":"冰箱","properties":{"priceRegion":{"start":4000,"end":6000},"brand":"haier","color":"red"}}
             */

            private String type;
            private DetailsBean details;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public DetailsBean getDetails() {
                return details;
            }


            public static class DetailsBean {
                @Override
                public String toString() {
                    return "DetailsBean{" +
                            "subject='" + subject + '\'' +
                            ", properties=" + properties +
                            '}';
                }

                /**
                 * subject : 冰箱
                 * properties : {"priceRegion":{"start":4000,"end":6000},"brand":"haier","color":"red"}
                 */

                private String subject;
                private PropertiesBean properties;

                public String getSubject() {
                    return subject;
                }

                public void setSubject(String subject) {
                    this.subject = subject;
                }

                public PropertiesBean getProperties() {
                    return properties;
                }



                public static class PropertiesBean {
                    @Override
                    public String toString() {
                        return "PropertiesBean{" +
                                "priceRegion=" + priceRegion +
                                ", brand='" + brand + '\'' +
                                ", color='" + color + '\'' +
                                '}';
                    }

                    /**
                     * priceRegion : {"start":4000,"end":6000}
                     * brand : haier
                     * color : red
                     */

                    private PriceRegionBean priceRegion;
                    private String brand;
                    private String color;

                    public PriceRegionBean getPriceRegion() {
                        return priceRegion;
                    }

                    public void setPriceRegion(PriceRegionBean priceRegion) {
                        this.priceRegion = priceRegion;
                    }

                    public String getBrand() {
                        return brand;
                    }

                    public void setBrand(String brand) {
                        this.brand = brand;
                    }

                    public String getColor() {
                        return color;
                    }

                    public void setColor(String color) {
                        this.color = color;
                    }

                    public static class PriceRegionBean {
                        @Override
                        public String toString() {
                            return "PriceRegionBean{" +
                                    "start=" + start +
                                    ", end=" + end +
                                    '}';
                        }

                        /**
                         * start : 4000
                         * end : 6000
                         */

                        private int start;
                        private int end;

                        public int getStart() {
                            return start;
                        }

                        public void setStart(int start) {
                            this.start = start;
                        }

                        public int getEnd() {
                            return end;
                        }

                        public void setEnd(int end) {
                            this.end = end;
                        }
                    }
                }
            }
        }
    }
}
