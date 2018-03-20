package com.example.dl.hymvp.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Incremental change is better than ambitious failure.
 *
 * @author : <a href="http://mysticcoder.coding.me/myBlog">MysticCoder</a>
 * @date : 2017/12/4
 * @desc :
 */


public class Survey {
    private List<SurveylistBean> surveylist;

    public List<SurveylistBean> getSurveylist() {
        return surveylist;
    }

    public void setSurveylist(List<SurveylistBean> surveylist) {
        this.surveylist = surveylist;
    }

    @Override
    public String toString() {
        return "SurveyModel{" +
                "surveylist=" + surveylist +
                '}';
    }

    public static class SurveylistBean implements Serializable {
        /**
         * author : 北京市垂杨柳医院
         * remark : 为不断改善医院住院服务质量，我院正在进行住院病人的满意度调查。非常感谢您抽出宝贵时间参加本次调查，提供您的看法与意见，能倾听您的意见，我们感到十分荣幸。谢谢！
         * id : 93
         * title : 住院病人满意度调查
         */

        private String author;
        private String remark;
        private int id;

        @Override
        public String toString() {
            return "SurveylistBean{" +
                    "author='" + author + '\'' +
                    ", remark='" + remark + '\'' +
                    ", id=" + id +
                    ", title='" + title + '\'' +
                    '}';
        }

        private String title;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
