package com.peoit.twopointcf.entity;

/**
 * Created by ling on 2015/8/10.
 * last:2015/8/10
 * description:
 */
public class InformationCenterBean {
    /*private  String time;
    private String title;
    private String content;

    public InformationCenterBean(String time, String title, String content) {
        this.time = time;
        this.title = title;
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
    }*/

//    public static class ResultEntity {
        /**
         * id : 11111
         * title : aaa
         * informationIntro : aa
         * content : <html><body><a href="www.baidu.com">aaaaaa</></body></html>
         * picturePath : /upload/9f58fc15d1aa4a7287160084d8d03c1a.jpg
         */

        private String id;
        private String title;
        private String informationIntro;
        private String content;
        private String picturePath;

        public void setId(String id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setInformationIntro(String informationIntro) {
            this.informationIntro = informationIntro;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setPicturePath(String picturePath) {
            this.picturePath = picturePath;
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getInformationIntro() {
            return informationIntro;
        }

        public String getContent() {
            return content;
        }

        public String getPicturePath() {
            return picturePath;
        }
//    }
}
