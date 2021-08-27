package com.bw.animationdemo;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * MineStudy
 * name: GiftEntity
 * time: 2021/8/26 22:03.
 * author: 王益德
 * Describe:
 */
public class GiftEntity {

    /**
     * code : 0
     * data : [{"gifImg":"string","id":0,"name":"string","previewImg":"string","price":0}]
     * msg : string
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements MultiItemEntity {
        /**
         * gifImg : string
         * id : 0
         * name : string
         * previewImg : string
         * price : 0
         */

        private String gifImg;
        private int id;
        private String name;
        private String previewImg;
        private int price;

        public String getGifImg() {
            return gifImg;
        }

        public void setGifImg(String gifImg) {
            this.gifImg = gifImg;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPreviewImg() {
            return previewImg;
        }

        public void setPreviewImg(String previewImg) {
            this.previewImg = previewImg;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        @Override
        public int getItemType() {
            return 0;
        }
    }
}
