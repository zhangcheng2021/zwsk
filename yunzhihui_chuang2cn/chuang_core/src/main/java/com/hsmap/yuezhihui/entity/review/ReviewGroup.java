package com.hsmap.yuezhihui.entity.review;

import com.hsmap.yuezhihui.base.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

public class ReviewGroup extends BaseEntity implements Serializable {
    private Integer id;

    private String name;

    private Integer specCount;

    private Integer productCount;

    private String address;

    private String amStartTime;

    private String amEndTime;

    private String pmStartTime;

    private String pmEndTime;

    private String territoryIds;

    private Integer start;

    private Integer isDel;

    private Integer sort;

    private Date createTime;

    private String remark;

    private Integer batchId;

    private String batchName;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSpecCount() {
        return specCount;
    }

    public void setSpecCount(Integer specCount) {
        this.specCount = specCount;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getAmStartTime() {
        return amStartTime;
    }

    public void setAmStartTime(String amStartTime) {
        this.amStartTime = amStartTime;
    }

    public String getAmEndTime() {
        return amEndTime;
    }

    public void setAmEndTime(String amEndTime) {
        this.amEndTime = amEndTime;
    }

    public String getPmStartTime() {
        return pmStartTime;
    }

    public void setPmStartTime(String pmStartTime) {
        this.pmStartTime = pmStartTime;
    }

    public String getPmEndTime() {
        return pmEndTime;
    }

    public void setPmEndTime(String pmEndTime) {
        this.pmEndTime = pmEndTime;
    }

    public String getTerritoryIds() {
        return territoryIds;
    }

    public void setTerritoryIds(String territoryIds) {
        this.territoryIds = territoryIds == null ? null : territoryIds.trim();
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
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

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName == null ? null : batchName.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", specCount=").append(specCount);
        sb.append(", productCount=").append(productCount);
        sb.append(", address=").append(address);
        sb.append(", amStartTime=").append(amStartTime);
        sb.append(", amEndTime=").append(amEndTime);
        sb.append(", pmStartTime=").append(pmStartTime);
        sb.append(", pmEndTime=").append(pmEndTime);
        sb.append(", territoryIds=").append(territoryIds);
        sb.append(", start=").append(start);
        sb.append(", isDel=").append(isDel);
        sb.append(", sort=").append(sort);
        sb.append(", createTime=").append(createTime);
        sb.append(", remark=").append(remark);
        sb.append(", batchId=").append(batchId);
        sb.append(", batchName=").append(batchName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
