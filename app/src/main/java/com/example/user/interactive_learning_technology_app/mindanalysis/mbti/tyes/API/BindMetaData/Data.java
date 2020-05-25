package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.API.BindMetaData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by a2734043 on 2018/6/8.
 */

public class Data {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("store")
    @Expose
    private Integer store;
    @SerializedName("staff")
    @Expose
    private Staff staff;
    @SerializedName("storeItem")
    @Expose
    private StoreItem storeItem;
    @SerializedName("meta")
    @Expose
    private String meta;
    @SerializedName("user")
    @Expose
    private User_ user;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("orderPrice")
    @Expose
    private Integer orderPrice;
    @SerializedName("completed")
    @Expose
    private Boolean completed;
    @SerializedName("expiration")
    @Expose
    private Object expiration;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStore() {
        return store;
    }

    public void setStore(Integer store) {
        this.store = store;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public StoreItem getStoreItem() {
        return storeItem;
    }

    public void setStoreItem(StoreItem storeItem) {
        this.storeItem = storeItem;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public User_ getUser() {
        return user;
    }

    public void setUser(User_ user) {
        this.user = user;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Object getExpiration() {
        return expiration;
    }

    public void setExpiration(Object expiration) {
        this.expiration = expiration;
    }

}
