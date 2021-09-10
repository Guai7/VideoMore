package com.bawei.minestudy.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

/**
 * MineStudy
 * name: VideoEntity
 * time: 2021/8/20 10:53.
 * author: 王益德
 * Describe:
 */
public class VideoEntity{

    /**
     * code : 0
     * msg : 请求成功
     * data : [{"id":102,"authname":"曹笑笑..","publishtime":"2020-08-21 20:48:20","caption":"嘿嘿","view_count":310559,"like_count":6556,"headpath":"http://ali-p1.yximgs.com/uhead/AB/2019/06/20/12/BMjAxOTA2MjAxMjU1MzRfMTY4Mjc3ODU5XzFfaGQ3NjRfNTIw_s.jpg","videopath":"http://txmov2.a.yximgs.com/upic/2020/08/21/20/BMjAyMDA4MjEyMDQ4MTRfMTY4Mjc3ODU5XzM0NzU3Nzc0NDU4XzFfMw==_b_B5a7befc8f302d4b2094b77ddaeb813d5.mp4?tag=1-1599265808-vdg-1-pk3asdcwkh-c228fed0db615313&tt=b&bp=10000","videomainimg":"http://ali2.a.yximgs.com/upic/2020/08/21/20/BMjAyMDA4MjEyMDQ4MTRfMTY4Mjc3ODU5XzM0NzU3Nzc0NDU4XzFfMw==_low_Bb52d886c2f8fecf20456f74eb6dcdd38.webp?tag=1-1599265808-vdg-0-7f7hycqhub-ecdfed633b500c5c&bp=10000"},{"id":103,"authname":"kitty--3","publishtime":"2020-08-22 18:05:42","caption":"#高清视频","view_count":925390,"like_count":44914,"headpath":"http://ali-p1.yximgs.com/uhead/AB/2020/05/08/23/BMjAyMDA1MDgyMzQwMzdfNDU1MzYxMTVfMV9oZDg0NV85MQ==_s.jpg","videopath":"http://alimov2.a.yximgs.com/upic/2020/08/22/18/BMjAyMDA4MjIxODA1MzhfNDU1MzYxMTVfMzQ4MDQ3Njc4NTNfMV8z_b_B7782c9c48475df0674209a0d58fa8eb5.mp4?tag=1-1599265808-vdg-1-eokfxyruy0-63689f95338e1698&tt=b&bp=10000","videomainimg":"http://tx2.a.yximgs.com/upic/2020/08/22/18/BMjAyMDA4MjIxODA1MzhfNDU1MzYxMTVfMzQ4MDQ3Njc4NTNfMV8z_low_Bd2ff6808eaf5a70c4476b750429c32da.webp?tag=1-1599265808-vdg-0-ptv04o3q6w-fbf0d2d05029d13a&bp=10000"},{"id":104,"authname":"半根萝卜＠","publishtime":"2020-08-25 18:27:36","caption":"#用快影上热门 #传唱王杰经典歌曲 可以笑的话，谁会哭 可找到知己，哪会孤独 可以爱的话，不退缩 可相知的心，哪怕追逐\u2026","view_count":103237,"like_count":3783,"headpath":"http://ali-p1.yximgs.com/uhead/AB/2018/01/15/21/BMjAxODAxMTUyMTA5MzJfNzUyOTE1NTE1XzFfaGQzOF81NzM=_s.jpg","videopath":"http://txmov2.a.yximgs.com/upic/2020/08/25/18/BMjAyMDA4MjUxODI3MzJfNzUyOTE1NTE1XzM0OTk0ODM4NjU4XzFfMw==_b_Bf7baf5d6e7e7373a2bfda1d4369c0c02.mp4?tag=1-1599265808-vdg-1-k4jabfchva-16e659671c537eb4&tt=b&bp=10000","videomainimg":"http://ali2.a.yximgs.com/upic/2020/08/25/18/BMjAyMDA4MjUxODI3MzJfNzUyOTE1NTE1XzM0OTk0ODM4NjU4XzFfMw==_low_Bdff5ec7586388723814ad0c22d35d3d9.webp?tag=1-1599265808-vdg-0-depvqvkmm2-3c35b061a0967aa7&bp=10000"},{"id":105,"authname":"S0_py0Nk","publishtime":"2020-08-23 12:38:08","caption":"我这是不是肢体不协调#fyp","view_count":22529,"like_count":1036,"headpath":"http://ali-p1.yximgs.com/uhead/AB/2020/08/26/00/BMjAyMDA4MjYwMDIwMjNfOTA5MTkyOTM1XzFfaGQ0MThfMjY2_s.jpg","videopath":"http://txmov2.a.yximgs.com/upic/2020/08/23/12/BMjAyMDA4MjMxMjM4MDNfOTA5MTkyOTM1XzM0ODQ2MDU5NjY0XzFfMw==_b_Ba95a584aa91122e32d717b4da4f64131.mp4?tag=1-1599265808-vdg-1-1ru9tep8sj-1dd6903c7361ceab&tt=b&bp=10000","videomainimg":"http://ali2.a.yximgs.com/upic/2020/08/23/12/BMjAyMDA4MjMxMjM4MDNfOTA5MTkyOTM1XzM0ODQ2MDU5NjY0XzFfMw==_low_Bf3a21e9f0c37ca10af336edf0fb4d359.webp?tag=1-1599265808-vdg-0-ulcumgovq3-d2914714a5d52ee1&bp=10000"},{"id":107,"authname":"拜托了！辛小菊","publishtime":"2020-08-31 18:28:21","caption":"爱一个人的时候，你是有多么擅长自欺欺人～","view_count":330720,"like_count":17455,"headpath":"http://ali2.a.yximgs.com/uhead/AB/2019/12/27/14/BMjAxOTEyMjcxNDU0MjRfMTY0MzQyMjA5MF8yX2hkODI5XzUwMg==_s.jpg","videopath":"http://txmov6.a.yximgs.com/upic/2020/08/31/18/BMjAyMDA4MzExODI4MTNfMTY0MzQyMjA5MF8zNTMzNzQ2NzA0NV8yXzM=_b_B798d765ecba654715115ea88db7495a5.mp4?tag=1-1599265808-vdg-1-bt0v1njxkf-e3d481d1c2527e4d&tt=b&bp=10000","videomainimg":"http://ali2.a.yximgs.com/upic/2020/08/31/18/BMjAyMDA4MzExODI4MTNfMTY0MzQyMjA5MF8zNTMzNzQ2NzA0NV8yXzM=_low_B16e960af99ad810692d1ef2ff6f8d168.webp?tag=1-1599265808-vdg-0-zmlnxf7u07-92b4f80df49eb8c9&bp=10000"},{"id":108,"authname":"木木star-","publishtime":"2020-08-23 22:35:34","caption":"#高清视频 女生超级加分的行为有哪些","view_count":1415011,"like_count":157756,"headpath":"http://ali-p1.yximgs.com/uhead/AB/2019/09/19/17/BMjAxOTA5MTkxNzA4MjBfMTA1NDk4NTQ0XzJfaGQzODVfODE1_s.jpg","videopath":"http://txmov2.a.yximgs.com/upic/2020/08/23/22/BMjAyMDA4MjMyMjM1MzBfMTA1NDk4NTQ0XzM0ODgzNjAwNzM0XzFfMw==_b_Bf3f94d04cfa8d8522974bc38248b991c.mp4?tag=1-1599265808-vdg-1-th7r0iins3-c6ce5f45d0b56fff&tt=b&bp=10000","videomainimg":"http://ali2.a.yximgs.com/upic/2020/08/23/22/BMjAyMDA4MjMyMjM1MzBfMTA1NDk4NTQ0XzM0ODgzNjAwNzM0XzFfMw==_low_B177a18650c1a058f72124d149b0bc9fa.webp?tag=1-1599265808-vdg-0-sftmpl5lqs-597315cee018b31a&bp=10000"},{"id":109,"authname":"星秀街拍","publishtime":"2020-08-29 22:17:06","caption":"#高清视频 #街拍","view_count":297410,"like_count":14818,"headpath":"http://ali-p1.yximgs.com/uhead/AB/2020/07/11/02/BMjAyMDA3MTEwMjIxMDdfNDY2MzI3NDE4XzFfaGQxMTdfMzU5_s.jpg","videopath":"http://txmov6.a.yximgs.com/upic/2020/08/29/22/BMjAyMDA4MjkyMjE3MDJfNDY2MzI3NDE4XzM1MjQxOTA4MTEyXzFfMw==_b_Bec45da0ae9885dd11b4202fa79ee15d2.mp4?tag=1-1599265808-vdg-1-ss8tmjx6ad-a0edd9682705fd1d&tt=b&bp=10000","videomainimg":"http://ali2.a.yximgs.com/upic/2020/08/29/22/BMjAyMDA4MjkyMjE3MDJfNDY2MzI3NDE4XzM1MjQxOTA4MTEyXzFfMw==_low_B263d1ee7efc250d5808a999d148fb9a6.webp?tag=1-1599265808-vdg-0-73kfbp1ezg-d2725617e9b14923&bp=10000"},{"id":110,"authname":"楠楠ʚ ɞ","publishtime":"2020-09-03 18:47:13","caption":"这么可爱的舞蹈，谁跳谁可爱，快艾特你的小伙伴来跳 #快手创作者中心 #用快影上热门 #cocoball #手势舞","view_count":159813,"like_count":1666,"headpath":"http://ali-p1.yximgs.com/uhead/AB/2018/11/20/20/BMjAxODExMjAyMDAwNThfMTY5MjYzMjNfMV9oZDY4N184ODg=_s.jpg","videopath":"http://txmov6.a.yximgs.com/upic/2020/09/03/18/BMjAyMDA5MDMxODQ3MDhfMTY5MjYzMjNfMzU0NzE5NTYxMzRfMV8z_b_B9dd86d4ccbd6bba83a8380ca3a0b7d0a.mp4?tag=1-1599265808-vdg-1-ukvwl33qlz-c33383ae94c547ca&tt=b&bp=10000","videomainimg":"http://ali2.a.yximgs.com/upic/2020/09/03/18/BMjAyMDA5MDMxODQ3MDhfMTY5MjYzMjNfMzU0NzE5NTYxMzRfMV8z_low_B61b600a877d44fadcd9c9364af11444b.webp?tag=1-1599265808-vdg-0-zrgjboxdxi-584f0ddfa5010f05&bp=10000"},{"id":111,"authname":"晓润航拍（盐源）","publishtime":"2020-08-26 09:53:07","caption":"#我们都太胆小了，民国时不敢做军阀，乱世里不敢去起义，太平盛世里，不敢说声我爱你。如果再来一次，我要去长安，看看大唐盛世和你 #","view_count":10904,"like_count":417,"headpath":"http://ali-p1.yximgs.com/uhead/AB/2018/07/19/20/BMjAxODA3MTkyMDQxNTZfNzM5NTc0MDNfMl9oZDkxOF84MDA=_s.jpg","videopath":"http://txmov2.a.yximgs.com/upic/2020/08/26/09/BMjAyMDA4MjYwOTUzMDNfNzM5NTc0MDNfMzUwMjkxNzk1NTlfMl8z_b_Be583297e1cd54fbf3cd082bbfc50335e.mp4?tag=1-1599265808-vdg-1-8jlz2w0irc-455a3c5bea9aba47&tt=b&bp=10000","videomainimg":"http://ali2.a.yximgs.com/upic/2020/08/26/09/BMjAyMDA4MjYwOTUzMDNfNzM5NTc0MDNfMzUwMjkxNzk1NTlfMl8z_low_B6d55457e94e16aaae0b8b4e2ccb935da.webp?tag=1-1599265808-vdg-0-pxjrdfutvt-a7f5f0c02bdd190e&bp=10000"},{"id":112,"authname":"江姐_","publishtime":"2020-08-30 21:16:23","caption":"00后一波被按了加速键，一波按了减速键。#高清视频 #快手小剧场 #快手剧星计划 #搞笑","view_count":62757,"like_count":3298,"headpath":"http://ali-p1.yximgs.com/uhead/AB/2020/02/03/17/BMjAyMDAyMDMxNzEwMTJfMTc1Njk5NzM0MF8yX2hkNjQyXzIw_s.jpg","videopath":"http://alimov2.a.yximgs.com/upic/2020/08/30/21/BMjAyMDA4MzAyMTE2MTdfMTc1Njk5NzM0MF8zNTI5NTY5MjgzMF8yXzM=_b_B78ba61c44aee8f1168e14ff47fe27eac.mp4?tag=1-1599265810-vdg-1-urqvflxsb0-af36d7e8adc5c98f&tt=b&bp=10000","videomainimg":"http://js2.a.yximgs.com/upic/2020/08/30/21/BMjAyMDA4MzAyMTE2MTdfMTc1Njk5NzM0MF8zNTI5NTY5MjgzMF8yXzM=_low_B4e7d7ff40005d9f3d404e3a1de570fe7.webp?tag=1-1599265810-vdg-0-w6ggsvdutm-98db78ebfcca9db7&bp=10000"}]
     */

    private int code;
    private String msg;
    /**
     * id : 102
     * authname : 曹笑笑..
     * publishtime : 2020-08-21 20:48:20
     * caption : 嘿嘿
     * view_count : 310559
     * like_count : 6556
     * headpath : http://ali-p1.yximgs.com/uhead/AB/2019/06/20/12/BMjAxOTA2MjAxMjU1MzRfMTY4Mjc3ODU5XzFfaGQ3NjRfNTIw_s.jpg
     * videopath : http://txmov2.a.yximgs.com/upic/2020/08/21/20/BMjAyMDA4MjEyMDQ4MTRfMTY4Mjc3ODU5XzM0NzU3Nzc0NDU4XzFfMw==_b_B5a7befc8f302d4b2094b77ddaeb813d5.mp4?tag=1-1599265808-vdg-1-pk3asdcwkh-c228fed0db615313&tt=b&bp=10000
     * videomainimg : http://ali2.a.yximgs.com/upic/2020/08/21/20/BMjAyMDA4MjEyMDQ4MTRfMTY4Mjc3ODU5XzM0NzU3Nzc0NDU4XzFfMw==_low_Bb52d886c2f8fecf20456f74eb6dcdd38.webp?tag=1-1599265808-vdg-0-7f7hycqhub-ecdfed633b500c5c&bp=10000
     */

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

    public static class DataBean implements MultiItemEntity, Serializable {
        private int id;
        private String authname;
        private String publishtime;
        private String caption;
        private int view_count;
        private int like_count;
        private String headpath;
        private String videopath;
        private String videomainimg;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAuthname() {
            return authname;
        }

        public void setAuthname(String authname) {
            this.authname = authname;
        }

        public String getPublishtime() {
            return publishtime;
        }

        public void setPublishtime(String publishtime) {
            this.publishtime = publishtime;
        }

        public String getCaption() {
            return caption;
        }

        public void setCaption(String caption) {
            this.caption = caption;
        }

        public int getView_count() {
            return view_count;
        }

        public void setView_count(int view_count) {
            this.view_count = view_count;
        }

        public int getLike_count() {
            return like_count;
        }

        public void setLike_count(int like_count) {
            this.like_count = like_count;
        }

        public String getHeadpath() {
            return headpath;
        }

        public void setHeadpath(String headpath) {
            this.headpath = headpath;
        }

        public String getVideopath() {
            return videopath;
        }

        public void setVideopath(String videopath) {
            this.videopath = videopath;
        }

        public String getVideomainimg() {
            return videomainimg;
        }

        public void setVideomainimg(String videomainimg) {
            this.videomainimg = videomainimg;
        }

        @Override
        public int getItemType() {
            return 0;
        }
    }
}
