package com.saleskit.cbbank.features.news;

import java.util.List;

public class News {

    /**
     * data : [{"articleId":34,"articleCategoryId":19,"articleCategoryName":"Tin tức","articleImage":"/files/upload/article/article-image.jpg","title":"Tiêu đề tin tức 3","shortContent":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus sed orci ex."},{"articleId":33,"articleCategoryId":20,"articleCategoryName":"Ưu đãi","articleImage":"/files/upload/article/article-image.jpg","title":"Tiêu đề tin tức 2","shortContent":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus sed orci ex."},{"articleId":30,"articleCategoryId":19,"articleCategoryName":"Tin tức","articleImage":"/files/upload/article/article-image.jpg","title":"Tiêu đề tin tức 1","shortContent":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus sed orci ex."}]
     * totalRecords : 3
     */

    private int totalRecords;
    private List<DataBean> data;

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * articleId : 34
         * articleCategoryId : 19
         * articleCategoryName : Tin tức
         * articleImage : /files/upload/article/article-image.jpg
         * title : Tiêu đề tin tức 3
         * shortContent : Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus sed orci ex.
         */

        private int articleId;
        private int articleCategoryId;
        private String articleCategoryName;
        private String articleImage;
        private String title;
        private String shortContent;

        public int getArticleId() {
            return articleId;
        }

        public void setArticleId(int articleId) {
            this.articleId = articleId;
        }

        public int getArticleCategoryId() {
            return articleCategoryId;
        }

        public void setArticleCategoryId(int articleCategoryId) {
            this.articleCategoryId = articleCategoryId;
        }

        public String getArticleCategoryName() {
            return articleCategoryName;
        }

        public void setArticleCategoryName(String articleCategoryName) {
            this.articleCategoryName = articleCategoryName;
        }

        public String getArticleImage() {
            return articleImage;
        }

        public void setArticleImage(String articleImage) {
            this.articleImage = articleImage;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getShortContent() {
            return shortContent;
        }

        public void setShortContent(String shortContent) {
            this.shortContent = shortContent;
        }
    }
}
