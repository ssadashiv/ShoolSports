package com.example.jclzh.shoolsports.model.bean;

import java.io.Serializable;

/**
 * Created by lzh on 2018/6/9.
 */

public class User implements Serializable{


    /**
     * status : 1
     * user : {"users_id":875,"wx_code":0,"pwd":"73318jczy","study_code":1601373318,"scholl_id":1,"class":"16计算机应用三班","desc":"我是一个学生","image":"/public/images/promdel.png","type":1,"value":118,"integral":0,"name":"栗振辉"}
     * token : 79328
     */

    private int status;
    private UserBean user;
    private int token;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }

    public static class UserBean implements Serializable{
        /**
         * users_id : 875
         * wx_code : 0
         * pwd : 73318jczy
         * study_code : 1601373318
         * scholl_id : 1
         * class : 16计算机应用三班
         * desc : 我是一个学生
         * image : /public/images/promdel.png
         * type : 1
         * value : 118
         * integral : 0
         * name : 栗振辉
         */

        private int users_id;
        private int wx_code;
        private String pwd;
        private int study_code;
        private int scholl_id;
        @com.google.gson.annotations.SerializedName("class")
        private String classX;
        private String desc;
        private String image;
        private int type;
        private int value;
        private int integral;
        private String name;
        private  String department ;

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public int getUsers_id() {
            return users_id;
        }

        public void setUsers_id(int users_id) {
            this.users_id = users_id;
        }

        public int getWx_code() {
            return wx_code;
        }

        public void setWx_code(int wx_code) {
            this.wx_code = wx_code;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public int getStudy_code() {
            return study_code;
        }

        public void setStudy_code(int study_code) {
            this.study_code = study_code;
        }

        public int getScholl_id() {
            return scholl_id;
        }

        public void setScholl_id(int scholl_id) {
            this.scholl_id = scholl_id;
        }

        public String getClassX() {
            return classX;
        }

        public void setClassX(String classX) {
            this.classX = classX;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

