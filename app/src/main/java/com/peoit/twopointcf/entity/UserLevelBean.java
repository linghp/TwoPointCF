package com.peoit.twopointcf.entity;

import java.io.Serializable;

/**
 * 用户等级
 * Created by zyz on 2015/10/15.
 */
public class UserLevelBean implements Serializable {
        private String published;// 发布项目统计数
        private String invested;//投资项目统计数

        public void setPublished(String published) {
            this.published = published;
        }

        public void setInvested(String invested) {
            this.invested = invested;
        }

        public String getPublished() {
            return published;
        }

        public String getInvested() {
            return invested;
        }

    @Override
    public String toString() {
        return "UserLevelBean{" +
                "published='" + published + '\'' +
                ", invested='" + invested + '\'' +
                '}';
    }
}
