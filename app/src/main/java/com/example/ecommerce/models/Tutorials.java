package com.example.ecommerce.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
 Created by ♕ A-Cis Sapkota on 7/7/2020.
*/
public class Tutorials {
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
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("nameInNepali")
        @Expose
        private String nameInNepali;
        @SerializedName("videoUrl")
        @Expose
        private String videoUrl;
        @SerializedName("new")
        @Expose
        private Boolean _new;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNameInNepali() {
            return nameInNepali;
        }

        public void setNameInNepali(String nameInNepali) {
            this.nameInNepali = nameInNepali;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public Boolean getNew() {
            return _new;
        }

        public void setNew(Boolean _new) {
            this._new = _new;
        }
    }


}
