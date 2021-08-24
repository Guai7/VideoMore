package com.bawei.minestudy.entity;

/**
 * MineStudy
 * name: LogAndRegEntity
 * time: 2021/8/19 14:45.
 * author: 王益德
 * Describe:
 */
public class LogAndRegEntity {

    /**
     * code : 0
     * data : {"createTime":"2021-08-19T06:37:04.855Z","money":0,"nikeName":"string","phoneNumber":"string","userAge":0,"userId":0,"userName":"string","userPwd":"string"}
     * msg : string
     */

    private int code;
    /**
     * createTime : 2021-08-19T06:37:04.855Z
     * money : 0
     * nikeName : string
     * phoneNumber : string
     * userAge : 0
     * userId : 0
     * userName : string
     * userPwd : string
     */

    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        private String createTime;
        private int money;
        private String nikeName;
        private String phoneNumber;
        private int userAge;
        private int userId;
        private String userName;
        private String userPwd;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public String getNikeName() {
            return nikeName;
        }

        public void setNikeName(String nikeName) {
            this.nikeName = nikeName;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public int getUserAge() {
            return userAge;
        }

        public void setUserAge(int userAge) {
            this.userAge = userAge;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserPwd() {
            return userPwd;
        }

        public void setUserPwd(String userPwd) {
            this.userPwd = userPwd;
        }
    }
}
