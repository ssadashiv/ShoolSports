package com.example.jclzh.shoolsports.model.bean;

import java.util.List;

public class MessageBean {

    /**
     * status : 1
     * data : [{"msg_id":6,"title":"消息3","content":"振辉公开按摩!一次50.","time":"2018-06-26T16:00:00.000Z","user_id":1},{"msg_id":5,"title":"消息2","content":"东东是个大色狼","time":"2018-06-26T16:00:00.000Z","user_id":1},{"msg_id":4,"title":"消息1","content":"东东晚上撸管!","time":"2018-06-26T16:00:00.000Z","user_id":1},{"msg_id":3,"title":"消息","content":"黄东东看小黄片!","time":"2018-06-26T16:00:00.000Z","user_id":1}]
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
         * msg_id : 6
         * title : 消息3
         * content : 振辉公开按摩!一次50.
         * time : 2018-06-26T16:00:00.000Z
         * user_id : 1
         */

        private int msg_id;
        private String title;
        private String content;
        private String time;
        private int user_id;

        public int getMsg_id() {
            return msg_id;
        }

        public void setMsg_id(int msg_id) {
            this.msg_id = msg_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }
    }
}
