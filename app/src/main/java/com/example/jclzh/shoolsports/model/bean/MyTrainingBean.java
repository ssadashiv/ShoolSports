package com.example.jclzh.shoolsports.model.bean;

import java.util.List;

public class MyTrainingBean {

    /**
     * status : 1
     * dataok : []
     * alldata : [{"task_id":1,"scholl_id":1,"tag":"2","content":"晋城职业技术学院","starttime":"2018-06-08T16:00:00.000Z","endtime":"2018-07-02T16:00:00.000Z","value":99,"pass":100,"receiver":"1","title":"明天跑操场100圈","distance":10000},{"task_id":2,"scholl_id":1,"tag":"2","content":"晋城职业技术学院","starttime":"2018-06-07T16:00:00.000Z","endtime":"2018-07-04T16:00:00.000Z","value":100,"pass":10000,"receiver":"1","title":"明天跑步","distance":1000000000}]
     */

    private int status;
    private List<?> dataok;
    private List<AlldataBean> alldata;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<?> getDataok() {
        return dataok;
    }

    public void setDataok(List<?> dataok) {
        this.dataok = dataok;
    }

    public List<AlldataBean> getAlldata() {
        return alldata;
    }

    public void setAlldata(List<AlldataBean> alldata) {
        this.alldata = alldata;
    }

    public static class AlldataBean {
        /**
         * task_id : 1
         * scholl_id : 1
         * tag : 2
         * content : 晋城职业技术学院
         * starttime : 2018-06-08T16:00:00.000Z
         * endtime : 2018-07-02T16:00:00.000Z
         * value : 99
         * pass : 100
         * receiver : 1
         * title : 明天跑操场100圈
         * distance : 10000
         */

        private int task_id;
        private int scholl_id;
        private String tag;
        private String content;
        private String starttime;
        private String endtime;
        private int value;
        private int pass;
        private String receiver;
        private String title;
        private int distance;

        public int getTask_id() {
            return task_id;
        }

        public void setTask_id(int task_id) {
            this.task_id = task_id;
        }

        public int getScholl_id() {
            return scholl_id;
        }

        public void setScholl_id(int scholl_id) {
            this.scholl_id = scholl_id;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getStarttime() {
            return starttime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getPass() {
            return pass;
        }

        public void setPass(int pass) {
            this.pass = pass;
        }

        public String getReceiver() {
            return receiver;
        }

        public void setReceiver(String receiver) {
            this.receiver = receiver;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        @Override
        public String toString() {
            return "AlldataBean{" +
                    "task_id=" + task_id +
                    ", scholl_id=" + scholl_id +
                    ", tag='" + tag + '\'' +
                    ", content='" + content + '\'' +
                    ", starttime='" + starttime + '\'' +
                    ", endtime='" + endtime + '\'' +
                    ", value=" + value +
                    ", pass=" + pass +
                    ", receiver='" + receiver + '\'' +
                    ", title='" + title + '\'' +
                    ", distance=" + distance +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MyTrainingBean{" +
                "status=" + status +
                ", dataok=" + dataok +
                ", alldata=" + alldata +
                '}';
    }
}
