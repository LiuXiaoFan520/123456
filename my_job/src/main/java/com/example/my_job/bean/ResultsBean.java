package com.example.my_job.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class ResultsBean {
        /**
         * _id : 5ccdbc219d212239df927a93
         * createdAt : 2019-05-04T16:21:53.523Z
         * desc : 2019-05-05
         * publishedAt : 2019-05-04T16:21:59.733Z
         * source : web
         * type : 福利
         * url : http://ww1.sinaimg.cn/large/0065oQSqly1g2pquqlp0nj30n00yiq8u.jpg
         * used : true
         * who : lijinshanmx
         */
        @Id()
        private Long id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private boolean flag = false;
        @Generated(hash = 1874142905)
        public ResultsBean(Long id, String createdAt, String desc, String publishedAt,
                String source, String type, String url, boolean used, String who,
                boolean flag) {
            this.id = id;
            this.createdAt = createdAt;
            this.desc = desc;
            this.publishedAt = publishedAt;
            this.source = source;
            this.type = type;
            this.url = url;
            this.used = used;
            this.who = who;
            this.flag = flag;
        }
        @Generated(hash = 1822271928)
        public ResultsBean() {
        }
        public Long getId() {
            return this.id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public String getCreatedAt() {
            return this.createdAt;
        }
        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }
        public String getDesc() {
            return this.desc;
        }
        public void setDesc(String desc) {
            this.desc = desc;
        }
        public String getPublishedAt() {
            return this.publishedAt;
        }
        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }
        public String getSource() {
            return this.source;
        }
        public void setSource(String source) {
            this.source = source;
        }
        public String getType() {
            return this.type;
        }
        public void setType(String type) {
            this.type = type;
        }
        public String getUrl() {
            return this.url;
        }
        public void setUrl(String url) {
            this.url = url;
        }
        public boolean getUsed() {
            return this.used;
        }
        public void setUsed(boolean used) {
            this.used = used;
        }
        public String getWho() {
            return this.who;
        }
        public void setWho(String who) {
            this.who = who;
        }
        public boolean getFlag() {
            return this.flag;
        }
        public void setFlag(boolean flag) {
            this.flag = flag;
        }
       
}
