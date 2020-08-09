package com.example.ecommerce.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
 Created by ♕ A-Cis Sapkota on 7/23/2020.
*/
   public class orders {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("payment_type")
    @Expose
    private String paymentType;
    @SerializedName("delivery_address")
    @Expose
    private String deliveryAddress;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("product")
    @Expose
    private Product product;
    @SerializedName("qty")
    @Expose
    private Integer qty;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("order_date")
    @Expose
    private Object orderDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Object getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Object orderDate) {
        this.orderDate = orderDate;
    }


    public class HibernateLazyInitializer {


    }

    public class Product {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("created")
        @Expose
        private String created;
        @SerializedName("lastModified")
        @Expose
        private Object lastModified;
        @SerializedName("version")
        @Expose
        private Integer version;
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
        private Integer quantity;
        @SerializedName("basePrice")
        @Expose
        private Double basePrice;
        @SerializedName("currentPrice")
        @Expose
        private Double currentPrice;
        @SerializedName("productimageUrl")
        @Expose
        private String productimageUrl;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("createdDate")
        @Expose
        private String createdDate;
        @SerializedName("modifiedDate")
        @Expose
        private String modifiedDate;
        @SerializedName("new")
        @Expose
        private Boolean _new;
        @SerializedName("hibernateLazyInitializer")
        @Expose
        private HibernateLazyInitializer hibernateLazyInitializer;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public Object getLastModified() {
            return lastModified;
        }

        public void setLastModified(Object lastModified) {
            this.lastModified = lastModified;
        }

        public Integer getVersion() {
            return version;
        }

        public void setVersion(Integer version) {
            this.version = version;
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

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public Double getBasePrice() {
            return basePrice;
        }

        public void setBasePrice(Double basePrice) {
            this.basePrice = basePrice;
        }

        public Double getCurrentPrice() {
            return currentPrice;
        }

        public void setCurrentPrice(Double currentPrice) {
            this.currentPrice = currentPrice;
        }

        public String getProductimageUrl() {
            return productimageUrl;
        }

        public void setProductimageUrl(String productimageUrl) {
            this.productimageUrl = productimageUrl;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getModifiedDate() {
            return modifiedDate;
        }

        public void setModifiedDate(String modifiedDate) {
            this.modifiedDate = modifiedDate;
        }

        public Boolean getNew() {
            return _new;
        }

        public void setNew(Boolean _new) {
            this._new = _new;
        }

        public HibernateLazyInitializer getHibernateLazyInitializer() {
            return hibernateLazyInitializer;
        }

        public void setHibernateLazyInitializer(HibernateLazyInitializer hibernateLazyInitializer) {
            this.hibernateLazyInitializer = hibernateLazyInitializer;
        }

    }
}
