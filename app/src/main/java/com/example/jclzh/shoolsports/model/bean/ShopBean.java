package com.example.jclzh.shoolsports.model.bean;

import java.util.List;

public class ShopBean {

    /**
     * status : 1
     * data : [{"shop_id":5,"title":"买二送一","integral":1,"desc":"可陪睡！","images":"/public/upload/shop/1530060948575.jpg","user_id":1,"price":123},{"shop_id":4,"title":"中物件","integral":1,"desc":"三陪！","images":"/public/upload/shop/1530060815455.jpg","user_id":1,"price":10},{"shop_id":3,"title":"大物件","integral":123,"desc":"可暖床！","images":"/public/upload/shop/1530060729012.jpg","user_id":1,"price":10},{"shop_id":2,"title":"小物件","integral":123,"desc":"获得此物可升天!","images":"/public/upload/shop/1530060628055.jpg","user_id":1,"price":5},{"shop_id":1,"title":"asdasd","integral":100,"desc":"asdasd","images":"/public/upload/shop/1530058803174.jpg","user_id":1,"price":121}]
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
         * shop_id : 5
         * title : 买二送一
         * integral : 1
         * desc : 可陪睡！
         * images : /public/upload/shop/1530060948575.jpg
         * user_id : 1
         * price : 123
         */

        private int shop_id;
        private String title;
        private int integral;
        private String desc;
        private String images;
        private int user_id;
        private int price;

        public int getShop_id() {
            return shop_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "shop_id=" + shop_id +
                    ", title='" + title + '\'' +
                    ", integral=" + integral +
                    ", desc='" + desc + '\'' +
                    ", images='" + images + '\'' +
                    ", user_id=" + user_id +
                    ", price=" + price +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ShopBean{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }
}
