package com.hsmap.yuezhihui.entity.product;

import com.hsmap.yuezhihui.base.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

public class ProductDetail extends BaseEntity implements Serializable {
    private Integer id;

    private Integer productId;

    private String userName;

    private String productName;

    private Integer isDel;

    private Integer sort;

    private Date createTime;

    private String remark;

    private String cover;

    private String basicInfo;

    private String productPlan;

    private String opinion;

    private String accessory;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    public String getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(String basicInfo) {
        this.basicInfo = basicInfo == null ? null : basicInfo.trim();
    }

    public String getProductPlan() {
        return productPlan;
    }

    public void setProductPlan(String productPlan) {
        this.productPlan = productPlan == null ? null : productPlan.trim();
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion == null ? null : opinion.trim();
    }

    public String getAccessory() {
        return accessory;
    }

    public void setAccessory(String accessory) {
        this.accessory = accessory == null ? null : accessory.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", productId=").append(productId);
        sb.append(", userName=").append(userName);
        sb.append(", productName=").append(productName);
        sb.append(", isDel=").append(isDel);
        sb.append(", sort=").append(sort);
        sb.append(", createTime=").append(createTime);
        sb.append(", remark=").append(remark);
        sb.append(", cover=").append(cover);
        sb.append(", basicInfo=").append(basicInfo);
        sb.append(", productPlan=").append(productPlan);
        sb.append(", opinion=").append(opinion);
        sb.append(", accessory=").append(accessory);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
