package com.example.jclzh.shoolsports.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lzh on 2018/6/26.
 */

public class PaiMingBean {

    /**
     * status : 1
     * data : [{"automation_id":14,"user_id":199,"time":"2018-06-08T16:00:00.000Z","distance":1000000,"tag":1,"name":"张芸雁","class":"15物流信息技术二班","image":"/public/dist/img/avatar.png"},{"automation_id":1,"user_id":199,"time":"2018-06-06T16:00:00.000Z","distance":100003,"tag":1,"name":"张芸雁","class":"15物流信息技术二班","image":"/public/dist/img/avatar.png"},{"automation_id":8537,"user_id":875,"time":"2018-06-09T16:00:00.000Z","distance":98,"tag":1,"name":"张芸雁","class":"15物流信息技术二班","image":"/public/dist/img/avatar.png"},{"automation_id":13,"user_id":199,"time":"2018-06-07T16:00:00.000Z","distance":5,"tag":1,"name":"张芸雁","class":"15物流信息技术二班","image":"/public/dist/img/avatar.png"}]
     */

    private int status;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * automation_id : 14
         * user_id : 199
         * time : 2018-06-08T16:00:00.000Z
         * distance : 1000000
         * tag : 1
         * name : 张芸雁
         * class : 15物流信息技术二班
         * image : /public/dist/img/avatar.png
         */

        private int automation_id;
        private int user_id;
        private String time;
        private int distance;
        private int tag;
        private String name;
        @SerializedName("class")
        private String classX;
        private String image;

        public int getAutomation_id() {
            return automation_id;
        }

        public void setAutomation_id(int automation_id) {
            this.automation_id = automation_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public int getTag() {
            return tag;
        }

        public void setTag(int tag) {
            this.tag = tag;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getClassX() {
            return classX;
        }

        public void setClassX(String classX) {
            this.classX = classX;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
