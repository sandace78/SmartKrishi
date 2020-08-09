package com.example.ecommerce.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
 Created by ♕ A-Cis Sapkota on 7/7/2020.
*/
 public class Categories {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("details")
    @Expose
    private List<Detail> details = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public class Detail {

        @SerializedName("id")
        @Expose
        private Object id;
        @SerializedName("categoryNameNepali")
        @Expose
        private String categoryNameNepali;
        @SerializedName("categoryNameEnglish")
        @Expose
        private String categoryNameEnglish;
        @SerializedName("descriptionNepali")
        @Expose
        private String descriptionNepali;
        @SerializedName("descriptionEnglish")
        @Expose
        private String descriptionEnglish;
        @SerializedName("imagePath")
        @Expose
        private String imagePath;
        @SerializedName("image")
        @Expose
        private Object image;

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public String getCategoryNameNepali() {
            return categoryNameNepali;
        }

        public void setCategoryNameNepali(String categoryNameNepali) {
            this.categoryNameNepali = categoryNameNepali;
        }

        public String getCategoryNameEnglish() {
            return categoryNameEnglish;
        }

        public void setCategoryNameEnglish(String categoryNameEnglish) {
            this.categoryNameEnglish = categoryNameEnglish;
        }

        public String getDescriptionNepali() {
            return descriptionNepali;
        }

        public void setDescriptionNepali(String descriptionNepali) {
            this.descriptionNepali = descriptionNepali;
        }

        public String getDescriptionEnglish() {
            return descriptionEnglish;
        }

        public void setDescriptionEnglish(String descriptionEnglish) {
            this.descriptionEnglish = descriptionEnglish;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        public Object getImage() {
            return image;
        }

        public void setImage(Object image) {
            this.image = image;
        }

    }
}
