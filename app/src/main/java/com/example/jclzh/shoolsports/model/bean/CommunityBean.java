package com.example.jclzh.shoolsports.model.bean;

import java.util.List;

/**
 * 2033152950
 * Created by zf on 2018/6/9.
 */

public class CommunityBean {

    /**
     * status : 1
     * data : [{"title":"这是创园的活动","image":null,"distance":10,"intergral":"<p>是量来项花样百出基驮运猪八戒磺酸坧采石又红又专<\/p>","newId":2},{"title":"这是标题","image":null,"distance":99,"intergral":"这是第一条新闻，晋城职业技术学院．","newId":1}]
     */

    public String status;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * title : 这是创园的活动
         * image : null
         * distance : 10
         * intergral : <p>是量来项花样百出基驮运猪八戒磺酸坧采石又红又专</p>
         * newId : 2
         */

        public String icon;
        public String title;
        public String image;
        public String distance;
        public String intergral;
        public String newId;
    }
}
