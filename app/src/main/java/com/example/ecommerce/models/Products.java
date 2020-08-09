package com.example.ecommerce.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/*
 Created by ♕ A-Cis Sapkota on 7/17/2020.
*/
 public class Products {
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

    public class Detail  implements Serializable {

        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("nameinEnglish")
        @Expose
        private String nameinEnglish;
        @SerializedName("nameInNepali")
        @Expose
        private String nameInNepali;
        @SerializedName("descriptionNepali")
        @Expose
        private String descriptionNepali;
        @SerializedName("descriptionEnglish")
        @Expose
        private String descriptionEnglish;
        @SerializedName("quantity")
        @Expose
        private int quantity;
        @SerializedName("basePrice")
        @Expose
        private double basePrice;
        @SerializedName("currentPrice")
        @Expose
        private double currentPrice;
        @SerializedName("imagePath")
        @Expose
        private String imagePath;
        @SerializedName("image")
        @Expose
        private Object image;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNameinEnglish() {
            return nameinEnglish;
        }

        public void setNameinEnglish(String nameinEnglish) {
            this.nameinEnglish = nameinEnglish;
        }

        public String getNameInNepali() {
            return nameInNepali;
        }

        public void setNameInNepali(String nameInNepali) {
            this.nameInNepali = nameInNepali;
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

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getBasePrice() {
            return basePrice;
        }

        public void setBasePrice(double basePrice) {
            this.basePrice = basePrice;
        }

        public double getCurrentPrice() {
            return currentPrice;
        }

        public void setCurrentPrice(double currentPrice) {
            this.currentPrice = currentPrice;
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
